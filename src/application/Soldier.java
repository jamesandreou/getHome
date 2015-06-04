package application;


import javafx.geometry.BoundingBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Soldier extends Sprite{

	//hitbox 72x65
	private int currentFrame;
	private final Image[] frames = {new Image("/assets/fb_0.png"),
			new Image("/assets/fb_1.png"),
			new Image("/assets/fb_2.png"),
			new Image("/assets/fb_3.png")};
	private long totalUpdates;
	private Double rotationAngle;
	private final Double width = frames[0].getWidth();
	private final Double height = frames[0].getHeight();
	private Double centerX;
	private Double centerY;
	private DropShadow shad;
	
	public Soldier(Double x, Double y){
		setRotationAngle(57.0);
		shad = new DropShadow();
		shad.setRadius(2.0);
		shad.setOffsetX(12.0);
		shad.setOffsetY(12.0);
		this.x = x;
		this.y = y;
		currentFrame = 0;
		totalUpdates = 0;
		this.node = new ImageView(frames[currentFrame]);
		node.setTranslateX(this.x);
		node.setTranslateY(this.y);
		node.setEffect(shad);
		setCenterX();
		setCenterY();
		bounds = new BoundingBox(x,y,node.getImage().getWidth(),node.getImage().getHeight());
	}
	
	public void update(Map map) {
		if(active){
			setCenterX();
			setCenterY();
			BoundingBox b = new BoundingBox(x+vX,y+vY,node.getImage().getWidth(),node.getImage().getHeight());
			if(map.noWall(b)){
				x += vX;
				y += vY;
			}
			if(totalUpdates % 10 == 0 || (vX == 0.0 && vY ==  0.0)) nextFrame();
			node.setTranslateX(x);
			node.setTranslateY(y);
			node.setRotate(rotationAngle);
			node.setImage(frames[currentFrame]);;
			updateBounds();
			totalUpdates++;
		}
	}
	
	public void kill(){
		node.setImage(frames[3]);
		node.setTranslateX(x -frames[3].getWidth()/4);
		node.setTranslateY(y - frames[3].getHeight()/4);
		vX=0;
		vY=0;
		node.setEffect(null);
		active = false;
	}
	
	private void nextFrame(){
		if(vX == 0.0 && vY ==  0.0){
			currentFrame = 0;
		}else{
			currentFrame = currentFrame == 1 ? 2 : 1;
		}
	}
	
	
	private void updateBounds(){
		bounds = new BoundingBox(x,y,node.getImage().getWidth(),node.getImage().getHeight());
	}
	
	public Double getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(Double rotationAngle) {
		assert rotationAngle >= 0.0 && rotationAngle <= 360.0;
		this.rotationAngle = rotationAngle;
	}


	public Double getCenterX() {
		return centerX;
	}

	public void setCenterX() {
		centerX = x + width / 2;
	}

	public Double getCenterY() {
		return centerY;
	}

	public void setCenterY() {
		centerY = y + height / 2;
	}

	public Double getWidth() {
		return width;
	}

	public Double getHeight() {
		return height;
	}

	public boolean collide2(Double px, Double py) {
		Double minX = this.x + width / 3;
		Double maxX = this.x + (width / 3) * 2;
		Double minY = this.y + height / 3;
		Double maxY = this.y + (height / 3) * 2;
		if(px >= minX && px <= maxX && py >= minY && py <= maxY) return true;
		return false;
	}

	
}
