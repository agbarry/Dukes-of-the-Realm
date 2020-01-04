/**
 * 
 */
package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * @author agbarry
 *
 */
public class NeutralCastle extends Castle {
	
	private String baronName;

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
	 * @param baronName
	 */
	public NeutralCastle(Image image, Pane layer, double x, double y, double treasure, int level,
			ArrayList<Soldier> listSoldier, Gate gate, Input input, String baronName) {
		
		super(image, layer, x, y, treasure, level, listSoldier, gate, input);
		this.baronName = baronName;
	}

	public String getBaronName() {
		return baronName;
	}

	public void setBaronName(String baronName) {
		this.baronName = baronName;
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
