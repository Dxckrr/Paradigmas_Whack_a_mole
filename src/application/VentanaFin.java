package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VentanaFin {
	Stage window; 
	Scene finallll;
	Label finaly;
	StackPane fin;
	Button siguiente ;
	
	public VentanaFin(Jugador nombrePlayer,String savedPuntuacion,String dificultad) {
		crearFinal(nombrePlayer,dificultad);
	}
	
	private void crearFinal(Jugador nombrePlayer,String dificultad) {
		window = new Stage();
		finaly = new Label("Se termino");
	
		fin = new StackPane();
		fin.getChildren().addAll(finaly);
		
		finallll = new Scene(fin,1080,720);

		window.setScene(finallll);
		window.setFullScreen(false);
		window.show();
		
		
		
	}

}
