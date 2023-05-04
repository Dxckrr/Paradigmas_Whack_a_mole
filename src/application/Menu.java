package application;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
public class Menu {

	Stage window;
	Scene modalidadJugadores, seleccionDificultades, introducirNombres;
	
	//Panel
	StackPane contenedorPrincipalModalidades;
	VBox contenedorModalidades;
	StackPane contenedorObtenerNombre;
	StackPane contenedorMenu;
	GridPane contenedorModalidad2Jugadores;
	//Botones
	
	Button unJugador;
	Button dosJugadores;
		
	Button facil;
	Button medio;
	Button dificil;
	
	
	Button saveNombreButton;
	
	final int modalidad2Players = 2;
	//Label
	
	Label escribirNombre;
	Label escribirNombrePlayer1;
	Label escribirNombrePlayer2;
	
	Label selecion;
	Label welcome;

	//Cuadros de texto
	
	TextField  nombre;
	
	TextField  nombresPlayer1;
	TextField  nombresPlayer2;
	
	//Nombres guardados
	
	String savedPlayer1;	//= new String[modalidad2Players];
	String savedPlayer2;
	

	public Menu() {
		crearSeleccionModalidad();
		//generarBotonesDificultad();
		//Establecer Icono
	      Image iconTopo = new Image(getClass().getResourceAsStream("/iconoJuego.png"));
	      window.getIcons().add(iconTopo);
	}
	
	
	private void crearSeleccionModalidad() {
		window = new Stage();
		window.setFullScreen(true);
		
	//	Text welcome = new Text("Seleccione una modalidad!");
	//	welcome.setFont(new Font(50));
		
		unJugador = new Button(" 1 jugador  ");
		dosJugadores = new Button("2 jugadores");
		
		unJugador.setScaleX(4);
		unJugador.setScaleY(4);
		dosJugadores.setScaleX(4);
		dosJugadores.setScaleY(4);

		unJugador.setOnAction(e -> {
			
		    generarPedirNombreUnJugador();
		
		});
		dosJugadores.setOnAction(e -> {
			
		    generarPedirNombreDosJugadores();
		});
		contenedorModalidades = new VBox();
		contenedorModalidades.setSpacing(150);	//Espacio entre botones
		contenedorModalidades.setAlignment(Pos.CENTER);
	
		contenedorModalidades.getChildren().addAll(unJugador,dosJugadores);
		contenedorPrincipalModalidades = new StackPane();
		
		//FONDO
		ImageView fondo = new ImageView("/FondoInicio.png");
		
		//Ajustar imagen a resolucion
		fondo.fitWidthProperty().bind(contenedorPrincipalModalidades.widthProperty());
		fondo.fitHeightProperty().bind(contenedorPrincipalModalidades.heightProperty());

		contenedorPrincipalModalidades.getChildren().add(fondo);

		fondo.toBack();	 // .toBack mande la imagen atras
		
	//	contenedorPrincipalModalidades.getChildren().add(welcome);
	//	contenedorPrincipalModalidades.setAlignment(welcome, Pos.TOP_CENTER);
		
		
		
		contenedorPrincipalModalidades.getChildren().add(contenedorModalidades);
		contenedorPrincipalModalidades.setMargin(contenedorModalidades, new Insets(255,230,0, 300)); // establecer un margen (ABAJO,IZQUIERDA,ARRIBA,DERECHA)

		
		modalidadJugadores = new Scene(contenedorPrincipalModalidades,1920,1080);
		modalidadJugadores.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		window.setScene(modalidadJugadores);
		window.setTitle("Wacamole"); 	//Estableciendo titulo
		window.show();
		
		
	}
	
	private void generarPedirNombreUnJugador() {
	
		contenedorObtenerNombre = new StackPane();
		
			escribirNombre = new Label("Jugador , Escribe tu nombre:");
			escribirNombre.setFont(new Font("Verdana",45));
			//escribirNombre.setTextFill(Color.WHEAT);
			nombre = new TextField();
			
			//Ancho y largo
			nombre.setPrefWidth(100);
			nombre.setPrefHeight(50);
			//nombre.setPrefWidth(nombre.getPrefWidth() / 2);
			
			
			saveNombreButton = new Button("Guardar");
	        saveNombreButton.setFont(new Font(20));

	        saveNombreButton.setOnAction(event -> {
	            // savedText = String.valueOf(nombre1.getText()); // Obtener el texto del cuadro de texto
	        		//nombre.getText = nombre del Jugador
	        	    guardarNombre(nombre.getText());
	        	    generarBotonesDificultad(nombre.getText(),"");
	        	
	        });
			//FONDO
			ImageView fondo = new ImageView("/FondoInicio.png");
			
			//Ajustar imagen a resolucion
			fondo.fitWidthProperty().bind(contenedorObtenerNombre.widthProperty());
			fondo.fitHeightProperty().bind(contenedorObtenerNombre.heightProperty());

			contenedorObtenerNombre.getChildren().add(fondo);

			fondo.toBack();	 // .toBack mande la imagen atras
	        contenedorObtenerNombre.setAlignment(escribirNombre, Pos.CENTER);
	        contenedorObtenerNombre.setAlignment(saveNombreButton, Pos.BOTTOM_CENTER);
	        contenedorObtenerNombre.getChildren().addAll(nombre, escribirNombre, saveNombreButton);
	        
	        contenedorObtenerNombre.setMargin(nombre, new Insets(275,200,0,0));// establecer un margen (ABAJO,IZQUIERDA,ARRIBA,DERECHA)
	        contenedorObtenerNombre.setMargin(escribirNombre, new Insets(150,300,0,0));// establecer un margen (ABAJO,IZQUIERDA,ARRIBA,DERECHA)
	        contenedorObtenerNombre.setMargin(saveNombreButton, new Insets(250,0,0,0));// establecer un margen (ABAJO,IZQUIERDA,ARRIBA,DERECHA)

	 
	      introducirNombres = new Scene(contenedorObtenerNombre, 1920, 1080);
	      nombre.setMaxWidth(contenedorObtenerNombre.getWidth() /3);	//Se define una vez se tiene claro el width el stackPane
	      
	      
	      

	      introducirNombres.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        window.setScene(introducirNombres);
	        window.setFullScreen(true);
	        

	}



	private void generarPedirNombreDosJugadores() {
	    contenedorObtenerNombre = new StackPane();

	    contenedorModalidad2Jugadores = new GridPane();
	    contenedorModalidad2Jugadores.setAlignment(Pos.CENTER);
	    contenedorModalidad2Jugadores.setVgap(20);

	    escribirNombrePlayer1 = new Label("Jugador 1, Escribe tu nombre:");
	    escribirNombrePlayer1.setFont(new Font("Verdana",40));
	    escribirNombrePlayer2 = new Label("Jugador 2, Escribe tu nombre:");
	    escribirNombrePlayer2.setFont(new Font("Verdana",40));

	    nombresPlayer1 = new TextField();
	    nombresPlayer2 = new TextField();

	    // Ancho y largo
	    nombresPlayer1.setPrefWidth(200);
	    nombresPlayer2.setPrefWidth(200);

	    saveNombreButton = new Button("Guardar");
	    saveNombreButton.setFont(new Font(30));

	    saveNombreButton.setOnAction(event -> {
	        guardarNombre(nombresPlayer1.getText(),nombresPlayer2.getText());
	      //  guardarNombre(nombresPlayer2.getText());
	        generarBotonesDificultad(nombresPlayer1.getText(),", "+nombresPlayer2.getText());

	    });
	    
		//FONDO
		ImageView fondo = new ImageView("/FondoInicio.png");
		
		//Ajustar imagen a resolucion
		fondo.fitWidthProperty().bind(contenedorObtenerNombre.widthProperty());
		fondo.fitHeightProperty().bind(contenedorObtenerNombre.heightProperty());

		contenedorObtenerNombre.getChildren().add(fondo);
		
	    contenedorModalidad2Jugadores.add(escribirNombrePlayer1, 0, 0);
	    contenedorModalidad2Jugadores.add(nombresPlayer1, 1, 0);
	    contenedorModalidad2Jugadores.add(escribirNombrePlayer2, 0, 1);
	    contenedorModalidad2Jugadores.add(nombresPlayer2, 1, 1);
	    contenedorModalidad2Jugadores.add(saveNombreButton, 0, 2, 2, 1);
	    
	    contenedorModalidad2Jugadores.setMargin(saveNombreButton, new Insets(0,0,0,200));
	    contenedorModalidad2Jugadores.setMargin(nombresPlayer1, new Insets(0,0,0,10));// establecer un margen (ABAJO,IZQUIERDA,ARRIBA,DERECHA)
	    contenedorModalidad2Jugadores.setMargin(nombresPlayer2, new Insets(0,0,0,10));// establecer un margen (ABAJO,IZQUIERDA,ARRIBA,DERECHA)

	    contenedorObtenerNombre.getChildren().add(contenedorModalidad2Jugadores);
	    contenedorMenu.setMargin(contenedorModalidad2Jugadores,  new Insets(200,0,0,10));
	    introducirNombres = new Scene(contenedorObtenerNombre, 1920, 1080);
	    introducirNombres.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	    window.setScene(introducirNombres);
	    window.setFullScreen(true);
     
	}



			//if(returnNombre(savedPlayer) == true) {
			//	generarBotonesDificultad();
				
			//}

	private void guardarNombre(String nombrePlayer) {
		
		savedPlayer1 = nombrePlayer;
		System.out.println(savedPlayer1);
		System.out.println(savedPlayer2);

	}
	private void guardarNombre(String nombrePlayer,String nombrePlayer2) {

		savedPlayer1 = nombrePlayer;
		savedPlayer2 = nombrePlayer2;
		System.out.println(savedPlayer1);
		System.out.println(savedPlayer2);
	}

	
	private void generarBotonesDificultad(String player,String player2) {

	//	window = new Stage();
	//	window.setFullScreen(true);
	//	window.setResizable(true);
		 welcome = new Label("Bienvenido(s) "+player+player2+"!");
		welcome.setFont(new Font(90));
		//welcome.setStyle("-fx-font-family: 'MiFuentePersonalizada'; -fx-font-size: 50px; -fx-font-weight: bold; -fx-fill: #333333;");

		
		selecion = new Label("Seleccione una dificultad!");
		selecion.setFont(new Font(50));


		//Botones

		facil = new Button("Facil");
		medio = new Button("Medio");
		dificil = new Button("Dificil");
		//Posiciones
		facil.setScaleX(3);
		facil.setScaleY(3);
		medio.setScaleX(3);
		medio.setScaleY(3);
		dificil.setScaleX(3);
		dificil.setScaleY(3);

		facil.setOnMouseClicked(click);
		medio.setOnMouseClicked(click);
		dificil.setOnMouseClicked(click);

		//Elemtnos menu | Posición
		contenedorMenu = new StackPane();

		contenedorMenu.setAlignment(welcome,Pos.TOP_CENTER);
		contenedorMenu.setAlignment(selecion, Pos.TOP_CENTER);
		

		contenedorMenu.setAlignment(facil, Pos.CENTER);
		contenedorMenu.setAlignment(medio, Pos.CENTER);
		contenedorMenu.setAlignment(dificil, Pos.CENTER);

		//Insets == Distancia del margen
		contenedorMenu.setMargin(welcome, new Insets(170,0,0,0)); //establecer un margen (ABAJO,IZQUIERDA,ARRIBA,DERECHA)
		contenedorMenu.setMargin(selecion, new Insets(260,0,0,0)); 

		
		
		contenedorMenu.setMargin(facil, new Insets(0,400,0,0));
		contenedorMenu.setMargin(medio, new Insets(0,0,0,0));
		contenedorMenu.setMargin(dificil, new Insets(0,0,0,400));

		//Fondo---

		// Cargar la imagen de fondo
		
		ImageView backgroundImageView = new ImageView("/Menu.png");
		
		//Ajustar imagen a resolucion
		backgroundImageView.fitWidthProperty().bind(contenedorMenu.widthProperty());
        backgroundImageView.fitHeightProperty().bind(contenedorMenu.heightProperty());

		contenedorMenu.getChildren().addAll(welcome,selecion,facil,medio,dificil,backgroundImageView);
		

		backgroundImageView.toBack();	 // .toBack mande la imagen atras

		 seleccionDificultades = new Scene(contenedorMenu,1920,1080);
		 seleccionDificultades.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		window.setScene(seleccionDificultades);	//Estableciendo el comienzo

		window.setFullScreen(true);
		
	}

	//private void eventoBotonDificultad


	EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			
			if(event.getSource()== facil) {	//&& savedPlayers.length == 2)
				window.close();
						Controller.iniciarJuego(savedPlayer1, savedPlayer2,"FACIL");
			}
			
			else if(event.getSource()== medio) {
				window.close();
		
				Controller.iniciarJuego(savedPlayer1, savedPlayer2,"MEDIO");
				
			}
			else if(event.getSource()== dificil) {
				window.close();
				Controller.iniciarJuego(savedPlayer1, savedPlayer2,"DIFICIL");				
			}

		}

	};

	
}


