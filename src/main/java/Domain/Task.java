package Domain;

import java.time.LocalDate;
import java.util.Timer;

public class Task {

    private int idTask;
    private String descriptionTask;
    private int timer;
    private boolean isCompleted;


    public Task() {
    }

    public Task(int idTask, String descriptionTask, int timer, boolean isCompleted) {
        this.idTask = idTask;
        this.descriptionTask = descriptionTask;
        this.timer = timer;
        this.isCompleted = isCompleted;
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
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }



    @Override
    public String toString() {
        return
                "idTask=" + idTask +
                ", descriptionTask='" + descriptionTask + '\'' +
                ", timer=" + timer +
                ", isCompleted=" + isCompleted;
    }
}
