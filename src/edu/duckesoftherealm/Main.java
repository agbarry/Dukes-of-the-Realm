package edu.duckesoftherealm;

import java.util.ArrayList;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Main extends Application {
	
	private Random rnd = new Random();
	
	private Pane playfieldLayer;

	private Image normalCastleImage; // Pour stocker l'image des chateaux normaux
	private Image playerImage;	// Pour stocker l'image des ducs
	private Image soldierImage;	// Pour stocker l'images des soldats
	private Image gateImage;	// Pour l'image de la porte
	
	private String soldierName;	// Pour le nom des soldats
	private String playerName1;

	private NormalCastle normalCastle;	// Pour les chateaux normaux
	
	private Troop troop1 = Troop.Piquier;
	private Troop troop2 = Troop.Chevalier;
	private Troop troop3 = Troop.Onagre;
	
	private ArrayList<NormalCastle> castlesNormal = new ArrayList<>();
	//private ArrayList<Soldier> soldiers = new ArrayList<>();
	
	private Text informationCastle = new Text();
	private boolean clique = false;
	//private boolean collision = false;

	private Scene scene;
	private Input input;
	private AnimationTimer gameLoop;
	
	Group root;

	@Override
	public void start(Stage primaryStage) {
		
		root = new Group();
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT + Settings.STATUS_BAR_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		
		System.out.println("La largeur de la fenêtre est: "+scene.getWidth());
		System.out.println("La hauteur de la fenêtre est: "+scene.getHeight());
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
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				processInput(input, now);

				// player input
				//player.processInput();


				// update score, health, etc
				update();
			}

			private void processInput(Input input, long now) {
				if (input.isExit()) {
					Platform.exit();
					System.exit(0);
				} 

			}

		};
		
		gameLoop.start();
		
	}
	
	
	private void loadGame() {
		normalCastleImage = new Image(getClass().getResource("/images/porte.jpg").toExternalForm(), 110, 110, true, true);
		
		playerImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), 20, 20, true, true);
		
		soldierImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), 10, 10, true, true); 
		soldierName = "Soldat";
		
		gateImage = new Image(getClass().getResource("/images/porte1.jpg").toExternalForm(), 20, 20, true, true);
		
		input = new Input(scene);
		input.addListeners();
		
		for(int i=0; i<6; i++) 
			createCaste();
		
		System.out.println("Le nombre de chateaux est: "+normalCastle.getNbreInstances());
	
		createStatusBar();	
	}
	
	public void createStatusBar() {
		HBox statusBar = new HBox();

		informationCastle.setText("Propriétaire: "+normalCastle.getDuke().getPlayerName()+"     Niveau: "+normalCastle.getLevel()+
				"     Quantité de troupes: "+normalCastle.getListSoldier().size()+"     Tresor: "+normalCastle.getTreasure());
		statusBar.getChildren().addAll(informationCastle);
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, Settings.SCENE_HEIGHT);
		statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}	
	
	private void createCaste() {
		
		NormalCastle normalCastle1;
		
		int x = rnd.nextInt(655) + Settings.BORDER_DISTANCE;
		int y = rnd.nextInt(455)+ Settings.BORDER_DISTANCE;
		
		
		Displacement displacement = new Displacement();
		Production_Unit product = new Production_Unit(); 
		
		// Création du chateaux
		normalCastle = new NormalCastle(normalCastleImage, playfieldLayer, x, y, 0.0, 1, null, product, displacement, null, input, null);
		
		normalCastle1 = normalCastle;
//		if(castlesNormal.size()>0) {	
//			for(int i=0; i<castlesNormal.size(); i++) {
//				if(normalCastle.collidesWith(castlesNormal.get(i))) {
//					System.out.println("Collision");
//					if(normalCastle.getX()>=castlesNormal.get(i).getX() && normalCastle.getX()<=castlesNormal.get(i).getX()+normalCastleImage.getWidth()) {
//						normalCastle.setX(x+normalCastleImage.getWidth()+Settings.CASTLE_DISTANCE);
//					}
//					
//					if(normalCastle.getX()<castlesNormal.get(i).getX() && normalCastle.getX()+normalCastleImage.getWidth()>=castlesNormal.get(i).getX()  ) {
//						normalCastle.setX(x-normalCastleImage.getWidth()-Settings.CASTLE_DISTANCE);
//					}
//					
//					if(normalCastle.getY()>=castlesNormal.get(i).getY() && normalCastle.getY()<=castlesNormal.get(i).getY()+normalCastleImage.getHeight()) {
//						normalCastle.setY(y+normalCastleImage.getHeight()+Settings.CASTLE_DISTANCE);
//					}
//					
//					if(normalCastle.getY()<castlesNormal.get(i).getY() && normalCastle.getY()+normalCastleImage.getHeight()>=castlesNormal.get(i).getY()  ) {
//						normalCastle.setY(y-normalCastleImage.getHeight()-Settings.CASTLE_DISTANCE);
//					}
//					
//					i=0;
//					
//					System.out.println("Après la collision la valeur de i = "+i);
//					
//					normalCastle.updateUI();	
//				}
//			}
//		}
		
		createGate();	// Création de la porte du châteaux
		createPlayer();	// Création du joueur propriétaire du chateaux ainsi que ses soldats
		createSoldier();	// Création de la liste des soldats du châteaux
		
		castlesNormal.add(normalCastle);		// Ajout du chateaux dans la liste de chateaux
		
		normalCastle1.getView().setOnMousePressed(e -> {
			clique = true;
			normalCastle = normalCastle1;
			System.out.println("Click on castle");
			e.consume();
		});
	}
	
	private void createPlayer() {
		Player player;
		String playerName = "Duke";
		
		double x = normalCastle.getCenterX();
		double y = normalCastle.getCenterY();
		
		int x1;
		if(castlesNormal.size()>0) {
			String NameTmp = castlesNormal.get(castlesNormal.size()-1).getDuke().getPlayerName().substring(4);
			x1 = Integer.parseInt(NameTmp)+1;
		}
		else
			x1 = 1;
		
		playerName = playerName+x1;
		
		player = new Player(playfieldLayer, playerImage, x, y, Settings.PLAYER_HEALTH, input, playerName);	
		normalCastle.setDuke(player);	// Modification du propriétaire du chateaux;
		
//		player.getView().setOnMousePressed(e -> {
//			clique = true;
//			playerName1 = player.getPlayerName();
//			System.out.println("Click on player");
//			e.consume();
//		});
		
	}
	
	private void createSoldier() {
		
		ArrayList<Soldier> soldiers = new ArrayList<>();
		
		double x = normalCastle.getCenterX();
		double y = normalCastle.getCenterY();
		
		int n = rnd.nextInt(40)+10;
		int nbrePiquier = 0, nbreChevalier = 0,  nbreOnagre = 0, nbreS = n/10;
		
		if(n%10 == 0) {
			nbrePiquier = 5 * nbreS;
			nbreChevalier = 3 * nbreS;
			nbreOnagre = 2 * nbreS;
		}
		else {
			if(n%10 <= 5) {
				nbrePiquier = (5 * nbreS)+(n%10);
				nbreChevalier = 3 * nbreS;
				nbreOnagre = 2 * nbreS;
			}	
			else {
				if(n%10 == 6 || n%10 == 7) {
					nbrePiquier = (5 * nbreS) + 5;
					nbreChevalier = (3 * nbreS) + ((n%10)-5);
					nbreOnagre = 2 * nbreS;
				}
				else if (n%10 == 8) {
					nbrePiquier = (5 * nbreS) + 5;
					nbreChevalier = (3 * nbreS) + 2;
					nbreOnagre = (2 * nbreS)+1;
				}
				else {
					nbrePiquier = (5 * nbreS) + 6;
					nbreChevalier = (3 * nbreS) + 2;
					nbreOnagre = (2 * nbreS)+1;
				}	
			}
				
		}
//		System.out.println("Le nombre total de troupe = "+n);
//		System.out.println("Le nombre de "+troop1+" = "+nbrePiquier);
//		System.out.println("Le nombre de "+troop2+" = "+nbreChevalier);
//		System.out.println("Le nombre de "+troop3+" = "+nbreOnagre);
		
		for(int i=0; i<nbrePiquier; i++) {
			Soldier soldier = new Soldier(playfieldLayer, soldierImage, x-20, y, Settings.SOLDIER_HEALTH, Settings.SOLDIER_DAMAGE, troop1, Settings.SOLDIER_SPEED);
			soldiers.add(soldier);
		}
		
		for(int i=0; i<nbreChevalier; i++) {
			Soldier soldier = new Soldier(playfieldLayer, soldierImage, x-20, y, Settings.SOLDIER_HEALTH, Settings.SOLDIER_DAMAGE, troop2, Settings.SOLDIER_SPEED);
			soldiers.add(soldier);
		}
		
		for(int i=0; i<nbreOnagre; i++) {
			Soldier soldier = new Soldier(playfieldLayer, soldierImage, x-20, y, Settings.SOLDIER_HEALTH, Settings.SOLDIER_DAMAGE, troop3, Settings.SOLDIER_SPEED);
			soldiers.add(soldier);
		}
		
		normalCastle.setListSoldier(soldiers);
	}
	
	public void createGate() {
		
		Gate gate;	// Pour extencier un objet de type porte
		
		double x = normalCastle.getX();
		double y = normalCastle.getY();
		
		double gateX,gateY;
		
		int n = rnd.nextInt(4)+1;
		if(n==1) {	// Pour mettre la porte vers le nord
			gateX = x+(normalCastleImage.getWidth()/2)-(gateImage.getWidth()/2);
			gateY = y;
		}
		else if(n==2) {	// Pour mettre la porte vers le sud
			gateX = x+(normalCastleImage.getWidth()/2)-(gateImage.getWidth()/2);
			gateY = y+normalCastleImage.getHeight()-gateImage.getHeight();
		}
		else if(n==3) {	// Pour mettre la porte vers l'Est
			gateX = x;
			gateY = y+(normalCastleImage.getHeight()/2)-(gateImage.getHeight()/2);
		}
		else {	// Pour mettre la porte vers l'Ouest
			gateX = x+normalCastleImage.getWidth()-gateImage.getWidth();
			gateY = y+(normalCastleImage.getHeight()/2)-(gateImage.getHeight()/2);
		}
		
		gate = new Gate(gateImage, playfieldLayer, gateX, gateY);	// Création de la porte.
		normalCastle.setGate(gate);
	}
	
	private void update() {
		if(clique) {
			informationCastle.setText("Propriétaire: "+normalCastle.getDuke().getPlayerName()+"     Niveau: "+normalCastle.getLevel()+
					"     Quantité de troupes: "+normalCastle.getListSoldier().size()+"     Tresor: "+normalCastle.getTreasure());
		}
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}

}