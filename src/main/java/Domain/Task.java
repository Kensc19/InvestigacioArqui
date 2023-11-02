package Domain;

public class Task {

    private int idTask;
    private String descriptionTask;
    private int timer;
    private boolean completed;
    private static Task instance;



    public Task() {
    }

    public Task(int idTask, String descriptionTask, int timer, boolean completed) {
        this.idTask = idTask;
        this.descriptionTask = descriptionTask;
        this.timer = timer;
        this.completed = completed;
    }

    public static Task getInstance() {
        if (instance == null) {
            instance = new Task();
        }
        return instance;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }



    @Override
    public String toString() {
        return
                "idTask=" + idTask +
                ", descriptionTask='" + descriptionTask + '\'' +
                ", timer=" + timer +
                ", isCompleted=" + completed;
    }
}
