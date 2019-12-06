package edu.duckesoftherealm;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


// Pour la gestion de la porte
public class Gate {
	
	private ImageView imageView;
	
	private double w;
    private double h;

    private Pane layer;
	
	private double gateX;	// Coordonnée x de la porte
    private double gateY;	// Coordonnée y de la porte
    
	/**
	 * @param imageView
	 * @param layer
	 * @param gateX
	 * @param gateY
	 */
	public Gate(Image image, Pane layer, double gateX, double gateY) {
		
		this.layer = layer;
		
		this.gateX = gateX;
		this.gateY = gateY;
		
		this.imageView = new ImageView(image);
        this.imageView.relocate(gateX, gateY);

        this.w = image.getWidth(); 
        this.h = image.getHeight(); 

        addToLayer();
	}
	
	public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public double getGateX() {
		return gateX;
	}

	public void setGateX(int gateX) {
		this.gateX = gateX;
	}

	public double getGateY() {
		return gateY;
	}

	public void setGateY(int gateY) {
		this.gateY = gateY;
	}
	
	
    
    
	
    
    

}
