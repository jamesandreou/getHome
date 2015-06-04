package application;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Gui {

	private ImageView bg;
	private Text liveLabel;
	private Text levelLabel;
	private int level;
	private int lives;
	private boolean active;

	public Gui (int lives){
		bg = new ImageView(Textures.GUI_BG);
		level = 0;
		this.lives = lives;
		liveLabel = new Text();
		levelLabel = new Text();
		bg.setTranslateX(2);
		bg.setTranslateY(700);
		liveLabel.setTranslateX(32);
		liveLabel.setTranslateY(730);
		levelLabel.setTranslateX(32);
		levelLabel.setTranslateY(770);
		Font f = new Font("Arial",20);
		liveLabel.setFont(f);
		levelLabel.setFont(f);
		setActive(true);
		update();
	}
	
	public Group getNodes(){
		Group g = new Group();
		g.getChildren().addAll(bg,liveLabel,levelLabel);
		return g;
	}
	
	public void update(){
		if(active){
			liveLabel.setText("Lives: " + lives);
			levelLabel.setText("Level: " + Integer.toString(level+1));
		}
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		if(active){
			bg.setVisible(true);
			levelLabel.setVisible(true);
			liveLabel.setVisible(true);
		}else{
			bg.setVisible(false);
			levelLabel.setVisible(false);
			liveLabel.setVisible(false);
		}
		this.active = active;
	}

	

	
}
