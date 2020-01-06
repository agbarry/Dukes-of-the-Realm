package edu.duckesoftherealm;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalCastle extends Castle {
	
	private Player duke;
	protected ArrayList<Production_Unit> lPUnit;	// La liste de soldat 
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
			ArrayList<Soldier> listSoldier, Gate gate, Input input, Player duke, ArrayList<Production_Unit> lPUnit,
			Displacement moveOrder) {
		
		super(image, layer, x, y, treasure, level, listSoldier, gate, input);
		this.duke = duke;
		this.lPUnit = lPUnit;
		this.moveOrder = moveOrder;
	}
	
	public Player getDuke() {
		return duke;
	}

	public void setDuke(Player duke) {
		this.duke = duke;
	}
	

	public ArrayList<Production_Unit> getlPUnit() {
		return lPUnit;
	}

	public void setlPUnit(ArrayList<Production_Unit> lPUnit) {
		this.lPUnit = lPUnit;
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
			if(getListSoldier().size()==0) {	
				setDuke(nC.getDuke());	// Changement du propriétaire du chateaux gagné après l'attaque
			}
			
		}	
	}

	@Override
	public void extractSoldiersToDeploy(ArrayList<Soldier> soldiers, int piker, int knight, int onager) {
		int verif1 = 0, verif2 = 0, verif3 = 0, i=0;
		while(i<this.getListSoldier().size()) {
			if( this.getListSoldier().get(i).getTroopSoldier().equals(Troop.Piquier) && verif1<piker ) {
				soldiers.add((Soldier) this.getListSoldier().get(i));
				verif1++;
				this.getListSoldier().remove(i);
			}
			else if (this.getListSoldier().get(i).getTroopSoldier().equals(Troop.Chevalier) && verif2<knight ) {
				soldiers.add((Soldier) this.getListSoldier().get(i));
				verif2++;
				this.getListSoldier().remove(i);
			}
			else if(this.getListSoldier().get(i).getTroopSoldier().equals(Troop.Onagre) && verif3<onager ) {
				soldiers.add((Soldier) this.getListSoldier().get(i));
				verif3++;
				this.getListSoldier().remove(i);
			}
			else
				i++;
		}
	}

}
