package application;

import java.util.ArrayList;
import java.util.List;
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
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import java.util.random.*;



public class TableroJuego extends Menu {
	
	//ESCENAS
	Scene juego;
	VentanaFin endGame;
	
	//Dificultades
	String dificultad;
	int tamañoHueco;
    int distanciaVertical;
    int distanciaHorizontal;
    int dimensiones;
    
	//Array "TOPOS"
	Hole [] topos ;
    
	//CONTENEDORES (PANEL)
	
	//Panel Stack
	StackPane contendorPrincipal ;
	//Panel
	GridPane cuadricula;
	//VBOX para las VIDAS
	VBox contenedorVidas;

	//OBJETOS / ELEMENTOS
	

	//Array de vidas   
	ImageView corazones[];
	//Puntuacion
	Label puntuacion;	//Objeto Grafico
	
	//Guadar Puntuacion
	String puntuacionActual;
	//Jugador Actual
	Jugador jugadorActual;
	// Jugador Siguiente
	Jugador jugadorSiguiente;
	
	//ELEMENTOS GRAFICOS
	
	//nombre
	Label jugadorActualEnPantalla;
	//Temporizador
	Label temporizador;
	//Boton para comenzar
	Button botonStart;
	
	
	//VARIABLES DEFINIDAS
	//Numero de errores
	int vidas = 3;
	//
    private int contador = 0;
	//
	private int random;
	
	//
	boolean jugando;
	//Controladro ClICK
	boolean clickPermitido;
	
	//IMAGENES
	Image hueco = new Image("/Hueco.png");
	Image topoFuera = new Image("/Hueco_TopoFuera.png");
	Image topoGolpeado = new Image("/Hueco_TopoGolpeado.png");
	//Mouse
	Image mousePredeterminado = new Image("/Mouse_Neutral.png");
	Image mouseClick = new Image("/Mouse_Click.png");
	

	
	public TableroJuego(String dificultad,Jugador jugadorActual,Jugador jugadorSiguiente, boolean jugando) {
	/*	if(jugadorSiguiente.getNombre() ==null) {
   		 	endGame = new VentanaFin(jugadorSiguiente,puntuacionActual,dificultad);          

		}*/
		this.jugando = jugando;
		this.jugadorSiguiente = jugadorSiguiente;
		this.dificultad = dificultad;
		this.jugadorActual = jugadorActual;
		jugadorActual.setPuntuacion(contador);
        switch (dificultad) {
        case "FACIL":
            tamañoHueco = 150;
            distanciaVertical = 55;
            distanciaHorizontal = 80;
            dimensiones = 4;
            break;
        case "MEDIO":
            tamañoHueco = 125;
            distanciaVertical = 42;
            distanciaHorizontal = 60;
            dimensiones = 6;
            break;
        case "DIFICIL":
            tamañoHueco = 100;
            distanciaVertical = 29;
            distanciaHorizontal = 40;
            dimensiones = 8;
            break;
            
        default:
            break;
        }
	
		//Definir Cantidad topos
		
		topos = new Hole[dimensiones * dimensiones];
		
		//Inicializar contenedor elementos
		
		contendorPrincipal = new StackPane();
		cuadricula = new GridPane();
		
		// Crear tablero o cuadricula
		crearCuadricula(dimensiones, tamañoHueco, distanciaVertical, distanciaHorizontal);
		
		// Crear contador de tiempo transcurrido
		crearContadorTiempoJuego();
		
		// Crear cuadro de score
		crearTableroScore();
		
		// Crear botón para INICIAR partida
		crearBotonInicioPartida();
		
		//
		crearVidas();
		//
		crearTextoJugadorActual();
		//
		crearFondo();
		// PANTALLA COMPLETA
		window.setFullScreen(true);
		
		// Definir cursor
		Cursor cursor = new ImageCursor(mousePredeterminado, mousePredeterminado.getWidth() / 2,
				mousePredeterminado.getHeight() / 2);

		juego.setCursor(cursor);

		
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
			topos[i].getHueco().setOnMouseClicked(click);
		
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
	
	private void crearTextoJugadorActual() {
		jugadorActualEnPantalla= new Label("Jugador: "+jugadorActual.getNombre());
		jugadorActualEnPantalla.setFont(new Font("Verdana",(20)));
		jugadorActualEnPantalla.setTextFill(Color.WHITE);
		contendorPrincipal.getChildren().add(jugadorActualEnPantalla);
		contendorPrincipal.setAlignment(jugadorActualEnPantalla, Pos.TOP_CENTER);
	}
	private void crearFondo() {
		ImageView fondo = new ImageView("/Fondo_TableroJuego.png");
		
		//Ajustar imagen a resolucion
		fondo.fitWidthProperty().bind(contendorPrincipal.widthProperty());
		fondo.fitHeightProperty().bind(contendorPrincipal.heightProperty());

		contendorPrincipal.getChildren().add(fondo);

		fondo.toBack();	 // .toBack mande la imagen atras
	}
	
	private void crearVidas() {
		contenedorVidas = new VBox();
		contenedorVidas.setSpacing(5); // establecer el espacio entre las imágenes de corazones
		contenedorVidas.setAlignment(Pos.CENTER_RIGHT); // establecer la alineación de las imágenes de corazones en el centro del VBox
		
		
		corazones = new ImageView[vidas];
	      for (int i = 0; i < corazones.length; i++) {
	    	ImageView corazon = new ImageView("/corazon.png");
	    	corazon.setFitHeight(80);	//Alto
	    	corazon.setFitWidth(80);	//Ancho
	    	corazones[i] = corazon;
		    contenedorVidas.setMargin(corazon, new Insets(1)); // establecer el margen de cada imagen de corazón
		    contenedorVidas.getChildren().add(corazon); // agregar la imagen de corazón al VBox
		   
		}

		contendorPrincipal.getChildren().add(contenedorVidas); // agregar el VBox al contenedor principal
		contenedorVidas.toBack();	//Colocar atras PARA NO OBSTRUIR AL TABLERO
		contendorPrincipal.setMargin(contenedorVidas, new Insets(0,0, 600, 150)); // establecer un margen (ABAJO,IZQUIERDA,ARRIBA,DERECHA)
		//contendorPrincipal.setAlignment(contenedorVidas, new Insets(10));

	}
	
	private void restarVidas() {
        vidas--; // reducir el número de vidas restantes en 1
        if (vidas >= 0) {
        	corazones[vidas].setVisible(false);
          //  corazones.get(vidas).setVisible(false); // ocultar la imagen de corazón correspondiente
        }
        if (vidas == 0) {
        	window.close();
          	if(jugando) {
            	jugando = false;
        		Controller.terminarJuego(jugando,jugadorSiguiente,dificultad,jugando);
       		 ArchivoXML.crearXml(dificultad, jugadorActual.getNombre(), jugadorActual.getPuntuacion());



        	}
        	else {
          		 endGame = new VentanaFin(jugadorSiguiente,puntuacionActual,dificultad);
         		 ArchivoXML.crearXml(dificultad, jugadorActual.getNombre(), jugadorActual.getPuntuacion());

        

        		 }
        }
    }
	
		//Creando el evento CLICK AL HUECO
	    //  private void eventoClickHueco() 
	EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if(clickPermitido) {
				clickPermitido = false;
				System.out.println(topos[random].isVerfificarTopo());
				if(event.getSource() == topos[random].getHueco()) {
					//Definir cursor cuando "CLICK"

					cambiarCursor(event, mouseClick, mousePredeterminado);
					/*Cursor cursorClick = new ImageCursor(mouseClick, mouseClick.getWidth() / 2, mouseClick.getHeight() / 2);
					juego.setCursor(cursorClick);*/
					
					//Actualizar contador
					incrementarContador();
					puntuacion.setText("Puntuacion: " + obtenerContador());
					
					//Crear un nuevo rectangle en donde se conoce a que rectangulo se da "click"

					ImageView topoSeleccionado = (ImageView) event.getSource();

					// Establecer el color de relleno del Rectangle
					topoSeleccionado.setImage(topoGolpeado);

					
					//Creando "timer" para devolver el rectangulo a su color original

					Timeline timer = new Timeline(new KeyFrame(
							Duration.seconds(0.5),	//Duracion
					        e -> {
					            topoSeleccionado.setImage(hueco);
					           /* Cursor cursor = new ImageCursor(mousePredeterminado, mousePredeterminado.getWidth() / 2, mousePredeterminado.getHeight() / 2);
					            juego.setCursor(cursor);*/
					        }));

					timer.play();	//Funcion para correr el timer
					
					
				}
				else {
					restarVidas();
				}
				
			}


		}

	};
	//-----------------------------------------------------

    public void incrementarContador() {
        contador++;
        jugadorActual.setPuntuacion(contador);
    }

    public int obtenerContador() {
        return contador;
    }
    //------------------------------------------------------
    
	private void cambiarCursor(MouseEvent event, Image cursorImagen, Image cursorImagenInicial) {
	    // cambiar el cursor al hacer clic
	    juego.setCursor(new ImageCursor(cursorImagen, cursorImagen.getWidth() / 2, cursorImagen.getHeight() / 2));
	    
	    // esperar 0.25 segundos y luego cambiar el cursor de nuevo
	    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(0.25), e -> {
	        juego.setCursor(new ImageCursor(cursorImagenInicial, cursorImagenInicial.getWidth() / 2, cursorImagenInicial.getHeight() / 2));
	    }));
	    timer.play();
	}




	private void crearContadorTiempoJuego() {
		temporizador = new Label("Tiempo: 00 ");
		temporizador.setFont(new Font("Verdana",20));
		//crear objeto grafico que muestra el tiempo transcurrido de juego
		//AQUI SOLAMENTE se crea el elemento gráfico, NO se inicia automáticamente
		//Agregar panel a contenedorVentana
		contendorPrincipal.getChildren().add(temporizador);
		contendorPrincipal.setAlignment(temporizador,Pos.TOP_RIGHT);

		
	}
	
	
	private void crearTableroScore() {
		puntuacion = new Label("Puntuacion: ");
		puntuacion.setFont(new Font("Verdana",20));
		//crear objeto grafico que muestra el tablero de score
		//AQUI SOLAMENTE se crea el elemento gráfico, NO se inicia automáticamente
		//Agregar panel a contenedorVentana
		contendorPrincipal.getChildren().add(puntuacion);
		contendorPrincipal.setAlignment(puntuacion, Pos.TOP_LEFT);
		
	}
	
	private void crearBotonInicioPartida() {
		
		botonStart = new Button("Iniciar!");
		botonStart.setFont(new Font("Verdana",30));
		
		contendorPrincipal.getChildren().add(botonStart);
		contendorPrincipal.setAlignment(botonStart,Pos.BOTTOM_RIGHT);
		botonStart.setOnMouseClicked(e -> {
			//Ejecutar todas las funcionas al dar "click" en el BOTON
				botonStart.setVisible(false);
			   	crearTableroScore();
			    reiniciarTiempoJuego();
			    reiniciarGeneracionTopo();
			});
		}

	private void reiniciarTiempoJuego() {	
		//final = no modificable
		final IntegerProperty countdown;
		
    	countdown = new SimpleIntegerProperty(40); // Inicializa el contador en 60 segundos || IntergerProperty, para mostrar en UI
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
                        
                      	if(jugando) {
                        	jugando = false;
                    		Controller.terminarJuego(jugando,jugadorSiguiente,dificultad,jugando);
                   		 ArchivoXML.crearXml(dificultad, jugadorActual.getNombre(), jugadorActual.getPuntuacion());



                    	}
                    	else {
                      		 endGame = new VentanaFin(jugadorSiguiente,puntuacionActual,dificultad);
                     		 ArchivoXML.crearXml(dificultad, jugadorActual.getNombre(), jugadorActual.getPuntuacion());

                    		 }
                     	
                        
                    }
                }));
        timeline.play(); // Inicia la línea de tiempo
	}

	/*private  Rectangle getRandom(Hole[] rectangles) {
		Random topoRandom = new Random();
		
		int randomIndex = topoRandom.nextInt(rectangles.length);
		Rectangle randomRectangle = rectangles[randomIndex].getHueco();
		
		return randomRectangle;
	} */
	
	private int getRandom(Hole[] rectangles) {
		Random topoRandom = new Random();
		
		int randomIndex = topoRandom.nextInt(rectangles.length);
		//Rectangle randomRectangle = rectangles[randomIndex].getHueco();
		
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
						
					//	topos[random].getHueco().setFill(Color.RED);
						topos[random].getHueco().setImage(topoFuera);
						topos[random].setVerificarTopo(true);
						//La funcion setId define un "ID" para un "nodo" dentro del layout de los topos 
						topos[random].getHueco().setId("selected"); // Asignar un ID al Rectangle seleccionado
					//	topos[random].getHueco().setOnMouseClicked(click);
						clickPermitido = true;
					}
							)
					);
			//Segundo EVENTO
			timeline.getKeyFrames().add(
					new KeyFrame(Duration.seconds(2), event -> {
						// Acceder al Rectangle seleccionado utilizando su ID
						ImageView TopoAnterior = (ImageView) cuadricula.lookup("#selected");	//Aqui el "ID" es llamado con #<name> , para esto se usa lookup
						if (TopoAnterior != null) {
							//RectangleAnterior.setOnMouseClicked(null);
							topos[random].setVerificarTopo(false);
							// Se recoloca el hueco
							TopoAnterior.setImage(hueco);
							TopoAnterior.setId(null); // Limpiar el ID del Rectangle
							//Asi nos aseguramos de resetear la variable para el siguiente Frame
							
							clickPermitido = false;
						}
						
					}
						)
					);
			timeline.play(); // Inicia la línea de tiempo
			
		

		
			
	}

	
}
