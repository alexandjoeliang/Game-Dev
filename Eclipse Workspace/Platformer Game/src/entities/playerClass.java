package entities;

import static utilz.constants.playerConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gamestates.playing;
import main.gameClass;
import utilz.constants.Directions;
import utilz.loadSave;
import static utilz.helpMethods.*;

public class playerClass extends entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 20;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 1.5f * gameClass.SCALE;
	private int[][] lvlData;
	private float xDrawOffset = 26.5f * gameClass.PLAYER_TO_TILE_RATIO * gameClass.SCALE;
	private float yDrawOffset = 18 * gameClass.PLAYER_TO_TILE_RATIO * gameClass.SCALE;
	private int dir;
	
	//jumping and.or gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * gameClass.SCALE;
	private float jumpSpeed = -3.5f * gameClass.SCALE;
	private float fallSpeedAfterCollision = 0.5f * gameClass.SCALE;
	private boolean inAir = false;
	
	//status bar ui
	private BufferedImage statusBarImg;
	
	private int statusBarWidth = (int) (288 * gameClass.SCALE);
	private int statusBarHeight = (int) (87 * gameClass.SCALE);
	private int statusBarX = (int) (10 * gameClass.SCALE);
	private int statusBarY = (int) (10 * gameClass.SCALE);
	
	private int healthBarWidth = (int) (225 * gameClass.SCALE);
	private int healthBarHeight = (int) (5 * gameClass.SCALE);
	private int healthBarXStart = (int) (52 * gameClass.SCALE);
	private int healthBarYStart = (int) (22 * gameClass.SCALE);
	
	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	private int healthWidth = healthBarWidth;
	
	//Attack box
	private Rectangle2D.Float attackBox;
	
	private int flipX = 0;
	private int flipW = 1;
	
	private boolean attackChecked;

	private playing play;
	public playerClass(float x, float y, int width, int height, playing playing) {
		super(x, y, width, height);
		this.play = playing;
		loadAnimations();
		initHitbox(x, y, (int) (11 * gameClass.PLAYER_TO_TILE_RATIO * gameClass.SCALE - 1), (int) (39 * gameClass.PLAYER_TO_TILE_RATIO * gameClass.SCALE - 1));
		initAttackBox();
	}

	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (128 * gameClass.SCALE), (int) (80 * gameClass.SCALE));
		
	}

	public void update() {
		updateHealthBar();
		
		if(currentHealth <= 0)
			play.setGameOver(true);
		
		
		
		updatePos();
		updateAttackBox();
		
		if(attacking)
			checkAttack();
			
		updateAnimationTick();
		setAnimation();
	}

	private void checkAttack() {
		if(attackChecked /*|| (aniIndex < 3 || aniIndex > 8)*/) {
			return;
		}
		
		play.checkEnemyHit(attackBox);
		attackChecked = true;
	}

	private void updateAttackBox() {
		if(right) {
			attackBox.x = hitbox.x - (int) (gameClass.SCALE * 20);
		}else if(left) {
			attackBox.x = hitbox.x + (int) (gameClass.SCALE * 20) - attackBox.width + hitbox.width;
		}
		attackBox.y = hitbox.y + (gameClass.SCALE * 50);
	}

	private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
	}

	public void render(Graphics g, int lvlOffset) {
		g.drawImage(animations[playerAction][aniIndex],
				(int) (hitbox.x - xDrawOffset) - lvlOffset + flipX,
				(int) (hitbox.y - yDrawOffset),
				width * flipW, height, null);
		//drawHitbox(g, lvlOffset);
		//drawAttackBox(g, lvlOffset);
		drawUI(g);
	}

	private void drawAttackBox(Graphics g, int xlvlOffset) {
		g.setColor(Color.red);
		g.drawRect((int) (attackBox.x - xlvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
	}

	private void drawUI(Graphics g) {
		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
		g.setColor(Color.red);
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
		
	}

	private void updateAnimationTick() {
		if(playerAction == ATTACK_1)
			aniSpeed = 10;
		
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				if(playerAction != JUMP && playerAction != FALLING) {
					aniIndex = 0;
				}
				attacking = false;
				attackChecked = false;
				aniSpeed = 20;
			}
			if(aniIndex < GetSpriteAmount(playerAction)) {
				aniIndex++;
			}

		}

	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving) {
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}
		
		if (inAir) {
			if(airSpeed < 0) {
				playerAction = JUMP;
			}
			else {
				playerAction = FALLING;
				
			}
		}
		
		if (attacking)
			playerAction = ATTACK_1;

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;
		
		
		if(jump) {
			jump();
		}
		
		if(!left && !right && !inAir || (left && right)) {
			return;
		}
		
		float xSpeed = 0;
		
		
		
		if(attacking) {
			if (left) {
				xSpeed -= playerSpeed;
			}
			if (right) {
				xSpeed += playerSpeed;
			}
		} else {
			if (left) {
				xSpeed -= playerSpeed;
				flipX = width;
				flipW = -1;
			}
			if (right) {
				flipX = 0;
				flipW = 1;
				xSpeed += playerSpeed;
			}
		
		}
			
		if(!inAir) {
			if(!IsEntityOnFloor(hitbox, lvlData)) {
				inAir = true;
			}
		}
		
		if(inAir) {
			if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			}else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if(airSpeed > 0) {
					resetInAir();
				}else {
					airSpeed = fallSpeedAfterCollision;
				}
				updateXPos(xSpeed);
			}
			
		}else {
			updateXPos(xSpeed);
			
		}
		moving = true;
		

		/*if(CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
			moving = true;
		}*/
		
		/*if (up && !down) {
			ySpeed = -playerSpeed;
			dir = Directions.UP;
			
		} else if (down && !up) {
			ySpeed = playerSpeed;
			dir = Directions.DOWN;
		}

		if(CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.y += ySpeed;
			moving = true;
		}*/
		

	}

	private void jump() {
		if(inAir) {
			return;
		}
		inAir = true;
		airSpeed = jumpSpeed;
		
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}

	private void updateXPos(float xSpeed) {
		if(CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		}else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
			
		}
	}

	public void changeHealth(int value) {
		currentHealth += value;
		
		if(currentHealth <= 0) {
			currentHealth = 0;
			//gameover
		}else if(currentHealth >= maxHealth) {
			currentHealth = maxHealth;
			
		}
	}
	
	private void loadAnimations() {
		BufferedImage img = loadSave.GetSpriteAtlas(loadSave.PLAYER_ATLAS);

			animations = new BufferedImage[7][12];
			for (int j = 0; j < animations.length; j++)
				for (int i = 0; i < animations[j].length; i++)
					animations[j][i] = img.getSubimage(i * 64, j * 64, 64, 64);

			statusBarImg = loadSave.GetSpriteAtlas(loadSave.STATUS_BAR);
			

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!IsEntityOnFloor(hitbox, lvlData)) {
			inAir = true;
		}
	}
	
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
		jump = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
		
	}

	public void resetAll() {
		resetDirBooleans();
		inAir = false;
		attacking = false;
		moving = false;
		playerAction = IDLE;
		currentHealth = maxHealth;
		
		hitbox.x = x;
		hitbox.y = y;
		
		if(!IsEntityOnFloor(hitbox, lvlData)) {
			inAir = true;
		}
		
	}

}