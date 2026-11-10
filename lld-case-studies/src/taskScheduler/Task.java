package taskScheduler;

public class Task {
    private final Runnable action;

    public Task(Runnable action){
        this.action=action;
    }

    public Runnable getAction() {
        return action;
    }
}
