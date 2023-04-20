package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hole {
	
	Rectangle hueco;
	boolean verificarTopo = false;
	
	public Hole(int size) {
		//Crear hueco
		hueco = new Rectangle(size,size); // topo[i] = new ImageView();
		hueco.setFill(Color.BLACK);
	
	}
	public Rectangle getHueco() {
		return hueco;
	}
	

	//Limitar el click a cuando "salga el topo"
	public void setVerificarTopo(boolean topo) {
		verificarTopo = topo;
	}
	public boolean isVerfificarTopo() {
		return verificarTopo;
	}
	

}
