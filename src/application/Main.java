package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	
	//Creacion de objetos
	
	Stage window; // Ventana
	Scene inicio, juego; // Escenas de juego y menu
	
	
	//Crear objeto "game" , la cual, crea los "huecos" y la cuadricula
	
	Topo game = new Topo();
	//Temporizador
	Tempo temporizador = new Tempo();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//PARTES MENU
		window = primaryStage;
		Text welcome = new Text("Bienvenido a Whack-a-mole!");
		welcome.setStyle(STYLESHEET_MODENA);
		welcome.setFont(new Font(50));
		Button start = new Button("Comenzar!");
		start.setScaleX(2);
		start.setScaleY(2);
		
		start.setOnAction(e-> window.setScene(juego)); 	// Funcion e-> para manejar eventos
		
		
		//Escena 1 -MENU-
		StackPane menu = new StackPane();
		menu.setAlignment(welcome,Pos.TOP_CENTER);
		menu.setAlignment(start,Pos.CENTER);
		menu.getChildren().addAll(welcome,start);
	
		//Establecer fondo
		BackgroundFill myBF = new BackgroundFill(Color.DIMGRAY, new CornerRadii(1),
		         new Insets(0.0,0.0,0.0,0.0));
		menu.setBackground(new Background(myBF));
		
		inicio = new Scene(menu,720,480);
		
	
		//Escena 2 -JUEGO-
		StackPane juegoSP = new StackPane();
		//Establecer fondo
		
		BackgroundFill fondo = new BackgroundFill(Color.BEIGE, new CornerRadii(1),
		         new Insets(0.0,0.0,0.0,0.0));
		juegoSP.setBackground(new Background(fondo));
		juegoSP.toBack();
		//PARTES JUEGO
		
		//Puntuacion(Contador)---------------------------------------------------------------------------------------------------------
		game.Puntuacion.setFont(new Font(20));
		temporizador.time.setFont(new Font(20));
		
		juegoSP.setAlignment(temporizador.time, Pos.TOP_RIGHT);
		juegoSP.setAlignment(game.Puntuacion,Pos.TOP_LEFT);
		juegoSP.getChildren().addAll(game,game.Puntuacion,temporizador.time);
		//
		
		//Button bt = new Button();
		//bt.setOnAction(e-> window.setScene(inicio)); 	// Funcion e-> para manejar eventos
		
		juego = new Scene(juegoSP,1080,720);
		
		//INICIO
		
		window.setScene(inicio);	//Estableciendo el comienzo
		window.setTitle("YESYYESYES GUACAMOLE"); 	//Estableciendo titulo
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
