package main;

import javax.swing.JPanel;
import inputs.keyboardInputs;
import inputs.mouseInputs;
import java.awt.Dimension;
import java.awt.Graphics;

//game contents
public class gamePanel extends JPanel{

	//panel variables
	private mouseInputs mInputs;
	private gameClass game;
	
	//panel constructor
	public gamePanel(gameClass game) {
		mInputs = new mouseInputs(this);
		this.game = game;
		
		setPanelSize();
		addKeyListener(new keyboardInputs(this));
		addMouseListener(mInputs);
		addMouseMotionListener(mInputs);
		addMouseWheelListener(mInputs);
		
	}
	
	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void updateGame() {
		
		
	}
	
	//graphics component
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.render(g);
		
	}
	
	
	public gameClass getGame() {
		return game;
		
	}
	
}
