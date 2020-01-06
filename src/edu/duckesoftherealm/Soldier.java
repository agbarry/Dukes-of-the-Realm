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
    private int damage;	// Le point de dégat du soldat
	private double speed;		// La vitesse du soldat
	private Troop troopSoldier;	// Le type de troupe du soldat
	
	private boolean removable = false;
	
	public Soldier(Pane layer, Image image, double x, double y, Troop troopSoldier) {
		super(layer, image, x, y);
		
		this.health = troopSoldier.getHealth();
        this.damage = troopSoldier.getDammage();
        this.speed = troopSoldier.getSpeed();
        
		this.troopSoldier = troopSoldier;
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setTroopSoldier(Troop troopSoldier) {
		this.troopSoldier = troopSoldier;
	}

	public Troop getTroopSoldier() {
		return troopSoldier;
	}
	
	public boolean isRemovable() {
        return removable;
    }
	
	public boolean isAlive() {
        return health > 0;
    }
	
	public boolean isApplicated() {
		return  damage > 0;
	}

	public void spriteMove(double cibleX, double cibleY) {
		if(this.x<cibleX)
			this.x += dx;
		else
			this.x -= dx;
		
		if(this.y<cibleY)
			this.y += dy;
		else
			this.y -= dy;
		
	}
	
	public void applicat() {
		this.setDamage(this.getDamage()-1);
	}
	
	
	public void damaged() {
		this.setHealth(this.getHealth()-1);
	}
	
	public void remove() {
        this.removable = true;
    }
	
	@Override
	public void checkRemovability() {
		// TODO Auto-generated method stub
		
	}
	
}
