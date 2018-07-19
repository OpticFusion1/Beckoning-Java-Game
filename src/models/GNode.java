package models;

import java.awt.Color;

// Represents a "screen" or area in the game
public class GNode {
	
	// Title text
	public String titleText;
	
	// Description text
	public String descText;
	
	// Choices available
	public GChoice[] choices;
	
	// Inventory items acquired
	public GItem[] addedItems;
	
	// Inventory Items removed
	public GItem[] removedItems;
	
	// Static references for constructor help
	public static final int ADDED = 1;
	public static final int REMOVED = 2;
	
	// Constructor for multi-item get/remove
	public GNode(String titleText, String descText, GChoice[] choices, GItem[] items, GItem[] removedItems) {
		this.titleText = titleText;
		this.descText = descText;
		this.choices = choices;
		this.addedItems = items;
		this.removedItems = removedItems;
	}
	
	// Constructor for multi-item get
	public GNode(String titleText, String descText, GChoice[] choices, GItem[] depItems, int addOrRemove) {
		this.titleText = titleText;
		this.descText = descText;
		this.choices = choices;
		if(addOrRemove == GNode.ADDED) {
			this.addedItems = depItems;
			this.removedItems = new GItem[]{};
		} else {
			this.removedItems = depItems;
			this.addedItems = new GItem[]{};
		}
	}
	
	// Constructor for single-item get
	public GNode(String titleText, String descText, GChoice[] choices, GItem item) {
		this.titleText = titleText;
		this.descText = descText;
		this.choices = choices;
		this.addedItems = new GItem[] {item};
		this.removedItems = new GItem[]{};
	}
	
	// Regular node constructor
	public GNode(String titleText, String descText, GChoice[] choices) {
		this(titleText, descText, choices, new GItem[] {}, GNode.ADDED);
	}
	
	// Color to display (Light-Gray by default)
	// Override to display different color
	public Color displayColor() {
		return new Color(230, 230, 230);
	}
}
