package utilz;

import main.gameClass;

public class constants {

	public static class EnemyConstants {
		public static final int SLIME = 0;
		
		public static final int IDLE = 0;
		public static final int WALKING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;
		
		public static final int SLIME_WIDTH_DEFAULT = 32;
		public static final int SLIME_HEIGHT_DEFAULT = 32;
		public static final int SLIME_SCALE = 3;
		public static final int SLIME_WIDTH = (int) (SLIME_WIDTH_DEFAULT * gameClass.SCALE * SLIME_SCALE);
		public static final int SLIME_HEIGHT = (int) (SLIME_HEIGHT_DEFAULT * gameClass.SCALE * SLIME_SCALE);
		
		public static final int SLIME_DRAWOFFSET_X = (int)(6 * gameClass.SCALE * SLIME_SCALE);
		public static final int SLIME_DRAWOFFSET_Y = (int)(15 * gameClass.SCALE * SLIME_SCALE);
		
		public static int GetSpriteAmount(int enemyType, int enemyState) {
			
			switch(enemyType) {
			case SLIME:
				switch(enemyState) {
				case IDLE:
					return 4;
				case WALKING:
				case ATTACK:
				case DEAD:
					return 5;
				case HIT:
					return 2;
				default://default amount
					return 0;
				}
			default://default enemy
				return 0;
			}
		}
		
		public static int GetMaxHealth(int enemyType) {
			switch(enemyType) {
			case SLIME:
				return 10;
			default:
				return 1;
				
			}
			
			
		}
		
		public static int GetEnemyDmg(int enemyType) {
			switch(enemyType) {
			case SLIME:
				return 10;
			default:
				return 0;
				
			}
			
		}
		
	}
	
	public static class Environment{
		public static final int TREES_WIDTH_DEFAULT = 1536;
		public static final int TREES_HEIGHT_DEFAULT = 832;
		
		public static final int TREES_WIDTH = (int) (TREES_WIDTH_DEFAULT * gameClass.SCALE);
		public static final int TREES_HEIGHT = (int) (TREES_HEIGHT_DEFAULT * gameClass.SCALE);
		
	}
	
	public static class UI{
		
		public static class Buttons{
			public static final float MB_SCALE = 1.2f;
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * gameClass.SCALE * MB_SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * gameClass.SCALE * MB_SCALE);
		}
		public static class pauseButtons{
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * gameClass.SCALE);
		}
		public static class URMButtons{
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * gameClass.SCALE);
		}
		public static class VolumeButtons{
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			
			public static final int SLIDER_DEFAULT_WIDTH = 215;
			
			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * gameClass.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * gameClass.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * gameClass.SCALE);
		}
		
	}
	
	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class playerConstants{
		/*public static final int AWAY = 0;
		public static final int LEFT = 1;
		public static final int TOWARDS = 2;
		public static final int RIGHT = 3;*/
		
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int ATTACK_1 = 4;
		public static final int HIT = 5;
		public static final int DEAD = 6;
		/*public static final int GROUND = 4;
		public static final int HIT = 5;
		public static final int ATTACK_1 = 6;
		public static final int ATTACK_JUMP_1 = 7;
		public static final int ATTACK_JUMP_2 = 8;*/

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case RUNNING:
			case IDLE:
			case ATTACK_1:
				return 11;
			case JUMP:
				return 3;
			case FALLING:
				return 2;
			case HIT:
				return 5;
			case DEAD:
				return 6;	
			/*case ATTACK_1:
			case ATTACK_JUMP_1:
			case ATTACK_JUMP_2:
				return 3;
			case GROUND:
				return 2;*/
			default:
				return 1;
			}
		}
		
		
		
	}
	
}
