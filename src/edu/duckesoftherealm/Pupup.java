package edu.duckesoftherealm;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Pupup {
	
public static void display(NormalCastle nC) {
		
	    Stage popupwindow=new Stage();
	          
	    popupwindow.initModality(Modality.APPLICATION_MODAL);
	    popupwindow.setTitle("This is a pop up window");     
	          
	    Label label1= new Label("Nombre de piquier: ");
	    
	    TextField text1 = new TextField();
	    text1.setMaxWidth(120);
	    
	    Label label2= new Label("Nombre de chevalier: ");
	    
	    TextField text2 = new TextField();
	    text2.setMaxWidth(120);
	    
	    Label label3= new Label("Nombre d'onagre:   ");
	    
	    TextField text3 = new TextField();
	    text3.setMaxWidth(140);
	    
	    Button button1= new Button("Valider");
	    button1.setPadding(new Insets(10));
	    Button button2 = new Button("Annuler");
	    button2.setPadding(new Insets(10));
	    
	    
	    Label label4 = new Label("			Vous avez:  "+nC.nbreTypeSoldier("Piquier")+" Piquier,		"+nC.nbreTypeSoldier("Chevalier")+
	    		" Chevalier	  et	"+nC.nbreTypeSoldier("Onagre")+" Onagre");
	    
	    label4.setPrefHeight(80);
	         
	    button2.setOnAction(e -> popupwindow.close());
	    
	    FlowPane root = new FlowPane();
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(20);   
	          
	    root.getChildren().addAll(label4, label1, text1, label2, text2, label3, text3, button1, button2);
	          
	    Scene scene1= new Scene(root, 620, 250);
	          
	    popupwindow.setScene(scene1);
	          
	    popupwindow.showAndWait();
	}

}
