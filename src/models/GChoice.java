package models;

import java.awt.Color;

// Represents a choice on a given node/"screen"
public class GChoice {

	// Label for the button
	public String actionText;
	
	// Node to swap to on button press
	public int actionNode;
	
	// Color of the choice button
	public Color choiceColor;
	
	// Constructor
	public GChoice(String text, int actionNode) {
		this.actionText = text;
		this.actionNode = actionNode;
		this.choiceColor = Color.LIGHT_GRAY;
	}
	
	// Returns whether the choice should be displayed or not
	public boolean shouldDisplay() {
		// Generic choices should always display
		return true;
	}
	
	// Any extra actions that happen when you hit the button
	public void activate() {
		// Do nothing for regular choices
	}
	
}
