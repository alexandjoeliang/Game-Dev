package utilz;

import java.awt.geom.Rectangle2D;

import main.gameClass;
import utilz.constants.Directions;

public class helpMethods {
	
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		
			//left
			for(int yStep = 0; yStep < ((int) (height)) / 2; yStep += gameClass.SCALE * gameClass.PLAYER_TO_TILE_RATIO) {
				if(IsSolid(x, y + yStep, lvlData)) {
					return false;
				}
				if(IsSolid(x, y + height - yStep, lvlData)) {
					return false;
				}
			}
			//up
			/*for(int xStep = 0; xStep < ((int) (width)) / 2; xStep += gameClass.SCALE * gameClass.PLAYER_TO_TILE_RATIO) {
				if(IsSolid(x + xStep, y, lvlData)) {
					return false;
				}
				if(IsSolid(x + width - xStep, y, lvlData)) {
					return false;
				}
			}*/
			//right
			for(int yStep = 0; yStep < ((int) (height)) / 2; yStep += gameClass.SCALE * gameClass.PLAYER_TO_TILE_RATIO) {
				if(IsSolid(x + width, y + yStep, lvlData)) {
					return false;
				}
				if(IsSolid(x + width, y + height - yStep, lvlData)) {
					return false;
				}
			}
			//down
			/*for(int xStep = 0; xStep < ((int) (width)) / 2; xStep += gameClass.SCALE * gameClass.PLAYER_TO_TILE_RATIO) {
				if(IsSolid(x + xStep, y + height, lvlData)) {
					return false;
				}
				if(IsSolid(x + width - xStep, y + height, lvlData)) {
					return false;
				}
			}*/

		
		
		return true;
	}
	
		
	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		
		int maxWidth = lvlData[0].length * gameClass.TILES_SIZE;
		
		if(x < 0 || x >= maxWidth) {
			
			return true;
			
		}
		if(y < 0 || y >= gameClass.GAME_HEIGHT) {
			
			return true;
			
		}
		float xIndex = x / gameClass.TILES_SIZE;
		float yIndex = y / gameClass.TILES_SIZE;
		
		return IsTileSolid((int)xIndex, (int)yIndex, lvlData);
		
	}
	
	public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
		int value = lvlData[yTile][xTile];
		
		if(value >= 48 || value < 0 || value != 11)
			return true;
		return false;	
		
	}
	
	
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile;
		
		if(xSpeed > 0) {
			//right
			currentTile = (int)((hitbox.x + hitbox.width) / gameClass.TILES_SIZE);
			int tileXPos = currentTile * gameClass.TILES_SIZE;
			int xOffset = (int)(gameClass.TILES_SIZE - hitbox.width);
			
			return tileXPos + xOffset - (1 / gameClass.PLAYER_TO_TILE_RATIO);
			
		}else{
			//left
			currentTile = (int)(hitbox.x / gameClass.TILES_SIZE);
			return currentTile * gameClass.TILES_SIZE;
			
		}
		
	}
	
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile;
		
		if(airSpeed > 0) {
			//jumping or hitting roof
			currentTile = (int)((hitbox.y + hitbox.height) / gameClass.TILES_SIZE);
			int tileYPos = currentTile * gameClass.TILES_SIZE;
			int yOffset = (int)(gameClass.TILES_SIZE - hitbox.height);
			
			return tileYPos + yOffset - (1 / gameClass.PLAYER_TO_TILE_RATIO);
			
		} else {
			//falling or touching floor
			currentTile = (int)(hitbox.y / gameClass.TILES_SIZE);
			return currentTile * gameClass.TILES_SIZE;
			
		}
		
		
	}
	
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		//check pixels below bottom
		if(!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
			if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData){
		if(xSpeed < 0)
			return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
		else
			return IsSolid(hitbox.x + xSpeed + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);
	}
	
	public static boolean IsAllTileWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
		for(int i = 0; i < xEnd - xStart; i++) {
			if(IsTileSolid(xStart + i, y, lvlData))
				return false;	
			if(!IsTileSolid(xStart + i, y + 1, lvlData))
				return false;	
		}
		return true;
	}
	
	public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float hitbox1, Rectangle2D.Float hitbox2, int yTile) {
		int xTile1 = (int) (hitbox1.x / gameClass.TILES_SIZE);
		int xTile2 = (int) (hitbox2.x / gameClass.TILES_SIZE);
		
		if(xTile1 > xTile2) 
			return IsAllTileWalkable(xTile2, xTile1, yTile, lvlData);
		else
			return IsAllTileWalkable(xTile1, xTile2, yTile, lvlData);
			

	}
	
	
	
	
}
