package models;

import gui.GFrame;
import gui.GFrameAlt;

// "Map" of all the defined nodes in the alternate game
@SuppressWarnings("unused")
public class GMapAlt {

	// Array of all the nodes
	public GNode[] nodes;
	
	// Constructor
	public GMapAlt() {
		
		this.nodes = new GNode[] {
				
				// N0: Starting Point 1
				new GNode("An awakening...",
						"After what seemed like your worst nightmare, you snap out of your deep sleep to find"
						+ " yourself in an unknown location. The floor and walls are metallic blue, heavily"
						+ " rusted, and warm to the touch. You stumble to your feet, making sure to avoid scraping"
						+ " your head against the low ceiling. Your vision swirls as you slowly adjust to your"
						+ " new surroundings.",
						new GChoice[] {
								new GChoice("Look around", 1)
								}),
				
				
				// N1: Starting Point 2
				new GNode("The Vessel",
						"You look all around you to find yourself in a control room of sorts. Various dials and"
						+ " monitors exist on a giant panel to your immediate left. In front of you is what undeniably"
						+ " looks like a steering wheel. This is a vessel or ship, but why aren't you moving, and"
						+ " where is everyone? It's time to go.",
						new GChoice[] {
								new GChoice("I've been here before", 2)
								}),
				
				// N2: Lower Deck (Main)
				new GNode("Lower Deck",
						"You find yourself on what could only be the ship's lower deck. To your left is a control panel"
						+ " and in front of you is a steering wheel. Also in your general vicinity are some small circular"
						+ " windows looking outwards and a large, sturdy door.",
						new GChoice[] {
								new GChoice("Check the control panel", 3) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().getTracker().mainCellUnlocked;
									}
								},
								new GChoice("Check the control panel", 13) {
									@Override
									public boolean shouldDisplay() {
										return GFrameAlt.getInstance().getTracker().mainCellUnlocked;
									}
								},
								new GChoice("Check out the steering wheel", 4),
								new GChoice("Look out the windows", 5),
								new GChoice("Go to the door", 14) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().getTracker().mainCellUnlocked;
									}
								},
								new GChoice("Go through the door", 6) {
									@Override
									public boolean shouldDisplay() {
										return GFrameAlt.getInstance().getTracker().mainCellUnlocked;
									}
								}
								}),
				
				// N3: Lower Deck (Panel / Dead)
				new GNode("Control Panel",
						"You swear this panel was loaded to the brim with gadgets, but now all you can spot is one"
						+ " lone button. There's a label directly above the button that reads 'MAIN CELL LOCK RELEASE'.",
						new GChoice[] {
								new GChoice("Go back", 2),
								new GChoice("Press it", 12) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().mainCellUnlocked = true;
										GFrameAlt.getInstance().getTracker().monsterFree = true;
									}
								}
								}),
				
				// N4: Lower Deck (Wheel)
				new GNode("The Steering Wheel",
						"A steering wheel stands in the middle of the room. It's spinning in place on its own accord.",
						new GChoice[] {
								new GChoice("Go back", 2)
								}),
				
				// N5: Lower Deck (Windows)
				new GNode("A Window",
						"Looking out the windows, you see the ship is coasting on a vast ocean of vividly pink liquid"
						+ " which you can't identify. The scene is highlighted by a brightly lit white sky, which"
						+ " beautifully contrasts with the surroundings. The image seems torn straight out of surreal"
						+ " painting. It provides you with a strange warm sensation, but somehow you can't shake"
						+ " the constant feeling that you shouldn't have come here.",
						new GChoice[] {
								new GChoice("Go back", 2)
								}),
				
				// N6: Staircase (Upper)
				new GNode("Upper Throat",
						"You find yourself towards the top of a long, dark throat. Large molars stick out of the red, fleshy"
						+ " walls and form a staircase of sorts. Which way will you go?",
						new GChoice[] {
								new GChoice("Return to the Lower Deck", 15),
								new GChoice("Go upwards", 7),
								new GChoice("Go downwards", 9)
								}),
				
				// N7: Staircase (Top / Blocked)
				new GNode("Top of the Throat",
						"You are at the topmost reach of the throat. A short hallway with metal walls converges with"
						+ " the flesh here, scarring it at the connections. At the end of the hallway is a sturdy iron door"
						+ " with seven distinct divots in it.",
						new GChoice[] {
								new GChoice("Try the door", 8) {
									@Override
									public boolean shouldDisplay() {
										return !(GFrameAlt.getInstance().hasItem(GItem.EYE1) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE2) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE3) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE4) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE5) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE6) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE7));
									}
								},
								new GChoice("Try the door using the seven eyes", 54) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().monsterFree = false;
									}
									
									@Override
									public boolean shouldDisplay() {
										return (GFrameAlt.getInstance().hasItem(GItem.EYE1) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE2) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE3) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE4) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE5) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE6) &&
												GFrameAlt.getInstance().hasItem(GItem.EYE7));
									}
								},
								new GChoice("Go downwards", 6)
								}),
				
				// N8: Sturdy Iron Door to Outside (Blocked)
				new GNode("Divoted Door",
						"You can't open this door without something that you're currently missing, and this time"
						+ " you're confident the answers lie somewhere below you. You have to go back downwards.",
						new GChoice[] {
								new GChoice("Go back", 7)
								}),
				
				// N9: Staircase (Middle)
				new GNode("Middle Throat",
						"You find yourself in the middle of a long, dark throat. Large molars stick out of the red, fleshy"
						+ " walls and form a staircase of sorts. Which way will you go?",
						new GChoice[] {
								new GChoice("Step out of the throat", 16),
								new GChoice("Go upwards", 6),
								new GChoice("Go downwards", 10)
								}),
				
				// N10: Staircase (Lower)
				new GNode("Lower Throat",
						"You find yourself towards the bottom of a long, dark throat. Large molars stick out of"
						+ " the red, fleshy walls and form a staircase of sorts. Which way will you go?",
						new GChoice[] {
								new GChoice("Step out of the throat", 40),
								new GChoice("Go upwards", 9),
								new GChoice("Go downwards", 11)
								}),
				
				// N11: Staircase (Bottom)
				new GNode("Bottom of the Throat",
						"You are at the bottom of the dark staircase. You can hardly see anything in the cramped"
						+ " space around you, but you can feel out an open metal door and the steps leading upwards.",
						new GChoice[] {
								new GChoice("Go through the door", 47),
								new GChoice("Go up the throat", 10)
								}),
				
				// N12: Main Cell Unlock
				new GNode("No Going Back",
						"The button has been pressed. The large door in the room creaks open, and you jump as a stone"
						+ " amulet you previously missed falls to the floor from the control panel."
						+ " It resembles an eye, and it has an inscription on the back: 'Use me in your time of need."
						+ " Confront your attacker directly with me, and you'll be safe.' It's a weird little message,"
						+ " but you grasp the amulet tightly regardless and bear it close, ready to shield yourself"
						+ " from what lies beyond the door. Even if it doesn't work, what other chance would you have?",
						new GChoice[] {
								new GChoice("Continue", 2)
								}),
				
				// N13: Control Panel Revisit
				new GNode("...",
						"I can't undo that now.",
						new GChoice[] {
								new GChoice("Back", 2)
								}),
				
				// N14: Main Cell Locked Door
				new GNode("Main Cell Door",
						"The door to the prison itself. You can't imagine what's locked inside being happy to see you"
						+ " again, but you feel compelled to open it anyways. There's a heavy electronic bolt sealing"
						+ " the door, but there must be a way to disable this nearby.",
						new GChoice[] {
								new GChoice("Back", 2)
								}),
				
				// N15: Control Panel Revisit
				new GNode("It's Closed...",
						"It seems the Main Cell door relocked itself in a cruel twist of fate. It seems the thing"
						+ " lying within doesn't want me to escape so easily.",
						new GChoice[] {
								new GChoice("Back", 6)
								}),
				
				// N16: Cabins (Main)
				new GNode("The Cabins",
						"These certainly must be the cabins of the ship. There's a grid of bunk-beds that surround"
						+ " the perimeter of the room, leaving the center surprisingly barren. Dust seems to linger"
						+ " in the air as if suspended by an unearthly force, creating an unsettling atmosphere. There"
						+ " are many different areas to potentially inspect.",
						new GChoice[] {
								new GChoice("Back to the throat", 9),
								new GChoice("Inspect the front end", 17) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().getTracker().cabinTunnelSeen;
									}
								},
								new GChoice("Go through tunnel to Medical Bay", 23) {
									@Override
									public boolean shouldDisplay() {
										return GFrameAlt.getInstance().getTracker().cabinTunnelSeen;
									}
								},
								new GChoice("Inspect the back-left corner", 19),
								new GChoice("Inspect the back-right corner", 22)
								}),
				
				// N17: Cabins (Front)
				new GNode("Front-End Cabins",
						"The front-end of the cabins seems mostly empty except for a crack in the wall you see through"
						+ " the perimeter of beds. On a closer look, the crack goes deeper into the wall than your eyes"
						+ " can see. This might lead somewhere.",
						new GChoice[] {
								new GChoice("Go back", 16),
								new GChoice("Explore the tunnel", 18) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().cabinTunnelSeen = true;
									}
								}
								}),
				
				// N18: Cabins (Tunnel)
				new GNode("Winding Endlessly",
						"You slip yourself through the crack in the wall and begin edging your way through the tunnel."
						+ " Before long, you find yourself ducking your head and crawling on your knees as the space rapidly"
						+ " encloses. The walls of the tunnel begin to grow damp and sticky, but you eventually you emerge"
						+ " on the other side through a bookcase after a tiresome amount of effort. Upon looking back, you can see"
						+ " the cabins right on the other side of the wall...",
						new GChoice[] {
								new GChoice("Continue", 23)
								}),
				
				// N19: Cabins (Back-Left)
				new GNode("Open Chest",
						"Under a bed in the back-left corner, you spot an ornate looking chest that looks very familiar."
						+ " Its lid is slightly ajar...",
						new GChoice[] {
								new GChoice("Go back", 16),
								new GChoice("Open the chest", 21) {
									@Override
									public boolean shouldDisplay() {
										return GFrameAlt.getInstance().hasItem(GItem.EYE1);
									}
								},
								new GChoice("Open the chest", 20) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().hasItem(GItem.EYE1);
									}
								}
								}),
				
				// N20: Cabins (Chest Opening / Eye 1)
				new GNode("A Marble Eye",
						"You lift the lid of the chest to find a large collection of marbles at the bottom. One of them is"
						+ " particularly striking in appearance and resembles an eye with a pink-flowered iris and a"
						+ " white diamond pupil. You decide to keep it, and leave the rest of the marbles alone.",
						new GChoice[] {
								new GChoice("Continue", 19) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().monsterRange += -25;
										GFrameAlt.getInstance().getTracker().monsterTime += -200;
									}
								}
								},
						new GItem[] { GItem.EYE1 }, GNode.ADDED),
				
				// N21: Cabins (Chest Opening / Repeat)
				new GNode("Nothing",
						"You lift the lid of the chest to find it completely empty...",
						new GChoice[] {
								new GChoice("Go back", 19)
								}),
				
				// N22: Cabins (Back-right)
				new GNode("Curious Corner",
						"Searching all the beds in this corner, you can't find anything useful. The only"
						+ " thing notable is a circular indent in the floor under one of the beds, but even that seems"
						+ " useless now.",
						new GChoice[] {
								new GChoice("Go back", 16)
								}),
				
				// N23: Medical Bay (Main)
				new GNode("Medical Bay",
						"You're in what used to be a medical bay. The floor here is grated like a vent, and it seems to"
						+ " be preventing you from stepping in a sea of deep scarlet liquid which lies a couple inches below"
						+ " your feet. There are medical beds that are nearly torn to pieces, shelves that are racked with a"
						+ " multitude of bottles, and the open entrance to an office to check out.",
						new GChoice[] {
								new GChoice("Go back through bookcase tunnel", 16),
								new GChoice("Inspect the medical beds", 24) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().getTracker().shelfButtonHit;
									}
								},
								new GChoice("Check the shelves", 25) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().shelfButtonHit = true;
									}
									
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().getTracker().shelfButtonHit;
									}
								},
								new GChoice("Inspect the medical beds", 26) {
									@Override
									public boolean shouldDisplay() {
										return (GFrameAlt.getInstance().getTracker().shelfButtonHit &&
												(!GFrameAlt.getInstance().hasItem(GItem.EYE2)));
									}
								},
								new GChoice("Check the shelves", 28) {
									@Override
									public boolean shouldDisplay() {
										return GFrameAlt.getInstance().getTracker().shelfButtonHit;
									}
								},
								new GChoice("Go into the office", 29)
								}),
				
				// N24: Medical Bay (Beds / Unactivated)
				new GNode("Tattered Beds",
						"The beds scattered throughout the room all resemble a similar state. Most of them are horribly"
						+ " dirty and stained dark red and black. The cushioning on the beds is torn considerably, and you"
						+ " even find some teeth and fingernails lodged into the sides of some of the headrests. You feel"
						+ " disgusted, and you don't even find anything useful in your search.",
						new GChoice[] {
								new GChoice("Go back", 23)
								}),
				
				// N25: Medical Bay (Shelves / Unactivated)
				new GNode("Loaded Shelves",
						"Numerous bottles line what were once empty shelves. You try examining a few of them, but the"
						+ " labels are written in weird cursive-like symbols that are completely unreadable to you. When"
						+ " trying to put things back, you realize one of the bottles you picked up was sitting on a"
						+ " weighted button. You can't seem to click the button back down by force, so you guess you've"
						+ " left something changed whether you like it or not.",
						new GChoice[] {
								new GChoice("Go back", 23)
								}),
				
				// N26: Medical Bay (Beds / Activated)
				new GNode("Tattered Beds?",
						"The beds scattered throughout the room all resemble a similar state. Most of them are horribly"
						+ " dirty and stained dark red and black. The cushioning on the beds is torn considerably, and you"
						+ " even find some teeth and fingernails lodged into the sides of some of the headrests. At the foot"
						+ " of the last bed, there's a weird rod sticking out of the grating in the floor.",
						new GChoice[] {
								new GChoice("Go back", 23),
								new GChoice("Pull up the rod", 27)
								}),
				
				// N27: Medical Bay (Beds / Rod)
				new GNode("An Artificial Eye",
						"You kneel down and start pulling the rod up. It extends much deeper into the floor"
						+ " than you initially thought, and there's at least a six foot stretch stained by the"
						+ " red liquid below the grating before you finish pulling the thing up. Finally being done,"
						+ " you can see there's an eyeball attached by a small chain to the bottom of the rod. On"
						+ " closer inspection it appears to be made of plastic, thankfully. You remove it from the"
						+ " chain and pocket it.",
						new GChoice[] {
								new GChoice("Continue", 23) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().monsterRange += -25;
										GFrameAlt.getInstance().getTracker().monsterTime += -200;
									}
								}
								},
						new GItem[] { GItem.EYE2 }, GNode.ADDED),
				
				// N28: Medical Bay (Shelves / Unactivated)
				new GNode("Loaded Shelves",
						"Numerous bottles line what were once empty shelves. You still can't understand any of the symbols"
						+ " on the containers. The button you revealed before still lies out in the open. It appears"
						+ " to be connected to something else in the Medical Bay.",
						new GChoice[] {
								new GChoice("Go back", 23)
								}),
				
				// N29: Medical Bay (Office)
				new GNode("Office",
						"You arrive in a tight-spaced office. There's no desk, but there is a simple square table in the"
						+ " center of the room. A series of crudely drawn sketches lie upon the table, and they all seem to"
						+ " depict the very entity that has been pursuing you. There's a circular hole in the back corner"
						+ " of the room and an additional doorway you didn't initially notice.",
						new GChoice[] {
								new GChoice("Go back to Medical Bay", 23),
								new GChoice("Examine the drawings", 30) {
									@Override
									public void activate() {
										// Fetch tracker reference
										EventTrackerAlt tracker = GFrameAlt.getInstance().getTracker();
										
										// If we haven't seen the drawings, anger monster
										if(!tracker.drawingsSeen) {
											tracker.monsterAggro += 120;
										}
										// Indicate drawings have been seen
										tracker.drawingsSeen = true;
									}
								},
								new GChoice("Drop through the hole", 31),
								new GChoice("Go through the doorway", 32)
								}),
				
				// N30: Medical Bay (Office / Drawings)
				new GNode("Uncomfortable Drawings",
						"The sketchings on the table, while poorly drawn, invoke a primal sense of fear within you. The"
						+ " longer you stare, the more real they start to seem. You start to feel as if the thing is"
						+ " behind you now, breathing down your neck...",
						new GChoice[] {
								new GChoice("...", 29)
								}),
				
				// N31: Medical Bay (Office / Hole)
				new GNode("Drop",
						"You plunge through the hole. The walls seem to swirl as you fall downwards, and you eventually"
						+ " land at the bottom of the pit.",
						new GChoice[] {
								new GChoice("Continue", 50)
								}),
				
				// N32: Cafeteria (Main)
				new GNode("Cafeteria",
						"You find yourself in the cafeteria. The temperature here is quite cold, and the walls and floor"
						+ " are made of aluminum. There's a set of dining tables stacked in the back-right corner of the"
						+ " room. On the other end, there's a food counter with a kitchen in back of it. There's also a"
						+ " door to the Medical Bay office towards the front end of the room.",
						new GChoice[] {
								new GChoice("Go to the Medical Bay office", 29),
								new GChoice("Check the stack of tables", 33),
								new GChoice("Inspect the food counter", 35),
								new GChoice("Go to the Kitchen", 36)
								}),
				
				// N33: Cafeteria (Tables)
				new GNode("Stacked Tables",
						"The tables are stacked neatly and are incredibly cold to the touch. This room must have been"
						+ " this temperature for a while. Under the bottom table in the pile, you see a circular"
						+ " indent in the ground that opens into a hole.",
						new GChoice[] {
								new GChoice("Go back", 32),
								new GChoice("Crawl down the hole", 34)
								}),
				
				// N34: Cafeteria (Hole)
				new GNode("A Shallow Plunge",
						"You crawl under the bottom table and descend down the hole in the floor. You climb a small"
						+ " ways down a fairly narrow metal tunnel before taking a small drop onto the floor below.",
						new GChoice[] {
								new GChoice("Continue", 43)
								}),
				
				// N35: Cafeteria (Food Counter)
				new GNode("Stocked Food Counter",
						"You approach the food counter to find it surprisingly stocked. However, every tray is covered"
						+ " in a thick cloudy glass that you can't remove or see through easily. Using the best of your"
						+ " abilities to look, it seems like all of the dishes available resemble some sort of clumped"
						+ " mush. Doesn't seem appetizing.",
						new GChoice[] {
								new GChoice("Go back", 32)
								}),
				
				// N36: Cafeteria (Kitchen)
				new GNode("Bloodied Kitchen",
						"Looking at the kitchen now, you're surprised you couldn't notice its horrid state from the"
						+ " cafeteria. There are dead rats everywhere: some on the counters, some piled in corners,"
						+ " and even some hanging from the ceiling. The worst part is that most of them appear to be"
						+ " half-eaten, with various limbs and parts missing from each one. There's a dirty pot of"
						+ " something currently cooking on one of the stoves as well as an open fridge in the corner"
						+ " of the room.",
						new GChoice[] {
								new GChoice("Go back to main room", 32),
								new GChoice("Check the cooking pot", 37) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().hasItem(GItem.EYE3);
									}
								},
								new GChoice("Check the fridge", 38),
								new GChoice("Inspect the rats", 39)
								}),
				
				// N37: Cafeteria (Kitchen / Cooking Pot)
				new GNode("A Human Eye",
						"You look into cooking pot to see a thick broth brewing. From this close, you smell the distinct"
						+ " stench of spoiled meat boiling. Before you can recoil away, you spot what can only be a human"
						+ " eye rising to the top of the broth. Overtaken by some odd compulsion, you fling your hand into"
						+ " the boiling stew and snatch the eye, scalding your hand in the process. You pocket the eye"
						+ " for later.",
						new GChoice[] {
								new GChoice("Continue", 36) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().monsterRange += -25;
										GFrameAlt.getInstance().getTracker().monsterTime += -200;
									}
								}
								},
						new GItem[] { GItem.EYE3 }, GNode.ADDED),
				
				// N38: Cafeteria (Kitchen / Fridge)
				new GNode("Empty Fridge",
						"The fridge is completely empty, defying your expectations. There's some sort of black goo in one of the"
						+ " corners. It trails out onto the floor, leading to the cooking pot you saw earlier.",
						new GChoice[] {
								new GChoice("Go back", 36)
								}),
				
				// N39: Cafeteria (Kitchen / Rats)
				new GNode("Dead Rats",
						"As you observed before, most of the rats around the room are missing body parts. On closer"
						+ " inspection, some of the incisions seem to be from a knife and others clearly appear to be"
						+ " bite marks. There's one particularly large pile of the little corpses in the corner that you"
						+ " estimate to be about one hundred rats tall at least. While there could be"
						+ " something useful under the pile, you can't bring yourself to go sorting through so much dead"
						+ " meat.",
						new GChoice[] {
								new GChoice("Go back", 36)
								}),
				
				// N40: Storage (Front)
				new GNode("Storage Front",
						"You find yourself in the front end of the upper storage area. It seems all of the boxes have"
						+ " disappeared from their places, leaving nothing but empty shelves. Regardless, the thick rows"
						+ " of shelves still block you off from the rest of the storage area.",
						new GChoice[] {
								new GChoice("Go back to the throat", 10),
								new GChoice("Inspect the shelves", 41) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().hasItem(GItem.EYE4);
									}
								},
								new GChoice("Look past the shelves", 42)
								}),
				
				// N41: Storage (Front / Inspect)
				new GNode("A Metal Eye",
						"You find some old bits and pieces of metal on some of the shelves. It's mostly unimportant junk"
						+ " like rusted screws and snipped padlocks, but you finally discover a particularly round and"
						+ " weighty piece of metal on one of the last shelves you check. On a closer look, the spherical"
						+ " shape has a engraving of an iris and pupil on one side. You decide to pocket the weird metal eye.",
						new GChoice[] {
								new GChoice("Continue", 40) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().monsterRange += -25;
										GFrameAlt.getInstance().getTracker().monsterTime += -200;
									}
								}
								},
						new GItem[] { GItem.EYE4 }, GNode.ADDED),
				
				// N42: Storage (Front / Look)
				new GNode("Back Entrance",
						"You angle your sight to see as far as you can past the shelves. You're unsurprised to find a hole"
						+ " in the ceiling on the other side of the room. Looks like that's your entrance point.",
						new GChoice[] {
								new GChoice("Go back", 40)
								}),
				
				// N43: Storage (Back)
				new GNode("Storage: Back Area",
						"You've dropped into the back area of storage. The room's contents have been completely emptied"
						+ " out, leaving only vacant shelves behind. There's not too much to see here.",
						new GChoice[] {
								new GChoice("Look back at where you dropped from", 44),
								new GChoice("Inspect the shelves", 45) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().hasItem(GItem.EYE5);
									}
								},
								new GChoice("Look past the shelves", 46)
								}),
				
				// N44: Storage (Back / Tunnel)
				new GNode("Ascension",
						"You look upwards at where you recently descended from. As you begin to look for a way back, you"
						+ " see a long tongue-like appendage begin to descend from the ceiling towards you. Before you can"
						+ " move, it wraps around your forearm and yanks you upward towards the tunnel you fell from! Midway"
						+ " through, it looses its hold on you, and the remaining momentum flings you the rest of the way"
						+ " up to the cafeteria. Dizzied, you reorient yourself to your surrounding.",
						new GChoice[] {
								new GChoice("Continue", 32)
								}),
				
				// N45: Storage (Back / Inspect)
				new GNode("A Wooden Eye",
						"You find some old bits and pieces of wood on some of the shelves in the back. It's mostly"
						+ " leftover shards of broken boxes, but you finally discover a particularly round and"
						+ " smooth sample of wood on one of the last shelves you check. On a closer look, the spherical"
						+ " piece has a carving of an iris and pupil on one side. You decide to pocket the wooden eye.",
						new GChoice[] {
								new GChoice("Continue", 43) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().monsterRange += -25;
										GFrameAlt.getInstance().getTracker().monsterTime += -200;
									}
								}
								},
						new GItem[] { GItem.EYE5 }, GNode.ADDED),
				
				// N46: Storage (Back / Look)
				new GNode("Quick Glance",
						"You angle your sight to see as far as you can past the shelves. In the front end of the room,"
						+ " you spot an entrance to the throat that you traversed before.",
						new GChoice[] {
								new GChoice("Go back", 43)
								}),
				
				// N47: Deep Storage (Front)
				new GNode("A Pulsing Rhythm",
						"You're in the front end of what used to be Deep Storage. The room's walls are smooth, but bear"
						+ " a strange organic warmth to them. A faint glow eminates from the back end of the room, and its"
						+ " the only thing providing light here, so you tread around carefully. From your position, you can"
						+ " spot a number of shelves with open boxes that block your access to the rest of the room.",
						new GChoice[] {
								new GChoice("Go back to the throat", 11),
								new GChoice("Examine the shelves", 48) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().shelvesInspected = true;
									}
								},
								new GChoice("Scan the floor", 49) {
									@Override
									public boolean shouldDisplay() {
										return (GFrameAlt.getInstance().getTracker().shelvesInspected &&
												(!GFrameAlt.getInstance().hasItem(GItem.EYE6)));
									}
								}
								}),
				
				// N48: Deep Storage (Front / Shelves)
				new GNode("Raided Boxes",
						"With what you're able to see, the boxes contents have been looted and tossed to the side"
						+ " haphazardly. Someone must have been looking for something of importance. It disturbs you"
						+ " that you're not sure who that someone might be.",
						new GChoice[] {
								new GChoice("Go back", 47)
								}),
				
				// N49: Deep Storage (Front / Floor)
				new GNode("A Resin Eye",
						"Using the faint light from the other end of the room, you squint your eyes and scan the floor"
						+ " for something that might have fallen out of one of the boxes. There's a lot of random clutter,"
						+ " including rocks of various sizes. Your eyes lock on one round shape in the mess, and you scoop"
						+ " it up into your hands. The object is somewhat chalky, smells like pine, and bears a pattern that"
						+ " resembles an eyeball. You decide to keep it in your pocket.",
						new GChoice[] {
								new GChoice("Continue", 47) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().monsterRange += -25;
										GFrameAlt.getInstance().getTracker().monsterTime += -200;
									}
								}
								},
						new GItem[] { GItem.EYE6 }, GNode.ADDED),
				
				// N50: Deep Storage (Back)
				new GNode("The Heart",
						"At the bottom of the pit you just fell into sits a gargantuan, beating heart. Its arteries"
						+ " bleed into the surrounding walls and ceiling, and it radiates a warm glow which"
						+ " illuminates the space you are in. There's a wall of shelves on one end of the room, but the"
						+ " rest of the room is barren, as if you're in the center of a cavity.",
						new GChoice[] {
								new GChoice("Inspect shelves", 51),
								new GChoice("Approach the heart", 52) {
									@Override
									public boolean shouldDisplay() {
										return !GFrameAlt.getInstance().hasItem(GItem.EYE7);
									}
								},
								new GChoice("Scan the ceiling", 53)
								}),
				
				// N51: Deep Storage (Back / Shelves)
				new GNode("Tipped Shelves",
						"There are dozens of shelves piled up and shoved together to form a wall of sorts on this end of"
						+ " the room. They're tilted on each other in such a way that you can easily climb up the sides"
						+ " and inspect the tops. While you can't find anything important, you do notice that you can"
						+ " easily leap down to the other section of the room from here. If you do so, you don't think"
						+ " you could climb back up again.",
						new GChoice[] {
								new GChoice("Go back", 50),
								new GChoice("Leap down", 47)
								}),
				
				// N52: Deep Storage (Back / Heart)
				new GNode("A Membranous Eye",
						"Looking closely at the heart, its exterior seems to be composed of a translucent organic material."
						+ " You can see particles of cosmic light floating around inside, but something else catches your"
						+ " attention. You can see what looks like an eyeball, but veiny and with a distinctly blue iris,"
						+ " hovering just beneath the surface. Without a thought, you plunge your hand into the heart and grab it for"
						+ " yourself, pocketing it. When you look back at the heart, there's eerily no mark on the surface"
						+ " where you reached in.",
						new GChoice[] {
								new GChoice("Continue", 50) {
									@Override
									public void activate() {
										GFrameAlt.getInstance().getTracker().monsterRange += -25;
										GFrameAlt.getInstance().getTracker().monsterTime += -200;
									}
								}
								},
						new GItem[] { GItem.EYE7 }, GNode.ADDED),
				
				// N53: Deep Storage (Back / Ceiling)
				new GNode("Porous Ceiling",
						"You glance upwards at the ceiling of the room. There are a number of dark looking holes here,"
						+ " including the particularly large one that you fell from. Thinking about the situation, you're"
						+ " not sure how you landed unharmed from your fall considering you can't even see the top of the"
						+ " pit you tumbled from. Then again, things don't seem to be obeying regular logic here.",
						new GChoice[] {
								new GChoice("Back", 50)
								}),
				
				// N54: Sturdy Iron Door to Outside (Open)
				new GNode("Unlocked...",
						"You place the eyes you collected in the seven divots in the door. As you place the last one, the"
						+ " door begins to creak, and then the eyes in their divots begin to spin around the center. After"
						+ " a full revolution the spinning stops, and the entrance slowly opens... ",
						new GChoice[] {
								new GChoice("Step outside", 55)
								}),
				
				// N55: True Victory
				new GNode("A Beautiful Horizon: TRUE VICTORY",
						"Stepping outside, you feel a cool breeze cross over you. The lovely white sky and pink sea you glimpsed"
						+ " earlier stretch on as far as you can see and form a mesmerizing horizon. It seems your"
						+ " true purpose here was to free that thing down below. Now that the door you came through has"
						+ " closed and locked for good, it appears that the plan has failed. For all you care, that"
						+ " monster can rot in this ship for eternity. In the end, you may unfortunately be here for just as long."
						+ " You were tricked, beckoned here against your will, and remaining here atop this ship is all the"
						+ " solace that is offered to you now. You hope, in time, that you will find peace in that.",
						new GChoice[] {
								}),
				
				};
	}
}
