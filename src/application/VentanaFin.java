package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VentanaFin {

	Stage ventana;
	Scene escena;
	
	VBox contenedor;
	
	Label titulo;
	Label puntaje;
	
	Rectangle rectanguloBlanco;
	StackPane contenedorPrincipal;
	
	public VentanaFin(ArrayList<Jugador> inJugadores,String dificultad) {
		crearFinalDosJugadores(inJugadores,dificultad);
	}
	
	public VentanaFin(Jugador uNjugador,String dificultad){
		crearFinalUnoJugador(uNjugador,dificultad);
	}
	
	private void crearFinalUnoJugador(Jugador jugador,String dificultad) {
		contenedorPrincipal = new StackPane();
		ventana = new Stage();
        contenedor = new VBox(10);
        contenedor.setPadding(new Insets(20));
        contenedor.setAlignment(Pos.CENTER);
        
        rectanguloBlanco = new Rectangle();
        rectanguloBlanco.setWidth(720);
        rectanguloBlanco.setHeight(480);
        rectanguloBlanco.setFill(Color.WHITE);
        contenedorPrincipal.getChildren().add(rectanguloBlanco);


        // Encabezado de la ventana
        titulo = new Label("Resultados finales - " + dificultad);
        titulo.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        contenedor.getChildren().add(titulo);
        
        puntaje = new Label("El jugador " + jugador.getNombre() + " ha obtenido un puntaje de: " + jugador.getPuntuacion());
        puntaje.setStyle("-fx-font-size: 34px;");
        contenedor.getChildren().add(puntaje);
        contenedorPrincipal.getChildren().add(contenedor);
        
		ImageView fondo = new ImageView("/FondoFinal.png");
		
		//Ajustar imagen a resolucion
		fondo.fitWidthProperty().bind(contenedorPrincipal.widthProperty());
		fondo.fitHeightProperty().bind(contenedorPrincipal.heightProperty());

		contenedorPrincipal.getChildren().add(fondo);

		fondo.toBack();	 // .toBack mande la imagen atras
        
        
        escena = new Scene(contenedorPrincipal,1920,1080);
        ventana.setScene(escena);
        ventana.show();
        
       // Platform.exit();	//Para cerrar ..despues de 5 segs pato futuro
	}
	
	private void crearFinalDosJugadores(ArrayList<Jugador> inJugadores,String dificultad) {
		contenedorPrincipal = new StackPane();
		ventana = new Stage();
        contenedor = new VBox(10);
        contenedor.setPadding(new Insets(20));
        contenedor.setAlignment(Pos.CENTER);
        
        
        rectanguloBlanco = new Rectangle();
        rectanguloBlanco.setWidth(720);
        rectanguloBlanco.setHeight(480);
        rectanguloBlanco.setFill(Color.WHITE);
        contenedorPrincipal.getChildren().add(rectanguloBlanco);
        
        // Encabezado de la ventana
        titulo = new Label("Resultados finales - " + dificultad);
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        contenedor.getChildren().add(titulo);
        
            for (Jugador jugador : inJugadores) {
                Label puntaje = new Label("El jugador " + jugador.getNombre() + " ha obtenido un puntaje de: " + jugador.getPuntuacion());
                puntaje.setStyle("-fx-font-size: 30px;");
                contenedor.getChildren().add(puntaje);
            }
            
            // Texto del ganador
            Jugador ganador = encontrarGanador(inJugadores);
            Label resultadoFinal = new Label("El ganador fue: " + ganador.getNombre() + " con un puntaje de " + ganador.getPuntuacion());
            resultadoFinal.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");
            contenedor.getChildren().add(resultadoFinal);
        
    		contenedorPrincipal.getChildren().add(contenedor);

    		ImageView fondo = new ImageView("/FondoFinal.png");
    		
    		//Ajustar imagen a resolucion
    		fondo.fitWidthProperty().bind(contenedorPrincipal.widthProperty());
    		fondo.fitHeightProperty().bind(contenedorPrincipal.heightProperty());

    		contenedorPrincipal.getChildren().add(fondo);

    		fondo.toBack();	 // .toBack mande la imagen atras

        
        escena = new Scene(contenedorPrincipal,1920,1080);
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


