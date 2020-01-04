package edu.duckesoftherealm;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class Dialog {

	public static void errorAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.showAndWait();
    }
    
    public static void warningAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
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
    
    public static boolean confirmDialog(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(message);
        Optional result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
    
    public static String inputDialog(String message, String title, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText("");
        dialog.setContentText(message);
        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            return (String) result.get();
        } else {
            return null;
        }
    }
}


