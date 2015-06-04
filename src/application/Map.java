package application;

import java.util.Random;

import javafx.geometry.BoundingBox;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;

public class Map {

	private ImageView groundNode;
	private ImageView wallsNode;
	private BoundingBox topWall;
	private BoundingBox sideWall;
	private BoundingBox midWall;
	private BoundingBox botWall;
	private BoundingBox nextLevel;
	
	
	public Map(){
		topWall = new BoundingBox(0.0,0.0,980.0,109.0);
		botWall = new BoundingBox(0.0,680.0,980.0,800.0);
		sideWall = new BoundingBox(0.0,0.0,30.0,800.0);
		midWall = new BoundingBox(170.0,344.0,980.0,100.0);
		nextLevel = new BoundingBox(860.0,445.0,120.0,235.0);
		groundNode = new ImageView(Textures.GROUND);
		wallsNode = new ImageView(Textures.WALLS);
		randomize();
	}
	
	
	public ImageView getGroundNode() {
		return groundNode;
	}
	public ImageView getWallsNode() {
		return wallsNode;
	}

	public Boolean noWall(BoundingBox sold){
		if(topWall.intersects(sold) ||
				sideWall.intersects(sold) ||
				midWall.intersects(sold) ||
				botWall.intersects(sold)){
			return false;
		}
		return true;
	}
	
	public Boolean levelUp(BoundingBox sold){
		if(nextLevel.intersects(sold)) return true;
		return false;
	}
	
	public void randomize(){
		Random r = new Random();
		double hue = r.nextInt(100) / 100.0;
		groundNode.setEffect(new ColorAdjust(hue,1.0,0,0));
	}

	public void darken(boolean dark){
		if(dark){
			ColorAdjust ca = new ColorAdjust();
			ca.setBrightness(-0.8);
			getGroundNode().setEffect(ca);
			getWallsNode().setEffect(ca);
		}else{
			getGroundNode().setEffect(null);
			getWallsNode().setEffect(null);
		}
	}
	
}
