package edu.duckesoftherealm;

import java.util.ArrayList;

import edu.duckesoftherealm.controller.PupupController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Pupup {
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public static <Soldier> ArrayList<Soldier> display(NormalCastle nC) {
		
		ArrayList<Soldier> lSoldiers = new ArrayList<>();
		
	    Stage popupwindow=new Stage();
	          
	    popupwindow.initModality(Modality.APPLICATION_MODAL);
	    popupwindow.setTitle("Lancement d'attaque"); 
	    
	    
	    FlowPane root = new FlowPane();
	          
	    Label label1= new Label("Nombre de piquier: ");
	    
	    TextField text1 = new TextField();
	    text1.setMaxWidth(120);
	    
	    Label label2= new Label("Nombre de chevalier: ");
	    
	    TextField text2 = new TextField();
	    text2.setMaxWidth(120);
	    
	    Label label3= new Label("Nombre d'onagre:   ");
	    
	    TextField text3 = new TextField();
	    text3.setMaxWidth(140);
	    
	    Label label4 = new Label("			Vous avez:  "+nC.nbreTypeSoldier("Piquier")+" Piquier,		"+nC.nbreTypeSoldier("Chevalier")+
	    		" Chevalier	  et	"+nC.nbreTypeSoldier("Onagre")+" Onagre");
	    label4.setPrefHeight(80);
	    
	    Label label5 = new Label();
	    label5.setPrefHeight(60);
	    label5.setTextFill(Color.RED);
	    
	    Button button1= new Button("Valider");
	    button1.setPadding(new Insets(10));
	   
	    button1.setOnAction(action -> {
	    	String piquier = text1.getText(), chevalier = text2.getText(), onagre = text3.getText();
	    	boolean valid = false;
	    	
	    	if( (piquier.isEmpty() & chevalier.isEmpty() & onagre.isEmpty()) || (piquier.isEmpty() & chevalier.isEmpty() & !onagre.isEmpty()) ||
	    		(piquier.isEmpty() & !chevalier.isEmpty() & onagre.isEmpty()) || (!piquier.isEmpty() & chevalier.isEmpty() & onagre.isEmpty()) ) {
	    		
	    		//label5.setText("Erreur, veuillez renseigner tous les champs avant de valider");
	    		Dialog.errorAlert("Erreur, tous les champs doivent être renseigner avant de valider", "Avertissement");
	    		text1.requestFocus();
	    	}
	    	else if( piquier.isEmpty() & !chevalier.isEmpty() & !onagre.isEmpty() ) {
	    		label5.setText("Erreur, veuillez indiquer la quantité de Piquier à déployer");
	    		text1.requestFocus();
	    	}
	    	else if( !piquier.isEmpty() & chevalier.isEmpty() & !onagre.isEmpty() ) {
	    		label5.setText("Erreur, veuillez indiquer la quantité de Chevalier à déployer");
	    		text2.requestFocus();
	    	}
	    	else if( !piquier.isEmpty() & !chevalier.isEmpty() & onagre.isEmpty() ) {
	    		label5.setText("Erreur, veuillez indiquer la quantité d'Onagre à déployer");
	    		text3.requestFocus();
	    	}
	    	else{
	    		if( PupupController.isInt(text1, text1.getText()) && PupupController.isInt(text2, text2.getText()) && PupupController.isInt(text3, text3.getText()) ) {
	    			int qtePiquier = Integer.parseInt(text1.getText());
	    			int qteChevalier = Integer.parseInt(text2.getText());
	    			int qteOnagre = Integer.parseInt(text3.getText());
	    			
	    			if(qtePiquier<0 || qteChevalier<0 || qteOnagre<0) {
	    				if(qtePiquier<0)
	    					text1.requestFocus();
	    				else if(qteChevalier<0)
	    					text2.requestFocus();
	    				else
	    					text3.requestFocus();
	    				
	    				label5.setText("Erreur, la quantité doit être positif");
	    			}
	    			else if(qtePiquier>nC.nbreTypeSoldier("Piquier") || qteChevalier>nC.nbreTypeSoldier("Chevalier") || qteOnagre>nC.nbreTypeSoldier("Onagre") ) {
	    				String msg;
	    				if(qtePiquier>nC.nbreTypeSoldier("Piquier")) {
		    				msg = "Piquier"; text1.requestFocus();
	    				}
	    				else if(qteChevalier>nC.nbreTypeSoldier("Chevalier") ) {
	    					msg = "Chevalier"; text2.requestFocus();
	    				}
	    				else {
	    					msg = "Onagre"; text3.requestFocus();
	    				}
	    				label5.setText("Erreur, vous disposez pas de telle quantité d'(e) "+msg);
	    			}
	    			else {
	    				
	    				// Marche bien il ne reste qu'à 
	    				int verif1 = 0, verif2 = 0, verif3 = 0;
	    				
	    				int i=0;
	    				while(i<nC.getListSoldier().size()) {
	    					if( nC.getListSoldier().get(i).getTroopSoldier().equals(Troop.Piquier) && verif1<qtePiquier ) {
	    						lSoldiers.add((Soldier) nC.getListSoldier().get(i));
	    						verif1++;
	    						nC.getListSoldier().remove(i);
	    					}
	    					else if (nC.getListSoldier().get(i).getTroopSoldier().equals(Troop.Chevalier) && verif2<qteChevalier ) {
	    						lSoldiers.add((Soldier) nC.getListSoldier().get(i));
	    						verif2++;
	    						nC.getListSoldier().remove(i);
	    					}
	    					else if(nC.getListSoldier().get(i).getTroopSoldier().equals(Troop.Onagre) && verif3<qteOnagre ) {
	    						lSoldiers.add((Soldier) nC.getListSoldier().get(i));
	    						verif3++;
	    						nC.getListSoldier().remove(i);
	    					}
	    					else
	    						i++;
	    				}
	    				valid = true;
	    				
	    			}
	    		}
	    		else {
	    			if(!PupupController.isInt(text1, text1.getText()))
	    				text1.requestFocus();
	    			else if(!PupupController.isInt(text2, text2.getText()))
	    				text2.requestFocus();
	    			else
	    				text3.requestFocus();
	    			
	    			label5.setText("La quantité doit être un entier");
	    		}
	    	}
	    	if(valid)
	    		popupwindow.close();
	    });
	    
	    Button button2 = new Button("Annuler");
	    button2.setPadding(new Insets(10));
	    button2.setOnAction(e -> popupwindow.close());
	    
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(20);   
	          
	    root.getChildren().addAll(label4, label1, text1, label2, text2, label3, text3, button1, button2, label5);
	          
	    Scene scene1= new Scene(root, 620, 250);
	          
	    popupwindow.setScene(scene1);
	    
	    popupwindow.showAndWait();
	    
		return lSoldiers;
	}

}
