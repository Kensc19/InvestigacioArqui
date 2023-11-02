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
        int hashCode = System.identityHashCode(tasks);
        System.out.println("Dirección de memoria del ArrayList: " + Integer.toHexString(hashCode));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void taskFound(int id){
        int hashCode = System.identityHashCode(tasks);
        System.out.println("Dirección de memoria del ArrayList: " + Integer.toHexString(hashCode));
        for(Task task: tasks){
            if(task.getIdTask() == id){
                task.setCompleted(true);
                break;
            }
        }
    }


}

