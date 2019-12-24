package edu.duckesoftherealm;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Player extends Sprite {
	
	private int health;	// La nombre de vie du joueur
	private double damage;	// Le point de dégat du joueur
	private Input input;
	
	private String playerName;	// Le nom du joueur
	

	public Player(Pane layer, Image image, double x, double y, int health, double damage, Input input, String playerName) {
		
		super(layer, image, x, y);
		
		this.health = health;
        this.damage = damage;
		
		this.input = input;
		this.playerName = playerName;
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
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
