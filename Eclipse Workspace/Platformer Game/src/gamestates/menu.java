package gamestates;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.gameClass;
import ui.menubuttons;
import utilz.loadSave;
import static utilz.constants.UI.Buttons.*;

public class menu extends state implements statemethods {

	private menubuttons[] buttons = new menubuttons[3];
	private BufferedImage backgroundImg, menuBG;
	private int menuX, menuY, menuWidth, menuHeight;
	
	public menu(gameClass game) {
		super(game);
		loadButtons();
		loadBackground();
		menuBG = loadSave.GetSpriteAtlas(loadSave.MENU_BACKGROUND_IMAGE);
	}

	private void loadBackground() {
		backgroundImg = loadSave.GetSpriteAtlas(loadSave.MENU_BACKGROUND);
		menuWidth = (int) (backgroundImg.getWidth() * gameClass.SCALE * MB_SCALE);
		menuHeight = (int) (backgroundImg.getHeight() * gameClass.SCALE * MB_SCALE);
		menuX = gameClass.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (90 * gameClass.SCALE);
	}

	private void loadButtons() {
		buttons[0] = new menubuttons(gameClass.GAME_WIDTH / 2, (int) (180 * gameClass.SCALE * MB_SCALE), 0, gamestate.PLAYING);
		buttons[1] = new menubuttons(gameClass.GAME_WIDTH / 2, (int) (245 * gameClass.SCALE * MB_SCALE), 1, gamestate.OPTIONS);
		buttons[2] = new menubuttons(gameClass.GAME_WIDTH / 2, (int) (310 * gameClass.SCALE * MB_SCALE), 2, gamestate.QUIT);
 		
	}

	@Override
	public void update() {
		for(menubuttons mb : buttons) {
			mb.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(menuBG, 0, 0, gameClass.GAME_WIDTH, gameClass.GAME_HEIGHT, null);
		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
		for(menubuttons mb : buttons) {
			mb.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(menubuttons mb : buttons) {
			if(isIn(e, mb)) {
				mb.setMousePressed(true);
				break;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(menubuttons mb : buttons) {
			if(isIn(e, mb)) {
				if(mb.isMousePressed())
					mb.applyGamestate();
				break;
			}
		}
		resetButtons();
	}

	private void resetButtons() {
		for(menubuttons mb : buttons) {
			mb.resetBools();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(menubuttons mb : buttons) {
			mb.setMouseOver(false);
		}
		for(menubuttons mb : buttons) {
			if(isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			gamestate.state = gamestate.PLAYING;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
