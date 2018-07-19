package models;

import java.awt.Color;

import gui.GFrame;

// Choice for a node that restarts the game
public class RestartChoice extends GChoice {

	// Constructor
	public RestartChoice(String text) {
		super(text, 0);
		this.choiceColor = new Color(255, 128, 128);
	}
	
	// Clear inventory on restart choices
	public void activate() {
		GFrame.getInstance().clearInventory();
		GFrame.getInstance().resetTracker();
	}

}
