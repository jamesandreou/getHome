package application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Textures {

	//Explosions
	public static Image[] EXPLOSION_0 = loadExplosion("/assets/exp_64.png");
	//public static Image[] EXPLOSION_1 = loadExplosion("/assets/exp_48.png");
	//Terrain
	public static Image GROUND = new Image("/assets/map.png");
	public static Image WALLS = new Image("/assets/walls.png");
	public static Image CURSOR = new Image("/assets/fb_cursor.png");
	//GUI
	public static Image GUI_BG = new Image("/assets/gui_bg.png");
	
	private static Image[] loadExplosion(String file){
		Image sheet = new Image(file);
		final int len = (int) (sheet.getWidth() / sheet.getHeight());
		final int dim = (int) sheet.getHeight();
		Image newFrames[] = new Image[len];
		PixelReader reader = sheet.getPixelReader();
		WritableImage newImage;
		for(int i = 0; i < len; i++){
			newImage = new WritableImage(reader, i * dim, 0, dim, dim);
			newFrames[i] = newImage;
		}
		return newFrames;
	}
	
}
