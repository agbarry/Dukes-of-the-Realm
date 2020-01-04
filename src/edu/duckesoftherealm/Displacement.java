/**
 * 
 */
package edu.duckesoftherealm;

/**
 * @author agbarry
 *
 */
public class Displacement {
	private double targetX;	// Cordonnée x de la cible
	private double targetY;	// Coordonnée y de la cible
	// A completer après
	private int numberTroop;
	
	// Constructeur par défaut pour pouvoir créer un chateaux (A revoir pour un eventuel changement)
	public Displacement() {
		super();
	}

	public Displacement(double targetX, double targetY, int numberTroop) {
		this.targetX = targetX;
		this.targetY = targetY;
		this.numberTroop = numberTroop;
	}
	
	public void setTargetX(double targetX) {
		this.targetX = targetX;
	}
	
	public void setTargetY(double targetY) {
		this.targetY = targetY;
	}
	
	public double getTargetX() {
		return targetX;
	}

	public double getTargetY() {
		return targetY;
	}

	public int getNumberTroop() {
		return numberTroop;
	}

	public void setNumberTroop(int numberTroop) {
		this.numberTroop = numberTroop;
	}
	

}
