package models;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import gui.GFrame;
import helpers.SoundPlayer;

// "Map" of all the defined nodes in the game
public class GMap {
	
	// Array of all the nodes
	public GNode[] nodes;
	
	// Constructor
	public GMap() {
		
		this.nodes = new GNode[] {
				
				// N0: Starting Point 1
				new GNode("A dizzying awakening...",
						"After what seemed like your worst nightmare, you snap out of your deep sleep to find"
						+ " yourself in an unknown location. The floor and walls are metallic blue, heavily"
						+ " rusted, and cold to the touch. You stumble to your feet, making sure to avoid scraping"
						+ " your head against the low ceiling. Your vision swirls as you slowly adjust to your"
						+ " new surroundings.",
						new GChoice[] {
								new GChoice("Look around", 1)
								}),
				
				
				// N1: Starting Point 2
				new GNode("Some sort of ship..?",
						"You look all around you to find yourself in a control room of sorts. Various dials and"
						+ " monitors exist on a giant panel to your immediate left. In front of you is what undeniably"
						+ " looks like a steering wheel? This must be a vessel or ship, but why aren't you moving, and"
						+ " where is everyone? It's time to start getting some answers for yourself.",
						new GChoice[] {
								new GChoice("Time to explore", 2)
								}),
				
				// N2: Lower Deck (Main)
				new GNode("The Lower Deck",
						"You find yourself on what could only be the ship's lower deck. To your left is a control panel"
						+ " and in front of you is a steering wheel. Also in your general vicinity are some small circular"
						+ " windows looking outwards and a door leading to the staircase.",
						new GChoice[] {
								new GChoice("Check the control panel", 3) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().panelPowered);
									}
								},
								new GChoice("Operate the control panel", 64) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().panelPowered);
									}
								},
								new GChoice("Check out the steering wheel", 4),
								new GChoice("Look out the windows", 8) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().windowsCleared);
									}
								},
								new GChoice("Look out the windows", 42) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().windowsCleared);
									}
								},
								new GChoice("Go to the staircase", 9)
								}),
				
				// N3: Lower Deck (Panel / Dead)
				new GNode("Dead Control Panel",
						"The worn control panel is definitely the most compelling visual element in the lower deck."
						+ " Despite its chipped white paint and eroded edges, it has an odd sort of magnificence"
						+ " in its presence and design. The surface of the panel is crowded with all sorts of"
						+ " interesting buttons, dials, and gauges, but sadly none of it is powered"
						+ " up. There's a thin slot on the left side. Maybe there's some way to get the power"
						+ " going again for this thing..?",
						new GChoice[] {
								new GChoice("Go back", 2),
								new ItemChoice("Use White Keycard", 61, GItem.WHITE_KEY, ItemChoice.NEEDED) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().panelPowered = true;
									}
								},
								new ItemChoice("Use Black Keycard", 60, GItem.BLACK_KEY, ItemChoice.NEEDED)
								}),
				
				// N4: Lower Deck (Wheel)
				new GNode("The Steering Wheel",
						"A steering wheel stands in the middle of the room. It's made of a dull looking wood that"
						+ " has grown soggy and rotted with age. After a quick test, the wheel surprisingly still"
						+ " turns despite its condition. What should you do?",
						new GChoice[] {
								new GChoice("Go back", 2),
								new GChoice("Turn the wheel left", 5) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().wheelPos = -1;
										
										if(GFrame.getInstance().getTracker().planksTaken &&
												(!GFrame.getInstance().getTracker().fridgeTipped)) {
											SoundPlayer.playWAV("slam.wav", -20);
											GFrame.getInstance().getTracker().fridgeTipped = true;
										}
									}
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos != -1);
									}
								},
								new GChoice("Straighten the wheel", 6) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().wheelPos = 0;
										
										if(GFrame.getInstance().getTracker().planksTaken &&
												(!GFrame.getInstance().getTracker().fridgeTipped)) {
											SoundPlayer.playWAV("slam.wav", -20);
											GFrame.getInstance().getTracker().fridgeTipped = true;
										}
									}
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos != 0);
									}
								},
								new GChoice("Turn the wheel right", 7) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().wheelPos = 1;
										
										if(GFrame.getInstance().getTracker().planksTaken &&
												(!GFrame.getInstance().getTracker().fridgeTipped)) {
											SoundPlayer.playWAV("slam.wav", -20);
											GFrame.getInstance().getTracker().fridgeTipped = true;
										}
									}
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos != 1);
									}
								}
								}),
				
				// N5: Lower Deck (Turn Wheel Left)
				new GNode("Turning it left",
						"You rotate the wheel to its leftmost position. The ship rumbles loudly, but doesn't"
						+ " appear to move otherwise...",
						new GChoice[] {
								new GChoice("Back to the deck", 2),
								new GChoice("Keep examining the wheel", 4)
								}),
				
				// N6: Lower Deck (Straighten Wheel)
				new GNode("Straightening things up",
						"You rotate the wheel to straighten it as best you can. The ship rumbles loudly, but"
						+ " remains motionless.",
						new GChoice[] {
								new GChoice("Back to the deck", 2),
								new GChoice("Keep examining the wheel", 4)
								}),
				
				// N7: Lower Deck (Turn Wheel Right)
				new GNode("Turning it right",
						"You rotate the wheel to its rightmost position. The ship rumbles loudly, but doesn't"
						+ " appear to move otherwise...",
						new GChoice[] {
								new GChoice("Back to the deck", 2),
								new GChoice("Keep examining the wheel", 4)
								}),
				
				// N8: Lower Deck (Windows / Painted)
				new GNode("Paint-smeared Windows",
						"The windows, at a closer glance, appear to be completely coated in a thick waxy substance."
						+ " Touching the hardened goop, the texture reminds you greatly of that of oil-based primer paint."
						+ " Who would paint over the windows of the ship? Of course, this means you can't look outside"
						+ " at all.",
						new GChoice[] {
								new GChoice("Go back", 2),
								new ItemChoice("Use the mustard on the paint", 40, GItem.MUSTARD, ItemChoice.NEEDED) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().windowsCleared = true;
									} 
								}
								}),
				
				// N9: Staircase (Upper)
				new GNode("Upper Flight",
						"You find yourself towards the top of a dark, creaky set of hollow metal stairs."
						+ " Rickety steps lead both upwards and downwards. Which way will you go?",
						new GChoice[] {
								new GChoice("Step out of the staircase", 2),
								new GChoice("Go upwards", 10),
								new GChoice("Go downwards", 12)
								}),
				
				// N10: Staircase (Top / Blocked)
				new GNode("Top Flight",
						"You are at the topmost reach of the staircase. Ahead of you is a sturdy iron door with"
						+ " a pressure wheel in the center.",
						new GChoice[] {
								new GChoice("Try the door", 11),
								new GChoice("Go downwards", 9),
								}) {
					@Override
					public Color displayColor() {
						return new Color(255, 200, 200);
					}
				},
				
				// N11: Sturdy Iron Door to Outside (Blocked)
				new GNode("Iron Door",
						"Trying with all your might, you can't even manage to budge the opening wheel one inch. The"
						+ " thing is truly stuck in place. There HAS to be something through this door, but how would"
						+ " you ever go about opening it?",
						new GChoice[] {
								new GChoice("Go back", 10),
								}) {
					@Override
					public Color displayColor() {
						return new Color(255, 200, 200);
					}
				},
				
				// N12: Staircase (Middle)
				new GNode("Middle Flight",
						"You find yourself in the middle of a dark, creaky set of hollow metal stairs. Rickety steps"
						+ " lead both upwards and downwards. Which way will you go?",
						new GChoice[] {
								new GChoice("Step out of the staircase", 16) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 0);
									}
								},
								new GChoice("Step out of the staircase", 21) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == -1);
									}
								},
								new GChoice("Step out of the staircase", 29) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 1);
									}
								},
								new GChoice("Go upwards", 9),
								new GChoice("Go downwards", 13)
								}),
				
				// N13: Staircase (Lower)
				new GNode("Lower Flight",
						"You find yourself towards the bottom of a dark, creaky set of hollow metal stairs. Rickety steps"
						+ " lead both upwards and downwards. Which way will you go?",
						new GChoice[] {
								new GChoice("Step out of the staircase", 49) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().fishAttacked);
									}
								},
								new GChoice("Step out of the staircase", 53) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().fishAttacked);
									}
								},
								new GChoice("Go upwards", 12),
								new GChoice("Go downwards", 14)
								}),
				
				// N14: Staircase (Bottom)
				new GNode("Bottom Flight",
						"You are at the bottom of the dark staircase. You can hardly see anything in the cramped"
						+ " space around you, but you can feel out a metal door and the steps leading upwards.",
						new GChoice[] {
								new GChoice("Check the door", 15) {
									@Override
									public boolean shouldDisplay() {
										return !(GFrame.getInstance().hasItem(GItem.BATTERY) &&
												GFrame.getInstance().hasItem(GItem.FLASHLIGHT));
									}
								},
								new ItemChoice("Light the area", 59, new GItem[] { GItem.BATTERY, GItem.FLASHLIGHT },
										ItemChoice.NEEDED),
								new GChoice("Go up the stairs", 13)
								}),
				
				
				// N15: Deep Storage (Door / Dark)
				new GNode("Locked",
						"You feel for a handle of some sort in the darkness, but this gets you nowhere fast. You"
						+ " can make out the door's hinges, but there's simply no easy means of getting this door"
						+ " open currently.",
						new GChoice[] {
								new GChoice("Go back", 14)
								}),
				
				// N16: Cabins (Main)
				new GNode("Ship Cabins",
						"You are now located in the tightest quarters you've seen yet. There are innumerable rows"
						+ " of bunkbeds that are squeezed so close together you have to rotate your body to get by."
						+ " Besides the beds, there is a odd looking chest in the back left corner of the room and"
						+ " a surprisingly barren spot in the back right corner.",
						new GChoice[] {
								new GChoice("Go to the stairs", 12) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 0);
									}
								},
								new GChoice("Inspect the chest", 17) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().chestOpened);
									}
								},
								new GChoice("Check out the barren corner", 20) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == -0);
									}
								},
								new GChoice("Check out the barren corner", 48) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos != 0);
									}
								},
								new GChoice("Go to Medical Wing through tunnel", 21) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().discoveredTunnel);
									}
								}
								}) {
					@Override
					public Color displayColor() {
						return new Color(200, 200, 255);
					}
				},
				
				// N17: Cabins (Chest)
				new GNode("Puzzle Chest",
						"The chest is rather standard in design, except for a scrawling on its top side and a"
						+ " brilliantly jeweled lock adorning its front. The scrawling is a set of four pictures"
						+ " in a row. Left to right, they are a bed, a syringe, a door with a wheel, and a fridge."
						+ " There are three main gems adorning the lock resembling buttons: a ruby, a sapphire,"
						+ " and an emerald. With the locking mechanism being as expensive as it is, you only"
						+ " wonder what could be contained within the chest itself.",
						new GChoice[] {
								new GChoice("Go back", 16) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().resetChestCombo();
									}
								},
								new GChoice("Press the ruby", 17) {
									@Override
									public void activate() {
										EventTracker tracker = GFrame.getInstance().getTracker();
										tracker.chestCombo[tracker.chestIndex] = 1;
										
										this.actionNode = 17;
										
										if(tracker.chestIndex == 3) {
											if(tracker.checkChestCombo()) {
												this.actionNode = 19;
												tracker.chestOpened = true;
											} else {
												SoundPlayer.playWAV("button_press.wav");
												
												this.actionNode = 18;
												tracker.resetChestCombo();
											}
										} else {
											SoundPlayer.playWAV("button_press.wav");
											
											tracker.chestIndex += 1;
										}
									}
								},
								new GChoice("Press the sapphire", 17) {
									@Override
									public void activate() {
										EventTracker tracker = GFrame.getInstance().getTracker();
										tracker.chestCombo[tracker.chestIndex] = 2;
										
										this.actionNode = 17;
										
										if(tracker.chestIndex == 3) {
											if(tracker.checkChestCombo()) {
												this.actionNode = 19;
												tracker.chestOpened = true;
											} else {
												SoundPlayer.playWAV("button_press.wav");
												this.actionNode = 18;
												tracker.resetChestCombo();
											}
										} else {
											SoundPlayer.playWAV("button_press.wav");
											tracker.chestIndex += 1;
										}
									}
								},
								new GChoice("Press the emerald", 17) {
									@Override
									public void activate() {
										EventTracker tracker = GFrame.getInstance().getTracker();
										tracker.chestCombo[tracker.chestIndex] = 3;
										
										this.actionNode = 17;
										
										if(tracker.chestIndex == 3) {
											if(tracker.checkChestCombo()) {
												this.actionNode = 19;
												tracker.chestOpened = true;
											} else {
												SoundPlayer.playWAV("button_press.wav");
												this.actionNode = 18;
												tracker.resetChestCombo();
											}
										} else {
											SoundPlayer.playWAV("button_press.wav");
											tracker.chestIndex += 1;
										}
									}
								}
								}),
				
				// N18: Cabins (Chest / Fail Combo)
				new GNode("Not Right",
						"That combination didn't seem to work properly. You might need to do some more"
						+ " investigating first.",
						new GChoice[] {
								new GChoice("Back out", 16),
								new GChoice("Try again", 17)
								}),
				
				// N19: Cabins (Chest / Correct Combo)
				new GNode("Opened!",
						"It worked! The lock slides off and you open the chest to find a white"
						+ " keycard. Wonder what this opens?",
						new GChoice[] {
								new GChoice("Nice!", 16)
								},
						new GItem[] { GItem.WHITE_KEY },
						GNode.ADDED),
				
				// N20: Cabins (Barren Corner)
				new GNode("Barren Corner",
						"There's nothing at all in this corner of the cabins, which is strange considering"
						+ " the massive amount of clutter on the other side of the room. There's a circular"
						+ " shaped indent in the floor, but that's the only thing remotely notable.",
						new GChoice[] {
								new GChoice("Go back", 16)
								}),
				
				// N21: Med Bay (Main)
				new GNode("Medical Wing",
						"You find yourself in a pasty white room with cracked tiles covering the floor. There"
						+ " are multiple make-shift beds as well as cabinets containing various sized bottles and"
						+ " boxes. This must have been the medical bay for the ship. There's a large door in the"
						+ " back right corner of the room that looks like it leads to an office. Near the office"
						+ " are bookshelves littered with dusty volumes and encyclopedias.",
						new GChoice[] {
								new GChoice("Go to the stairs", 12) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == -1);
									}
								},
								new GChoice("Search the cabinets", 23),
								new GChoice("Go to the large door", 22) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().officeOpened);
									}
								},
								new GChoice("Go into the office", 56) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().officeOpened);
									}
								},
								new GChoice("Examine the bookcases", 24) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().carvedTunnel);
									}
								},
								new GChoice("Go to the bookcases", 27) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().carvedTunnel);
									}
								}
								}),
				
				// N22: Med Bay (Locked Door)
				new GNode("Locked Office",
						"You're in the corner of the medical wing near a large sturdy wooden door. Compared"
						+ " to the rest of the setting around you, it looks remarkably new and polished."
						+ " Unfortunately, it's also locked and won't budge one bit.",
						new GChoice[] {
								new GChoice("Back to center of the room", 21)
								}),
				
				// N23: Med Bay (Cabinets)
				new GNode("Medical Cabinets",
						"The cabinets are absolutely trashed. The glass on the displays is mostly broken, and"
						+ " various supplies are strewn out along the shelves. You carefully search the"
						+ " entirety of the cabinets, taking care to avoid the glass shards and syringes"
						+ " carelessly lying about. You come back with nothing in the end.",
						new GChoice[] {
								new GChoice("Back to center of the room", 21)
								}) {
					@Override
					public Color displayColor() {
						return new Color(200, 200, 255);
					}
				},
				
				// N24: Med Bay (Bookcases / Intact)
				new GNode("Flimsy Bookcases",
						"The bookcases are heavily rotted and can barely support the weight of the books on"
						+ " them. Most of the books are illegibly stained with water, but you spot a"
						+ " fair-conditioned chemistry book and an unlabeled leather-bound journal.",
						new GChoice[] {
								new GChoice("Leave the bookcase", 21),
								new GChoice("Read the chemistry book", 25),
								new GChoice("Read the journal", 26),
								new ItemChoice("Cleave at the bookcase", 46, new GItem[] {
										GItem.KNIFE
								}, ItemChoice.NEEDED) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().carvedTunnel = true;
									}
									
									@Override
									public boolean shouldDisplay() {
										boolean result = super.shouldDisplay();
										return (result && (!GFrame.getInstance().getTracker().carvedTunnel));
									}
								}
								}),
				
				// N25: Med Bay (Bookcases / Chemistry)
				new GNode("Chemistry Book",
						"Most of this book is a bit too complex for you, but there is a nice section with some easy"
						+ " household chemistry tips. 'Vinegar is a common ingredient in homes that can be used"
						+ " to substitute main-brand cleaners! Works well on stains and helps break down messes.'"
						+ " At least when you're out of here, you can try to get that lingering wine spill out"
						+ " of your old carpet.",
						new GChoice[] {
								new GChoice("Leave the bookcase", 21),
								new GChoice("Back to inspecting bookcase", 24) {
									@Override
									public void activate() {
										if(GFrame.getInstance().getTracker().carvedTunnel) {
											this.actionNode = 27;
										} else {
											this.actionNode = 24;
										}
									}
								}
								}),
				
				// N26: Med Bay (Bookcases / Chemistry)
				new GNode("Leather-bound Journal",
						"'We pulled up the artifact today. I got a small glimpse of it as it was being carted off"
						+ " to Deep Storage. Really ordinary looking statue, but it did make me feel quite uneasy"
						+ " in a strange way I've not experienced before. This job is paying me quite handsomely,"
						+ " and I'm kind of blown away that this was our goal all along. What are we doing, and"
						+ " why am I really here?' The writing gets progressively harder to read, and you can't"
						+ " make out too much more of the text.",
						new GChoice[] {
								new GChoice("Leave the bookcase", 21),
								new GChoice("Back to inspecting bookcase", 24) {
									@Override
									public void activate() {
										if(GFrame.getInstance().getTracker().carvedTunnel) {
											this.actionNode = 27;
										} else {
											this.actionNode = 24;
										}
									}
								}
								}),
				
				// N27: Med Bay (Bookcases / Broken)
				new GNode("Broken Bookcases",
						"The bookcases are shattered and a thin tunnel is revealed behind the wreckage. The"
						+ " chemistry book and leather-bound journal you saw on the shelves are earlier now"
						+ " lay at the floor near your feet.",
						new GChoice[] {
								new GChoice("Leave the bookcase", 21),
								new GChoice("Read the chemistry book", 25),
								new GChoice("Read the journal", 26),
								new GChoice("Crawl through the tunnel", 28) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().discoveredTunnel = true;
									}
									
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().discoveredTunnel);
									}
								},
								new GChoice("Crawl through tunnel to cabins", 16) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().discoveredTunnel);
									}
								}
								}),
				
				// N28: Med Bay (Discover Tunnel)
				new GNode("A Discovery..?",
						"You crawl through the tunnel, roughing your way through the splintered wood and metal"
						+ " lining it. After a short distance you find yourself entering another room, but you"
						+ " still can't lift your head as you appear to be under some furniture. You force"
						+ " yourself through the mess, and end up emerging out from under a bed into a small"
						+ " clearing. This appears to be the cabins.",
						new GChoice[] {
								new GChoice("Continue", 16)
								}),
				
				// N29: Cafeteria (Main)
				new GNode("Cafeteria",
						"You find yourself in an empty chamber filled with rows of hollow aluminum tables. The"
						+ " room echoes with an unnerving silence and an uncomfortable stillness. You don't want"
						+ " to stay here long. There's a long counter where food was most likely served, and a"
						+ " kitchen area in back of that.",
						new GChoice[] {
								new GChoice("Go to the stairs", 12) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 1);
									}
								},
								new GChoice("Check the tables", 30) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos != 0);
									}
								},
								new GChoice("Check the tables", 31) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 0);
									}
								},
								new ItemChoice("Inspect the food counter", 32, GItem.MUSTARD, ItemChoice.BARRING),
								new GChoice("Go to the kitchen", 33) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().fridgeTipped);
									}
								},
								new GChoice("Go to the kitchen", 35) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().fridgeTipped);
									}
								}
								}),
				
				// N30: Cafeteria (Tables / No Hole)
				new GNode("Cafeteria Tables",
						"Inspecting the tables, there's nothing particularly special to pick up or notice for"
						+ " the most part. The one exception to this is the weird circular indent in the floor"
						+ " you found under a lonely table in the back-right corner of the room.",
						new GChoice[] {
								new GChoice("Go back", 29)
								}),
				
				// N31: Cafeteria (Tables / Hole)
				new GNode("Tables and Large Hole",
						"Inspecting the tables, there's nothing particularly special to pick up or notice for"
						+ " the most part. However, there's a large hole in the floor under a lonely table in"
						+ " the back-right corner of the room.",
						new GChoice[] {
								new GChoice("Go back", 29),
								new GChoice("Descend down the hole", 47)
								}),
				
				// N32: Cafeteria (Counter)
				new GNode("Food Counter",
						"The counters are nearly devoid of anything edible, with the exception of some remaining"
						+ " condiments. It's mostly just packets of mustard. Considering the lack of anything else"
						+ " to eat, you grab as many of the packets as you can carry.",
						new GChoice[] {
								new GChoice("Go back", 29)
								},
						new GItem[] { GItem.MUSTARD }, GNode.ADDED),
				
				// N33: Cafeteria (Kitchen / Normal)
				new GNode("The Kitchen",
						"You're in the ship's kitchen now. The counters and work stations are all completely"
						+ " cleaned out. A handful of standard-looking lamps swing idly above your head on the"
						+ " ceiling. There's also a heavy looking fridge in the darkest corner of the room.",
						new GChoice[] {
								new GChoice("Go back", 29),
								new GChoice("Check the fridge", 34) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().planksTaken);
									}
								},
								new GChoice("Check the fridge", 83) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().planksTaken);
									}
								}
								}) {
					@Override
					public Color displayColor() {
						return new Color(200, 255, 200);
					}
				},
				
				// N34: Cafeteria (Kitchen / Locked Fridge / Planked)
				new GNode("Heavy Fridge",
						"The heavy metal fridge is leaning acutely from the wall, ready to tip. Regardless, the"
						+ " thing is still so enormous that you can't get it to budge. The doors of the otherwise"
						+ " spotless appliance are coated in a thick layer of rust, making opening them normally"
						+ " impossible as well. There are a few planks of wood set under the fridge.",
						new GChoice[] {
								new GChoice("Go back", 33),
								new GChoice("Carefully take planks", 82) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().planksTaken = true;
									}
								}
								}) {
					@Override
					public Color displayColor() {
						return new Color(200, 255, 200);
					}
				},
				
				// N35: Cafeteria (Kitchen / Bumped)
				new GNode("The Kitchen",
						"You're in the ship's kitchen now. The counters and work stations are all completely"
						+ " cleaned out. A handful of standard-looking lamps swing above your head on the"
						+ " ceiling. A heavy looking fridge is lying tipped over on the floor in the corner.",
						new GChoice[] {
								new GChoice("Go back", 29),
								new ItemChoice("Check the tipped fridge", 36, GItem.KNIFE, ItemChoice.BARRING),
								new GChoice("Check the tipped fridge", 39) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().bodyFound);
									}
								}
								}) {
					@Override
					public Color displayColor() {
						return new Color(200, 255, 200);
					}
				},
				
				// N36: Cafeteria (Kitchen / Tipped Fridge)
				new GNode("Tipped Fridge",
						"The heavy metal fridge is lodged partially into the floor from its gargantuan weight."
						+ " It's rusted doors are slightly ajar. Peeling back the doors of the fridge reveals"
						+ " its contents. Is that..." + '\n' + "        ...a body?",
						new GChoice[] {
								new GChoice("Go back", 35),
								new GChoice("Inspect the body...", 37) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().bodyFound = true;
										//GFrame.getInstance().createPopup(200, "popup.png");
									}
								}
								}),
				
				// N37: Cafeteria (Kitchen / Body Scare)
				new GNode("...",
						"Its face is gone...",
						new GChoice[] {
								new GChoice("Continue", 38)
								}),
				
				// N38: Cafeteria (Kitchen / Body Find Knife)
				new GNode("...",
						"There's a bloody kitchen knife in its hands. You pry it from the body and wipe it clean"
						+ " on its clothes. Despite how wrong this is, you can't help but feel it'll be necessary"
						+ " to have this knife with you.",
						new GChoice[] {
								new GChoice("Go back", 35)
								},
						new GItem[] { GItem.KNIFE }, GNode.ADDED),
				
				// N39: Cafeteria (Kitchen / Body Nope)
				new GNode("",
						"I'm not going back over there.",
						new GChoice[] {
								new GChoice("Continue", 35)
								}),
				
				// N40: Lower Deck (Clear Windows)
				new GNode("Vinegar Power",
						"As goofy as it is, you start rubbing lots of mustard on the layer of oil paint covering"
						+ " one of the windows. While it doesn't do much, you eventually wear away a small portion"
						+ " of the primer which allows you to start peeling it off in mass. With the window clear"
						+ " you finally take a good look outside...",
						new GChoice[] {
								new GChoice("Continue", 41)
								}),
				
				// N41: Lower Deck (Water Surprise)
				new GNode("Water?!",
						"There's nothing but water outside as far as you can see. This would be normal for a ship,"
						+ " but in this case the water is above the windows (by a couple fathoms at least). While this"
						+ " is certainly stressful to behold, you can't help but be thankful that some sort of"
						+ " miracle has prevented any water from flooding the inner parts of the ship.",
						new GChoice[] {
								new GChoice("Continue", 42)
								}),
				
				// N42: Lower Deck (Windows / Cleared)
				new GNode("An Open View of the Sea",
						"The underseas view out the windows is terrifying, yet beautiful all the same. Barnicle-laden"
						+ " ropes hang outside in your immediate vision, reminding you of the watery grave in which"
						+ " this ship has found itself. You catch sight of a large school of fish swimming on by.",
						new GChoice[] {
								new GChoice("Go back", 2),
								new GChoice("Greet the fish", 43) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().fishAggro += 1;
									}
									
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().fishAggro == 0);
									}
								},
								new GChoice("Greet the fish", 44) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().fishAggro += 1;
									}
									
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().fishAggro == 1);
									}
								},
								new GChoice("Greet the fish", 45) {
									@Override
									public void activate() {
										SoundPlayer.playWAV("slam.wav");
										GFrame.getInstance().getTracker().fishAggro += 1;
										GFrame.getInstance().getTracker().fishAttacked = true;
										GFrame.getInstance().getTracker().fridgeTipped = true;
									}
									
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().fishAggro == 2);
									}
								}
								}),
				
				// N43: Lower Deck (Windows / Fish Greet 1)
				new GNode("Fishy",
						"The fish catch sight of you and turn tail in the other direction. Seems they'd rather be"
						+ " left alone.",
						new GChoice[] {
								new GChoice("Continue", 42)
								}),
				
				// N44: Lower Deck (Windows / Fish Greet 2)
				new GNode("Fishy...",
						"The fish catch sight of you and toss an angry look. Fish are really unfriendly.",
						new GChoice[] {
								new GChoice("Continue", 42)
								}),
				
				// N45: Lower Deck (Windows / Fish Greet 2)
				new GNode("Fishy!",
						"The fish catch sight of you and swim off into the distance quickly. Moments later,"
						+ " a giant swarm darts right toward the ship, slamming it with heavy force. You hear lots"
						+ " of shifting down below you, but the ship itself is at least still rooted properly. It's"
						+ " best that you don't bother the fish again.",
						new GChoice[] {
								new GChoice("Continue with caution", 42)
								}),
				
				// N46: Medical Bay (Break Bookcase)
				new GNode("Smashing",
						"You cleave straight through the bookcase with surprising ease. The wood splinters and"
						+ " flies into the air around you. On the other side of the wreckage is a tunnel...",
						new GChoice[] {
								new GChoice("Continue", 27)
								}),
				
				// N47: Tunnel (Downwards)
				new GNode("Take the Plunge",
						"You carefully plunge down the hole into the floor. Landing on your feet, you begin to"
						+ " observe your new surroundings.",
						new GChoice[] {
								new GChoice("Continue", 51) {
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().fishAttacked);
									}
								},
								new GChoice("Continue", 53) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().fishAttacked);
									}
								}
								}),
				
				// N48: Cabins (Barren Corner / Hole Open)
				new GNode("Barren Corner?",
						"There's mostly nothing at all in this corner of the cabins, which is strange considering"
						+ " the massive amount of clutter on the other side of the room. However, there is a"
						+ " circular hole in the floor leading down one floor.",
						new GChoice[] {
								new GChoice("Go back", 16),
								new GChoice("Climb down the hole", 47) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == -1);
									}
								},
								new GChoice("Climb down the hole", 70) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 1 &&
												!(GFrame.getInstance().getTracker().lockOneOpen &&
												GFrame.getInstance().getTracker().lockTwoOpen));
									}
								},
								new GChoice("Climb down the hole", 71) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 1 &&
												(GFrame.getInstance().getTracker().lockOneOpen &&
												GFrame.getInstance().getTracker().lockTwoOpen));
									}
								}
								}),
				
				// N49: Storage (Normal / Front)
				new GNode("Storage",
						"This room must be the storage room, although you can't see past the front few rows of"
						+ " boxes that block your access to the rest of the room.",
						new GChoice[] {
								new GChoice("Back to the stairs", 13),
								new ItemChoice("Search the nearby boxes", 50, GItem.FLASHLIGHT, ItemChoice.BARRING)
								}),
				
				// N50: Storage (Normal / Front)
				new GNode("Let there be (Flash)light!",
						"Most of the boxes are filled with eroded rocks, but you finally discover a flashlight"
						+ " perched on top of a smaller box to your left. Unfortunately, it has no battery in it.",
						new GChoice[] {
								new GChoice("Go back", 49)
								},
						new GItem[] { GItem.FLASHLIGHT }, GNode.ADDED),
				
				// N51: Storage (Normal / Back)
				new GNode("Storage Back",
						"This room must be the storage room. You find yourself surrounded by rows and rows of"
						+ " boxes of various sizes. You can faintly see an exit door to the room, but it is blocked"
						+ " by a large group of, you guessed it, more boxes. There's a hole in the ceiling that you"
						+ " just came from and can climb back through. Otherwise, there's plenty to explore.",
						new GChoice[] {
								new GChoice("Climb up the hole in the ceiling", 16) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == -1);
									}
								},
								new GChoice("Climb up the hole in the ceiling", 29) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 0);
									}
								},
								new GChoice("Climb up the hole in the ceiling", 56) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 1);
									}
								},
								new ItemChoice("Search the room", 52, GItem.BLACK_KEY, ItemChoice.BARRING)
								}),
				
				// N52: Storage (Normal / Back Explore)
				new GNode("A skeleton..!",
						"After the body you saw earlier, the skeleton you stumble across while searching the rows"
						+ " of boxes isn't quite so scary. It is desperately clutching a black keycard to its chest."
						+ " You don't know exactly where this goes, but it looks important. After searching the rest"
						+ " of the boxes, you realize most of what is stored down here is just rocks and minerals of"
						+ " sorts. Some of the rocks have a weird language written on them, but this doesn't help you.",
						new GChoice[] {
								new GChoice("Continue", 51)
								},
						new GItem[] { GItem.BLACK_KEY }, GNode.ADDED),
				
				// N53: Storage (Bumped)
				new GNode("Toppled Storage",
						"You find yourself in the middle of the storage room. Boxes everywhere have toppled into"
						+ " a giant mess. Luckily you can climb over the wreckage to explore the room. There's a hole"
						+ " in the ceiling in the back of the room that you can probably reach as well as a doorway"
						+ " to the staircase.",
						new GChoice[] {
								new GChoice("Go to the stairs", 13),
								new GChoice("Explore the wreckage of boxes", 54),
								new GChoice("Climb up the hole in the ceiling", 55)
								}),
				
				// N54: Storage (Bumped / Explore)
				new GNode("Nothing Much",
						"Nothing much except rocks exist inside the boxes. The one thing of interest you can find"
						+ " is a bony hand poking through the mess. The poor fellow must have died long ago, and you"
						+ " can't reach him under the heavy wreckage.",
						new GChoice[] {
								new GChoice("Go back", 53)
								}),
				
				// N55: Storage (Tunnel Upwards / Bumped)
				new GNode("Up the Ceiling...",
						"You reach up at the hole in the ceiling and get a faint grip at the edge. With some upper"
						+ " body strength, you manage to pull yourself up...",
						new GChoice[] {
								new GChoice("Continue", 16) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == -1);
									}
								},
								new GChoice("Continue", 29) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 0);
									}
								},
								new GChoice("Continue", 56) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 1);
									}
								}
								}),
				
				// N56: Medical Bay (Office)
				new GNode("An Office",
						"You're now in an office with a small desk blanketed by papers. The room offers very little"
						+ " space to move around, with the exception of an open area with a circular indent in the"
						+ " floor. Also of note is a large wooden door near the front of the desk.",
						new GChoice[] {
								new GChoice("Go down the hole in the circular indent", 47) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 1);
									}
								},
								new GChoice("Go down the hole in the circular indent", 70) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 0 &&
												!(GFrame.getInstance().getTracker().lockOneOpen &&
												GFrame.getInstance().getTracker().lockTwoOpen));
									}
								},
								new GChoice("Go down the hole in the circular indent", 71) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 0 &&
												(GFrame.getInstance().getTracker().lockOneOpen &&
												GFrame.getInstance().getTracker().lockTwoOpen));
									}
								},
								new ItemChoice("Search the desk", 57, GItem.BATTERY, ItemChoice.BARRING),
								new GChoice("Open the door", 58) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().officeOpened = true;
									}
									
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().officeOpened);
									}
								},
								new GChoice("Head out into the Medical Bay", 21) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().officeOpened);
									}
								}
								}),
				
				// N57: Medical Bay (Office / Desk)
				new GNode("Desk Search",
						"Throughout your searches, you end up scanning most of the papers on the desk. Lots of"
						+ " prescriptions and issuances of antipsychotic medications and heavy painkillers. Wonder"
						+ " what was going on with this ship? Alongside the papers, you also find some batteries in"
						+ " the front compartment of the desk. You grab a few for later.",
						new GChoice[] {
								new GChoice("Go back", 56)
								},
						new GItem[] { GItem.BATTERY }, GNode.ADDED),
				
				// N58: Medical Bay (Office / Open Door)
				new GNode("Office Opened",
						"You observe the large wooden door. It has a thick bolt on it from this side that you undo."
						+ " With the lock undone, you open the door to find yourself in the Medical Bay.",
						new GChoice[] {
								new GChoice("Continue", 21)
								}),
				
				// N59: Staircase (Bottom / Lighted)
				new GNode("A Dark Door",
						"Lighting the area with your battery-powered flashlight, you can make out a pitch-black door with"
						+ " a keycard slot. You've never seen an inanimate object look so menacing before. Some"
						+ " feeling in your gut tells you that this could only lead to a terrible, forbidden place.",
						new GChoice[] {
								new ItemChoice("Use the Black Keycard", 63, GItem.BLACK_KEY, ItemChoice.NEEDED) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().dStorageOpened = true;
									}
									
									@Override
									public boolean shouldDisplay() {
										boolean itemHave = super.shouldDisplay();
										return ((!GFrame.getInstance().getTracker().dStorageOpened) && itemHave);
									}
								},
								new ItemChoice("Use the White Keycard", 62, GItem.WHITE_KEY, ItemChoice.NEEDED) {
									@Override
									public boolean shouldDisplay() {
										boolean itemHave = super.shouldDisplay();
										return ((!GFrame.getInstance().getTracker().dStorageOpened) && itemHave);
									}
								},
								new GChoice("Enter the door", 72) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().dStorageOpened);
									}
								},
								new GChoice("Back up the stairs", 13)
								}),
				
				// N60: Control Panel (Wrong Key)
				new GNode("Wrong One",
						"You put the black keycard into the slot on the side of the large control panel. While"
						+ " the card fits perfectly, nothing happens. Maybe it's the wrong key?",
						new GChoice[] {
								new GChoice("Back", 3)
								}),
				
				// N61: Control Panel (Right Key)
				new GNode("Powered Up!",
						"You put the white keycard into the slot on the side of the large control panel. The"
						+ " card fits perfectly, and moments later the machine whirrs to life with its dials"
						+ " buzzing and lights flashing!",
						new GChoice[] {
								new GChoice("Continue", 64)
								}),
				
				// N62: Deep Storage Door (Wrong Key)
				new GNode("Absolutely Nothing",
						"You put the white keycard into the slot on the dark door. After a moment, a garbled"
						+ " electronic voice sputters 'No access' in response. Must be the wrong keycard.",
						new GChoice[] {
								new GChoice("Back", 59)
								}),
				
				// N63: Deep Storage Door (Right Key)
				new GNode("Unlocked",
						"You put the black keycard into the slot on the dark door. After a moment, a garbled"
						+ " electronic voice sputters 'Access granted' in response. As if with a mind of its"
						+ " own, the door slowly swings open revealing a new room...",
						new GChoice[] {
								new GChoice("Go back", 59),
								new GChoice("Continue", 72)
								}),
				
				// N64: Powered Control Panel (Main)
				new GNode("Powered Control Panel",
						"The control panel is online and ready to operate. Most of the buttons are unlabeled,"
						+ " and you don't want to risk messing with the ship. However, there are few notably"
						+ " prominent switches and buttons that do possess labels. What should you press?",
						new GChoice[] {
								new GChoice("Go back", 2),
								new GChoice("Hit the button labeled 'RELEASE'", 65),
								new GChoice("Toggle 'Deep Storage Lock #2' to 'Off'", 68) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().lockTwoOpen = true;
									}
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().lockTwoOpen);
									}
								},
								new GChoice("Toggle 'Deep Storage Vent Lock #2' to 'On'", 68) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().lockTwoOpen = false;
									}
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().lockTwoOpen);
									}
								},
								new GChoice("Read the sensors", 69)
								}),
				
				// N65: Powered Control Panel (Pre-release)
				new GNode("Confirmation",
						"This button seems really important, and you're not sure there will be a way to reverse"
						+ " this action. Do you proceed anyways?",
						new GChoice[] {
								new GChoice("Go back", 64),
								new GChoice("Press the button", 66)
								}),
				
				// N66: Powered Control Panel (Release Pressed)
				new GNode("Release!",
						"You press the release button and hear a series of small explosions below you. Before you"
						+ " can react, you hear the sound of multiple inflating balloons, and then the whole ship"
						+ " starts moving up! It's not long before you hear a large splash, and then the roof of"
						+ " the lower deck starts to open...",
						new GChoice[] {
								new GChoice("Continue", 67)
								}),
				
				// N67: Regular Victory
				new GNode("VICTORY!",
						"The roof fully opens to reveal a beautiful early morning sky. The sun hits your face with"
						+ " a glowing warmth, and a tingling sense of relief washes over you. However, how did you"
						+ " find yourself on the ship in the first place, and where did all the crew go? You're missing"
						+ " pieces of the story, but at least you're alive and free. Now, time to climb outside and"
						+ " look for help!",
						new GChoice[] {
								}),
				
				// N68: Powered Control Panel (Lock Toggle)
				new GNode("Flipped",
						"You toggle the second lock for 'Deep Storage Vent'. You're not immediately sure what"
						+ " this does.",
						new GChoice[] {
								new GChoice("Continue", 64)
								}),
				
				// N69: Powered Control Panel (Sensors)
				new GNode("Sensor Readings",
						"The sensors on the control panel are a bit confusing to you, but you can make out a few"
						+ " important things. The ship appears to have a depth meter that reads '40 fathoms', which"
						+ " is quite deep underwater. You're not sure sure if the ship was meant for these type of"
						+ " depths. The longitude and latitude read as 28 and -161 respectively.",
						new GChoice[] {
								new GChoice("Continue", 64)
								}),
				
				// N70: Deep Storage Tunnel (Locked)
				new GNode("Blocked Tunnel",
						"You try to leap down the hole in the floor, until you realize it is blocked by a thick"
						+ " metal trapdoor. You can't go any further.",
						new GChoice[] {
								new GChoice("Go back up", 48) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 1);
									}
								},
								new GChoice("Go back up", 56) {
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().wheelPos == 0);
									}
								}
								}),
				
				// N71: Deep Storage Tunnel (Unlocked)
				new GNode("Deep Plunge",
						"You carefully try to descend the hole, but your feet slip and you end up falling down. You"
						+ " twist and tumble on your way down, hitting parts of your body against the rigid parts of"
						+ " the tunnel. Finally, you land on the bottom, surprisingly on your feet and relatively"
						+ " unscathed. Weird. There's no way back up from where you just came from.",
						new GChoice[] {
								new GChoice("Continue", 76)
								}),
				
				// N72: Deep Storage (Front)
				new GNode("Deep Storage",
						"This room truly gives off a sinister feeling, and you need your flashlight to be able"
						+ " to see anything at all around here. Rows of boxes line the area, all tied down"
						+ " to the floor with strong rope or thick chains. You can see a small control panel"
						+ " nearby, but the rest of the room is blocked off from all the boxes.",
						new GChoice[] {
								new GChoice("Go to the stairs", 14),
								new GChoice("Check out the control panel", 73),
								new GChoice("Explore what you can", 75)
								}),
				
				// N73: Deep Storage (Front / Control Panel)
				new GNode("Simple Control Panel",
						"Unlike the other control panel from the lower deck, this panel only has one switch. It"
						+ " reads 'Deep Storage Vent Lock #1'.",
						new GChoice[] {
								new GChoice("Go back", 72),
								new GChoice("Toggle 'Deep Storage Lock #1' to 'Off'", 74) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().lockOneOpen = true;
									}
									@Override
									public boolean shouldDisplay() {
										return (!GFrame.getInstance().getTracker().lockOneOpen);
									}
								},
								new GChoice("Toggle 'Deep Storage Vent Lock #1' to 'On'", 74) {
									@Override
									public void activate() {
										GFrame.getInstance().getTracker().lockOneOpen = false;
									}
									@Override
									public boolean shouldDisplay() {
										return (GFrame.getInstance().getTracker().lockOneOpen);
									}
								}
								}),
				
				// N74: Deep Storage Tunnel (Front / Flip Switch)
				new GNode("Flipped...",
						"You toggle the switch, faintly hearing a small clicking sound somewhere above you on"
						+ " the ship. You're not entirely sure what that just did.",
						new GChoice[] {
								new GChoice("Continue", 73)
								}),
				
				// N75: Deep Storage (Front / Explore)
				new GNode("A Look Around",
						"Looking around the room, you can barely spot a hole in the ceiling past the rows of boxes"
						+ " blocking you in. You try to peer into the contents of nearby boxes, but they're all"
						+ " locked up tight with multiple bulky locks that you don't have keys to.",
						new GChoice[] {
								new GChoice("Continue", 72)
								}),
				
				// N76: Deep Storage (Back)
				new GNode("Deep Storage: Back Area",
						"After the fall, you give a look around to your surroundings. You appear to be in the back"
						+ " end of Deep Storage. You can barely see the front entrance you emerged from before behind"
						+ " rows of boxes. Turning around, you catch sight of the one container in the room that is open."
						+ " It lies there, beckoning you in...",
						new GChoice[] {
								new GChoice("Approach the box", 77)
								}),
				
				// N77: Deep Storage (Back / Open Box)
				new GNode("Strange Contents",
						"It's so strange that in this room of heavy locks and high security, this one box is left"
						+ " carelessly open. You gaze inside at its contents, which consist of a strange blue idol"
						+ " made of some sort of stone as well as some weathered-looking photographs.",
						new GChoice[] {
								new GChoice("Look at the photos", 78),
								new GChoice("Inspect the idol", 79)
								}),
				
				// N78: Deep Storage (Back / Photos)
				new GNode("Unsettling Photographs",
						"The photographs picture multiple familiar rooms around the ship, but filled with people for"
						+ " a change. Eerily, there's one figure in nearly all the photos whose face is scratched"
						+ " out with a dark ink. Judging from the outfits of the crew members, it looks like it's"
						+ " the same person etched out in every picture.",
						new GChoice[] {
								new GChoice("Back", 77)
								}),
				
				// N79: Deep Storage (Back / Pre-idol)
				new GNode("It's Grasp",
						"The idol is simple in design and resembles a polished sphere held in place by a hand."
						+ " For some reason, you find it hard to look away, and as you gaze longer and longer the"
						+ " idol appears to shift forms. The image of a bloodshot eye begins to take place of the"
						+ " plain sphere, and the fingers on the hand grasping it become thin and bony. You have an"
						+ " inert desire to touch the idol, but you're afraid of the irreparable consequences this"
						+ " will have.",
						new GChoice[] {
								new GChoice("Back", 77),
								new GChoice("Proceed", 80) {
									@Override
									public void activate() {
										// Creates a special text file
										try {
											List<String> lines = Arrays.asList("You found us, as was foretold.",
													"Your work here is not yet finished.",
													"Unlock the seal and set us free.");
											Path file = Paths.get("It_Begins.txt");
											Files.write(file, lines, Charset.forName("UTF-8"));
										} catch (IOException e) {
											System.out.println("File didn't generate properly");
											e.printStackTrace();
										}
									}
								}
								}),
				
				// N80: Deep Storage (Back / Idol)
				new GNode("Whisked Away",
						"Touching the idol sends shocks of ethereal energy up your arm that inject waves of acute"
						+ " paralysis into your spine. Before you collapse, you see a horrible visage in the corner"
						+ " of your vision. Everything goes dark quickly, and you feel your body being transmuted and"
						+ " transported to something and somewhere completely unknown.",
						new GChoice[] {
								new GChoice("Continue...", 81) {
									@Override
									public void activate() {
										// Create popup
										GFrame.getInstance().createPopupAndClose("blackscreen.png");
									}
								}
								}),
				
				// N81: Deep Storage (Gotcha)
				new GNode("I SEE YOU",
						"...",
						new GChoice[] {
								}),
				
				// N82: Kitchen (Planks)
				new GNode("Gotcha",
						"With a quick and careful move, you snatch the planks out from under the fridge. It tips"
						+ " even more than before, so you take a step back out of caution. The planks are worn, but"
						+ " you take one on the off-chance that it'll prove useful.",
						new GChoice[] {
								new GChoice("Continue", 83)
								},
						new GItem[] { GItem.PLANK }, GNode.ADDED),
				
				// N83: Cafeteria (Kitchen / Locked Fridge / Plankless)
				new GNode("Heavy Fridge",
						"The heavy metal fridge is leaning very sharply from the wall, ready to tip. Regardless, the"
						+ " thing is still so enormous that you can't get it to budge. The doors of the otherwise"
						+ " spotless appliance are coated in a thick layer of rust, making opening them normally"
						+ " impossible as well.",
						new GChoice[] {
								new GChoice("Go back", 33)
								}) {
					@Override
					public Color displayColor() {
						return new Color(200, 255, 200);
					}
				}

				
				};
		
	}
	
}
