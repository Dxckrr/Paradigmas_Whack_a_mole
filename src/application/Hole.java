package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hole {
	
	ImageView hueco;
	boolean verificarTopo = false;
	
	public Hole(int size) {
		//Crear hueco
		hueco = new ImageView("/Hueco.png"); // topo[i] = new ImageView();
		//Definir ancho y alto
		hueco.setFitWidth(size);
		hueco.setFitHeight(size);

		//hueco.setFill(Color.BLACK);
	
	}
	public ImageView getHueco() {
		return hueco;
	}
	

	//Limitar el click a cuando "salga el topo"
	public void setVerificarTopo(boolean topo) {
		verificarTopo = topo;
	}
	public boolean isVerfificarTopo() {
		return verificarTopo;
	}
	
	public void setImage(Image Imagen) {
		
		hueco.setImage(Imagen);
	}

}
