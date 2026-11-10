package taskScheduler;

import java.util.concurrent.Future;

public class ScheduledTask {
    private final String taskId;
    private final Task task;
    private final long executionTime;
    private TaskStatus status;
    private Future<?> future;


    public ScheduledTask(String taskId, Task task, long executionTime) {
        this.taskId = taskId;
        this.task = task;
        this.executionTime = executionTime;
        this.status = TaskStatus.SCHEDULED;
    }

    public String getTaskId() {
        return taskId;
    }
    public Task getTask(){
        return  this.task;
    }
    public long getExecutionTime() {
        return executionTime;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Future<?> getFuture() {
        return future;
    }

    public void setFuture(Future<?> future) {
        this.future = future;
    }
        
}
