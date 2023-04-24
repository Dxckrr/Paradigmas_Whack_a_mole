package application;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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



public class TableroJuego extends Menu {
	
	Scene juego;
	VentanaFin endGame;
	//Panel Stack
	StackPane contendorPrincipal ;
	//Puntuacion
	Label puntuacion;
	//Panel
	GridPane cuadricula;
	//Array "TOPOS"
	Hole [] topos ;
	//Temporizador
	Label temporizador;
	//Boton para comenzar
	Button botonStart;
	
	private int random;
	
	public TableroJuego(int dimensiones,int size,int vertical, int horizontal) {
		
		topos = new Hole [dimensiones*dimensiones];
		//Inicializar contenedor elementos
		
		contendorPrincipal = new StackPane();
		cuadricula = new GridPane();
		
		//Crear tablero o cuadricula
		crearCuadricula(dimensiones,size,vertical,horizontal);
		//Crear contador de tiempo transcurrido
		crearContadorTiempoJuego();
		//Crear cuadro de score
		crearTableroScore();
		//Crear botón para INICIAR partida
		crearBotonInicioPartida();
		
	}
	
	private void crearCuadricula(int dimensiones, int size,int vertical, int horizontal ) {
		//Crear la cuadricula (GridPane)
		
		//Distancia entre topos
		cuadricula.setVgap(vertical);
		cuadricula.setHgap(horizontal);
		
		//Inicializando ubicacion 
		
		int ancho = 0;
		int largo = 0;
		
		//Crear botones o rectangulos de cada celda de la cuadricula
		for (int i = 0; i < topos.length; i++) {
			
			topos[i] = new Hole(size); // topo[i] = new ImageView();
			
		
			if(i %dimensiones == 0) {
				ancho=0;
				largo ++;
				cuadricula.add(topos[i].getHueco(),ancho,largo);	//Añadir elementos al layout
				continue;	//Funcion continue para skipear el codigo y volver al for
				
			}
			ancho++; 
			cuadricula.add(topos[i].getHueco(),ancho,largo);	//Añadir elementos al layout
			
			
			//Tablero.posicion(ii).add(new Hueco())
			//Por cada Hueco agregado vincular el método de click
		}
		//Agregar GridPane creado a contenedorVentana 
		cuadricula.setAlignment(Pos.CENTER);
		contendorPrincipal.getChildren().addAll(cuadricula);
		
		juego = new Scene(contendorPrincipal,1080,720);
		window.setScene(juego);
		
		
	}
	
		//Creando el evento CLICK AL HUECO
	    //  private void eventoClickHueco() 
		EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		    	System.out.println(topos[random].isVerfificarTopo());
		    	if(topos[random].isVerfificarTopo()) {
		    		
			    	//Actualizar contador
			    	
			    	incrementarContador();
		            puntuacion.setText("Puntuacion: " + obtenerContador());
			    	//Crear un nuevo rectangle en donde se conoce a que rectangulo se da "click"
		            
			    	   Rectangle rectangle = (Rectangle) event.getSource();
			           
			           // Establecer el color de relleno del Rectangle
			           rectangle.setFill(Color.CORNFLOWERBLUE);
			           
			           //Creando "timer" para devolver el rectangulo a su color original
			           
			           Timeline timer = new Timeline(new KeyFrame(
			                   Duration.seconds(0.5),	//Duracion
			                   e -> rectangle.setFill(Color.BLACK) ));	//Devolviendo el color al recttangulo, en este caso "BLACK"
			           
			           timer.play();	//Funcion para correr el timer
		    		
		    	}
		      	topos[random].setVerificarTopo(false);

		    }
		   
		};
		
	
	
	private void crearContadorTiempoJuego() {
		temporizador = new Label("Tiempo: 00 ");
		temporizador.setFont(new Font(20));
		//crear objeto grafico que muestra el tiempo transcurrido de juego
		//AQUI SOLAMENTE se crea el elemento gráfico, NO se inicia automáticamente
		//Agregar panel a contenedorVentana
		contendorPrincipal.getChildren().add(temporizador);
		contendorPrincipal.setAlignment(temporizador,Pos.TOP_RIGHT);

		
	}
	
	
	private void crearTableroScore() {
		puntuacion = new Label("Puntuacion: 0 ");
		puntuacion.setFont(new Font(20));
		//crear objeto grafico que muestra el tablero de score
		//AQUI SOLAMENTE se crea el elemento gráfico, NO se inicia automáticamente
		//Agregar panel a contenedorVentana
		contendorPrincipal.getChildren().add(puntuacion);
		contendorPrincipal.setAlignment(puntuacion, Pos.TOP_LEFT);
		
	}
	
	private void crearBotonInicioPartida() {
		
		botonStart = new Button("Iniciar!");
		botonStart.setFont(new Font(30));
		
		contendorPrincipal.getChildren().add(botonStart);
		contendorPrincipal.setAlignment(botonStart,Pos.BOTTOM_RIGHT);
		botonStart.setOnMouseClicked(e -> {
			//Ejecutar todas las funcionas al dar "click" en el BOTON
			
			   	//reiniciarScore();
			    reiniciarTiempoJuego();
			    reiniciarGeneracionTopo();
			});
		}

	private void reiniciarTiempoJuego() {	//Error al reset
		//final = no modificable
		final IntegerProperty countdown;
		
    	countdown = new SimpleIntegerProperty(10); // Inicializa el contador en 60 segundos || IntergerProperty, para mostrar en UI
    	// Crea una línea de tiempo que actualiza el contador cada segundo
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE); // Repite indefinidamente
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    countdown.set(countdown.get() - 1); // Contador --
                    temporizador.setText("Tiempo : "+Integer.toString(countdown.get())); // Actualiza el texto del label con el contador
            	
                    if (countdown.get() == 0) {
                        timeline.stop(); // Detiene la línea de tiempo = "temporizador"  cuando el contador llega a 0
                        window.close();
                        endGame = new VentanaFin();
                    }
                }));
        timeline.play(); // Inicia la línea de tiempo
	}

	private void reiniciarScore() {
		puntuacion = new Label("Puntuacion:");
		
	}
	//-----------------------------------------------------
    private static int contador = 0;

    public static void incrementarContador() {
        contador++;
    }

    public static int obtenerContador() {
        return contador;
    }
    //------------------------------------------------------
	/*private  Rectangle getRandom(Hole[] rectangles) {
		Random topoRandom = new Random();
		
		int randomIndex = topoRandom.nextInt(rectangles.length);
		Rectangle randomRectangle = rectangles[randomIndex].getHueco();
		
		return randomRectangle;
	} */
	
	private int getRandom(Hole[] rectangles) {
		Random topoRandom = new Random();
		
		int randomIndex = topoRandom.nextInt(rectangles.length);
		Rectangle randomRectangle = rectangles[randomIndex].getHueco();
		
		return randomIndex;
	}
	private void reiniciarGeneracionTopo() {
		
			
			Timeline timeline = new Timeline();
			timeline.setCycleCount(Animation.INDEFINITE); // Repite indefinidamente

			//Primer EVENTO 
			timeline.getKeyFrames().add(
					new KeyFrame(Duration.seconds(1), event -> {
						//Funcion para generar un "topo" aleatorio"
						
						//Rectangle randomRectangle = getRandom(topos);
						
						// Cambiar el color del Rectangle seleccionado
						this.random = getRandom(topos);
						
						topos[random].getHueco().setFill(Color.RED);
						topos[random].setVerificarTopo(true);
						//La funcion setId define un "ID" para un "nodo" dentro del layout de los topos 
						topos[random].getHueco().setId("selected"); // Asignar un ID al Rectangle seleccionado
						topos[random].getHueco().setOnMouseClicked(click);
					
					}
							)
					);
			//Segundo EVENTO
			timeline.getKeyFrames().add(
					new KeyFrame(Duration.seconds(2), event -> {
						// Acceder al Rectangle seleccionado utilizando su ID
						Rectangle RectangleAnterior = (Rectangle) cuadricula.lookup("#selected");	//Aqui el "ID" es llamado con #<name> , para esto se usa lookup
						if (RectangleAnterior != null) {
							RectangleAnterior.setOnMouseClicked(null);
							RectangleAnterior.setFill(Color.BLACK);
							RectangleAnterior.setId(null); // Limpiar el ID del Rectangle
							//Asi nos aseguramos de resetear la variable para el siguiente Frame
							
						}
					}
						)
					);
			timeline.play(); // Inicia la línea de tiempo
			
		

		
			
	}

	
}
