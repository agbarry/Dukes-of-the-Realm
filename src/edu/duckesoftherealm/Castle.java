/**
 * 
 */
package edu.duckesoftherealm;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * @author agbarry
 *
 */
public abstract class Castle extends Sprite {
	
	// Variable de classe pour compter le nombre de chateaux crées dans le jeu
	Random rnd = new Random();
	private static int nbreInstances = 0;
    
    private double treasure;	// Le tresor du chateaux
    private int level;		// Le niveau du chateaux
    
    protected ArrayList<Soldier> listSoldier;	// La liste de soldat 
    private Gate gate;	// Pour la porte du chateaux
    @SuppressWarnings("unused")
	private Input input; // Pour les interactions avec les chateaux
    
    
    public Castle(Image image, Pane layer, double x, double y, double treasure, int level,
			ArrayList<Soldier> listSoldier, Gate gate, Input input) {
    
    	super(layer, image, x, y);
		
		this.treasure = treasure;
		this.level = level;
		
		this.listSoldier = listSoldier;
		
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

	
	public Gate getGate() {
		return gate;
	}


	public void setGate(Gate gate) {
		this.gate = gate;
	}
	
	// Permet d'avoir le nombre de soldat par type
	public int nbreTypeSoldier(String name) {
		int tmp = 0;
		if(name.equals("Piquier")) {
			for(int i=0; i<listSoldier.size(); i++) {
				if(listSoldier.get(i).getTroopSoldier().equals(Troop.Piquier))
					tmp++;
			}
		}
		else if(name.equals("Chevalier")) {
			for(int i=0; i<listSoldier.size(); i++) {
				if(listSoldier.get(i).getTroopSoldier().equals(Troop.Chevalier))
					tmp++;
			}
		}
		else {
			for(int i=0; i<listSoldier.size(); i++) {
				if(listSoldier.get(i).getTroopSoldier().equals(Troop.Onagre))
					tmp++;
			}
		}

		return tmp;
	}

	public void damagedBy(Soldier soldier, int pos) {
		while(soldier.isApplicated() && this.listSoldier.get(pos).isAlive() ) {
			this.listSoldier.get(pos).damaged();
			soldier.applicat();
		}
	}
	
	public abstract void attack(ArrayList<NormalCastle> castles, ArrayList<Soldier> soldiers);

}
