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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
public class Menu {

	Stage window;
	Scene inicio;
	
	//Panel
	StackPane contenedorMenu;

	//Botones

	Button facil;
	Button medio;
	Button dificil;
	
	//Dificultades

	final int dificultad1 = 4;
	final int dificultad2 = 6;
	final int dificultad3 = 8;
	
	//Tamaño hueco
	
	final int tamaño1 = 100;
	final int tamaño2 = 75;
	final int tamaño3 = 50;
	
	//Distancia entre huecos
	
	//Vertical
	final int distanciaVerticalFacil = 55;
	final int distanciaVerticalMedio = 42;
	final int distanciaVerticalDificil = 29;
	
	//Horizontal
	final int distanciaHorizontalFacil = 80;
	final int distanciaHorizontalMedio = 60;
	final int distanciaHorizontalDificil = 40;
	
	
	
	
	public Menu() {
		generarBotonesDificultad();
		//Establecer Icono
	      Image iconTopo = new Image(getClass().getResourceAsStream("/Topo.png"));
	      window.getIcons().add(iconTopo);
	}

	private void generarBotonesDificultad() {
		window = new Stage();
	//	window.setFullScreen(true);
	//	window.setResizable(true);
		Text welcome = new Text("Bienvenido a Whack-a-Mole!");
		welcome.setFont(new Font(50));


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

		contenedorMenu.setAlignment(facil, Pos.CENTER_LEFT);
		contenedorMenu.setAlignment(medio, Pos.CENTER);
		contenedorMenu.setAlignment(dificil, Pos.CENTER_RIGHT);

		//Insets == Distancia del margen

		contenedorMenu.setMargin(facil, new Insets(100));
		contenedorMenu.setMargin(medio, new Insets(0));
		contenedorMenu.setMargin(dificil, new Insets(100));

		//Fondo---

		// Cargar la imagen de fondo
		
		ImageView backgroundImageView = new ImageView("/Fondo.png");
		
		//Ajustar imagen a resolucion
		backgroundImageView.fitWidthProperty().bind(contenedorMenu.widthProperty());
        backgroundImageView.fitHeightProperty().bind(contenedorMenu.heightProperty());

		contenedorMenu.getChildren().addAll(welcome,facil,medio,dificil,backgroundImageView);
		

		backgroundImageView.toBack();	 // .toBack mande la imagen atras

		inicio = new Scene(contenedorMenu,1080,720);

		
		
		
		
		window.setScene(inicio);	//Estableciendo el comienzo
		window.setTitle("Wacamole"); 	//Estableciendo titulo
		window.show();
		
		
	}

	//private void eventoBotonDificultad


	EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if(event.getSource()== facil) {
				window.close();
				new TableroJuego(dificultad1, tamaño1,distanciaVerticalFacil,distanciaHorizontalFacil);
				
			}
			else if(event.getSource()== medio) {
				window.close();
				new TableroJuego(dificultad2, tamaño2,distanciaVerticalMedio,distanciaHorizontalMedio);
			}
			else if(event.getSource()== dificil) {
				window.close();
				new TableroJuego(dificultad3, tamaño3,distanciaVerticalDificil,distanciaHorizontalDificil);
			}

		}

	};


}

