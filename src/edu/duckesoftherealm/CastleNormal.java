package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class CastleNormal extends Castle {
	
	private Player duke;

	/**
	 * @param image
	 * @param layer
	 * @param x
	 * @param y
	 * @param treasure
	 * @param level
	 * @param listSoldier
	 * @param pUnit
	 * @param moveOrder
	 * @param gate
	 * @param duke
	 */
	public CastleNormal(Image image, Pane layer, double x, double y, double treasure, int level,
			ArrayList<Soldier> listSoldier, Production_Unit pUnit, Displacement moveOrder, Gate gate, Player duke) {
		
		super(image, layer, x, y, treasure, level, listSoldier, pUnit, moveOrder, gate);
		
		this.duke = duke;
	}

	public Player getDuke() {
		return duke;
	}

	public void setDuke(Player duke) {
		this.duke = duke;
	}

	@Override
	public void checkRemovability() {
		// TODO Auto-generated method stub
		
	}

}
