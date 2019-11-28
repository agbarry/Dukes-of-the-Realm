/**
 * 
 */
package edu.duckesoftherealm;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * @author agbarry
 *
 */
public class Soldier extends Sprite {
	
	private Troop troopSoldier;	// Le type de troupe du soldat
	private double speed;		// La vitesse du soldat
	
	
	
	// A terminer après
	
	public Soldier(Pane layer, Image image, double x, double y, int health, double damage, Troop troopSoldier,
			double speed) {
		super(layer, image, x, y, health, damage);
		this.troopSoldier = troopSoldier;
		this.speed = speed;
	}
	

	public Troop getTroopSoldier() {
		return troopSoldier;
	}

	public double getSpeed() {
		return speed;
	}


	@Override
	public String toString() {
		return "Soldier [troopSoldier=" + troopSoldier + ", speed=" + speed + "]";
	}


	@Override
	public void checkRemovability() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
