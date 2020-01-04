package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalCastle extends Castle {
	
	private Player duke;
	protected Production_Unit pUnit;	// L'unité de production
    private Displacement moveOrder;	// Ordre de déplacement 


	/**
	 * @param image
	 * @param layer
	 * @param x
	 * @param y
	 * @param treasure
	 * @param level
	 * @param listSoldier
	 * @param gate
	 * @param input
	 * @param duke
	 * @param pUnit
	 * @param moveOrder
	 */
	public NormalCastle(Image image, Pane layer, double x, double y, double treasure, int level,
			ArrayList<Soldier> listSoldier, Gate gate, Input input, Player duke, Production_Unit pUnit,
			Displacement moveOrder) {
		
		super(image, layer, x, y, treasure, level, listSoldier, gate, input);
		this.duke = duke;
		this.pUnit = pUnit;
		this.moveOrder = moveOrder;
	}
	
	public Player getDuke() {
		return duke;
	}

	public void setDuke(Player duke) {
		this.duke = duke;
	}

	public Production_Unit getpUnit() {
		return pUnit;
	}

	public void setpUnit(Production_Unit pUnit) {
		this.pUnit = pUnit;
	}

	public Displacement getMoveOrder() {
		return moveOrder;
	}

	public void setMoveOrder(Displacement moveOrder) {
		this.moveOrder = moveOrder;
	}

	@Override
	public void checkRemovability() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spriteMove(double x, double y) {
		// TODO Auto-generated method stub
		
	}

}
