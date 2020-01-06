/**
 * 
 */
package edu.duckesoftherealm;

/**
 * @author agbarry
 *
 */
public class Production_Unit {

	private Troop troopType;	// Le type de la troupe
	private int remainingTurns;	// Nombre de tours restants avant productions
	private int improvement;	// Amélioration
	private boolean test;

	// A terminer après

	public Production_Unit() {
		super();
	}

	public Production_Unit(Troop troopType, int remainingTurns) {
		this.troopType = troopType;
		this.remainingTurns = remainingTurns;
		this.test = true;
	}

	public Production_Unit(int remainingTurns, int improvement) {
		this.remainingTurns = remainingTurns;
		this.improvement = improvement;
		this.test = false;
	}


	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public Troop getTroopType() {
		return troopType;
	}


	public int getRemainingTurns() {
		return remainingTurns;
	}


	public int getImprovement() {
		return improvement;
	}

	public void setRemainingTurns(int remainingTurns) {
		this.remainingTurns = remainingTurns;
	}

	public void setImprovement(int improvement) {
		this.improvement = improvement;
	}
	


}
