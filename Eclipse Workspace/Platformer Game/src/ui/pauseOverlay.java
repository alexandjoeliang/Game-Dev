package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.gamestate;
import gamestates.playing;
import main.gameClass;
import utilz.loadSave;
import static utilz.constants.UI.pauseButtons.*;
import static utilz.constants.UI.URMButtons.*;
import static utilz.constants.UI.VolumeButtons.*;

public class pauseOverlay {
	
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	private soundButtons musicButton, sfxButton;
	private urmButton menuB, replayB, unpauseB;
	private playing play;
	private volumeButton volumeButtons;
	
	public pauseOverlay(playing play) {
		this.play = play;
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeButtons();
		
	}
	
	private void createVolumeButtons() {
		int vX = (int)(660 * gameClass.SCALE);
		int vY = (int)(305 * gameClass.SCALE);
		volumeButtons = new volumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}

	private void createUrmButtons() {
		int menuX = (int) (665 * gameClass.SCALE);
		int replayX = (int) (739 * gameClass.SCALE);
		int unpauseX = (int) (814 * gameClass.SCALE);
		int bY = (int) (353 * gameClass.SCALE);

		menuB = new urmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
		replayB = new urmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
		unpauseB = new urmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
		
	}

	private void createSoundButtons() {
		int soundX = (int) (800 * gameClass.SCALE);
		int musicY = (int) (165 * gameClass.SCALE);
		int sfxY = (int) (211 * gameClass.SCALE);
		
		musicButton = new soundButtons(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new soundButtons(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void loadBackground() {
		backgroundImg = loadSave.GetSpriteAtlas(loadSave.PAUSE_BACKGROUND);
		bgW = (int) (backgroundImg.getWidth() * gameClass.SCALE);
		bgH = (int) (backgroundImg.getHeight() * gameClass.SCALE);
		bgX = (int) (gameClass.GAME_WIDTH / 2 - bgW / 2);
		bgY = (int) (50 * gameClass.SCALE);
		
	}

	public void update() {

		musicButton.update();
		sfxButton.update();
		menuB.update();
		replayB.update();
		unpauseB.update();
		volumeButtons.update();
		
	}
	
	
	public void draw(Graphics g) {
		//background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
		
		//sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);
		
		//urmButtons
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		
		//slider
		volumeButtons.draw(g);
		
	}
	
	public void mouseDragged(MouseEvent e) {
		if(volumeButtons.isMousePressed()) {
			volumeButtons.changeX(e.getX());
		}
		
	}

	public void mousePressed(MouseEvent e) {
		if(isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if(isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
		else if(isIn(e, menuB))
			menuB.setMousePressed(true);
		else if(isIn(e, replayB))
			replayB.setMousePressed(true);
		else if(isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
		else if(isIn(e, volumeButtons))
			volumeButtons.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if(isIn(e, musicButton)) {
			if(musicButton.isMousePressed())
				musicButton.setMuted(!musicButton.isMuted());
		}
		else if(isIn(e, sfxButton)) {
			if(sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());
		}
		else if(isIn(e, menuB)) {
			if(menuB.isMousePressed())
				gamestate.state = gamestate.MENU;	
				play.unpauseGame();
		}
		else if(isIn(e, replayB)) {
			if(replayB.isMousePressed())
				System.out.println("replay lvl");
		}
		else if(isIn(e, unpauseB)) {
			if(unpauseB.isMousePressed())
				play.unpauseGame();
		}
		
		
		
		musicButton.resetBools();
		sfxButton.resetBools();
		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();
		volumeButtons.resetBools();
		
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		volumeButtons.setMouseOver(false);
		
		if(isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if(isIn(e, sfxButton))
			sfxButton.setMouseOver(true);
		else if(isIn(e, menuB))
			menuB.setMouseOver(true);
		else if(isIn(e, replayB))
			replayB.setMouseOver(true);
		else if(isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else if(isIn(e, volumeButtons))
			volumeButtons.setMouseOver(true);
		
	}
	
	private boolean isIn(MouseEvent e, pauseButton b) {
		return(b.getBounds().contains(e.getX(), e.getY()));
		
		
		
	}


}
