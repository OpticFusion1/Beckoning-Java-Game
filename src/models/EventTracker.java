package models;

// Class containing event flags and other tracking information for game session
public class EventTracker {

	// Position of the Lower Deck steering wheel
	// Either -1, 0, or 1 (left, middle, or right)
	public int wheelPos = 0;
	
	// Combination entered for the chest
	// -1 = NULL / Not entered
	// 1 = Ruby, 2 = Sapphire, 3 = Emerald
	// Index is the value that is currently being entered
	public int[] chestCombo = new int[] {-1, -1, -1, -1};
	public int chestIndex = 0;
	
	// Flag for chest opening in storage
	public boolean chestOpened = false;
	
	// Flag for discovering tunnel between Medbay and Cabins
	public boolean discoveredTunnel = false;
	
	// Flag for opening office door in Medbay
	public boolean officeOpened = false;
	
	// Flag for carving the tunnel between Medbay and Cabins
	public boolean carvedTunnel = false;
	
	// Flag for whether fish have rammed the ship
	// Counter indicating how many times player has greeted the fish
	public boolean fishAttacked = false;
	public int fishAggro = 0;
	
	// Flag for whether body in fridge is inspected
	public boolean bodyFound = false;
	
	// Flag for whether the paint from the windows was cleared
	public boolean windowsCleared = false;
	
	// Flag for whether the control panel on the lower deck was powered
	public boolean panelPowered = false;
	
	// Flag for whether the main door to Deep Storage was opened
	public boolean dStorageOpened = false;
	
	// Flags for the two vent locks (ceiling hole) that lead to the back of Deep Storage
	public boolean lockOneOpen = false;
	public boolean lockTwoOpen = false;
	
	// Flag for whether the planks under the fridge in the kitchen were grabbed
	public boolean planksTaken = false;
	// Flag for whether the fridge has tipped over
	public boolean fridgeTipped = false;
	
	// Resets everything back to its starting condition
	public void resetTracker() {
		// Reset wheel position
		this.wheelPos = 0;
		
		// Reset fish aggro.
		this.fishAggro = 0;
		
		// Reset chest combination and index
		this.resetChestCombo();
		this.chestOpened = false;
		
		// Reset all other flags to their default states
		this.discoveredTunnel = false;
		
		this.officeOpened = false;
		
		this.carvedTunnel = false;
		
		this.fishAttacked = false;
		
		this.bodyFound = false;
		
		this.windowsCleared = false;
		
		this.panelPowered = false;
		
		this.dStorageOpened = false;
		
		this.lockOneOpen = false;
		this.lockTwoOpen = false;
		
		this.planksTaken = false;
		this.fridgeTipped = false;
	}
	
	// Reset chest combination and index
	public void resetChestCombo() {
		chestCombo[0] = -1;
		chestCombo[1] = -1;
		chestCombo[2] = -1;
		chestCombo[3] = -1;
		chestIndex = 0;
	}
	
	// Checks if the chest combination is correct
	// The correct combination should be 2, 2, 1, 3
	// or Sapphire, Sapphire, Ruby, Emerald
	public boolean checkChestCombo() {
		return (chestCombo[0] == 2 &&
				chestCombo[1] == 2 &&
				chestCombo[2] == 1 &&
				chestCombo[3] == 3);
	}
}
