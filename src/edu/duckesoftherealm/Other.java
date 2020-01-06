package edu.duckesoftherealm;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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
    
    public static void ContextMenuPlayer(ImageView img, double x, double y) {
		
		ContextMenu contextMenu = new ContextMenu();
		
		Menu addMenu = new Menu("Ajouter");
		MenuItem addProduction = new MenuItem("Production");
		MenuItem addLevel= new MenuItem("Niveau");
		addMenu.getItems().addAll(addProduction, addLevel);
		
		SeparatorMenuItem separator1 = new SeparatorMenuItem();
		MenuItem removeTheLast= new MenuItem("Retirer le dernier");
		SeparatorMenuItem separator2 = new SeparatorMenuItem();
		MenuItem cancelProduction = new MenuItem("Annuler la production");
		
		addProduction.setOnAction(evt -> {
			Stage popupwindow=new Stage();    
		    popupwindow.initModality(Modality.APPLICATION_MODAL);
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
		    
		    Button button1= new Button("Valider");
		    button1.setDefaultButton(true);
		    
		    Button button2 = new Button("Annuler");
		    button2.setDefaultButton(true);
		    button2.setOnAction(e -> popupwindow.close());
		     
		    root.setPadding(new Insets(20));
	        root.setVgap(10);
	        root.setHgap(20);
		    root.getChildren().addAll(text1, text2, text3, button1, button2);
		          
		    Scene scene1= new Scene(root, 210, 200);     
		    popupwindow.setScene(scene1);
		    popupwindow.showAndWait();
		});
		addLevel.setOnAction(evt ->  {});
		removeTheLast.setOnAction(evt -> {});
		cancelProduction.setOnAction(evt -> {});
		
		contextMenu.getItems().addAll(addMenu, separator1, removeTheLast, separator2, cancelProduction);
		contextMenu.show(img, x, y);
	}
    
}


