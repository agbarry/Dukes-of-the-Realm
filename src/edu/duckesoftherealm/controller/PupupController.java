package edu.duckesoftherealm.controller;

import edu.duckesoftherealm.Other;
import edu.duckesoftherealm.Troop;
import edu.duckesoftherealm.NormalCastle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PupupController {
	
	public static boolean isInt(TextField input, String message) {
		try {
			Integer.parseInt(input.getText());
			return true;
			
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	public static boolean controlBeforeAttack(TextField txt1, TextField txt2, TextField txt3, NormalCastle nC, Label lbl) {
		boolean valid = false;
    	
    	if( (txt1.getText().isEmpty() & txt2.getText().isEmpty() & txt3.getText().isEmpty()) || (txt1.getText().isEmpty() & txt2.getText().isEmpty() & !txt3.getText().isEmpty()) ||
    		(txt1.getText().isEmpty() & !txt2.getText().isEmpty() & txt3.getText().isEmpty()) || (!txt1.getText().isEmpty() & txt2.getText().isEmpty() & txt3.getText().isEmpty()) ) {
    		
    		Other.errorAlert("Erreur, tous les champs doivent être renseigner avant de valider", "Avertissement");
    		txt1.requestFocus();
    	}
    	else if( txt1.getText().isEmpty() & !txt2.getText().isEmpty() & !txt3.getText().isEmpty() ) {
    		lbl.setText("Erreur, veuillez indiquer la quantité de Piquier à déployer");
    		txt1.requestFocus();
    	}
    	else if( !txt1.getText().isEmpty() & txt2.getText().isEmpty() & !txt3.getText().isEmpty() ) {
    		lbl.setText("Erreur, veuillez indiquer la quantité de Chevalier à déployer");
    		txt2.requestFocus();
    	}
    	else if( !txt1.getText().isEmpty() & !txt2.getText().isEmpty() & txt3.getText().isEmpty() ) {
    		lbl.setText("Erreur, veuillez indiquer la quantité d'Onagre à déployer");
    		txt3.requestFocus();
    	}
    	else{
    		if( PupupController.isInt(txt1, txt1.getText()) && PupupController.isInt(txt2, txt2.getText()) && PupupController.isInt(txt3, txt3.getText()) ) {
    			if(Integer.parseInt(txt1.getText())<0 || Integer.parseInt(txt2.getText())<0 || Integer.parseInt(txt3.getText())<0) {
    				if(Integer.parseInt(txt1.getText())<0)
    					txt1.requestFocus();
    				else if(Integer.parseInt(txt2.getText())<0)
    					txt2.requestFocus();
    				else
    					txt3.requestFocus();
    				
    				lbl.setText("Erreur, la quantité doit être positif");
    			}
    			else if(Integer.parseInt(txt1.getText())>nC.nbreTypeSoldier("Piquier") || Integer.parseInt(txt2.getText())>nC.nbreTypeSoldier("Chevalier") || Integer.parseInt(txt3.getText())>nC.nbreTypeSoldier("Onagre") ) {
    				String msg;
    				if(Integer.parseInt(txt1.getText())>nC.nbreTypeSoldier("Piquier")) {
	    				msg = "Piquier"; txt1.requestFocus();
    				}
    				else if(Integer.parseInt(txt2.getText())>nC.nbreTypeSoldier("Chevalier") ) {
    					msg = "Chevalier"; txt2.requestFocus();
    				}
    				else {
    					msg = "Onagre"; txt3.requestFocus();
    				}
    				lbl.setText("Erreur, vous disposez pas de telle quantité d'(e) "+msg);
    			}
    			else 
    				valid = true;
    		}
    		else {
    			if(!isInt(txt1, txt1.getText()))
    				txt1.requestFocus();
    			else if(!isInt(txt2, txt2.getText()))
    				txt2.requestFocus();
    			else
    				txt3.requestFocus();
    			
    			lbl.setText("La quantité doit être un entier");
    		}
    	}
		return valid;		
	}
	
	public static boolean controlBeforeProduct(TextField txt1, TextField txt2, TextField txt3, NormalCastle nC, Label lbl) {
		boolean valid = false;
    	
    	if( (txt1.getText().isEmpty() & txt2.getText().isEmpty() & txt3.getText().isEmpty()) || (txt1.getText().isEmpty() & txt2.getText().isEmpty() & !txt3.getText().isEmpty()) ||
    		(txt1.getText().isEmpty() & !txt2.getText().isEmpty() & txt3.getText().isEmpty()) || (!txt1.getText().isEmpty() & txt2.getText().isEmpty() & txt3.getText().isEmpty()) ) {
    		
    		Other.errorAlert("Erreur, tous les champs doivent être renseigner avant de valider", "Avertissement");
    		txt1.requestFocus();
    	}
    	else if( txt1.getText().isEmpty() & !txt2.getText().isEmpty() & !txt3.getText().isEmpty() ) {
    		lbl.setText("Erreur, veuillez indiquer la quantité de Piquier à produire");
    		txt1.requestFocus();
    	}
    	else if( !txt1.getText().isEmpty() & txt2.getText().isEmpty() & !txt3.getText().isEmpty() ) {
    		lbl.setText("Erreur, veuillez indiquer la quantité de Chevalier à produire");
    		txt2.requestFocus();
    	}
    	else if( !txt1.getText().isEmpty() & !txt2.getText().isEmpty() & txt3.getText().isEmpty() ) {
    		lbl.setText("Erreur, veuillez indiquer la quantité d'Onagre à produire");
    		txt3.requestFocus();
    	}
    	else{
    		if( PupupController.isInt(txt1, txt1.getText()) && PupupController.isInt(txt2, txt2.getText()) && PupupController.isInt(txt3, txt3.getText()) ) {
    			if(Integer.parseInt(txt1.getText())<0 || Integer.parseInt(txt2.getText())<0 || Integer.parseInt(txt3.getText())<0) {
    				if(Integer.parseInt(txt1.getText())<0)
    					txt1.requestFocus();
    				else if(Integer.parseInt(txt2.getText())<0)
    					txt2.requestFocus();
    				else
    					txt3.requestFocus();
    				
    				lbl.setText("Erreur, la quantité doit être positif");
    			}
    			else if( Troop.Piquier.getProductionCost()*Integer.parseInt(txt1.getText()) + Troop.Chevalier.getProductionCost()*Integer.parseInt(txt2.getText()) +
    					Troop.Onagre.getProductionCost()*Integer.parseInt(txt3.getText())>nC.getTreasure()) {
    				
    					lbl.setText("Erreur, votre trésor est insuffisant");
    					txt1.requestFocus();
    			}
    			else {
    				double total = (Troop.Piquier.getProductionCost()*Integer.parseInt(txt1.getText())) + (Troop.Chevalier.getProductionCost()*Integer.parseInt(txt2.getText())) +
        					(Troop.Onagre.getProductionCost()*Integer.parseInt(txt3.getText()) );
    				
    				nC.setTreasure(nC.getTreasure()-total);
    				valid = true;
    			}
    		}
    		else {
    			if(!isInt(txt1, txt1.getText()))
    				txt1.requestFocus();
    			else if(!isInt(txt2, txt2.getText()))
    				txt2.requestFocus();
    			else
    				txt3.requestFocus();
    			
    			lbl.setText("La quantité doit être un entier");
    		}
    	}
		return valid;		
	}

}
