package Service;

import Domain.Task;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {

    public void initTimer(long tiempo, int idTask){

        Timer timer = new Timer();

        TimerTask tarea = new TimerTask() {

            // indica cuando finaliza la tarea
            @Override
            public void run() {
                System.out.println("Tarea finalizada");
                TaskManager.getInstance().taskFound(idTask);
                timer.cancel();

            }

        };

        timer.schedule(tarea,tiempo);
    }


}
