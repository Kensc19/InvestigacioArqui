package Service;

import Domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);

        // Obtener el tiempo límite de la tarea
        long dueTime = task.getDueDate();

        // Programar la tarea para la alerta cuando expire el tiempo
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!task.isCompleted()) {
                    System.out.println("Tarea ID: " + task.getIdTask() + " ha vencido.");
                    task.setCompleted(true);
                    timer.cancel(); // Detener el Timer después de la alerta
                }
            }
        }, dueTime);
        task.setTimer(timer);
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

