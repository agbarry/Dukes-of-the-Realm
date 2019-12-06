package edu.duckesoftherealm;

import java.util.ArrayList;

import java.util.Random;

//import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Main extends Application {
	
	private Random rnd = new Random();
	
	private Pane playfieldLayer;

	private Image castleNormalImage;
	private Image playerImage;
	private Image soldierImage;
	private Image gateImage;
	
	private String playerName;
	private String soldierName;

	private Player player;
	private CastleNormal castleNormal;
	private Gate gate;
	
	private Troop troop1 = Troop.Piquier;
	
	private ArrayList<CastleNormal> castlesNormal = new ArrayList<>();
	private ArrayList<Soldier> soldiers = new ArrayList<>();
	
	private boolean collision = false;

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
		primaryStage.setResizable(false);
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
		castleNormalImage = new Image(getClass().getResource("/images/porte.jpg").toExternalForm(), 160, 160, true, true);
		
		playerImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), 20, 20, true, true);
		playerName = "Aguibou";
		
		soldierImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), 10, 10, true, true); 
		soldierName = "Soldat";
		
		gateImage = new Image(getClass().getResource("/images/porte1.jpg").toExternalForm(), 20, 20, true, true);
		
		
		for(int i=0; i<3; i++) 
			createCaste();
	
		createStatusBar();	
	}
	
	public void createStatusBar() {
		HBox statusBar = new HBox();
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, Settings.SCENE_HEIGHT);
		statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}	
	
	private void createCaste() {
		
		int x = rnd.nextInt(820) + 5;
		int y = rnd.nextInt(620)+ 5;
		
		/*System.out.println("X ="+x+" Y="+y);
		System.out.println("ImageX="+castleNormalImage.getWidth()+" ImageY="+castleNormalImage.getHeight());*/
		
		if( (x+castleNormalImage.getWidth())>=Settings.SCENE_WIDTH)
			x -= castleNormalImage.getWidth()-10;
		if( (y+castleNormalImage.getHeight())>=Settings.SCENE_HEIGHT)
			y -= castleNormalImage.getHeight() -10;
		
		Displacement displacement = new Displacement();
		Production_Unit product = new Production_Unit(); 
		
		// Création du chateaux
		castleNormal = new CastleNormal(castleNormalImage, playfieldLayer, x, y, 200.5, 1, null, product, displacement, null, null);
		
//		if(castlesNormal.size()>0)
//		for(CastleNormal cN: castlesNormal) {
//			if(castleNormal.collidesWith(cN)) {
//				System.out.println("Collision");
//				if(castleNormal.getX()>=cN.getX() && castleNormal.getX()<=cN.getX()+castleNormalImage.getWidth())
//					castleNormal.setX(x+castleNormalImage.getWidth());
//				
//				if(castleNormal.getY()>=cN.getY() && castleNormal.getY()<=cN.getY()+castleNormalImage.getHeight())
//					castleNormal.setY(y+castleNormalImage.getHeight());
//				
//				castleNormal.updateCastle();	
//			}
//		}
		
		createGate(x, y);	// Création de la porte
		
		createPlayer();	// Création du joueur propriétaire du chateaux ainsi que ses soldats
		
		castleNormal.setListSoldier(soldiers);	// Modification des listes de soldats du chateaux
		castleNormal.setGate(gate);				// Moddification de la porte du chateaux
		castleNormal.setDuke(player);			// Modification du propriétaire du chateaux;
		
		castlesNormal.add(castleNormal);		// Ajout du chateaux dans la liste de chateaux
		
	}
	
	private void createPlayer() {
		double x = castleNormal.getCenterX();
		double y = castleNormal.getCenterY();
		
		player = new Player(playfieldLayer, playerImage, x, y, Settings.PLAYER_HEALTH, Settings.PLAYER_DAMAGE, input, playerName);
		
		Soldier soldier = new Soldier(playfieldLayer, soldierImage, x-20, y-30, Settings.SOLDIER_HEALTH, Settings.SOLDIER_DAMAGE, troop1, Settings.SOLDIER_SPEED);
		soldiers.add(soldier);
	}
	
	public void createGate(int x, int y) {
		int n = rnd.nextInt(4)+1;
		double gateX,gateY;
		if(n==1) {	// Pour mettre la porte vers le nord
			gateX = x+(castleNormalImage.getWidth()/2)-(gateImage.getWidth()/2);
			gateY = y;
		}
		else if(n==2) {	// Pour mettre la porte vers le sud
			gateX = x+(castleNormalImage.getWidth()/2)-(gateImage.getWidth()/2);
			gateY = y+castleNormalImage.getHeight()-gateImage.getHeight();
		}
		else if(n==3) {	// Pour mettre la porte vers l'Est
			gateX = x;
			gateY = y+(castleNormalImage.getHeight()/2)-(gateImage.getHeight()/2);
		}
		else {	// Pour mettre la porte vers l'Ouest
			gateX = x+castleNormalImage.getWidth()-gateImage.getWidth();
			gateY = y+(castleNormalImage.getHeight()/2)-(gateImage.getHeight()/2);
		}
		
		gate = new Gate(gateImage, playfieldLayer, gateX, gateY);	// Création de la porte.
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}

}