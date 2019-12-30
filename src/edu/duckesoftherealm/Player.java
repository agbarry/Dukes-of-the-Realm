package edu.duckesoftherealm;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Player extends Sprite {
	
	private int health;	// La nombre de vie du joueur
	@SuppressWarnings("unused")
	private Input input;	// Variable pour les interactions
	private String playerName;	// Le nom du joueur
	

	public Player(Pane layer, Image image, double x, double y, int health, Input input, String playerName) {
		
		super(layer, image, x, y);
		
		this.health = health;
		this.input = input;
		this.playerName = playerName;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public static void manySec(long s) {
	    try {
	      Thread.currentThread().sleep(s * 1000);
	      }
	    catch (InterruptedException e) {
	      e.printStackTrace();
	      }
}

	@Override
	public void checkRemovability() {
		// TODO Auto-generated method stub
		
	}

	
}
