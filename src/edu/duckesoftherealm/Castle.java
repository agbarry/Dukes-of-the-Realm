/**
 * 
 */
package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author agbarry
 *
 */
public abstract class Castle {
	
	private ImageView imageView;
	private double w;	// Pour reccuperer la largeur de l'image
    private double h;	// Pour reccuperer la hauteur de l'image

    private Pane layer;
    
    protected double x;
    protected double y;
    
    private double treasure;	// Le tresor du chateaux
    private int level;		// Le niveau du chateaux
    
    protected ArrayList<Soldier> listSoldier;	// La liste de soldat
    protected Production_Unit pUnit;	// L'unité de production
    private Displacement moveOrder;	// Ordre de déplacement 
    private Gate gate;	// Pour la porte du chateaux
    
    
    public Castle(Image image, Pane layer, double x, double y, double treasure, int level,
			ArrayList<Soldier> listSoldier, Production_Unit pUnit, Displacement moveOrder, Gate gate) {
    
		this.layer = layer;
		this.x = x;
		this.y = y;
		
		this.imageView = new ImageView(image);
		this.imageView.relocate(x, y);
		this.w = image.getWidth(); 
        this.h = image.getHeight();
		
		this.treasure = treasure;
		this.level = level;
		
		this.listSoldier = listSoldier;
		this.pUnit = pUnit;
		this.moveOrder = moveOrder;
		
		this.gate = gate;
		
		addToLayer();
	}


	public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

	protected ImageView getImageView() {
		return imageView;
	}

	public Pane getLayer() {
		return layer;
	}

	public double getTreasure() {
		return treasure;
	}
	
	public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }

	public int getLevel() {
		return level;
	}

	public ArrayList<Soldier> getListSoldier() {
		return listSoldier;
	}
	
	public void setListSoldier(ArrayList<Soldier> listSoldier) {
		this.listSoldier = listSoldier;
	}

	public Production_Unit getpUnit() {
		return pUnit;
	}

	public Displacement getMoveOrder() {
		return moveOrder;
	}
	
	public Gate getGate() {
		return gate;
	}


	public void setGate(Gate gate) {
		this.gate = gate;
	}

	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}
	
	public void updateCastle() {
        imageView.relocate(x, y);
    }


	// Pour la gestion des collissions des chateaux
    public boolean collidesWith(Castle castle) {
    	return getImageView().getBoundsInParent().intersects(castle.getImageView().getBoundsInParent());
    }
}
