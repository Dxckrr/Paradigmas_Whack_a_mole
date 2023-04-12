package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Tempo extends Topo{
    
    Label time = new Label("Tiempo: ");
    
    public Tempo() {
    	crear();
    	random();
        
    }
    
    private void crear() {
    	//final = no modificable
    	final IntegerProperty countdown = new SimpleIntegerProperty(60); // Inicializa el contador en 60 segundos || IntergerProperty, para mostrar en UI
    	// Crea una línea de tiempo que actualiza el contador cada segundo
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE); // Repite indefinidamente
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdown.set(countdown.get() - 1); // Contador --
                    time.setText("Tiempo restante: "+Integer.toString(countdown.get())); // Actualiza el texto del label con el contador
                    
            	
          	        
                    if (countdown.get() == 0) {
                        timeline.stop(); // Detiene la línea de tiempo = "temporizador"  cuando el contador llega a 0
                    }
                }));
        timeline.play(); // Inicia la línea de tiempo
 
    }
    
    private void random() {

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE); // Repite indefinidamente
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1.5), event -> {
                   	//Funcion para generar un "topo" aleatorio"------------------------------------------------------------------------------------------------------
        		    Rectangle randomRectangle = getRandom(topo);
        	        // Cambiar el color del Rectangle seleccionado
        		    
        	        randomRectangle.setFill(Color.RED);
                }));
        timeline.play(); // Inicia la línea de tiempo

    }
}
