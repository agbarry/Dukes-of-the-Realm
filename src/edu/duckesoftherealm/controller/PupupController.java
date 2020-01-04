package edu.duckesoftherealm.controller;

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

}
