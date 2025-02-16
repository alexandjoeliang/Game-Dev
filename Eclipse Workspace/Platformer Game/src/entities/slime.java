package entities;

import static utilz.constants.Directions.*;
import static utilz.constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.gameClass;

public class slime extends enemy {

	//attack box
	private Rectangle2D.Float attackBox;
	private int attackBoxOffsetX;
	
	public slime(float x, float y) {
		super(x, y, SLIME_WIDTH, SLIME_HEIGHT, SLIME);
		initHitbox(x, y, (int) (21 * gameClass.SCALE * SLIME_SCALE), (int) (17 * gameClass.SCALE * SLIME_SCALE));
		initAttackBox();
	}
	
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (21 * gameClass.SCALE * SLIME_SCALE), (int) (17 * gameClass.SCALE * SLIME_SCALE));
		attackBoxOffsetX = (int) (30);
	}

	public void update(int[][] lvlData, playerClass player) {
		updateBehavior(lvlData, player);
		updateAnimationTick();
		updateAttackBox();
		
	}

	private void updateAttackBox() {
		if(walkDir == RIGHT)
			attackBox.x = hitbox.x + attackBoxOffsetX;
		else
			attackBox.x = hitbox.x - 2 * attackBoxOffsetX - attackBox.width + hitbox.width;
		
		attackBox.y = hitbox.y;
		
	}

	private void updateBehavior(int[][] lvlData, playerClass player) {
		if(firstUpdate)
			firstUpdateCheck(lvlData);
		
		
		if(inAir)
			updateInAir(lvlData);
		else {
			switch(enemyState) {
			case IDLE:
				newState(WALKING);
				break;
			case WALKING:
				if(canSeePlayer(lvlData, player)) {
					turnTowardsPlayer(player);
					if(isPlayerAttackable(player))
						newState(ATTACK);
				}
				
				move(lvlData);	
				
				break;
			case ATTACK:
				if(aniIndex == 0)
					attackChecked = false;
				
				if(aniIndex == 3 && !attackChecked)
					checkPlayerHit(attackBox, player);
				break;
				
			case HIT:
				
				break;
			}
		}
		
	}
	
	public void drawAttackBox(Graphics g, int xLvlOffset) {
		g.setColor(Color.red);
		g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
	}
	
	public int flipX() {
		if(walkDir == RIGHT)
			return 0;
		else
			return width;
		
	}
	
	public int flipW() {
		if(walkDir == RIGHT)
			return 1;
		else
			return -1;
		
	}
	
	
	
}
