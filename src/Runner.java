import java.io.File;

import gui.GFrame;
import gui.GFrameAlt;

// Main class used to run the game
public class Runner {

	// Runs the game
	public static void main(String[] args) {
		// Check for existence of special file
		File spcFile = new File("It_Begins.txt");
		
		// If file exists, load up alternate game
		if(spcFile.exists() && !spcFile.isDirectory()) { 
			// Alternate game loadup
		    GFrameAlt altFrame = GFrameAlt.getInstance();
		    altFrame.selectNode(0);
		} else {
			// If not, load up normal game
			GFrame frame = GFrame.getInstance();
			frame.selectNode(0);
		}
	}

}
