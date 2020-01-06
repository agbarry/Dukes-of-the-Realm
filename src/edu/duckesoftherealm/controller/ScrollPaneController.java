package edu.duckesoftherealm.controller;

import edu.duckesoftherealm.Input;
import javafx.scene.control.ScrollPane;

public class ScrollPaneController {
	
	public static void Mouvement(Input input, ScrollPane scrollPane) {
		if(input.isMoveRight()) {
			scrollPane.setHvalue(scrollPane.getHvalue()+1);
		}
		if(input.isMoveLeft()) {
			scrollPane.setHvalue(scrollPane.getHvalue()-1);
		}
		if(input.isMoveDown()) {
			scrollPane.setVvalue(scrollPane.getVvalue()+1);
		}
		if(input.isMoveUp()){
			scrollPane.setVvalue(scrollPane.getVvalue()-1);
		}
	}
	
}
