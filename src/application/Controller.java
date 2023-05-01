package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller {
	
	
/*	public static boolean iniciarJuego(String  nombreJugador,String dificultad) {
		boolean segundoJuego = true;
		Jugador jugadorActual = new Jugador(nombreJugador);
		TableroJuego juego= new TableroJuego(dificultad,jugadorActual,null,segundoJuego);
		
		return true;
		
	}*/
	
	public static boolean iniciarJuego(String  nombreJugador1,String nombreJugador2,String dificultad) {
		Jugador jugadorActual = new Jugador(nombreJugador1);
		Jugador jugadorSiguiente = new Jugador(nombreJugador2);

		TableroJuego juego= new TableroJuego(dificultad, jugadorActual,jugadorSiguiente);
		


		return true;
		
	}
	
	public static void terminarJuego(boolean isJugando, Jugador JugadorSiguiente, String dificultad) {
		
		
		if(!isJugando) {
			TableroJuego juego= new TableroJuego(dificultad, JugadorSiguiente,null);	
			}
	
	
	}
}