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
	
	
	public Displacement(int targetX, int targetY) {
		super();
		this.targetX = targetX;
		this.targetY = targetY;
	}

	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}
	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}
	
	@Override
	public String toString() {
		return "Displacement [targetX=" + targetX + ", targetY=" + targetY + ", toString()=" + super.toString() + "]";
	}
	

}
