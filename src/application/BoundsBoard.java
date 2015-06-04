package application;

import javafx.geometry.Bounds;
import javafx.scene.Group;

public class BoundsBoard{

	private int[][] sequence;	
	private int seqCtr;
	private Explosion[] expArr;
	private int waitTime;
	private boolean active;
	
	public BoundsBoard(Double x, Double y, int[][] sequence) {
		expArr = new Explosion[10];
		this.sequence = sequence;
		seqCtr = -1;
		waitTime = 0;
		x-=48;
		y-=42;
		for (int i = 0; i < 10; i+=2){
			expArr[i]= new Explosion(x+ (112* i) / 2, y);
			expArr[i+1]= new Explosion(x+ (112* i) / 2, y+128);
		}
		active = true;
	}

	public void updateCycle(){
		if(active){
			if(waitTime == 0){
				nextSequence();
				for(int i = 0; i < 10; i++){
					if(sequence[seqCtr % sequence.length][i] > 0){
						expArr[i].play();
						if(waitTime<=0) waitTime = sequence[seqCtr % sequence.length][i];
					}
				}
			}else{
				waitTime--;
			}
		}
	}

	public void updateSprites(){
		for(int i = 0; i < 10; i++){
			expArr[i].update();
		}
	}
	
	public void addNodes(Group g){
		for(int i = 0; i < 10; i++){
			g.getChildren().add(expArr[i].node);
		}
	}
	
	public boolean soldierHit(Bounds b){
		for(int i = 0; i < 10; i++){
			if(expArr[i].bounds.intersects(b) &&
					expArr[i].getCurrentFrame() < 40 &&
					expArr[i].active) return true;
		}
		return false;
	}
	
	private void nextSequence(){
		resetExplosions();
		seqCtr++;
	}
	

	private void resetExplosions(){
		for(int i = 0; i < 10; i++){
			expArr[i].stop();
		}
	}

	public int[][] getSequence() {
		return sequence;
	}

	public void setSequence(int[][] sequence) {
		this.sequence = sequence;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
