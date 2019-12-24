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
	
	private int health;	// Le nombre de vie du soldat
    private double damage;	// Le point de dégat du soldat
	private Troop troopSoldier;	// Le type de troupe du soldat
	private double speed;		// La vitesse du soldat
	
	
	public Soldier(Pane layer, Image image, double x, double y, int health, double damage, Troop troopSoldier, double speed) {
		
		super(layer, image, x, y);
		
		this.health = health;
        this.damage = damage;
        
		this.troopSoldier = troopSoldier;
		this.speed = speed;
	}
	

	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public double getDamage() {
		return damage;
	}


	public void setDamage(double damage) {
		this.damage = damage;
	}


	public void setTroopSoldier(Troop troopSoldier) {
		this.troopSoldier = troopSoldier;
	}


	public void setSpeed(double speed) {
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
