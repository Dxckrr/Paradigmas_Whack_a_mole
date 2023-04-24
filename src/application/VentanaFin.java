package application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VentanaFin {
	Stage window;
	Scene finallll;
	Label finaly;
	StackPane fin;
	
	public VentanaFin() {
		crearFinal();
	}
	
	private void crearFinal() {
		window = new Stage();
		finaly = new Label("Se termino");
		
		fin = new StackPane();
		fin.getChildren().add(finaly);
		
		finallll = new Scene(fin,1080,720);

		window.setScene(finallll);
		window.show();
		
	}

}
