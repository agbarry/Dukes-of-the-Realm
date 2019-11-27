/**
 * 
 */
package edu.duckesoftherealm;

/**
 * @author agbarry
 *
 */
public class Production_Unit {
	
	 private Troop troopType;
	 private int remainingTurns;
	 private int improvement;
	 
	 
	public Production_Unit(Troop troopType, int remainingTurns) {
		super();
		this.troopType = troopType;
		this.remainingTurns = remainingTurns;
	}


	public Production_Unit(int remainingTurns, int improvement) {
		super();
		this.remainingTurns = remainingTurns;
		this.improvement = improvement;
	}
	
	
	// A terminer après
	 

}
