package main;

import javax.swing.JPanel;
import inputs.keyboardInputs;
import inputs.mouseInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import static main.gameClass.GAME_HEIGHT;
import static main.gameClass.GAME_WIDTH;


//game contents
public class gamePanel extends JPanel{

	//panel variables
	private mouseInputs mInputs;
	private gameClass game;
	private gameWindow window;
	
	//panel constructor
	public gamePanel(gameClass game) {
		
		this.game = game;
		
		setPanelSize();
		
		window = new gameWindow(this);
		mInputs = new mouseInputs(this, window);
		
		
		addKeyListener(new keyboardInputs(this));
		addMouseListener(mInputs);
		addMouseMotionListener(mInputs);
		addMouseWheelListener(mInputs);
		
	}
	
	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
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
