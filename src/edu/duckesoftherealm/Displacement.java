/**
 * 
 */
package edu.duckesoftherealm;

/**
 * @author agbarry
 *
 */
public class Displacement {
	private int targetX;	// Cordonnée x de la cible
	private int targetY;	// Coordonnée y de la cible
	// A completer après
	private int numberTroop;
	
	// Constructeur par défaut pour pouvoir créer un chateaux (A revoir pour un eventuel changement)
	public Displacement() {
		super();
	}

	public Displacement(int targetX, int targetY, int numberTroop) {
		this.targetX = targetX;
		this.targetY = targetY;
		this.numberTroop = numberTroop;
	}
	
	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}
	
	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}
	
	public int getNumberTroop() {
		return numberTroop;
	}

	public void setNumberTroop(int numberTroop) {
		this.numberTroop = numberTroop;
	}

	@Override
	public String toString() {
		return "Displacement [targetX=" + targetX + ", targetY=" + targetY + ", toString()=" + super.toString() + "]";
	}
	

}
