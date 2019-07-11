package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import helpers.SoundPlayer;
import models.EventTracker;
import models.GChoice;
import models.GItem;
import models.GMap;
import models.GNode;

public class GFrame extends JFrame {

	// Singleton instance of class
	private static GFrame instance = null;
	
	// Prevents warnings
	private static final long serialVersionUID = 1L;
	
	// Width and Height of the frame
	private int fWidth = 800;
	private int fHeight = 400;
	
	// Current node
	public GNode currentNode;
	
	// Map of nodes
	public GMap nodeMap;
	
	// Title of current node
	public JLabel nodeTitle = new JLabel("Situation");
	
	// Description (Main text) of the current node
	public TextAreaPanel descPanel;
	
	// Button you push to restart the adventure
	public JButton restartButton = new JButton("Retry");
	
	// List of Buttons that can be pressed
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	// Current Inventory
	private ArrayList<GItem> inventory = new ArrayList<GItem>();
	
	// Event tracker containing event flags and other information
	private EventTracker events = new EventTracker();
	
	// Constructor
	protected GFrame() {
		super();
		
		// Set default size for frame
		Dimension size = new Dimension(this.fWidth, this.fHeight);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setResizable(false);
		
		// Set Layout to allow for exact content positioning
		this.setLayout(null);
		
		// Change font for nodeTitle
		Font titleFont = this.nodeTitle.getFont();
		this.nodeTitle.setFont(new Font(titleFont.getName(), Font.BOLD, 16));
		
		// Set bounds/properties of nodeTitle and add to frame
		this.nodeTitle.setBounds(100, 20, 600, 30);
		this.nodeTitle.setBackground(Color.lightGray);
		this.nodeTitle.setOpaque(true);
		this.nodeTitle.setVisible(true);
		this.add(this.nodeTitle);
		
		// Set up node description text panel
		this.descPanel = new TextAreaPanel(Color.cyan);
		this.descPanel.setBounds(100, 50, this.descPanel.panelWidth, this.descPanel.panelHeight);
		this.add(this.descPanel);
		
		// Set bound/properties of restartButton and add to frame
		this.restartButton.setBounds(10, 20, 80, 30);
		this.restartButton.setBackground(new Color(255, 128, 128));
		this.restartButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				GFrame.this.inventory.clear();
				GFrame.this.resetTracker();
			    GFrame.this.selectNode(0);
			  } 
		});
		this.restartButton.setFocusPainted(false);
		this.restartButton.setVisible(true);
		this.add(this.restartButton);
		
		// Initialize node map
		this.nodeMap = new GMap();
		
		// "Cache" sound playing
		SoundPlayer.playWAV("Gotcha2.wav", -80);
		
		// Prepare frame for viewing
		this.setTitle("The Beckoning");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}
	
	// Loads up a new node
	public void loadNode(GNode node) {
		// Start by clearing the list of current buttons
		for(JButton button : this.buttons) {
			this.remove(button);
		}
		this.buttons.clear();
		
		// Set node as current node
		this.currentNode = node;
		
		boolean itemAdded = false;
		// For each item at node, add to inventory if we don't have it
		for(int i = 0; i < this.currentNode.addedItems.length; i++) {
			if(!this.inventory.contains(this.currentNode.addedItems[i])) {
				this.inventory.add(this.currentNode.addedItems[i]);
				itemAdded = true;
			}
		}
		
		// Play "ding" sound if new item acquired
		if(itemAdded) {
			SoundPlayer.playWAV("ding.wav");
		}
		
		// For each item removed at node, removed from inventory if we have it
		for(int i = 0; i < this.currentNode.removedItems.length; i++) {
			if(this.inventory.contains(this.currentNode.removedItems[i])) {
				this.inventory.remove(this.currentNode.removedItems[i]);
			}
		}
		
		// Set new title and description
		this.nodeTitle.setText(" " + this.currentNode.titleText);
		this.descPanel.nodeText.setText(this.currentNode.descText);
		
		// Set background color
		this.getContentPane().setBackground(this.currentNode.displayColor());
		
		// Add each choice to the screen as a unique button
		int yPos = 0;
		for(int i = 0; i < this.currentNode.choices.length; i++) {
			// Grab the choice
			GChoice c = this.currentNode.choices[i];
			
			// Display the choice if we've met the conditions
			if(c.shouldDisplay()) {
				// Initialize the button for the choice
				this.initializeButton(c, yPos);
				
				// Increment position counters
				// Helps organize button layout
				yPos += 1;
			}
		}
		
		// Refresh the whole screen
		this.repaint();
	}
	
	// Selects the node to be loaded
	public void selectNode(int index) {
		GNode node = this.nodeMap.nodes[index];
		this.loadNode(node);
	}
	
	// Initialize a button on the screen
	public void initializeButton(GChoice c, int yPos) {
		// Initialize button with text and listener
		JButton choiceButton = new JButton(c.actionText);
		
		// Set the color
		choiceButton.setBackground(c.choiceColor);
			
		// Add listener that changes nodes and does any additional actions
		choiceButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				// Do special functionality on press (None by default)
				c.activate();
				
				// Select the node this action links to
			    GFrame.this.selectNode(c.actionNode);
			  } 
		});
		
		// Set bounds correctly
		choiceButton.setBounds(100, 210 + (yPos * 35), 600, 30);
		
		// Add button to references and to screen
		this.buttons.add(choiceButton);
		this.add(choiceButton);
	}
	
	// Creates a pop-up that lasts for a small period and disappears
	public void createPopup(int delay, String imagePath) {
		// Create new frame and initialize settings
		JFrame testFrame = new JFrame();
		Dimension testSize = new Dimension(this.fWidth, this.fHeight);
		testFrame.setMinimumSize(testSize);
		testFrame.setResizable(false);
		testFrame.setLayout(null);
		testFrame.setUndecorated(true);
		
		// Make pop-up frame appear at location of main frame
		Point location = this.getLocation();
		testFrame.setLocation(location);
		
		// Create panel within frame
		JPanel testPanel = new JPanel(new BorderLayout());
		testPanel.setVisible(true);
		testPanel.setBounds(0, 0, this.fWidth, this.fHeight);
		
		// Set popup image
		ImageIcon image;
		try {
			File file = new File("res/images/" + imagePath);
			URL url = file.toURI().toURL();
			image = new ImageIcon(url);
			JLabel label = new JLabel("", image, JLabel.CENTER);
			testPanel.add(label, BorderLayout.CENTER );
			testFrame.add(testPanel);
		} catch (Exception e) {
			System.out.println("Image not found.");
			e.printStackTrace();
		}

		
		// Set frame to be visible
		testFrame.setVisible(true);
		testFrame.pack();
		
		// Create timer for small period that closes pop-up on finish
		ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          testFrame.dispose();
		      }
		};
		
		// Start the timer
		new Timer(delay, taskPerformer).start();
	}
	
	// Creates a pop-up that lasts for a small period and disappears,
	// closing the game with it
	public void createPopupAndClose(String imagePath) {
		// Create new frame and initialize settings
		JFrame testFrame = new JFrame();
		Dimension testSize = new Dimension(this.fWidth, this.fHeight);
		testFrame.setMinimumSize(testSize);
		testFrame.setResizable(false);
		testFrame.setLayout(null);
		testFrame.setUndecorated(true);
		
		// Make pop-up frame appear at location of main frame
		Point location = this.getLocation();
		testFrame.setLocation(location);
		
		// Create panel within frame
		JPanel testPanel = new JPanel(new BorderLayout());
		testPanel.setVisible(true);
		testPanel.setBounds(0, 0, this.fWidth, this.fHeight);
		
		// Set popup image
		ImageIcon image;
		try {
			File file = new File("res/images/" + imagePath);
			URL url = file.toURI().toURL();
			image = new ImageIcon(url);
			JLabel label = new JLabel("", image, JLabel.CENTER);
			testPanel.add(label, BorderLayout.CENTER );
			testFrame.add(testPanel);
		} catch (Exception e) {
			System.out.println("Image not found.");
			e.printStackTrace();
		}

		
		// Set frame to be visible
		testFrame.setVisible(true);
		testFrame.pack();
		
		// Create timer for small period that closes pop-up on finish
		ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          testFrame.dispose();
		          GFrame.getInstance().dispatchEvent(new WindowEvent(GFrame.this, WindowEvent.WINDOW_CLOSING));
		      }
		};
		
		// Start the timer
		new Timer(700, taskPerformer).start();
	}
	
	// Returns if the inventory contains the given item
	public boolean hasItem(GItem item) {
		return (this.inventory.contains(item));
	}
	
	// Clears out the inventory of all items collected
	public void clearInventory() {
		this.inventory.clear();
	}
	
	// Resets all event flags to original states
	public void resetTracker() {
		this.events.resetTracker();
	}
	
	// Fetches the event tracker
	public EventTracker getTracker() {
		return this.events;
	}
	
	// Retrieves instance of the GFrame, constructing one if it doesn't exist already
	public static GFrame getInstance() {
		if(instance == null) {
			instance = new GFrame();
		}
		return instance;
	}
	
}
