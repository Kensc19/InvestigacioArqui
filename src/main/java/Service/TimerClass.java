package Service;

import Domain.Task;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
    TaskManager taskManager;
    Task task;


    public void initTimer(long tiempo, int idTask){

        Timer timer = new Timer();
        if(taskManager == null){
            taskManager = TaskManager.getInstance();

        }

        TimerTask tarea = new TimerTask() {

            // indica cuando finaliza la tarea
            @Override
            public void run() {
                System.out.println("Tarea finalizada");
                if(task == null) {
                    task = Task.getInstance();
                }
                taskManager.taskFound(task.getIdTask());
                timer.cancel();

            }

        };

        timer.schedule(tarea,tiempo);
    }


}
