package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Parent;
import javafx.scene.control.Label;

public class Tempo extends Parent{
    
    private Label time = new Label("Tiempo: ");
    private int timeRemaining = 60;
    private Timer timer;
    
    public Tempo() {
    	crear();
        
    }
    
    private void crear() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    time.setText("Tiempo: " + timeRemaining);
                } else {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}