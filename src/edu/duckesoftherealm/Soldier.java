/**
 * 
 */
package edu.duckesoftherealm;

/**
 * @author agbarry
 *
 */
public class Soldier extends Player {
	
	private Troop troopSoldier;	// Le type de troupe du soldat
	private double speed;		// La vitesse du soldat
	private int dammage;		// La santé du soldat
	
	// A terminer après
	
	public Soldier(Input input, String playerName, Troop troopSoldier, double speed, int dammage) {
		super(input, playerName);
		this.troopSoldier = troopSoldier;
		this.speed = speed;
		this.dammage = dammage;
	}

	public Troop getTroopSoldier() {
		return troopSoldier;
	}

	public double getSpeed() {
		return speed;
	}

	public int getDammage() {
		return dammage;
	}
	
	
	
}
