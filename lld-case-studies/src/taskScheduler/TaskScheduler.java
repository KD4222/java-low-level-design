package taskScheduler;

import java.sql.Time;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {

    private final Object lock= new Object();

    private final PriorityQueue<ScheduledTask> queue;
    private final HashMap<String,ScheduledTask> taskMap;

    private final ExecutorService workerPool;

    private final Thread schedulerThread;

    private boolean isShutdown= false;

    public TaskScheduler() {
        this.queue = new PriorityQueue<>(Comparator.comparingLong(ScheduledTask::getExecutionTime));
        this.taskMap = new HashMap<>();
        this.workerPool = Executors.newFixedThreadPool(3);
        this.schedulerThread = new Thread(
            this::runScheduler,
            "task-scheduler"
        );
        this.schedulerThread.start();
    }

    public String schedule(Runnable action, long delay, TimeUnit unit){
        if (isShutdown) {
            throw new IllegalStateException("Scheduler is shut down");
        }

        if(action==null || delay<0 ||unit==null)throw new IllegalArgumentException("Invalid task details.");

        String taskId= UUID.randomUUID().toString();
        long executionTime= System.nanoTime() + unit.toNanos(delay);
        ScheduledTask task = new ScheduledTask(taskId, new Task(action), executionTime);
        
        synchronized(lock){
            queue.add(task);
            taskMap.put(taskId,task);
            lock.notify();
            
        }
        
        return taskId;
    }

    public boolean cancel(String taskId) {
        ScheduledTask task;

        synchronized (lock) {
            task = taskMap.get(taskId);
            if (task == null) return false;
        }

        boolean cancelled = false;

        synchronized (task) {
            if (task.getStatus() == TaskStatus.DISPATCHED ||
                task.getStatus() == TaskStatus.SCHEDULED) {

                task.setStatus(TaskStatus.CANCELLED);
                cancelled = true;

            } else if (task.getStatus() == TaskStatus.RUNNING) {

                if (task.getFuture() != null &&
                    task.getFuture().cancel(true)) {

                    task.setStatus(TaskStatus.CANCELLED);
                    cancelled = true;
                }
            }
        }

        if (cancelled) {
            synchronized (lock) {
                lock.notify();
            }
        }

        return cancelled;
    }

    private void runScheduler(){
        while(!Thread.currentThread().isInterrupted()){
            ScheduledTask task = null;
            boolean shouldDispatch = false;
            synchronized(lock){
                if (isShutdown) {
                    return;
                }
                while(queue.isEmpty()){
                    try{
                        lock.wait();
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                task = queue.peek();

                if (task.getStatus() == TaskStatus.CANCELLED) {
                    queue.poll(); // Remove cancelled task from PriorityQueue
                    taskMap.remove(task.getTaskId());
                    continue;     // Check the next task
                }

                long waitTime = task.getExecutionTime() - System.nanoTime();

                if(waitTime<=0){
                    queue.poll();
                    task.setStatus(TaskStatus.DISPATCHED);
                    shouldDispatch = true;
                }else if(waitTime>0){
                    long millis = TimeUnit.NANOSECONDS.toMillis(waitTime);
                    int nanos = (int)(waitTime - TimeUnit.MILLISECONDS.toNanos(millis));

                    try{
                        lock.wait(millis, nanos);
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            if (shouldDispatch) {
                ScheduledTask dispatchedTask = task;

                synchronized (dispatchedTask) {
                    Future<?> future = workerPool.submit(() -> {

                        synchronized (dispatchedTask) {
                            if (dispatchedTask.getStatus() == TaskStatus.CANCELLED) {
                                return;
                            }

                            dispatchedTask.setStatus(TaskStatus.RUNNING);
                        }

                        try {
                            dispatchedTask.getTask().getAction().run();

                            synchronized (dispatchedTask) {
                                dispatchedTask.setStatus(TaskStatus.COMPLETED);
                            }

                        } catch (RuntimeException e) {
                            synchronized (dispatchedTask) {
                                dispatchedTask.setStatus(TaskStatus.FAILED);
                            }
                        }
                    });
                    
                        dispatchedTask.setFuture(future);
                }
            }
        }
    }

    public void shutdown(){
        synchronized (lock) {
            isShutdown = true;

            Iterator<Map.Entry<String,ScheduledTask>> iterator = taskMap.entrySet().iterator();


            while(iterator.hasNext()){
                ScheduledTask task = iterator.next().getValue();

                if(task.getStatus()==TaskStatus.DISPATCHED || task.getStatus()==TaskStatus.SCHEDULED){
                    task.setStatus(TaskStatus.CANCELLED);
                    iterator.remove();
                }
            }

            queue.clear();
        }

        schedulerThread.interrupt();

        try{
            schedulerThread.join();
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        workerPool.shutdown();  
    }
}
