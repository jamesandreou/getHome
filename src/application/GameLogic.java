package application;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

import javafx.scene.Group;


public class GameLogic {
	
	private Group spriteNodes;	
	private Soldier SOLDIER;
	private BoundsBoard topBoard;
	private BoundsBoard botBoard;
	private LevelData[] Data;
	private final int numLevels = 3;
	private Gui GUI;
	private Map map;
	private int deathTimer;
	private boolean gameActive;
	
	public GameLogic (){
		init();
	}
	
	private void init(){
		spriteNodes = new Group();
		map = new Map();
		GUI = new Gui(5);
		SOLDIER = new Soldier(845.0,245.0);
		deathTimer = 128;
		gameActive = true;
		loadLevels();
		loadInitialSprites();
		spawn('a');
	}
	
	public void updateData(Double mx, Double my) {
		if(gameActive){
			updatePlayer(mx,my);
			topBoard.updateCycle();
			botBoard.updateCycle();
			GUI.update();
		}else{
			
		}	
	}
	
	
	public void updateSprites(){
		SOLDIER.update(map);
		topBoard.updateSprites();
		botBoard.updateSprites();
	}
	
	private void updatePlayer(Double mx, Double my){
		if(deathTimer == 128){
			checkCollisions();
			updateSoldierMovement(mx,my);
		}else if(deathTimer <= 0){
			if(SOLDIER.y > 350.0){
				spawn('b');
			}else{
				spawn('a');
			}
			deathTimer = 128;
		}else{
			deathTimer--;
		}
	}
	
	private void updateSoldierMovement(Double mx, Double my) {
		Double centerX = SOLDIER.getCenterX();
		Double centerY = SOLDIER.getCenterY();
		Double yComp = (my-centerY);
		Double xComp = (mx-centerX);
		Double newAngle = Math.atan(yComp / xComp);
		if(SOLDIER.collide2(mx, my)){
			SOLDIER.vX = 0.0;
			SOLDIER.vY = 0.0;
		}else{
			Double newVX = 5 * Math.cos(Math.abs(newAngle));
			Double newVY = 5 * Math.sin(Math.abs(newAngle));
		    SOLDIER.vX = mx > centerX ? newVX : -newVX;
			SOLDIER.vY = my > centerY ? newVY : -newVY;
		}
		newAngle = Math.toDegrees(newAngle);
		newAngle = xComp >= 0 ? 90.0 + newAngle : 270.0 + newAngle;
		SOLDIER.setRotationAngle(newAngle);
	}

	private void checkCollisions(){
		if((topBoard.soldierHit(SOLDIER.bounds) ||
				botBoard.soldierHit(SOLDIER.bounds)) &&
				deathTimer == 128){
			died();
		}
		if(map.levelUp(SOLDIER.bounds)) nextLevel();
	}
	
	private void died() {
		SOLDIER.kill();
		deathTimer--;
		GUI.setLives(GUI.getLives()-1);
		if(GUI.getLives()<= 0){
			gameOver();
		}
	}

	private void gameOver(){
		topBoard.setActive(false);
		botBoard.setActive(false);
		gameActive = false;
		map.darken(true);
		GUI.setActive(false);
	}
	
	private void loadInitialSprites() {
		spriteNodes.getChildren().addAll(map.getGroundNode(),SOLDIER.node);	
		topBoard.addNodes(spriteNodes);
		botBoard.addNodes(spriteNodes);
		spriteNodes.getChildren().addAll(map.getWallsNode(),GUI.getNodes());
	}

	public Group getNodes(){
		return spriteNodes;
	}

	private void loadLevels(){
		Data = new LevelData[numLevels];
		Data[0] = new LevelData(Levels.LEVEL_1_TOP,Levels.LEVEL_1_BOT);
		Data[1] = new LevelData(Levels.LEVEL_2_TOP,Levels.LEVEL_2_BOT);
		Data[2] = new LevelData(Levels.LEVEL_3_TOP,Levels.LEVEL_3_BOT);
		GUI.setLevel(0);
		topBoard = new BoundsBoard(160.0,109.0,Data[GUI.getLevel()].getTopData());
		botBoard = new BoundsBoard(160.0,442.0,Data[GUI.getLevel()].getBotData());
	}
	
	private void nextLevel(){
		GUI.setLevel(GUI.getLevel() < numLevels-1 ? GUI.getLevel()+1 : 0);
		topBoard.setSequence(Data[GUI.getLevel()].getTopData());
		botBoard.setSequence(Data[GUI.getLevel()].getBotData());
		GUI.setLives(GUI.getLives()+GUI.getLevel()+1);
		spawn('a');
		map.randomize();
	}
	
	private void spawn(char point){
		if(point=='a'){
			SOLDIER.x = 845.0;
			SOLDIER.y = 245.0;
		}else{
			SOLDIER.x = 100.0;
			SOLDIER.y = 400.0;
		}
		SOLDIER.active=true;
		initMouse(point);
	}
	
	private void initMouse(char point){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		int xStart=0;
		int yStart=0;
		try {
			if(point == 'a'){
				xStart = (int) (((screenWidth-980) / 2) + 845);
			    yStart = (int) (((screenHeight-800) / 2) + 245);
			}else{
				xStart = (int) (((screenWidth-980) / 2) + 100);
			    yStart = (int) (((screenHeight-800) / 2) + 400);
			}
		    Robot robot = new Robot();
		    robot.mouseMove(xStart, yStart);
		} catch (AWTException e) {
		}
	}
	
}
