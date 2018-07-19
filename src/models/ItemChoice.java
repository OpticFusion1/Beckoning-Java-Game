package models;

import java.awt.Color;

import gui.GFrame;

// Choices that are seen dependent on the player's inventory
public class ItemChoice extends GChoice {

	// Items needed by player to see the choice
	public GItem[] neededItems;
	
	// Items that prevent player from seeing the choice
	public GItem[] preventedItems;
	
	// Constants to leave alone
	public static final int BARRING = 1;
	public static final int NEEDED = 2;
	
	public ItemChoice(String text, int actionNode, GItem[] neededItems, GItem preventedItems[]) {
		super(text, actionNode);
		this.choiceColor = Color.CYAN;
		this.neededItems = neededItems;
		this.preventedItems = preventedItems;
	}
	
	public ItemChoice(String text, int actionNode, GItem[] dependentItems, int barOrNeed) {
		super(text, actionNode);
		if(barOrNeed == ItemChoice.BARRING) {
			this.neededItems = new GItem[]{};
			this.preventedItems = dependentItems;
			this.choiceColor = Color.LIGHT_GRAY;
		} else {
			this.preventedItems = new GItem[]{};
			this.neededItems = dependentItems;
			this.choiceColor = Color.CYAN;
		}
	}
	
	public ItemChoice(String text, int actionNode, GItem depItem, int barOrNeed) {
		this(text, actionNode, new GItem[] {depItem}, barOrNeed);
	}
	
	@Override
	public boolean shouldDisplay() {
		// Return false if we do not have a needed item
		for(GItem needed: this.neededItems) {
			if(!(GFrame.getInstance().hasItem(needed))) {
				return false;
			}
		}
		
		// Return false if we have a prevented item
		for(GItem prevented: this.preventedItems) {
			if(GFrame.getInstance().hasItem(prevented)) {
				return false;
			}
		}
		
		// Otherwise, return true
		return true;
	}

}
