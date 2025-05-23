package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.enemyManager;
import entities.playerClass;
import levels.levelManager;
import main.gameClass;
import ui.gameOverOverlay;
import ui.pauseOverlay;
import utilz.loadSave;
import static utilz.constants.Environment.*;

public class playing extends state implements statemethods {
	
	private playerClass player;
	private levelManager levelHandler;
	private enemyManager enemyHandler;
	private pauseOverlay pauseMenu;
	private gameOverOverlay gameOverScreen;
	private boolean paused = false;
	
	private int xLvlOffset;
	private int leftBorder = (int) (0.45f * gameClass.GAME_WIDTH);
	private int rightBorder = (int) (0.55f * gameClass.GAME_WIDTH);
	
	private int lvlTilesWide = loadSave.GetLevelData()[0].length;
	private int maxTilesOffset = lvlTilesWide - gameClass.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset * gameClass.TILES_SIZE;
	
	private boolean gameOver;
	
	private BufferedImage bgIMG1, bgIMG2, bgIMG3, bgIMG4;
	
	public playing(gameClass game) {
		super(game);
		initClasses();
		bgIMG1 = loadSave.GetSpriteAtlas(loadSave.LVL_BG_ONE);
		bgIMG2 = loadSave.GetSpriteAtlas(loadSave.LVL_BG_TWO);
		bgIMG3 = loadSave.GetSpriteAtlas(loadSave.LVL_BG_THREE);
		bgIMG4 = loadSave.GetSpriteAtlas(loadSave.LVL_BG_FOUR);
	}
	
	private void initClasses() {
		player = new playerClass(200 * gameClass.SCALE, 200 * gameClass.SCALE,
				(int) (gameClass.PLAYER_DEFAULT_SIZE * gameClass.SCALE),
				(int) (gameClass.PLAYER_DEFAULT_SIZE * gameClass.SCALE), this);
		levelHandler = new levelManager(game);
		enemyHandler = new enemyManager(this);
		
		player.loadLvlData(levelHandler.getCurrentLevel().getLevelData());
		pauseMenu = new pauseOverlay(this);
		gameOverScreen = new gameOverOverlay(this);
		
	}

	@Override
	public void update() {
		if(!paused && !gameOver) {
			levelHandler.update();
			checkCloseToBorder();
			player.update();
			enemyHandler.update(levelHandler.getCurrentLevel().getLevelData(), player);
			
		}
		else {
			pauseMenu.update();
		}
	}
	

	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;
		
		if(diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if(diff < leftBorder)
			xLvlOffset += diff - leftBorder;
		
		if(xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if(xLvlOffset < 0)
			xLvlOffset = 0;
		
	}

	@Override
	public void draw(Graphics g) {
		
		drawTerrain(g);
		
		levelHandler.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		enemyHandler.draw(g, xLvlOffset);
		
		if(paused) {
			g.setColor(new Color(0,0,0,100));
			g.fillRect(0, 0, gameClass.GAME_WIDTH, gameClass.GAME_WIDTH);
			pauseMenu.draw(g);
		}else if(gameOver) {
			gameOverScreen.draw(g);
		}
	}

	private void drawTerrain(Graphics g) {
		
		g.drawImage(bgIMG1, 0, 0, gameClass.GAME_WIDTH, gameClass.GAME_HEIGHT, null);
		
		for(int i = 0; i < 2; i++)
			g.drawImage(bgIMG2, i * TREES_WIDTH - (int) (xLvlOffset * 0.1) - i, 0, TREES_WIDTH, TREES_HEIGHT, null);
		
		for(int i = 0; i < 2; i++)
			g.drawImage(bgIMG4, (i * (int) ( TREES_WIDTH * 1.5 ) ) - (int) (xLvlOffset * 0.6) - i, -TREES_HEIGHT / 2, (int) (TREES_WIDTH * 1.5), (int) (TREES_HEIGHT * 1.5), null);
		
		
	}

	public void resetAll() {
		//todo reset playing enemy, lvl etc
		gameOver = false;
		paused = false;
		player.resetAll();
		enemyHandler.resetAllEnemies();
		
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyHandler.checkEnemyHit(attackBox);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//if(!gameOver)
		//	if(e.getButton() == MouseEvent.BUTTON1)
				
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!gameOver)
			if(paused)
				pauseMenu.mousePressed(e);
			else
				player.setAttacking(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!gameOver)
			if(paused)
				pauseMenu.mouseReleased(e);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(!gameOver)
			if(paused)
				pauseMenu.mouseMoved(e);
		
	}
	
	public void unpauseGame() {
		paused = false;
		
	}
	
	public void mouseDragged(MouseEvent e) {
		if(!gameOver)
			if(paused)
				pauseMenu.mouseDragged(e);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		if(gameOver)
			gameOverScreen.keyPressed(e);
		else
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
			case KeyEvent.VK_ESCAPE:
				//gamestate.state = gamestate.MENU;
				//player.resetDirBooleans();
				paused = !paused;
			break;
			}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!gameOver)
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
