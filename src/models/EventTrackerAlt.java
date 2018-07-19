package models;

// Class containing event flags and other tracking information for alternate game session
public class EventTrackerAlt {
	
	// Flag indicating whether button was pressed on control panel in Lower Deck
	public boolean mainCellUnlocked = false;
	
	// Flag indicating whether tunnel was explored in cabins
	public boolean cabinTunnelSeen = false;
	
	// Flag indicating whether shelf button was activated in Med Bay
	public boolean shelfButtonHit = false;
	
	// Flag indicating whether shelves were inspected in Deep Storage
	public boolean shelvesInspected = false;
	
	// Flag indicating whether monster is active
	public boolean monsterFree = false;
	
	// Flag indicating whether monster should kill the player
	public boolean shouldKill = false;
	
	// Flag indicating whether player has observed the drawings
	public boolean drawingsSeen = false;
	
	// Statistics for the monster's attack patterns
	public int monsterAggro = 0;
	public int monsterRange = 200;
	public int monsterTime = 2200;
	
	// Resets everything back to its starting condition
	public void resetTracker() {
		// Reset all game flags
		this.mainCellUnlocked = false;
		this.cabinTunnelSeen = false;
		this.shelfButtonHit = false;
		this.shelvesInspected = false;
		this.monsterFree = false;
		this.shouldKill = false;
		this.drawingsSeen = false;
		
		// Reset monster attack statistics
		this.monsterAggro = 0;
		this.monsterRange = 200;
		this.monsterTime = 2200;
	}

}
