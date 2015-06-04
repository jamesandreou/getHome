package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;



public class World {

	private Scene gameScene;
	private Group rootNode;
	public static Timeline gameLoop;
	
	private final int WINDOW_X;
	private final int WINDOW_Y;

	private final int framesPerSecond;
	
	private GameLogic logic;
	protected double MOUSE_X;
	protected double MOUSE_Y;
	
	
	public World(){
		framesPerSecond = 60;
		WINDOW_X = 960;
		WINDOW_Y = 780;
		logic = new GameLogic();
		buildGameLoop();
		initGame();
	}

	@SuppressWarnings("unchecked")
	protected final void buildGameLoop(){
		final Duration timeOneFrame = Duration.millis(1000/getFramesPerSecond());
		final KeyFrame oneFrame = new KeyFrame(timeOneFrame, new EventHandler() {
			@Override
			public void handle(Event event) {
				logic.updateData(MOUSE_X,MOUSE_Y);
				logic.updateSprites();
			}
		}); 
		 setGameLoop(TimelineBuilder.create()
	                .cycleCount(Animation.INDEFINITE)
	                .keyFrames(oneFrame)
	                .build());
	}
	
	private void initGame(){
		rootNode = logic.getNodes();
		gameScene = new Scene(rootNode,WINDOW_X,WINDOW_Y);
		gameScene.setCursor(new ImageCursor(Textures.CURSOR,
				Textures.CURSOR.getWidth() / 2,
				Textures.CURSOR.getHeight() /2));
		gameScene.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				MOUSE_X = e.getX();
				MOUSE_Y = e.getY();
			}
		});
		gameLoop.play();
	}


	
	public Scene getGameScene() {
		return gameScene;
	}


	public int getFramesPerSecond() {
		return framesPerSecond;
	}
	
	
	protected static void setGameLoop(Timeline gameLoop) {
	        World.gameLoop = gameLoop;
	}


}
