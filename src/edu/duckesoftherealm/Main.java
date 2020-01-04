package edu.duckesoftherealm;

import java.util.ArrayList;

import java.util.Random;

import edu.duckesoftherealm.controller.ScrollPaneController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Main extends Application {
	
	private Random rnd = new Random();
	
	private Pane playfieldLayer;

	private Image normalCastleImage;  // Pour representer le chateaux des autres joueurs
	private Image neutralCastleImage; // Pour representer les chateaux neutres
	private Image userCastle;		  // Pour representer le chateaux de l'utilisateur
	private Image playerImage;		  // Pour représenter les ducs
	private Image soldierImage;		  // Pour représenter les soldats
	private Image gateImage;		  // Pour représenter les portes
	private Image campaignImage;	  // Pour représenter les zones libres
	
	private Player player;	// Pour instancier les ducs des chateaux

	private NormalCastle normalCastle;	// Pour instancier les chateaux par défaut
	private NeutralCastle neutralCastle;	// Pour instancier les chateaux neutres
	private Campaign campaign;	// Pour instancier les campagnes
	
	private ArrayList<NormalCastle> normalCastles = new ArrayList<>(); // Pour stocker tous les chateaux du royaume
	private ArrayList<NeutralCastle> neutralCastles = new ArrayList<>(); // Pour stocker les chateaux neutres du royaume
	private ArrayList<Campaign> campaigns = new ArrayList<>(); // Pour stocker les campagnes du royaume
	
	private ArrayList<Soldier> soldiersToDeploy = new ArrayList<>();	// Pour stocker les soldats à déployer lors d'une attaque
	
	private boolean cliqueNormal = false; // Pour le clique sur les chateaux par défaut
	private boolean cliqueNeutral = false; // Pour le clique sur les chateaux neutres;
	private boolean attack = false; 	  // Pour le clique sur le chateaux de l'utilisateur
	
	private Troop piker = Troop.Piquier;
	private Troop knight = Troop.Chevalier;
	private Troop onager = Troop.Onagre;

	private Scene scene;
	private Input input;
	private Text castleInformation1;	// Pour l'affichage des informations des chateaux sur la barre de status
	private AnimationTimer gameLoop;
	
	Group root;

	@Override
	public void start(Stage primaryStage) {
		
		root = new Group();
		ScrollPane scrollPane = new ScrollPane(root);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setPannable(true);
		scene = new Scene(scrollPane, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT + Settings.STATUS_BAR_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		
		// Create window
		primaryStage.setTitle("Dukes of the Realm"); // Setting the title to Stage
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.show();
		
		AnchorPane anchorPane = new AnchorPane();
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(30, 50, 30, 50));
		Background bgGreen = new Background( new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), null ),
							 new BackgroundFill(Color.ALICEBLUE, new CornerRadii(40), null ));
		vBox.setBackground(bgGreen);
	    
		Button buttonNew = new Button("Nouveau");
		buttonNew.setPrefWidth(Settings.DEFAULT_BUTTON_WIDTH);
		buttonNew.setPrefHeight(Settings.DEFAULT_BUTTON_HEIGHT);
		buttonNew.setDefaultButton(true);
		buttonNew.setOnAction(e ->  {
	    	
	    	root.getChildren().clear();
			playfieldLayer = new Pane();
			root.getChildren().add(playfieldLayer); 
			
			loadGame(); // Pour le chargement du jeu.
			
			gameLoop = new AnimationTimer() {
				@Override
				public void handle(long now) {
					processInput(input, now);
					
					// soldat movement
					soldiersToDeploy.forEach(sprite -> sprite.spriteMove(normalCastles.get(0).getMoveOrder().getTargetX(), normalCastles.get(0).getMoveOrder().getTargetY()));
					
					// update soldat in scene
					soldiersToDeploy.forEach(sprite -> sprite.updateUI());

					// Pour l'affichage des infos d'un chateaux
					update();
				}

				private void processInput(Input input, long now) {
					if (input.isExit()) {
						Platform.exit();
						System.exit(0);
					} 
					
					ScrollPaneController.Mouvement(input, scrollPane);
				}

			};
			
			gameLoop.start();
	    	
	    });
	    
	    Button buttonRestart = new Button("Reprendre");
	    buttonRestart.setPrefWidth(Settings.DEFAULT_BUTTON_WIDTH);
	    buttonRestart.setPrefHeight(Settings.DEFAULT_BUTTON_HEIGHT);
	    buttonRestart.setDefaultButton(true);
	    buttonRestart.setOnAction( action -> {} );
	    
	    Button buttonAbout = new Button("A propos");
	    buttonAbout.setPrefWidth(Settings.DEFAULT_BUTTON_WIDTH);
	    buttonAbout.setPrefHeight(Settings.DEFAULT_BUTTON_HEIGHT);
	    buttonAbout.setDefaultButton(true);
	    buttonAbout.setOnAction( action -> {});
	    
	    Button buttonQuit = new Button("Quitter");
	    buttonQuit.setPrefWidth(Settings.DEFAULT_BUTTON_WIDTH);
	    buttonQuit.setPrefHeight(Settings.DEFAULT_BUTTON_HEIGHT);
	    buttonQuit.setDefaultButton(true);
	    buttonQuit.setOnAction(action -> {
	    	Platform.exit();
			System.exit(0);
	    });
	    
	    vBox.getChildren().addAll(buttonNew, buttonRestart, buttonAbout, buttonQuit);
	    
	    anchorPane.getChildren().add(vBox);
	    AnchorPane.setTopAnchor(vBox, Settings.VBOX_Y);
	    AnchorPane.setLeftAnchor(vBox, Settings.VBOX_X);

		root.getChildren().add(anchorPane);
	}
	
	private void loadGame() {
		userCastle = new Image(getClass().getResource("/images/Chateaux1.jpeg").toExternalForm(), Settings.CASTLE_WIDTH, Settings.CASTLE_HEIGHT, true, true);
		normalCastleImage = new Image(getClass().getResource("/images/chateaux1.jpg").toExternalForm(), Settings.CASTLE_WIDTH, Settings.CASTLE_HEIGHT, true, true);
		
		neutralCastleImage = new Image(getClass().getResource("/images/chateaux.jpeg").toExternalForm(), Settings.NEUTRALCASTLE_WIDTH, Settings.NEUTRALCASTLE_HEIGHT, true, true);
		
		playerImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT, true, true);
		soldierImage = new Image(getClass().getResource("/images/images.jpeg").toExternalForm(), Settings.SOLDIER_WIDTH, Settings.SOLDIER_HEIGHT, true, true); 
		
		gateImage = new Image(getClass().getResource("/images/porte1.jpg").toExternalForm(), 20, 20, true, true);
		
		campaignImage = new Image(getClass().getResource("/images/campagne.jpeg").toExternalForm(), Settings.CAMPAIGN_WIDTH, Settings.CAMPAIGN_HEIGHT, true, true);

		input = new Input(scene);
		input.addListeners();
		
		int number = rnd.nextInt(Settings.MEDIUM_NUMBER_NORMALCASTLE)+Settings.MIN_NUMBER_NORMALCASTLE;
		createNCastle(number, true);
		
		number = rnd.nextInt(Settings.MEDIUM_NUMBER_NEUTRALCASTLE)+Settings.MIN_NUMBER_NEUTRALCASTLE;
		createNCastle(number, false);
			
		number = rnd.nextInt(Settings.MEDIUM_NUMBER_CAMPAIGN)+Settings.MIN_NUMBER_CAMPAIGN;
		createCampaign(number);
	
		createStatusBar();	
	}
	
	// Permet de créer la barre de status
	public void createStatusBar() {
		HBox statusBar = new HBox();
		
		castleInformation1 = new Text();
		castleInformation1.setText("Bienvenue au royaume 'Dukes of the Realm' qui compte "+normalCastles.size()+" chateaux d'origine et "+neutralCastles.size()+" chateaux neutres");
		
		statusBar.getChildren().addAll(castleInformation1);
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, playfieldLayer.getHeight()+Settings.SPRITE_DISTANCE);
		statusBar.setPrefSize(playfieldLayer.getWidth(), Settings.STATUS_BAR_HEIGHT);
		statusBar.setAlignment(Pos.CENTER);
		root.getChildren().add(statusBar);
	}	
	
	// Méthode permettant de créer les chateaux du royaume
	private void createNCastle(int number, boolean whichCastle) {
		int verif;
		for(int i=0; i<number; i++) {
			verif = 0;
				
			int x = rnd.nextInt((int) scene.getWidth()) + Settings.SPRITE_DISTANCE;
			int y = rnd.nextInt((int) scene.getHeight())+ Settings.SPRITE_DISTANCE;
			
			if(normalCastles.size()>0) {
				for(int j=0; j<normalCastles.size(); j++) {
					if( (x<=normalCastles.get(j).getX()-normalCastleImage.getWidth()-Settings.SPRITE_DISTANCE || x>=normalCastles.get(j).getX()+normalCastleImage.getWidth()+Settings.SPRITE_DISTANCE) || 
						(y<=normalCastles.get(j).getY()-normalCastleImage.getHeight()-Settings.SPRITE_DISTANCE || y>=normalCastles.get(j).getY()+normalCastleImage.getHeight()+Settings.SPRITE_DISTANCE) ) 
					{	verif++;	}	
				}
				if(neutralCastles.size()>0) {
					for(int j=0; j<neutralCastles.size(); j++) {
						if( (x<=neutralCastles.get(j).getX()-neutralCastleImage.getWidth()-Settings.SPRITE_DISTANCE || x>=neutralCastles.get(j).getX()+neutralCastleImage.getWidth()+Settings.SPRITE_DISTANCE) || 
							(y<=neutralCastles.get(j).getY()-neutralCastleImage.getHeight()-Settings.SPRITE_DISTANCE || y>=neutralCastles.get(j).getY()+neutralCastleImage.getHeight()+Settings.SPRITE_DISTANCE) ) 
						{	verif++;	}
					}
				}
				// Si les coordonnées sont bonnes, on procéde à la création du chateau
				if(verif == normalCastles.size() && whichCastle) 
					createCastle(x, y, normalCastleImage, whichCastle);
				else if(verif == normalCastles.size()+neutralCastles.size() && !whichCastle)
					createCastle(x, y, neutralCastleImage, whichCastle);
				else
					number++;
			}
			else
				createCastle(x, y, userCastle, whichCastle);
		}
	}
	
	// Méthode permettant de créer un chateaux
	private void createCastle(int x, int y, Image img, boolean whichCastle) {
		if(whichCastle) {
			NormalCastle normalCastle1;
			Displacement displacement = new Displacement();
			Production_Unit product = new Production_Unit();
			normalCastle = new NormalCastle(img, playfieldLayer, x, y, 0.0, 1, null, null, input, null, product, displacement);
				
			createGate(whichCastle);	// Création de la porte du châteaux
			createPlayer();	// Création du joueur propriétaire du chateaux ainsi que ses soldats
			createSoldier(whichCastle);	// Création de la liste des soldats du châteaux
				
			normalCastles.add(normalCastle);	// Ajout du chateaux dans la liste de chateaux
			normalCastle1 = normalCastle;
			
			normalCastle1.getView().setOnMousePressed(e -> {
				normalCastle = normalCastle1; cliqueNormal = true; cliqueNeutral = false;
					
				if(attack && !"Duke1".equals(normalCastle.getDuke().getPlayerName())) {	// Pour lancer une attaque
					cliqueNormal = false; attack = false;
					if(normalCastles.get(0).getListSoldier().size()>0) {
						soldiersToDeploy = Pupup.display(normalCastles.get(0));	// Récupération des soldats à déployer
						normalCastle.attack(normalCastles, soldiersToDeploy);	// Attack du chateaux cible
					}
					else {
						Dialog.infoAlert("Désolé, vous n'avez pas d'armée dans votre reserve.", "Information");
					}
				}
					
				if( "Duke1".equals(normalCastle.getDuke().getPlayerName()) ) {
					attack = true;
				}
					
				e.consume();
			});
		}
		else {
			NeutralCastle neutralCastle1;
			neutralCastle = new NeutralCastle(img, playfieldLayer, x, y, 0.0, 1, null, null, input, "Baron");
			createGate(whichCastle);	// Création de la porte du châteaux
			createSoldier(whichCastle);	// Création de la liste des soldats du châteaux
			
			neutralCastles.add(neutralCastle);		// Ajout du chateaux dans la liste de chateaux
			neutralCastle1 = neutralCastle;
			
			neutralCastle1.getView().setOnMousePressed(e -> {
				neutralCastle = neutralCastle1; cliqueNeutral = true; cliqueNormal = false;
				if(attack && !"Duke1".equals(neutralCastle.getBaronName())) {	// Pour lancer une attaque
					cliqueNeutral = false; attack = false;
					if(normalCastles.get(0).getListSoldier().size()>0) {
						soldiersToDeploy = Pupup.display(normalCastles.get(0));
						neutralCastle.attack(normalCastles, soldiersToDeploy);	// Attack du chateaux cible
					}
					else {
						Dialog.infoAlert("Désolé, vous n'avez pas d'armée dans votre reserve.", "Information");
					}
				}
				
				if( "Duke1".equals(neutralCastle.getBaronName()) ) {
					attack = true;
				}
				
				e.consume();
			});
		}	
	}
	
	// Méthode permettant de créer un joueur
	private void createPlayer() {
		String playerName = "Duke";
		double x = normalCastle.getCenterX();
		double y = normalCastle.getCenterY();
		
		int x1;
		if(normalCastles.size()>0) {
			String NameTmp = normalCastles.get(normalCastles.size()-1).getDuke().getPlayerName().substring(4);
			x1 = Integer.parseInt(NameTmp)+1;
		}
		else {
			x1 = 1;
		}
		playerName = playerName+x1;
		
		player = new Player(playfieldLayer, playerImage, x, y, Settings.PLAYER_HEALTH, input, playerName);	
		normalCastle.setDuke(player);	// Modification du propriétaire du chateaux;
	}
	
	// Méthode permettant de créer la liste des soldats
	private void createSoldier(boolean forWhichCastle) {
		ArrayList<Soldier> soldiers = new ArrayList<>();
		
		double x, y;
		int n;
		if(forWhichCastle) {
			x = normalCastle.getCenterX();
			y = normalCastle.getCenterY();
			n = rnd.nextInt(40)+10;
		}
		else {
			x = neutralCastle.getCenterX();
			y = neutralCastle.getCenterY();
			n = rnd.nextInt(20)+10;
		}
		
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
			Soldier soldier = new Soldier(playfieldLayer, soldierImage, x-20, y, piker);
			soldiers.add(soldier);
		}
		
		for(int i=0; i<nbreChevalier; i++) {
			Soldier soldier = new Soldier(playfieldLayer, soldierImage, x-20, y, knight);
			soldiers.add(soldier);
		}
		
		for(int i=0; i<nbreOnagre; i++) {
			Soldier soldier = new Soldier(playfieldLayer, soldierImage, x-20, y, onager);
			soldiers.add(soldier);
		}
		
		if(forWhichCastle)
			normalCastle.setListSoldier(soldiers);
		else
			neutralCastle.setListSoldier(soldiers);
	}
	
	// Permet de créer la porte d'un chateaux
	private void createGate(boolean forWhichCastle) {
		Gate gate;	// Pour extencier un objet de type porte
		
		double x, y, width, height;
		if(forWhichCastle) {
			x = normalCastle.getX();
			y = normalCastle.getY();
			width = normalCastleImage.getWidth();
			height = normalCastleImage.getHeight();
		}
		else {
			x = neutralCastle.getX();
			y = neutralCastle.getY();
			width = neutralCastleImage.getWidth();
			height = neutralCastleImage.getHeight();
		}
		
		double gateX,gateY;
		int n = rnd.nextInt(4)+1;
		if(n==1) {	// Pour mettre la porte vers le nord
			gateX = x+(width/2)-(gateImage.getWidth()/2);
			gateY = y;
		}
		else if(n==2) {	// Pour mettre la porte vers le sud
			gateX = x+(width/2)-(gateImage.getWidth()/2);
			gateY = y+height-gateImage.getHeight();
		}
		else if(n==3) {	// Pour mettre la porte vers l'Est
			gateX = x;
			gateY = y+(height/2)-(gateImage.getHeight()/2);
		}
		else {	// Pour mettre la porte vers l'Ouest
			gateX = x+width-gateImage.getWidth();
			gateY = y+(height/2)-(gateImage.getHeight()/2);
		}
		
		gate = new Gate(gateImage, playfieldLayer, gateX, gateY);	// Création de la porte.
		
		if(forWhichCastle)
			normalCastle.setGate(gate);
		else
			neutralCastle.setGate(gate);
	}
	
	private void createCampaign(int number) {
		int verif;
		for(int i=0; i<number; i++) {
			verif = 0;
				
			int x = rnd.nextInt((int) scene.getWidth()) + Settings.SPRITE_DISTANCE;
			int y = rnd.nextInt(460)+ Settings.SPRITE_DISTANCE;
			
			for(int j=0; j<normalCastles.size(); j++) {
				if( (x<=normalCastles.get(j).getX()-campaignImage.getWidth()-Settings.SPRITE_DISTANCE || x>=normalCastles.get(j).getX()+normalCastleImage.getWidth()+Settings.SPRITE_DISTANCE) || 
					(y<=normalCastles.get(j).getY()-campaignImage.getHeight()-Settings.SPRITE_DISTANCE || y>=normalCastles.get(j).getY()+normalCastleImage.getHeight()+Settings.SPRITE_DISTANCE) ) 
				{	verif++;	}	
			}
			
			for(int j=0; j<neutralCastles.size(); j++) {
				if( (x<=neutralCastles.get(j).getX()-campaignImage.getWidth()-Settings.SPRITE_DISTANCE || x>=neutralCastles.get(j).getX()+neutralCastleImage.getWidth()+Settings.SPRITE_DISTANCE) || 
					(y<=neutralCastles.get(j).getY()-campaignImage.getHeight()-Settings.SPRITE_DISTANCE || y>=neutralCastles.get(j).getY()+neutralCastleImage.getHeight()+Settings.SPRITE_DISTANCE) ) 
				{	verif++;	}
			}
			
			for(int j=0; j<campaigns.size(); j++) {
				if( (x<=campaigns.get(j).getX()-campaignImage.getWidth()-Settings.SPRITE_DISTANCE || x>=campaigns.get(j).getX()+campaignImage.getWidth()+Settings.SPRITE_DISTANCE) || 
					(y<=campaigns.get(j).getY()-campaignImage.getHeight()-Settings.SPRITE_DISTANCE || y>=campaigns.get(j).getY()+campaignImage.getHeight()+Settings.SPRITE_DISTANCE) ) 
				{	verif++;	}
			}
			
			// Si les coordonnées sont bonnes, on procéde à la création du chateau
			if(verif == normalCastles.size()+neutralCastles.size()+campaigns.size() ) {
				campaign = new Campaign(playfieldLayer, campaignImage, x, y);
				campaigns.add(campaign);
			}
			else
				number++;
		}
	}
	
	// Permet de faire la mise à jour des infos sur la barre des tâches
	private void update() {
		// L'ajout de la condition sur la liste de soldat est à vérifier demain
		if(cliqueNormal) {
			castleInformation1.setText("Propriétaire: "+normalCastle.getDuke().getPlayerName()+" |Niveau: "+normalCastle.getLevel()+
					" |Quantité total de soldats: "+normalCastle.getListSoldier().size()+" dont: {"+normalCastle.nbreTypeSoldier("Piquier")+" Piquier |"+
					normalCastle.nbreTypeSoldier("Chevalier")+" Chevalier |"+normalCastle.nbreTypeSoldier("Onage")+" Onagre}" );
		}
		
		if(cliqueNeutral) {
			castleInformation1.setText("Propriétaire: "+neutralCastle.getBaronName()+" |Niveau: "+neutralCastle.getLevel()+
					" |Quantité de troupes: "+neutralCastle.getListSoldier().size()+" dont: {"+neutralCastle.nbreTypeSoldier("Piquier")+" Piquier |"+
					neutralCastle.nbreTypeSoldier("Chevalier")+" Chevalier |"+neutralCastle.nbreTypeSoldier("Onage")+" Onagre}" );
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}