package Service;

import Domain.Task;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
    TaskManager taskManager;
    Task task;


    public void initTimer(long tiempo){

        Timer timer = new Timer();
        taskManager = new TaskManager();
        task = new Task();

        TimerTask tarea = new TimerTask() {

            // indica cuando finaliza la tarea
            @Override
            public void run() {
                System.out.println("Tarea finalizada");
                timer.cancel();


            }

        };

        timer.schedule(tarea,tiempo);

    }


}
