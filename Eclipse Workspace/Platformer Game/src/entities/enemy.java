package entities;

import static utilz.constants.EnemyConstants.*;
import static utilz.helpMethods.*;

import java.awt.geom.Rectangle2D;

import static utilz.constants.Directions.*;

import main.gameClass;

public abstract class enemy extends entity {

	protected int aniIndex, enemyState, enemyType;
	protected int aniTick, aniSpeed = 20;
	protected boolean firstUpdate = true;
	protected boolean inAir = false;
	protected float fallSpeed;
	protected float gravity = 0.04f * gameClass.SCALE;
	protected float walkSpeed = 0.6f * gameClass.SCALE;
	protected int walkDir = LEFT;
	
	protected int tileY;
	protected float attackDistance = gameClass.TILES_SIZE;
	
	protected int maxHealth;
	protected int currentHealth;
	
	protected boolean active = true;
	protected boolean attackChecked;
	
	public enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);
		maxHealth = GetMaxHealth(enemyType);
		currentHealth = maxHealth;
		
	}

	protected void firstUpdateCheck(int[][] lvlData) {
		if(!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
		firstUpdate = false;
	}
	
	protected void updateInAir(int[][] lvlData) {
		if(CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.y += fallSpeed;
			fallSpeed += gravity;
		}else {
			inAir = false;
			hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, fallSpeed);
			tileY = (int) (hitbox.y + hitbox.height) / gameClass.TILES_SIZE;
		}
	}
	
	protected void move(int[][] lvlData) {
		float xSpeed = 0;
		
		if(walkDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;
		
		if(CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
			if(IsFloor(hitbox, xSpeed, lvlData)) {
				hitbox.x += xSpeed;
				return;
			}
		
		changeWalkDir();
	}
	
	protected void turnTowardsPlayer(playerClass player) {
		if(player.hitbox.x > hitbox.x)
			walkDir = RIGHT;
		else
			walkDir = LEFT;
		
	}
	
	protected boolean canSeePlayer(int[][] lvlData, playerClass player) {
		int playerTileY = (int) ((player.getHitbox().y + player.getHitbox().height) / gameClass.TILES_SIZE);
		if(playerTileY == tileY)
			if(isPlayerInRange(player)) {
				if(IsSightClear(lvlData, hitbox, player.hitbox, tileY))
					return true;
			}
		return false;
				
	}
	
	protected boolean isPlayerInRange(playerClass player) {
		int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
		return absValue <= attackDistance * 5;
	}

	protected boolean isPlayerAttackable(playerClass player) {
		int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
		return absValue <= attackDistance;
	}
	
	protected void newState(int enemyState) {
		this.enemyState = enemyState;
		aniTick = 0;
		aniIndex = 0;
	}
	
	public void hurt(int amount) {
		currentHealth -= amount;
		if(currentHealth <= 0)
			newState(DEAD);
		else
			newState(HIT);
		
	}
	
	protected void checkPlayerHit(Rectangle2D.Float attackBox, playerClass player) {
		if(attackBox.intersects(player.hitbox))
			player.changeHealth(-GetEnemyDmg(enemyType));
		attackChecked = true;
	}
	
	
	protected void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
				aniIndex = 0;
				
				switch(enemyState) {
					case DEAD -> active = false;
					case ATTACK, HIT -> newState(IDLE);
				}
			}
		}
	}
	
	protected void changeWalkDir() {
		if(walkDir == LEFT)
			walkDir = RIGHT;
		else
			walkDir = LEFT;
	}
	
	public void resetEnemy() {
		hitbox.x = x;
		hitbox.y = y;
		firstUpdate = true;
		currentHealth = maxHealth;
		newState(IDLE);
		active = true;
		fallSpeed = 0;
	}

	public int getAniIndex() {
		return aniIndex;
	}
	public int getEnemyState() {
		return enemyState;
	}
	public boolean isActive() {
		return active;
	}
	
	
	
}
