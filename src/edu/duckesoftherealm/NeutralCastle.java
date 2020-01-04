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

	@Override
	public void attack(ArrayList<NormalCastle> castles, ArrayList<Soldier> soldiers) {
		if(soldiers.size()>0) {
			// Modification de l'ordre de déplacement du chateaux de l'utilisateur
			castles.get(0).getMoveOrder().setTargetX(this.getView().getLayoutX());
			castles.get(0).getMoveOrder().setTargetY(this.getView().getLayoutY());
			castles.get(0).getMoveOrder().setNumberTroop(soldiers.size());
			
			int pos = rnd.nextInt(this.getListSoldier().size());
			while(soldiers.size()>0) {
				this.damagedBy(soldiers.get(0), pos);
				if( !soldiers.get(0).isApplicated() ) {
					soldiers.get(0).removeFromLayer();
					soldiers.remove(0);
				}
				
				if( !this.getListSoldier().get(pos).isAlive() ) {
					this.getListSoldier().get(pos).removeFromLayer();
					this.getListSoldier().remove(pos);
					if(this.getListSoldier().size()>0)
						pos = rnd.nextInt(this.getListSoldier().size());
				}
				
				if(this.getListSoldier().size()==0) {	// Si la liste des soldats du chateaux attaqué est non null
					this.setBaronName(castles.get(0).getDuke().getPlayerName());
					break;
					
				}
			}
		}
		
	}
	

}
