package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import java.util.random.*;



public class Topo extends Parent implements EventHandler<MouseEvent>{
	
	//Panel Stack
	StackPane Panel = new StackPane();
	//Puntuacion
	Label Puntuacion = new Label("Puntuacion: "+Contador.obtenerContador());
	//Panel
	GridPane Panel1 = new GridPane();
	//Array "TOPOS"
	Rectangle [] topo = new Rectangle[16];
			
	public Topo() {
		createTopo();
	}
	
	
	
	//Creando el evento
	EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent me) {
	    	//Actualizar contador
	    	Contador.incrementar();
            Puntuacion.setText("Puntuacion: " + Contador.obtenerContador());
	    	
	    	//Crear un nuevo rectangle en donde se conoce a que rectangulo se da "click"
	    	   Rectangle rectangle = (Rectangle) me.getSource();
	           
	           // Establecer el color de relleno del Rectangle
	           rectangle.setFill(Color.CORNFLOWERBLUE);
	           
	           //Creando "timer" para devolver el rectangulo a su color original
	           
	           Timeline timer = new Timeline(new KeyFrame(
	                   Duration.seconds(0.5),	//Duracion
	                   e -> rectangle.setFill(Color.BLACK) ));	//Devolviendo el color al recttangulo, en este caso "BLACK"
	           
	           timer.play();	//Funcion para correr el timer
	    }
	    

	};
	
	public  Rectangle getRandom(Rectangle[] rectangles) {
		Random topoRandom = new Random();
		
		int randomIndex = topoRandom.nextInt(rectangles.length-1);
		Rectangle randomRectangle = rectangles[randomIndex];
		
		return randomRectangle;
		
	}
	
	private void createTopo() {
		//TOPOS-----------------------------------------------------------------------------------------------------------------------------------------
		
		
		//Colocando distancia entre topos
		Panel1.setVgap(55);
		Panel1.setHgap(100);
		// Ancho (x) Y Largo(y) |Ubicacion
		int x = 0;
		int y= 0;
		
		//Recorriendo y creando los topos
		for(int i=0; i< topo.length;i++) {
			topo[i] = new Rectangle(100,100); // topo[i] = new ImageView();
			topo[i].setFill(Color.BLACK);	
			topo[i].setOnMouseClicked(click);
			
			if(i %4 == 0) {
				x=0;
				y++;
				Panel1.add(topo[i],x,y);	//Añadir elementos al layout
				continue;	//Funcion continue para skipear el codigo y volver al for
				
			}
			x++; 
			Panel1.add(topo[i],x,y);	//Añadir elementos al layout
		}
		
		
		Panel1.setAlignment(Pos.CENTER);	//Centrar los elementos
		this.getChildren().addAll(Panel1);
	}


}
