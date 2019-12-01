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

	// A terminer après

	public Production_Unit() {
		super();
	}

	public Production_Unit(Troop troopType, int remainingTurns) {
		this.troopType = troopType;
		this.remainingTurns = remainingTurns;
	}

	public Production_Unit(int remainingTurns, int improvement) {
		this.remainingTurns = remainingTurns;
		this.improvement = improvement;
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


}
