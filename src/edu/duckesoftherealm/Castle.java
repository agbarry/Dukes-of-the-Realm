/**
 * 
 */
package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * @author agbarry
 *
 */
public abstract class Castle extends Sprite {
	
	// Variable de classe pour compter le nombre de chateaux crées dans le jeu
	private static int nbreInstances = 0;
    
    private double treasure;	// Le tresor du chateaux
    private int level;		// Le niveau du chateaux
    
    protected ArrayList<Soldier> listSoldier;	// La liste de soldat
    protected Production_Unit pUnit;	// L'unité de production
    private Displacement moveOrder;	// Ordre de déplacement 
    private Gate gate;	// Pour la porte du chateaux
    @SuppressWarnings("unused")
	private Input input; // Pour les interactions avec les chateaux
    
    
    public Castle(Image image, Pane layer, double x, double y, double treasure, int level,
			ArrayList<Soldier> listSoldier, Production_Unit pUnit, Displacement moveOrder, Gate gate, Input input) {
    
    	super(layer, image, x, y);
		
		this.treasure = treasure;
		this.level = level;
		
		this.listSoldier = listSoldier;
		this.pUnit = pUnit;
		this.moveOrder = moveOrder;
		
		this.gate = gate;
		this.input = input;
		
		nbreInstances++;
	}


	public static int getNbreInstances() {
		return nbreInstances;
	}

	public double getTreasure() {
		return treasure;
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
	
	// Permet d'avoir le nombre de soldat par type
	public int nbreTypeSoldier(String name) {
		int tmp = 0;
		for(int i=0; i<listSoldier.size(); i++) {
			if(name.equals("Piquier")) {
				if(listSoldier.get(i).getTroopSoldier().equals(Troop.Piquier))
					tmp++;
			}
			else if(name.equals("Chevalier")) {
				if(listSoldier.get(i).getTroopSoldier().equals(Troop.Chevalier))
					tmp++;
			}
			else {
				if(listSoldier.get(i).getTroopSoldier().equals(Troop.Onagre))
					tmp++;
			}
		}
		return tmp;
	}


}
