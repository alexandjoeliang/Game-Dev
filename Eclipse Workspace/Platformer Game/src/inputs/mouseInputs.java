package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import gamestates.gamestate;
import main.gamePanel;
import main.gameWindow;

public class mouseInputs implements MouseListener, MouseMotionListener, MouseWheelListener {

	private gamePanel panel;
	private gameWindow window;
	//allows use of methods defined in panel
	public mouseInputs(gamePanel panel, gameWindow window) {
		this.panel = panel;
		this.window = window;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		switch(gamestate.state) {
		case PLAYING:
			panel.getGame().getPlaying().mouseDragged(e);
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch(gamestate.state) {
		case MENU:
			panel.getGame().getMenu().mouseMoved(e);
			break;
		case PLAYING:
			panel.getGame().getPlaying().mouseMoved(e);
			break;
		default:
			break;
		
		
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//if (e.getButton() == MouseEvent.BUTTON1)
			//panel.getGame().getPlayer().setAttacking(true);
		switch(gamestate.state) {
		case PLAYING:
			panel.getGame().getPlaying().mouseClicked(e);
			break;
		default:
			break;
		
		
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			window.changeCursor("pressed");
		
		switch(gamestate.state) {
		case MENU:
			panel.getGame().getMenu().mousePressed(e);
			break;
		case PLAYING:
			panel.getGame().getPlaying().mousePressed(e);
			break;
		default:
			break;
		
		
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			window.changeCursor("released");
		
		switch(gamestate.state) {
		case MENU:
			panel.getGame().getMenu().mouseReleased(e);
			break;
		case PLAYING:
			panel.getGame().getPlaying().mouseReleased(e);
			break;
		default:
			break;
		
		
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() == 1) {
			System.out.println("scroll down(ccw)");
		}
		else {
			System.out.println("scroll up(cw)");
		}
	}

}
