import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * -----------------------------------------------------------------------------------
 * The MainMenuGUI shapes the menu screen that is presented to the player upon launch.
 * It leverages stages, closing the menu window and opening the game in another window
 * when the player clicks start.
 * -----------------------------------------------------------------------------------
 */

public class MainMenuGUI extends Application {

	GameMenu mainMenu;
	Stage mainMenuStage;
	Pane root = new Pane();
	
	
/*
 * -----------------------------------------------------------------------------------
 * This main launch is what sets the entire program in motion.
 * -----------------------------------------------------------------------------------
 */	
	public static void main(String[] args)
	{
		launch(args);
		
	}
	
	
/*
 * -----------------------------------------------------------------------------------
 * A stage is the term for the application window. mainMenuStage here creates the 
 * menu window
 * -----------------------------------------------------------------------------------
 */	
	@Override
	public void start(Stage mainMenuStage) throws Exception {
		
		//The this keyword is important for when mainMenuStage is referenced to close
		//the menu window with a click of the start button.
		this.mainMenuStage = mainMenuStage;
		
		
		//This changes what is displayed in the title bar of the window.
		mainMenuStage.setTitle("SPACING OUT");

		//Create a scene that will sit in the stage, structuring the visual appearance.
		Scene scene = new Scene(root);
		mainMenuStage.setScene(scene);
		
		//Setting the stage width and height separately prevents bars on the window edges
		mainMenuStage.setWidth(600);
		mainMenuStage.setHeight(600);
		
		//.show makes the stage visible 
		mainMenuStage.show();
		
		//The user is prevented from resizing the window, because the background does
		//not scale with the window and things would look wonky.
		mainMenuStage.setResizable(false);
		
		//Create a new instance of a GameMenu to populate the scene with.
		mainMenu = new GameMenu();
		
		//Pass the created menu to the scene so it appears in the stage.
		root.getChildren().addAll(mainMenu);
				
		}
	

	
/*
 * -----------------------------------------------------------------------------------
 * The GameMenu class and associated constructor are where the menu is built out of 
 * buttons, labels, etc. The contents of this class determine how the elements are 
 * positioned in their scene.
 * 
 * VBoxes are moved in and out of the screen to create an animated menu.
 * VBox represents a collection of items arranged vertically, a counterpart to HBox.
 * -----------------------------------------------------------------------------------
 */		
	public class GameMenu extends Parent {
		public GameMenu() {
			
			
			//The first VBox is for the main menu, the number that follows determines the
			//spacing between items in the VBox
			VBox menuMain = new VBox(10);
			menuMain.setAlignment(Pos.CENTER);
			//StackPanes do not center well, so the TranslateX here is used to brute-
			//force the VBox buttons towards the center of the screen.
			menuMain.setTranslateX(150);
			
			//The scores menu is similar to the main menu
			VBox menuScores = new VBox(10);
			menuScores.setAlignment(Pos.CENTER);
			menuScores.setTranslateX(150);
			
			//Initially the scores menu is located well below the window's pane size.
			//This allows is to slide up from below.
			menuScores.setTranslateY(1000);
			
			//The controls menu is very similar to the scores menu, also located below
			//the window pane
			VBox menuControls = new VBox(10);
			menuControls.setAlignment(Pos.CENTER);
			menuControls.setTranslateX(150);
			menuControls.setTranslateY(1000);
			
			//The title is simple text, with various attributes. You can change the
			//font, text size, and text colour here.
			Text textTitle = new Text("SPACING OUT");
			textTitle.setFont(Font.font ("Roboto", 40));
			textTitle.setFill(Color.WHITE);
			
			//The scores label is important because it uses GetHighScore from the
			//Level class to retrieve the game's local highscore from a .dat file.
			Label lblScores = new Label("Highscore: " + Level.GetHighScore());
			lblScores.setFont(Font.font ("Roboto", 20));
			lblScores.setTextFill(Color.WHITE);	
			
			//A simple label that outlines the controls. The \n moves the text
			//to the next line.
			Label lblControls = new Label("Move: Left/Right Arrow Keys \n"
					+ "Shoot: Spacebar");
			lblControls.setFont(Font.font ("Roboto", 20));
			lblControls.setTextFill(Color.WHITE);		
			
			//These margin settings for the title, scores, and controls
			//text/labels adds spacing above and below the text, for
			//formatting reasons.
			VBox.setMargin(textTitle, new Insets(100,0,100,0));
			VBox.setMargin(lblScores, new Insets(100,0,100,0));			
			VBox.setMargin(lblControls, new Insets(100,0,100,0));
			
			//The first button, located in the main menu VBox is the one
			//that starts the game. The button text is set here.
			MenuButton btnStart = new MenuButton("START");
			//When the button is clicked, events are triggered.
			btnStart.setOnMouseClicked(event -> {
				
				//First a new instance of ActGame is created with the constructor.
				ActGame game = new ActGame();
				
				//The current stage, the main menu window, is closed.
				mainMenuStage.close();
				
				//The Stage outlined within the newly created ActGame instance
				//is shown, meaning that the game opens in a new window.
				game.getMainStage().show();

			});
			
			//The scores button is also located in the main menu and moves VBoxes around to 
			//hide the main menu and display the Scores menu.
			MenuButton btnScores = new MenuButton("SCORES");
			btnScores.setOnMouseClicked(event -> {
				getChildren().add(menuScores);
				
				//A translate transition is used to set the position of the main menu to 1000, 
				//shooting it downwards off the screen in a time of .25 seconds.
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuMain);
				tt.setToY(1000);
				
				//Shortly after the main menu leaves, the scores menu rises up from that same position
				//below the window frame
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuScores);
				tt1.setToY(0);
				
				//.play triggers the TranslateTransitions
				tt.play();
				tt1.play();
				
				//The main menu is removed from the scene so that it can be added back in with a button
				//click later.
				tt.setOnFinished(evt -> {
				getChildren().remove(menuMain);
				});
				
			});
			
			//The Controls button operates in the same way as the Scores button, brining the controls
			//menu up from below the window boundaries and sending the main menu downwards before 
			//removing the main menu from the scene.
			MenuButton btnControls = new MenuButton("CONTROLS");
			btnControls.setOnMouseClicked(event -> {
				getChildren().add(menuControls);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuMain);
				tt.setToY(1000);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuControls);
				tt1.setToY(0);
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt -> {
				getChildren().remove(menuMain);
				});
				
			});
			
			
			//The exit button means that freedom from the program is only a simple button click away.
			MenuButton btnExit = new MenuButton("EXIT");
			btnExit.setOnMouseClicked(event -> {
				System.exit(0);
				
			});
			
			
			//The BackScores button is a back button that appears in the scores menu and operates
			//essentially as a reverse Scores button, bringing back the main menu and hiding the 
			//scores one.
			MenuButton btnBackScores = new MenuButton("BACK");
			btnBackScores.setOnMouseClicked(event -> {
				getChildren().add(menuMain);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuScores);
				tt.setToY(1000);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuMain);
				tt1.setToY(0);
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt -> {
				getChildren().remove(menuScores);
				});
				
			});
			
			//Like the BackScores button, the BackControls button works like a reverse controls button
			//hiding the controls menu and brining back the main menu.
			MenuButton btnBackControls = new MenuButton("BACK");
			btnBackControls.setOnMouseClicked(event -> {
				getChildren().add(menuMain);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuControls);
				tt.setToY(1000);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuMain);
				tt1.setToY(0);
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt -> {
				getChildren().remove(menuControls);
				});
				
			});
			
			//The getChildren statements populate the various menu VBoxes with the controls above.
			//The main menu is populated with the game title, start button, scores button, controls
			//button and the exit button.
			menuMain.getChildren().addAll(textTitle, btnStart, btnScores, btnControls, btnExit);
			//The Scores menu only has the scores label that displays the local highscore and the 
			//back button.
			menuScores.getChildren().addAll(lblScores, btnBackScores);
			//The controls menu is populated with the controls label and a back button, the simplest
			//menu in the game.
			menuControls.getChildren().addAll(lblControls, btnBackControls);
			
			
			//A rectangle is created to be used as a background for the menu window
			Rectangle bg = new Rectangle (600, 600);
		
			//The inital menu is populated with the background rectangle and the main menu,
			//with buttons ready to acces the remainder of the program.
			getChildren().addAll(bg, menuMain);
						
			
			
		}
	}
	
/*
 * -----------------------------------------------------------------------------------
 * The MenuButton class extends StackPane, allowing for buttons that consist of layered
 * effects, giving things some style.
 * -----------------------------------------------------------------------------------
 */		
	private static class MenuButton extends StackPane {
		
		//Initialize text variable
		private Text text;
		
		
		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(Font.font ("Roboto", 20));
			text.setFill(Color.WHITE);
			
			//A rectangle is created to the be button background
			Rectangle bg = new Rectangle (300, 50);
			bg.setFill(Color.GREY);
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg,text);
			
			//OnMouseEntered and Exited are used to give the buttons
			//mousover effects. The background turns from grey to white
			//and the text changes from white to black on mouseover,
			//reverting when the mouse is moved away.
			setOnMouseEntered(event -> {
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});
			
			setOnMouseExited(event -> {
				bg.setFill(Color.GREY);
				text.setFill(Color.WHITE);				
			});
			
			//A dropshadow glow effect is used to cast a white glow around
			//a button when it is clicked.
			DropShadow drop = new DropShadow(50 , Color.WHITE);
			drop.setInput(new Glow());
			
			setOnMousePressed(event -> setEffect(drop));
			setOnMouseReleased(event -> setEffect(null));
			
		}
	}
}