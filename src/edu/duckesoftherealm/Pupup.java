package edu.duckesoftherealm;

import java.util.ArrayList;

import edu.duckesoftherealm.controller.PupupController;
import edu.duckesoftherealm.Soldier;
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
	
	public static ArrayList<Soldier> display(NormalCastle nC) {
		ArrayList<Soldier> lSoldiers = new ArrayList<Soldier>();
		
	    Stage popupwindow=new Stage();    
	    popupwindow.initModality(Modality.APPLICATION_MODAL);
	    popupwindow.setTitle("Lancement d'attaque"); 
	    
	    FlowPane root = new FlowPane();
	          
	    Label label1= new Label("Nombre de piquier: ");
	    Label label2= new Label("Nombre de chevalier: ");
	    Label label3= new Label("Nombre d'onagre:   ");
	    Label label4 = new Label("			Vous avez:  "+nC.nbreTypeSoldier("Piquier")+" Piquier,		"+nC.nbreTypeSoldier("Chevalier")+
	    		" Chevalier	  et	"+nC.nbreTypeSoldier("Onagre")+" Onagre");
	    label4.setPrefHeight(80);
	    Label label5 = new Label();
	    label5.setPrefHeight(60);
	    label5.setTextFill(Color.RED);
	    
	    TextField text1 = new TextField();
	    text1.setMaxWidth(120);
	    TextField text2 = new TextField();
	    text2.setMaxWidth(120);
	    TextField text3 = new TextField();
	    text3.setMaxWidth(140);
	    
	    Button button1= new Button("Valider");
	    button1.setPadding(new Insets(10));
	    button1.setOnAction(action -> {
	    	// Si les données saisi par le joueur sont bonnes on procéde à la constitution des soldats à déployer
	    	if( PupupController.controlBeforeAttack(text1, text2, text3, nC, label5) ) {	
	    		int nPiker = Integer.parseInt(text1.getText());
	    		int nKnight = Integer.parseInt(text2.getText());
	    		int nOnager = Integer.parseInt(text3.getText());
	    		nC.extractSoldiersToDeploy(lSoldiers, nPiker, nKnight, nOnager);	// Extraction des soldats à déployer
				popupwindow.close();
	    	}
	    	
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
