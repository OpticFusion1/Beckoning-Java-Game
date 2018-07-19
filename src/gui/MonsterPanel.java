package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import helpers.SoundPlayer;

public class MonsterPanel extends JPanel implements MouseListener {

	// Prevents warnings
	private static final long serialVersionUID = 1L;
	
	// Image of the monster
	private static Image monsterImg = null;
	
	// Size of the panel
	protected static final int mWidth = 482;
	protected static final int mHeight = 375;
	
	// Coordinates monster can warp to
	private Dimension[] coords = new Dimension[] {
			new Dimension(-370, -230),	// Top-left
			new Dimension(-360, 280),	// Bottom-left
			new Dimension(730, -230),	// Top-right
			new Dimension(520, 300),	// Bottom-right
			new Dimension(-390, -300),	// Top-left Hidden
			new Dimension(-450, 130),	// Left
			new Dimension(100, -330),	// Top
			new Dimension(755, -50),	// Right
			
	};
	
	// Constructor
	protected MonsterPanel() {
		super();
		
		// Initialize image if not initialized
		if(monsterImg == null) {
			try {
				File file = new File("res/images/monster_large.png");
				URL url = file.toURI().toURL();
				MonsterPanel.monsterImg = new ImageIcon(url).getImage();
			} catch (Exception e) {
				System.out.println("Image not found.");
				e.printStackTrace();
			}
		}
		
		// Add mouse listener
		this.addMouseListener(this);
		
		// Make background of panel transparent
		this.setOpaque(false);
	}
	
	// Warps monster to new coordinate from list and sets it as visible
	protected void warp() {
		Random r = new Random();
		
		// Get random index from coordinates list
		int nextPos = r.nextInt(this.coords.length);
		
		// Set new position for monster
		this.setBounds(this.coords[nextPos].width, this.coords[nextPos].height, mWidth, mHeight);
		
		this.setVisible(true);
	}
	
	// Warps the monster off the screen and hides it
	protected void goAway() {
		this.setVisible(false);
		this.setBounds(-800, -800, mWidth, mHeight);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(MonsterPanel.monsterImg, 0, 0, null);
	}

	//----------------------------
	// Mouse Listener Methods
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(this.isVisible()) {
			// Hide the panel
			this.goAway();
			
			// Play warp sound
			SoundPlayer.playWAV("warp.wav", -20);
			
			// Stop monster from killing player and reset aggro counter
			GFrameAlt.getInstance().getTracker().shouldKill = false;
			GFrameAlt.getInstance().getTracker().monsterAggro = 0;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Nothing
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Nothing
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Nothing
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Nothing
	}
	
}
