package edu.duckesoftherealm;

public class Player extends Sprite {

	private Input input;
	
	private String playerName;	// Le nom du joueur
	
	// A terminer après

	public Player(Input input, String playerName) {
		super();
		this.input = input;
		this.playerName = playerName;
	}

	public Input getInput() {
		return input;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	
	
}
