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
	public void extractSoldiersToDeploy(ArrayList<Soldier> soldiers, int piker, int knight, int onager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(NormalCastle nC, ArrayList<Soldier> soldiers) {
		if(soldiers.size()>0) {
			// Modification de l'ordre de déplacement du chateaux de l'utilisateur
			nC.getMoveOrder().setTargetX(this.getView().getLayoutX());
			nC.getMoveOrder().setTargetY(this.getView().getLayoutY());
			nC.getMoveOrder().setNumberTroop(soldiers.size());
			
			int pos = rnd.nextInt(this.getListSoldier().size()), i = 0;
			while(i<soldiers.size()) {
				if(getListSoldier().size()>0) {
					damagedBy(soldiers.get(i), pos);	// Application du dégat du soldat à un soldat tiré au hasard sur le chateau adverse
					if( !soldiers.get(i).isApplicated() ) {
						soldiers.get(i).remove();	// Pour pouvoir supprimer le soldat après l'application de tout son dégat aux soldats adverses
						i++;
					}
					
					if( !getListSoldier().get(pos).isAlive() ) {
						getListSoldier().get(pos).removeFromLayer();	// Remove from layer
						getListSoldier().remove(pos);	// 	Remove from list
						if(getListSoldier().size()>0)
							pos = rnd.nextInt(getListSoldier().size());
					}
				}
				else {
					soldiers.get(i).remove();
					i++;
				}
			}
			if(this.getListSoldier().size()==0) {	
				setBaronName(nC.getDuke().getPlayerName());	// Changement du propriétaire du chateaux gagné après l'attaque
			}
		}
		
	}
	

}
