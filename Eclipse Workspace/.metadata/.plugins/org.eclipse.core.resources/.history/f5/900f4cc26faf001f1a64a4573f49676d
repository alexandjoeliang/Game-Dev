package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.playerClass;
import levels.levelManager;
import main.gameClass;
import ui.pauseOverlay;

public class playing extends state implements statemethods {
	
	private playerClass player;
	private levelManager levelHandler;
	private pauseOverlay pauseMenu;
	private boolean paused = true;
	
	
	
	public playing(gameClass game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		player = new playerClass(200 * gameClass.SCALE, 200 * gameClass.SCALE, (int) (gameClass.PLAYER_DEFAULT_SIZE * gameClass.SCALE), (int) (gameClass.PLAYER_DEFAULT_SIZE * gameClass.SCALE));
		levelHandler = new levelManager(game);
		player.loadLvlData(levelHandler.getCurrentLevel().getLevelData());
		pauseMenu = new pauseOverlay(this);
		
	}

	@Override
	public void update() {
		levelHandler.update();
		player.update();
		pauseMenu.update();
	}

	@Override
	public void draw(Graphics g) {
		if(!paused) {
			levelHandler.draw(g);
			player.render(g);
		}
		else {
			pauseMenu.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(paused)
			pauseMenu.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused)
			pauseMenu.mouseReleased(e);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused)
			pauseMenu.mouseMoved(e);
		
	}
	
	public void unpauseGame() {
		paused = false;
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(true);
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_S:
			player.setDown(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_BACK_SPACE:
			gamestate.state = gamestate.MENU;
			player.resetDirBooleans();
		break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(false);
			break;
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_S:
			player.setDown(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}
		
	}
	
	//ensures player does not keep moving if clicked off application
	public void windowFocusLost() {
		player.resetDirBooleans();
		
	}
	
	public playerClass getPlayer() {
		return player;
		
	}
	
}
