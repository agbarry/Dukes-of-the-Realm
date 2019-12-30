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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Main extends Application {
	
	private Random rnd = new Random();
	
	private Pane playfieldLayer;

	private Image normalCastleImage; // Pour representer le chateaux des autres joueurs
	private Image userCastle;		 // Pour representer le chateaux de l'utilisateur
	private Image playerImage;		 // Pour représenter les ducs
	private Image soldierImage;		 // Pour représenter les soldats
	private Image gateImage;		 // Pour représenter les portes
	
	private String playerName1;

	private NormalCastle normalCastle;	// Pour instancier les chateaux
	
	private ArrayList<NormalCastle> castlesNormal = new ArrayList<>(); // Pour stocker tous les chateaux du royaume
	
	private boolean clique = false;
	private boolean attack = false;
	
	private Troop troop1 = Troop.Piquier;
	private Troop troop2 = Troop.Chevalier;
	private Troop troop3 = Troop.Onagre;

	private Scene scene;
	private Input input;
	private AnimationTimer gameLoop;
	private Text castleInformation1;	// Pour l'affichage des informations des chateaux sur la barre de status
	private Text castleInformation2;
	private ImageView imageView;
	
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
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				processInput(input, now);

				// Pour l'affichage des infos d'un chateaux
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
		userCastle = new Image(getClass().getResource("/images/chateaux.jpeg").toExternalForm(), 110, 110, true, true);
		normalCastleImage = new Image(getClass().getResource("/images/chateaux1.jpg").toExternalForm(), 110, 110, true, true);
		
		playerImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), 20, 20, true, true);
		soldierImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), 10, 10, true, true); 
		
		gateImage = new Image(getClass().getResource("/images/porte1.jpg").toExternalForm(), 20, 20, true, true);
		
		input = new Input(scene);
		input.addListeners();
		
		createNCastle(8);
		
		//System.out.println("Le nombre de chateaux est: "+normalCastle.getNbreInstances());
	
		createStatusBar();	
	}
	
	public void createStatusBar() {
		HBox statusBar = new HBox();
		
		castleInformation1 = new Text();
		castleInformation1.setText("Bienvenue au royaume Dukes of the Realm qui compte "+castlesNormal.size()+" chateaux");
		castleInformation2 = new Text();
		imageView = new ImageView();
		statusBar.getChildren().addAll(castleInformation1, imageView, castleInformation2);
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, Settings.SCENE_HEIGHT);
		statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}	
	
	
	// Fonction permettant de créer les chateaux du royaume
	private void createNCastle(int number) {
		int verif;
		for(int i=0; i<number; i++) {
			verif = 0;
				
			int x = rnd.nextInt(660) + Settings.CASTLE_DISTANCE;
			int y = rnd.nextInt(460)+ Settings.CASTLE_DISTANCE;
			
			if(castlesNormal.size()>0) {
				for(int j=0; j<castlesNormal.size(); j++) {
					if( (x<=castlesNormal.get(j).getX()-normalCastleImage.getWidth()-Settings.CASTLE_DISTANCE || x>=castlesNormal.get(j).getX()+normalCastleImage.getWidth()+Settings.CASTLE_DISTANCE) || 
						(y<=castlesNormal.get(j).getY()-normalCastleImage.getHeight()-Settings.CASTLE_DISTANCE || y>=castlesNormal.get(j).getY()+normalCastleImage.getHeight()+Settings.CASTLE_DISTANCE) ) 
					{	verif++;	}	
				}
				// Si les coordonnées sont bonnes, on procéde à la création du chateau
				if(verif == castlesNormal.size()) 
					createCastle(x, y, normalCastleImage);
				else
					number++;
			}
			else
				createCastle(x, y, userCastle);
		}
	}
	
	// Permet de créer un chateaux
	private void createCastle(int x, int y, Image img) {
			
		NormalCastle normalCastle1;
		Displacement displacement = new Displacement();
		Production_Unit product = new Production_Unit();
			
		normalCastle = new NormalCastle(img, playfieldLayer, x, y, 0.0, 1, null, product, displacement, null, input, null);
			
		normalCastle1 = normalCastle;
			
		createGate();	// Création de la porte du châteaux
		createPlayer();	// Création du joueur propriétaire du chateaux ainsi que ses soldats
		createSoldier();	// Création de la liste des soldats du châteaux
			
		castlesNormal.add(normalCastle);		// Ajout du chateaux dans la liste de chateaux
			
		normalCastle1.getView().setOnMousePressed(e -> {
			NormalCastle nC = null;
			normalCastle = normalCastle1;
			clique = true;
				
			// Pour lancer une attaque
			if(attack && !"Duke1".equals(normalCastle.getDuke().getPlayerName())) {
				clique = false;
				attack = false;
				//normalCastle = nC;
				Pupup.display(normalCastle);
			}
				
			if( "Duke1".equals(normalCastle.getDuke().getPlayerName()) ) {
				attack = true;
				//nC = normalCastle1;
			}
				
			double i = e.getSceneX();
			double j = e.getSceneY();
//			System.out.println(i+","+j);
//				if( i<normalCastle.getDuke().getX() && j<normalCastle.getDuke().getY() ) {
//					double x1 = normalCastle.getDuke().getX()-1;
//					double y1 = normalCastle.getDuke().getY()-1;
//					
//					while( (x1>i && y1>j) || (x1>i && y1==j) || (x1==i && y1>j) ) {
//						//pause(20);
//						if(x1>i && y1>j) {
//							normalCastle.getDuke().setX(x1);
//							normalCastle.getDuke().setY(y1);
//							x1--; y1--;
//						}
//						else if(x1>i && y1==j) {
//							normalCastle.getDuke().setX(x1);
//							x1--;
//						}
//						else {
//							normalCastle.getDuke().setY(y1);
//							y1--;
//						}
//						normalCastle.getDuke().updateUI();
//						
//					}
//				}
				
				//System.out.println("Click on castle");
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
		else {
			x1 = 1;
		}
		
		playerName = playerName+x1;
		
		player = new Player(playfieldLayer, playerImage, x, y, Settings.PLAYER_HEALTH, input, playerName);	
		normalCastle.setDuke(player);	// Modification du propriétaire du chateaux;
		
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
			castleInformation1.setText("Propriétaire: "+normalCastle.getDuke().getPlayerName()+"     Niveau: "+normalCastle.getLevel()+
					"     Quantité de troupes: "+normalCastle.getListSoldier().size()+"dont: "+normalCastle.nbreTypeSoldier("Piquier")+" Piquier, "+normalCastle.nbreTypeSoldier("Onage")+" Onagre" );
			imageView.setImage(playerImage);
		}
	}
	
//	private void attack() {
//		if(attack) {
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Information Dialog");
//			alert.setHeaderText(null);
//			alert.setContentText("I have a great message for you!");
//			
//			alert.getOwner();
//			alert.showAndWait();
//		}
//	}
	
	

	public static void main(String[] args) {
		launch(args);
	}

}