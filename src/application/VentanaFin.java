package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaFin {
	Stage window; 
	Scene finallll;
	Label finaly;
	StackPane fin;
	Button siguiente ;
	
	public VentanaFin(ArrayList<Jugador> inJugadores,String dificultad) {
		crearFinalDosJugadores(inJugadores,dificultad);
	}
	
	public VentanaFin(Jugador uNjugador,String dificultad){
		crearFinalUnoJugador(uNjugador,dificultad);
	}
	
	private void crearFinalUnoJugador(Jugador jugador,String dificultad) {
		Stage ventana = new Stage();
        VBox contenedor = new VBox(10);
        contenedor.setPadding(new Insets(20));
        contenedor.setAlignment(Pos.CENTER);
        
        // Encabezado de la ventana
        Label titulo = new Label("Resultados finales - " + dificultad);
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        contenedor.getChildren().add(titulo);
        
        Label puntaje = new Label("El jugador " + jugador.getNombre() + " ha obtenido un puntaje de: " + jugador.getPuntuacion());
        puntaje.setStyle("-fx-font-size: 16px;");
        contenedor.getChildren().add(puntaje);
        
        
        Scene escena = new Scene(contenedor);
        ventana.setScene(escena);
        ventana.show();
	}
	
	private void crearFinalDosJugadores(ArrayList<Jugador> inJugadores,String dificultad) {
		Stage ventana = new Stage();
        VBox contenedor = new VBox(10);
        contenedor.setPadding(new Insets(20));
        contenedor.setAlignment(Pos.CENTER);
        
        // Encabezado de la ventana
        Label titulo = new Label("Resultados finales - " + dificultad);
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        contenedor.getChildren().add(titulo);
        
            for (Jugador jugador : inJugadores) {
                Label puntaje = new Label("El jugador " + jugador.getNombre() + " ha obtenido un puntaje de: " + jugador.getPuntuacion());
                puntaje.setStyle("-fx-font-size: 16px;");
                contenedor.getChildren().add(puntaje);
            }
            
            // Texto del ganador
            Jugador ganador = encontrarGanador(inJugadores);
            Label resultadoFinal = new Label("El ganador fue: " + ganador.getNombre() + " con un puntaje de " + ganador.getPuntuacion());
            resultadoFinal.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            contenedor.getChildren().add(resultadoFinal);
        
        // Texto para cada jugador

        
        Scene escena = new Scene(contenedor);
        ventana.setScene(escena);
        ventana.show();
    }
    
    private Jugador encontrarGanador(ArrayList<Jugador> jugadores) {
        Jugador ganador = jugadores.get(0);
        for (int i = 1; i < jugadores.size(); i++) {
            if (jugadores.get(i).getPuntuacion() > ganador.getPuntuacion()) {
                ganador = jugadores.get(i);
            }
        }
        return ganador;
    }

		
	
	}


