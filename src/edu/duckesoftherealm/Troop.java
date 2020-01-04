/**
 * 
 */
package edu.duckesoftherealm;

/**
 * @author agbarry
 *
 */
public enum Troop {
	
	// Le troupes possibles
	Piquier(Settings.PIKER_PRODUCTIONCOST, Settings.PIKER_PRODUCTIONTIME, Settings.PIKER_SPEED, Settings.PIKER_HEALTH, Settings.PIKER_DAMAGE),
	Chevalier(Settings.KNIGHT_PRODUCTIONCOST, Settings.KNIGHT_PRODUCTIONTIME, Settings.KNIGHT_SPEED, Settings.KNIGHT_HEALTH, Settings.KNIGHT_DAMAGE),
	Onagre(Settings.ONAGER_PRODUCTIONCOST, Settings.ONAGER_PRODUCTIONTIME, Settings.ONAGER_SPEED, Settings.ONAGER_HEALTH, Settings.ONAGER_DAMAGE);
	
	private double productionCost;
	private int productionTime;
	private double speed;
	private int health;
	private int dammage;
	
	Troop(double pC, int pT, double s, int h, int d) {
		productionCost = pC;
		productionTime = pT;
		speed = s;
		health = h;
		dammage = d;
	}

	public double getProductionCost() {
		return productionCost;
	}

	public int getProductionTime() {
		return productionTime;
	}

	public double getSpeed() {
		return speed;
	}

	public int getHealth() {
		return health;
	}

	public int getDammage() {
		return dammage;
	}

	public void setProductionCost(double productionCost) {
		this.productionCost = productionCost;
	}

	public void setProductionTime(int productionTime) {
		this.productionTime = productionTime;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setDammage(int dammage) {
		this.dammage = dammage;
	}
}
