package edu.duckesoftherealm;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


// Pour la gestion de la porte
public class Gate extends Sprite {
    
	/**
	 * @param imageView
	 * @param layer
	 * @param gateX
	 * @param gateY
	 */
	public Gate(Image image, Pane layer, double gateX, double gateY) {
		
		super(layer, image, gateX, gateY);
	}

	@Override
	public void checkRemovability() {
		// TODO Auto-generated method stub
		
	}

}
