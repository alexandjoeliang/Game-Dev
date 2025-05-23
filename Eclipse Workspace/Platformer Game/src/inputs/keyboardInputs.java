package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.gamestate;
import main.gamePanel;

public class keyboardInputs implements KeyListener {
	
	private gamePanel panel;
	
	//allows use of methods defined in panel
	public keyboardInputs(gamePanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (gamestate.state){
		case MENU:
			panel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			panel.getGame().getPlaying().keyReleased(e);
			break;
		default:
			break;
		
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (gamestate.state){
		case MENU:
			panel.getGame().getMenu().keyPressed(e);
			break;
		case PLAYING:
			panel.getGame().getPlaying().keyPressed(e);
			break;
		default:
			break;
		
		}
	}
		
}
