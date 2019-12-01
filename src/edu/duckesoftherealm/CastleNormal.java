package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class CastleNormal extends Castle {
	
	private Player duke;

	public CastleNormal(Image image, Pane layer, double x, double y, double treasure, int level,
			ArrayList<Soldier> listSoldier, Production_Unit pUnit, Displacement moveOrder, int gateX, int gateY,
			Player duke) {
		super(image, layer, x, y, treasure, level, listSoldier, pUnit, moveOrder, gateX, gateY);
		this.duke = duke;
	}

	public Player getDuke() {
		return duke;
	}

	public void setDuke(Player duke) {
		this.duke = duke;
	}

}
