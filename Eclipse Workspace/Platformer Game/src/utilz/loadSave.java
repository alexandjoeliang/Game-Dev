package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.slime;
import main.gameClass;

import static utilz.constants.EnemyConstants.SLIME;

public class loadSave {

	public static final String PLAYER_ATLAS = "/bettyspritesheet.png";
	public static final String LEVEL_ATLAS = "/outside_sprites.png";
	//public static final String LEVEL_ONE_DATA = "/frame1_data.png";
	public static final String LEVEL_ONE_DATA = "/frame2_data.png";
	public static final String MENU_BUTTONS = "/menu_buttons.png";
	public static final String MENU_BACKGROUND = "/menu_background.png";
	public static final String MENU_BACKGROUND_IMAGE = "/menu_bg.png";
	public static final String PAUSE_BACKGROUND = "/pause_menu.png";
	public static final String SOUND_BUTTONS = "/sound_button.png";
	public static final String URM_BUTTONS = "/urm_buttons.png";
	public static final String VOLUME_BUTTONS = "/volume_buttons.png";
	
	public static final String STATUS_BAR = "/health_power_bar.png";
	
	public static final String SLIME_SPRITE = "/SlimeSheet.png";
	
	public static final String LVL_BG_ONE = "/levelBG/1.png";
	public static final String LVL_BG_TWO = "/levelBG/2.png";
	public static final String LVL_BG_THREE = "/levelBG/3.png";
	public static final String LVL_BG_FOUR = "/levelBG/4.png";
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = loadSave.class.getResourceAsStream(fileName);
		try {
			img = ImageIO.read(is);


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static ArrayList<slime> getSlimes(){
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<slime> list = new ArrayList<>();
		for(int j = 0; j < img.getHeight(); j++)
			for(int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if(value == SLIME) 
					list.add(new slime(i * gameClass.TILES_SIZE, j * gameClass.TILES_SIZE));
			}
		
		return list;
	}
	
	
	public static int[][] GetLevelData(){
		
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		
		for(int j = 0; j < img.getHeight(); j++) {
			for(int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if(value >= 48) {
					value = 0;
				}
				lvlData[j][i] = value;
			}
			
		}
		return lvlData;
		
	}
	
}
