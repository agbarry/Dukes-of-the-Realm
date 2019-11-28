package edu.duckesoftherealm;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

//import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
/*import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;*/

public class Main extends Application {
	
	private Random rnd = new Random();
	
	private Pane playfieldLayer;

	private Image playerImage;
	private Image soldierImage;
	
	private String playerName;
	private String soldierName;

	private Player player;
	private Soldier soldier;
	private Troop troop;
	private List<Soldier> soldiers = new ArrayList<>();

	private Scene scene;
	private Input input;
	
	
	
	Group root;

	@Override
	public void start(Stage primaryStage) {

		root = new Group();
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT + Settings.STATUS_BAR_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		
		// Create window
		primaryStage.setTitle("Dukes of the Realm"); // Setting the title to Stage
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
		
		playfieldLayer = new Pane();
		root.getChildren().add(playfieldLayer);
		
		loadGame(); // Pour le chargement du jeu.
		
		

		// create layers
		/*for (int i=100; i<Settings.SCENE_WIDTH; i+=100) {
			Rectangle r = new Rectangle(i, i, 50, 50);
			r.setFill(Color.ANTIQUEWHITE);
			root.getChildren().add(r);
		}
		
		Circle c = new Circle(500, 500, 100, Color.AQUA);
		root.getChildren().add(c);*/
		
		//Creating Translate Transition 
		/*TranslateTransition translateTransition = new TranslateTransition();   
				translateTransition.setDuration(Duration.millis(10000)); 	//Setting the duration of the transition 
				translateTransition.setNode(r); 	//Setting the node for the transition 
				translateTransition.setByX(300); 	//Setting the value of the transition along the x axis. 
				translateTransition.setCycleCount(50); 	//Setting the cycle count for the transition
				translateTransition.setAutoReverse(true); 	//Setting auto reverse value to false 
				translateTransition.play(); //Playing the animation 
		 */		
		
	}
	
	
	private void loadGame() {
		playerImage = new Image(getClass().getResource("/images/chateaux.jpg").toExternalForm(), 30, 30, true, true);
		playerName = "Aguibou";
		soldierImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), 20, 20, true, true);
		soldierName = "Soldat";
		
		for(int i=0; i<10; i++)
			createPlayer();
		
		
		createStatusBar();
		
	}
	
	public void createStatusBar() {
		HBox statusBar = new HBox();
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, Settings.SCENE_HEIGHT);
		statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}
	
	private void createPlayer() {
		double x = rnd.nextInt(860);
		double y = rnd.nextInt(860);
		//troop = Troop.Piquier;
		
		player = new Player(playfieldLayer, playerImage, x, y, Settings.PLAYER_HEALTH, Settings.PLAYER_DAMAGE, input, playerName);
		
		/*soldier = new Soldier(playfieldLayer, soldierImage, x, y, Settings.SOLDIER_HEALTH, Settings.SOLDIER_DAMAGE, troop, Settings.SOLDIER_SPEED);
		soldiers.add(soldier);*/
	}	
	

	public static void main(String[] args) {
		launch(args);
	}

}