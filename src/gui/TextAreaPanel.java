package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextAreaPanel extends JPanel {

	// Prevents warnings
	private static final long serialVersionUID = 1L;
	
	// Width and height of the panel
	protected int panelWidth = 600;
	protected int panelHeight = 150;
	
	// Area where text is inserted
	protected JTextArea nodeText;
	
	protected TextAreaPanel(Color bgColor) {
		super();
		
		// Allow exact positioning
		this.setLayout(null);
		
		// Set background color
		this.setBackground(bgColor);
		
		// Set bounds/properties of nodeText and add to frame
		this.nodeText = new JTextArea();
		Font textFont = this.nodeText.getFont();
		this.nodeText.setFont(new Font(textFont.getName(), Font.PLAIN, 14));
		this.nodeText.setBounds(5, 0, panelWidth - 10, 150);
		this.nodeText.setLineWrap(true);
		this.nodeText.setWrapStyleWord(true);
		this.nodeText.setEditable(false);
		this.nodeText.setOpaque(false);
		this.nodeText.setVisible(true);
		this.add(this.nodeText);
	}

}
