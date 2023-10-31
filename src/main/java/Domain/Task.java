package Domain;

import java.time.LocalDate;
import java.util.Timer;

public class Task {

    private int idTask;
    private String descriptionTask;
    private Timer timer;
    private boolean isCompleted;
    private int dueDate;

    public Task() {
    }

    public Task(int idTask, String descriptionTask, Timer timer, boolean isCompleted, int dueDate) {
        this.idTask = idTask;
        this.descriptionTask = descriptionTask;
        this.timer = timer;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
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

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }
}
