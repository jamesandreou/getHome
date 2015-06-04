package application;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Explosion extends Sprite{

	//bounds 112x118
	
	private Image frames[];
	private int currentFrame;
	private int explosionType;
	
	public Explosion(Double px, Double py){
		setExplosionType(0);
		node = new ImageView(frames[0]);
		x = px;
		y = py;
		node.setTranslateX(x);
		node.setTranslateY(y);
		bounds = new BoundingBox(x+62,y+56,60,68);
		stop();
	}
	

	
	private void nextFrame(){
		if(currentFrame < 63){
			currentFrame++;
		}else{
			stop();
		}
	}
	
	public void update() {
		nextFrame();
		if(active){
			node.setImage(frames[currentFrame]);
		}
	}

	public void play(){
		node.setVisible(true);
		active = true;
	}
	
	
	public void stop(){
		currentFrame = 0;
		active = false;
		node.setVisible(false);
	}
	
	public int getCurrentFrame(){
		return currentFrame;
	}



	public int getExplosionType() {
		return explosionType;
	}



	public void setExplosionType(int explosionType) {
		switch (explosionType){
		default: frames=Textures.EXPLOSION_0;
		break;
		}
	}
}
