package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.gameClass;
import utilz.loadSave;

public class levelManager {

	private gameClass game;
	private BufferedImage[] levelSprite;
	private level levelOne;
	
	public levelManager(gameClass game) {
		
		this.game = game;
		//levelSprite = loadSave.GetSpriteAtlas(loadSave.LEVEL_ATLAS);
		importOutsideSprite();
		levelOne = new level(loadSave.GetLevelData());
		
	}
	
	private void importOutsideSprite() {
		BufferedImage img = loadSave.GetSpriteAtlas(loadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for(int j = 0; j < 4; j++ ) {
			for(int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
				
			}
		}
		
		
	}
	
	
	public void draw(Graphics g) {
		for(int j = 0; j < gameClass.TILES_IN_HEIGHT; j++) {
			for(int i = 0; i < gameClass.TILES_IN_WIDTH; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], gameClass.TILES_SIZE * i, gameClass.TILES_SIZE * j, gameClass.TILES_SIZE, gameClass.TILES_SIZE, null);
			}
			
		}
		
		
	}
	
	public void update() {
		
		
	}
	
	public level getCurrentLevel() {
		return levelOne;
		
	}
	
}
