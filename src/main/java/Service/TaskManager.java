package Service;

import Domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {

    private List<Task> tasks;

    private static TaskManager instance;

    public TaskManager() {
        tasks = new ArrayList<>();
    }


    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        tasks.add(task);
       //System.out.println(getTasks().toString());
        int hashCode = System.identityHashCode(tasks);
        System.out.println("Dirección de memoria del ArrayList: " + Integer.toHexString(hashCode));
    }

    public List<Task> getTasks() {
        return tasks;
    }


    public void checkTasksStatus() {
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.println("Tarea ID: " + task.getIdTask() + ", Descripción: " + task.getDescriptionTask() + ", Completada: " + task.isCompleted());
            }
        }
    }
}

