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
	Piquier(100, 5, 2, 1, 1),
	Chevalier(500, 20, 6, 3, 5),
	Onagre(1000, 50, 1, 5, 10);
	
	private int productionCost;
	private int productionTime;
	private int speed;
	private int health;
	private int dammage;
	
	Troop(int pC, int pT, int s, int h, int d) {
		productionCost = pC;
		productionTime = pT;
		speed = s;
		health = h;
		dammage = d;
	}

	public int getProductionCost() {
		return productionCost;
	}

	public int getProductionTime() {
		return productionTime;
	}

	public int getSpeed() {
		return speed;
	}

	public int getHealth() {
		return health;
	}

	public int getDammage() {
		return dammage;
	}
}
