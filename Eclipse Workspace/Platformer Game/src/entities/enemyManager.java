package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.playing;
import utilz.loadSave;
import static utilz.constants.EnemyConstants.*;

public class enemyManager {
	
	private playing play;
	private BufferedImage[][] slimeArray;
	private ArrayList<slime> slimes = new ArrayList<>();
	
	
	public enemyManager(playing play) {
		this.play = play;
		loadEnemyImgs();
		addEnemies();
		
	}

	private void addEnemies() {
		slimes = loadSave.getSlimes();
	}

	public void update(int[][] lvlData, playerClass player) {
		for(slime s : slimes)
			if(s.isActive())
				s.update(lvlData, player);
		
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawSlimes(g, xLvlOffset);
		
		
	}
	
	private void drawSlimes(Graphics g, int xLvlOffset) {
		for(slime s : slimes) {
			if(s.isActive()) {
				g.drawImage(slimeArray[s.getEnemyState()][s.getAniIndex()],
						(int)(s.getHitbox().x) - xLvlOffset - SLIME_DRAWOFFSET_X + s.flipX(),
						(int)(s.getHitbox().y) - SLIME_DRAWOFFSET_Y,
						SLIME_WIDTH * s.flipW(), SLIME_HEIGHT, null );
				//s.drawHitbox(g, xLvlOffset);
				//s.drawAttackBox(g, xLvlOffset);
			}
		}
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for(slime s : slimes)
			if(s.isActive())
			if(attackBox.intersects(s.getHitbox())) {
				s.hurt(10);//how much dmg player deals
				return;
			}

	}

	private void loadEnemyImgs() {
		slimeArray = new BufferedImage[5][5];
		BufferedImage temp = loadSave.GetSpriteAtlas(loadSave.SLIME_SPRITE);
		for(int j = 0; j < slimeArray.length; j++)
			for(int i = 0; i < slimeArray[j].length; i++)
				slimeArray[j][i] = temp.getSubimage(i * SLIME_WIDTH_DEFAULT, j * SLIME_HEIGHT_DEFAULT, SLIME_WIDTH_DEFAULT, SLIME_HEIGHT_DEFAULT);
		
	}

	public void resetAllEnemies() {
		for(slime s : slimes)
			s.resetEnemy();
		
	}
	
	
	
	
}
