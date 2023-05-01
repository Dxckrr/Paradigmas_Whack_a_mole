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
	StackPane contenedorModalidades;
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

	//Cuadros de texto
	
	TextField  nombre;
	
	TextField  nombresPlayer1;
	TextField  nombresPlayer2;
	
	//Nombres guardados
	
	String savedPlayer1;	//= new String[modalidad2Players];
	String savedPlayer2;
	

	public Menu() {
		crearseleccionModalidad();
		//generarBotonesDificultad();
		//Establecer Icono
	      Image iconTopo = new Image(getClass().getResourceAsStream("/iconoJuego.png"));
	      window.getIcons().add(iconTopo);
	}
	
	
	private void crearseleccionModalidad() {
		window = new Stage();
		
		Text welcome = new Text("Seleccione una modalidad!");
		welcome.setFont(new Font(50));
		
		unJugador = new Button(" 1 jugador  ");
		dosJugadores = new Button("2 jugadores");
		
		unJugador.setScaleX(2);
		unJugador.setScaleY(2);
		dosJugadores.setScaleX(2);
		dosJugadores.setScaleY(2);

		unJugador.setOnAction(e -> {
			
		    generarPedirNombreUnJugador();
		
		});
		dosJugadores.setOnAction(e -> {
			
		    generarPedirNombreDosJugadores();
		});
		
		contenedorModalidades = new StackPane();
		
		contenedorModalidades.setAlignment(unJugador, Pos.BOTTOM_CENTER);
		contenedorModalidades.setAlignment(dosJugadores, Pos.TOP_CENTER);
		
		contenedorModalidades.setMargin(unJugador, new Insets(50));
		contenedorModalidades.setMargin(dosJugadores, new Insets(50));

		
		contenedorModalidades.getChildren().addAll(unJugador,dosJugadores);
		
		modalidadJugadores = new Scene(contenedorModalidades,480,240);
		modalidadJugadores.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		window.setScene(modalidadJugadores);
		window.setTitle("Wacamole"); 	//Estableciendo titulo
		window.show();

		
	}
	
	private void generarPedirNombreUnJugador() {
		
		contenedorObtenerNombre = new StackPane();
		
			escribirNombre = new Label("Jugador , Escribe tu nombre:");
			escribirNombre.setFont(new Font("Verdana",20));
			nombre = new TextField();
			
			//Ancho y largo
			nombre.setPrefWidth(200);
			
			
			saveNombreButton = new Button("Guardar");
	        saveNombreButton.setFont(new Font(20));

	        saveNombreButton.setOnAction(event -> {
	            // savedText = String.valueOf(nombre1.getText()); // Obtener el texto del cuadro de texto
	        		//nombre.getText = nombre del Jugador
	        	    guardarNombre(nombre.getText());
	        	    generarBotonesDificultad(nombre.getText(),"");
	        	
	        });
	        
	        contenedorObtenerNombre.setAlignment(escribirNombre, Pos.TOP_CENTER);
	        contenedorObtenerNombre.setAlignment(saveNombreButton, Pos.BOTTOM_CENTER);
	        contenedorObtenerNombre.getChildren().addAll(nombre, escribirNombre, saveNombreButton);
	 
	      introducirNombres = new Scene(contenedorObtenerNombre, 480, 240);
	      introducirNombres.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        window.setScene(introducirNombres);
	        

	}



	private void generarPedirNombreDosJugadores() {
	    contenedorObtenerNombre = new StackPane();

	    contenedorModalidad2Jugadores = new GridPane();
	    contenedorModalidad2Jugadores.setAlignment(Pos.CENTER);
	    contenedorModalidad2Jugadores.setVgap(20);

	    escribirNombrePlayer1 = new Label("Jugador 1, Escribe tu nombre:");
	    escribirNombrePlayer1.setFont(new Font("Verdana",20));
	    escribirNombrePlayer2 = new Label("Jugador 2, Escribe tu nombre:");
	    escribirNombrePlayer2.setFont(new Font("Verdana",20));

	    nombresPlayer1 = new TextField();
	    nombresPlayer2 = new TextField();

	    // Ancho y largo
	    nombresPlayer1.setPrefWidth(200);
	    nombresPlayer2.setPrefWidth(200);

	    saveNombreButton = new Button("Guardar");
	    saveNombreButton.setFont(new Font(20));

	    saveNombreButton.setOnAction(event -> {
	        guardarNombre(nombresPlayer1.getText(),nombresPlayer2.getText());
	      //  guardarNombre(nombresPlayer2.getText());
	        generarBotonesDificultad(nombresPlayer1.getText(),", "+nombresPlayer2.getText());

	    });

	    contenedorModalidad2Jugadores.add(escribirNombrePlayer1, 0, 0);
	    contenedorModalidad2Jugadores.add(nombresPlayer1, 1, 0);
	    contenedorModalidad2Jugadores.add(escribirNombrePlayer2, 0, 1);
	    contenedorModalidad2Jugadores.add(nombresPlayer2, 1, 1);
	    contenedorModalidad2Jugadores.add(saveNombreButton, 0, 2, 2, 1);

	    contenedorObtenerNombre.getChildren().add(contenedorModalidad2Jugadores);
	    introducirNombres = new Scene(contenedorObtenerNombre, 720, 480);
	    introducirNombres.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	    window.setScene(introducirNombres);
     
	}



			//if(returnNombre(savedPlayer) == true) {
			//	generarBotonesDificultad();
				
			//}

	private void guardarNombre(String nombrePlayer) {
		
		savedPlayer1 = nombrePlayer;
		System.out.println(savedPlayer1);
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
		Text welcome = new Text("Bienvenido(s) "+player+player2+"!");
		welcome.setFont(new Font(50));
		//welcome.setStyle("-fx-font-family: 'MiFuentePersonalizada'; -fx-font-size: 50px; -fx-font-weight: bold; -fx-fill: #333333;");

		
		Text selecion = new Text("Seleccione una dificultad!");
		selecion.setFont(new Font(30));


		//Botones

		facil = new Button("Facil");
		medio = new Button("Medio");
		dificil = new Button("Dificil");
		//Posiciones
		facil.setScaleX(2);
		facil.setScaleY(2);
		medio.setScaleX(2);
		medio.setScaleY(2);
		dificil.setScaleX(2);
		dificil.setScaleY(2);

		facil.setOnMouseClicked(click);
		medio.setOnMouseClicked(click);
		dificil.setOnMouseClicked(click);

		//Elemtnos menu | Posición
		contenedorMenu = new StackPane();

		contenedorMenu.setAlignment(welcome,Pos.TOP_CENTER);
		contenedorMenu.setAlignment(selecion, Pos.TOP_CENTER);
		

		contenedorMenu.setAlignment(facil, Pos.CENTER_LEFT);
		contenedorMenu.setAlignment(medio, Pos.CENTER);
		contenedorMenu.setAlignment(dificil, Pos.CENTER_RIGHT);

		//Insets == Distancia del margen
		contenedorMenu.setMargin(selecion, new Insets(70));
		
		contenedorMenu.setMargin(facil, new Insets(100));
		contenedorMenu.setMargin(medio, new Insets(0));
		contenedorMenu.setMargin(dificil, new Insets(100));

		//Fondo---

		// Cargar la imagen de fondo
		
		ImageView backgroundImageView = new ImageView("/Fondo.png");
		
		//Ajustar imagen a resolucion
		backgroundImageView.fitWidthProperty().bind(contenedorMenu.widthProperty());
        backgroundImageView.fitHeightProperty().bind(contenedorMenu.heightProperty());

		contenedorMenu.getChildren().addAll(welcome,selecion,facil,medio,dificil,backgroundImageView);
		

		backgroundImageView.toBack();	 // .toBack mande la imagen atras

		 seleccionDificultades = new Scene(contenedorMenu,1080,720);
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


