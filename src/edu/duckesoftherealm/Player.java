package edu.duckesoftherealm;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Player extends Sprite {

	private Input input;
	
	private String playerName;	// Le nom du joueur
	
	// A terminer après

	public Player(Pane layer, Image image, double x, double y, int health, double damage, Input input,
			String playerName) {
		
		super(layer, image, x, y, health, damage);
		
		this.input = input;
		this.playerName = playerName;
		
	}

	@Override
	public void checkRemovability() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Player [input=" + input + ", playerName=" + playerName + "]";
	}
	
}
