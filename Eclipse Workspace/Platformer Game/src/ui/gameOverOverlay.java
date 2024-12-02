package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gamestates.gamestate;
import gamestates.playing;
import main.gameClass;

public class gameOverOverlay {
	
	private playing playobj;
	
	public gameOverOverlay(playing playobj) {
		this.playobj = playobj;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, gameClass.GAME_WIDTH, gameClass.GAME_HEIGHT);
		
		g.setColor(Color.white);
		g.drawString("Game Over", gameClass.GAME_WIDTH / 2, 150);
		g.drawString("Press esc to enter Main Menu!", gameClass.GAME_WIDTH / 2, 300);
	
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			playobj.resetAll();
			gamestate.state = gamestate.MENU;
		}
	}
	
}
