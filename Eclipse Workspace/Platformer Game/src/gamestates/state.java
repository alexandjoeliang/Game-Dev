package gamestates;

import java.awt.event.MouseEvent;

import main.gameClass;
import ui.menubuttons;

public class state {

	protected gameClass game;
	public state(gameClass game) {
		this.game = game;
		
		
	}
	
	public boolean isIn(MouseEvent e, menubuttons mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	
	public gameClass getGame() {
		
		
		return game;
	}
	
}
