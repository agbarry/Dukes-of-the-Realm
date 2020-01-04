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
					this.setDuke(castles.get(0).getDuke());
					break;
				}
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
