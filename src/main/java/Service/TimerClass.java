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
        taskManager = new TaskManager();

        TimerTask tarea = new TimerTask() {

            // indica cuando finaliza la tarea
            @Override
            public void run() {
                System.out.println("Tarea finalizada");
                timer.cancel();

                if(task == null) {
                    task = Task.getInstance();
                    task.setCompleted(true);
                    System.out.println(task.isCompleted());
                }

            }

        };

        timer.schedule(tarea,tiempo);

    }


}
