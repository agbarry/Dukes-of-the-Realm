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
	    
	    Label label1 = new Label("	Vous avez:  "+nC.nbreTypeSoldier("Piquier")+" Piquier |"+nC.nbreTypeSoldier("Chevalier")+
	    		" Chevalier |"+nC.nbreTypeSoldier("Onagre")+" Onagre");
	    label1.setPrefHeight(60);
	    Label label2 = new Label();
	    label2.setPrefHeight(60);
	    label2.setTextFill(Color.RED);
	    
	    TextField text1 = new TextField();
	    text1.setPromptText("Nombre de piquier");
	    text1.setPrefWidth(160);
	    TextField text2 = new TextField();
	    text2.setPromptText("Nombre de chevalier");
	    text2.setPrefWidth(160);
	    TextField text3 = new TextField();
	    text3.setPromptText("Nombre d'onagre");
	    text3.setPrefWidth(160);
	    
	    Button button1= new Button("Valider");
	    button1.setDefaultButton(true);
	    button1.setPadding(new Insets(10));
	    button1.setOnAction(action -> {
	    	// Si les données saisi par le joueur sont bonnes on procéde à la constitution des soldats à déployer
	    	if( PupupController.controlBeforeAttack(text1, text2, text3, nC, label2) ) {	
	    		int nPiker = Integer.parseInt(text1.getText());
	    		int nKnight = Integer.parseInt(text2.getText());
	    		int nOnager = Integer.parseInt(text3.getText());
	    		nC.extractSoldiersToDeploy(lSoldiers, nPiker, nKnight, nOnager);	// Extraction des soldats à déployer
				popupwindow.close();
	    	}
	    	
	    });
	    
	    Button button2 = new Button("Annuler");
	    button2.setDefaultButton(true);
	    button2.setPadding(new Insets(10));
	    button2.setOnAction(e -> popupwindow.close());
	    
        root.setPadding(new Insets(12));
        root.setVgap(12);
        root.setHgap(20);       
	    root.getChildren().addAll(label1, text1, text2, text3, button1, button2, label2);
	          
	    Scene scene1= new Scene(root, 390, 230);     
	    popupwindow.setScene(scene1);
	    popupwindow.showAndWait();
	    
		return lSoldiers;
	}
}
