package taskScheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final DateTimeFormatter TIMESTAMP_FORMAT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        try {
            System.out.println("Starting TaskScheduler with its 3 worker threads.");

            // A long task and a second task start together to demonstrate concurrency.
            scheduleTask(scheduler, "Long-running task", 2, 3);
            scheduleTask(scheduler, "Concurrent task", 2, 0);

            // These tasks use different delays so their scheduled execution times vary.
            scheduleTask(scheduler, "Task after 4 seconds", 4, 0);
            scheduleTask(scheduler, "Task after 6 seconds", 6, 0);

            // Cancel this task well before its scheduled execution time.
            String cancelledTaskId = scheduler.schedule(
                createTask("Cancelled task", 0),
                5,
                TimeUnit.SECONDS
            );
            System.out.println("Scheduled 'Cancelled task' for 5 seconds from now (id: "
                + cancelledTaskId + ").");

            Thread.sleep(300);
            boolean cancelled = scheduler.cancel(cancelledTaskId);
            System.out.println("Cancellation of 'Cancelled task': "
                + (cancelled ? "successful" : "not cancelled"));

            // Wait until the 6-second task and the long-running task have completed.
            System.out.println("Waiting for the scheduled tasks to finish...");
            Thread.sleep(8_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted; shutting down cleanly.");
        } finally {
            System.out.println("Requesting graceful scheduler shutdown...");
            scheduler.shutdown();
            System.out.println("Scheduler shutdown requested. Running tasks may finish.");
        }
    }

    private static void scheduleTask(
        TaskScheduler scheduler,
        String taskName,
        long delaySeconds,
        long runSeconds
    ) {
        String taskId = scheduler.schedule(
            createTask(taskName, runSeconds),
            delaySeconds,
            TimeUnit.SECONDS
        );
        System.out.println("Scheduled '" + taskName + "' in " + delaySeconds
            + " seconds (id: " + taskId + ").");
    }

    private static Runnable createTask(String taskName, long runSeconds) {
        return () -> {
            System.out.println("Executing '" + taskName + "' at " + timestamp()
                + " on thread " + Thread.currentThread().getName() + ".");

            if (runSeconds > 0) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(runSeconds));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("'" + taskName + "' was interrupted.");
                    return;
                }
            }

            System.out.println("Completed '" + taskName + "' at " + timestamp()
                + " on thread " + Thread.currentThread().getName() + ".");
        };
    }

    private static String timestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMAT);
    }
}
