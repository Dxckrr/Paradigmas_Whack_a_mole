package application;

import java.util.ArrayList;

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
		if(nombreJugador2 == null) {
			TableroJuego juego= new TableroJuego(dificultad,jugadorActual,null,false);
		}
		else {
			TableroJuego juego= new TableroJuego(dificultad,jugadorActual,jugadorSiguiente,true);

		}

		return true;
		
	}
	
	public static void continuarJuego(Jugador JugadorSiguiente, String dificultad) {
	
			TableroJuego juego= new TableroJuego(dificultad, JugadorSiguiente,null,false);		}
	
	
	//
	static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	public static void crearFinal(Jugador jugadorJugando,String dificultad) {
			jugadores.add(jugadorJugando);
	}
	
	public static void finalizarJuego(String inDificultad) {
		if(jugadores.size()==1) {
			crearVentanaFinalUnJugador(inDificultad);
		}
		crearVentanaFinal(jugadores,inDificultad);
		System.out.println(jugadores);
	}
	public static void crearVentanaFinal(ArrayList<Jugador> jugadores,String inDificultad) {
		
			VentanaFin ventanaEnd = new VentanaFin(jugadores,inDificultad);

	}	
	
	public static void crearVentanaFinalUnJugador(String inDificultad) {
		VentanaFin ventanaEnd = new VentanaFin(jugadores.get(0), inDificultad);
	}
}