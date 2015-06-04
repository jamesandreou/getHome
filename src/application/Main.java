package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	
	public static World world;
	
	private Scene initGame(){
		world = new World();
		return world.getGameScene();
	}
	
	@Override
	public void start(Stage primaryStage) {
			primaryStage.setScene(initGame());
			primaryStage.setTitle("Get Home ALPHA");
			primaryStage.setResizable(false);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
