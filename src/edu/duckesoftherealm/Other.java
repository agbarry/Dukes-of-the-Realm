package edu.duckesoftherealm;

import edu.duckesoftherealm.controller.PupupController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Other {

	public static void errorAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.showAndWait();
    }
    
    public static void infoAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.showAndWait();
    }
    
    public static void ContextMenuPlayer(NormalCastle nC, ImageView img, double x, double y) {
		
		ContextMenu contextMenu = new ContextMenu();
		
		Menu addMenu = new Menu("Ajouter");
		MenuItem addProduction = new MenuItem("Production");
		MenuItem addLevel= new MenuItem("Niveau");
		addMenu.getItems().addAll(addProduction, addLevel);
		SeparatorMenuItem separator1 = new SeparatorMenuItem();
		Menu removeMenu = new Menu("Retirer");
		MenuItem removeTheLastProduc= new MenuItem("La dernière production");
		MenuItem removeTheLastLevel = new MenuItem("Le dernier niveau");
		removeMenu.getItems().addAll(removeTheLastProduc, removeTheLastLevel);
		SeparatorMenuItem separator2 = new SeparatorMenuItem();
		MenuItem cancelProduction = new MenuItem("Annuler la production");
		
		addProduction.setOnAction(evt -> {
			Stage popupwindow=new Stage();    
		    popupwindow.initModality(Modality.APPLICATION_MODAL);
		    popupwindow.setResizable(false);
		    popupwindow.setTitle("Ajout de production"); 
		    
		    FlowPane root = new FlowPane();
		    
		    TextField text1 = new TextField();
		    text1.setPromptText("Nombre de piquier");
		    text1.setPrefWidth(160);
		    TextField text2 = new TextField();
		    text2.setPromptText("Nombre de chevalier");
		    text2.setPrefWidth(160);
		    TextField text3 = new TextField();
		    text3.setPromptText("Nombre d'onagre");
		    text3.setPrefWidth(160);
		    
		    Label label = new Label("");
		    label.setPrefHeight(60);
		    label.setTextFill(Color.RED);
		    
		    Label label1 = new Label("			Vous avez:  "+nC.getTreasure()+" florins");
		    label1.setPrefHeight(60);
		    
		    Button buttonValidate= new Button("Valider");
		    buttonValidate.setDefaultButton(true);
		    buttonValidate.setOnAction(action -> {
		    	// Si les données saisi par le joueur sont bonnes on procéde à la constitution des soldats à déployer
		    	if( PupupController.controlBeforeProduct(text1, text2, text3, nC, label) ) {	
		    		int nPiker = Integer.parseInt(text1.getText());
		    		int nKnight = Integer.parseInt(text2.getText());
		    		int nOnager = Integer.parseInt(text3.getText());
		    		
		    		Production_Unit pUnit;
		    		
		    		for(int i=0; i<nPiker; i++) {
		    			pUnit = new Production_Unit(Troop.Piquier, Troop.Piquier.getProductionTime());
		    			nC.getlPUnit().add(pUnit);
		    		}
		    		
		    		for(int i=0; i<nKnight; i++) {
		    			pUnit = new Production_Unit(Troop.Chevalier, Troop.Chevalier.getProductionTime());
		    			nC.getlPUnit().add(pUnit);
		    		}
		    		
		    		for(int i=0; i<nOnager; i++) {
		    			pUnit = new Production_Unit(Troop.Onagre, Troop.Onagre.getProductionTime());
		    			nC.getlPUnit().add(pUnit);
		    		}
		    		
					popupwindow.close();
		    	}
		    	
		    });
		    
		    Button buttonCancel = new Button("Annuler");
		    buttonCancel.setDefaultButton(true);
		    buttonCancel.setOnAction(e -> popupwindow.close());
		     
		    root.setPadding(new Insets(20));
	        root.setVgap(10);
	        root.setHgap(20);
		    root.getChildren().addAll(label1, text1, text2, text3, buttonValidate, buttonCancel, label);
		          
		    Scene scene1= new Scene(root, 390, 220);     
		    popupwindow.setScene(scene1);
		    popupwindow.showAndWait();
		});
		
		addLevel.setOnAction(evt ->  {
			int newLevel = nC.getLevel()+1;
			double cost = Settings.COST_LEVEL*newLevel;
			if(cost>nC.getTreasure()) {
				infoAlert("Erreur, votre trésor est insufisant", "Information");
			}
			else {
				nC.setTreasure(nC.getTreasure()-cost);
			}
		});
		
		
		removeTheLastProduc.setOnAction(evt -> {
			if(nC.getlPUnit().size()>0) {
				for(int i=(nC.getlPUnit().size()-1); i>0; i--) {
					if( nC.getlPUnit().get(i).isTest() ) {
						nC.getlPUnit().remove(i);
						break;
					}
				}
			}
			else 
				infoAlert("Erreur, vous n'avez pas de production en cours pour le moment","Information" );
		});
		
		removeTheLastLevel.setOnAction(evt -> {
			if(nC.getlPUnit().size()>0) {
				for(int i=(nC.getlPUnit().size()-1); i>0; i--) {
					if( !nC.getlPUnit().get(i).isTest() ) {
						nC.getlPUnit().remove(i);
						break;
					}
				}
			}
			else 
				infoAlert("Erreur, vous n'avez pas de production en cours pour le moment","Information" );
		});
		
		
		cancelProduction.setOnAction(evt -> {
			if(nC.getlPUnit().size()>0) {
				while(nC.getlPUnit().size()>0) {
					nC.getlPUnit().remove(nC.getlPUnit().size()-1);
				}
				infoAlert("Production annuler avec succés","Information" );
			}
			else
				infoAlert("Erreur, vous n'avez pas de production en cours pour le moment","Information" );
			
		});
		
		contextMenu.getItems().addAll(addMenu, separator1, removeMenu, separator2, cancelProduction);
		contextMenu.show(img, x, y);
	}
    
}


