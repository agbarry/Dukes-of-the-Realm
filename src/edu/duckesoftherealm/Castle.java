/**
 * 
 */
package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author agbarry
 *
 */
public abstract class Castle {
	
	private ImageView imageView;

    private Pane layer;
    
    private long treasure;	// Le tresor du chateaux
    private long level;		// Le niveau du chateaux
    
    protected ArrayList<Soldier> listSoldier;	// La liste de soldat
    protected Production_Unit pUnit;	// L'unité de production
    private Displacement moveOrder;	// Ordre de déplacement 
    
    private int gateX;	// Coordonnée x de la porte
    private int gateY;	// Coordonnée y de la porte
    
    // A terminer après
    
	public Castle(ImageView imageView, Pane layer, long treasure, long level, ArrayList<Soldier> listSoldier,
			Production_Unit pUnit, Displacement moveOrder, int gateX, int gateY) {
		super();
		this.imageView = imageView;
		this.layer = layer;
		this.treasure = treasure;
		this.level = level;
		this.listSoldier = listSoldier;
		this.pUnit = pUnit;
		this.moveOrder = moveOrder;
		this.gateX = gateX;
		this.gateY = gateY;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public Pane getLayer() {
		return layer;
	}

	public long getTreasure() {
		return treasure;
	}

	public long getLevel() {
		return level;
	}

	public ArrayList<Soldier> getListSoldier() {
		return listSoldier;
	}

	public Production_Unit getpUnit() {
		return pUnit;
	}

	public Displacement getMoveOrder() {
		return moveOrder;
	}

	public int getGateX() {
		return gateX;
	}

	public int getGateY() {
		return gateY;
	}
	
	
    
}
