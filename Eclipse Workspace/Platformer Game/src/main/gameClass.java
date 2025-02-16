package main;

import java.awt.Graphics;

import gamestates.gamestate;
import gamestates.menu;
import gamestates.playing;


public class gameClass implements Runnable {

	//game variables
	private gamePanel panel;
	private Thread gameThread;
	private final int FPS_SET = 60;
	private final int UPS_SET = 200;

	private playing playingobj;
	private menu menuobj;
	
	public final static int TILES_DEFAULT_SIZE = 64;
	public final static int PLAYER_DEFAULT_SIZE = 192;
	public final static float PLAYER_TO_TILE_RATIO = PLAYER_DEFAULT_SIZE / TILES_DEFAULT_SIZE;
	public final static float SCALE = 1.0f;
	public final static int TILES_IN_WIDTH = 24;
	public final static int TILES_IN_HEIGHT = 13;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	//game constructor - creates main loop
	public gameClass() {
		initClasses();
		
		
		panel = new gamePanel(this);
		panel.setFocusable(true);
		panel.requestFocus();
		
		
		startGameLoop();
	}

	private void initClasses() {
		menuobj = new menu(this);
		playingobj = new playing(this);
	}

	//creates separate thread for game to run on - smooth FPS
	private void startGameLoop() {
	
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void update() {
		switch(gamestate.state) {
		case MENU:
			menuobj.update();
			break;
		case PLAYING:
			playingobj.update();
			break;
		case OPTIONS:
			//
			break;
		case QUIT:
			System.exit(0);
		default:
			break;
		}
		
	}
	
	public void render(Graphics g) {
		switch(gamestate.state) {
		case MENU:
			menuobj.draw(g);
			break;
		case PLAYING:
			playingobj.draw(g);
			break;
		default:
			break;
		
		}
				
	}
	
	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) {
			
			long currentTime = System.nanoTime();
			
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			if(deltaF >= 1) {
				panel.repaint();
				frames++;
				deltaF--;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
			
		}
		
	}

	public void windowFocusLost() {
		if(gamestate.state == gamestate.PLAYING) {
			playingobj.getPlayer().resetDirBooleans();
		}
		
	}

	public menu getMenu() {
		return menuobj;
	}
	public playing getPlaying() {
		return playingobj;
	}

	
}
