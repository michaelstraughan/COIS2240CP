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

public class MainMenuGUI extends Application {

	GameMenu mainMenu;

	
	public static void main(String[] args)
	{
		launch(args);
		
	}
	
	@Override
	public void start(Stage mainMenuStage) throws Exception {
		mainMenuStage.setTitle("SPACING OUT");
		mainMenuStage.setResizable(false);
		
		Pane root = new Pane();
		root.setPrefSize(600, 600);
		
		Scene scene = new Scene(root);
		mainMenuStage.setScene(scene);
		mainMenuStage.show();
		
		mainMenu = new GameMenu();
		
		root.getChildren().addAll(mainMenu);
			
		}

	
	
	
	private class GameMenu extends Parent {
		public GameMenu() {
			
			VBox menuMain = new VBox(10);
			menuMain.setAlignment(Pos.CENTER);
			menuMain.setTranslateX(150);
			
			VBox menuScores = new VBox(10);
			menuScores.setAlignment(Pos.CENTER);
			menuScores.setTranslateX(150);
			menuScores.setTranslateY(1000);
			
			VBox menuControls = new VBox(10);
			menuControls.setAlignment(Pos.CENTER);
			menuControls.setTranslateX(150);
			menuControls.setTranslateY(1000);
			
			Text textTitle = new Text("SPACING OUT");
			textTitle.setFont(Font.font ("Roboto", 40));
			textTitle.setFill(Color.WHITE);
			
			Label lblScores = new Label("score placeholder");
			lblScores.setFont(Font.font ("Roboto", 20));
			lblScores.setTextFill(Color.WHITE);	
			
			Label lblControls = new Label("Move: Left/Right Arrow Keys \n"
					+ "Shoot: Spacebar");
			lblControls.setFont(Font.font ("Roboto", 20));
			lblControls.setTextFill(Color.WHITE);		
			
			menuMain.setMargin(textTitle, new Insets(100,0,100,0));
			menuScores.setMargin(lblScores, new Insets(100,0,100,0));			
			menuControls.setMargin(lblControls, new Insets(100,0,100,0));
			
			MenuButton btnStart = new MenuButton("START");
			btnStart.setOnMouseClicked(event -> {

				//mainMenu.setVisible(false);
				//mainMenuStage.setScene(scene2);
				
			});
			
			MenuButton btnScores = new MenuButton("SCORES");
			btnScores.setOnMouseClicked(event -> {
				getChildren().add(menuScores);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuMain);
				tt.setToY(1000);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuScores);
				tt1.setToY(0);
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt -> {
				getChildren().remove(menuMain);
				});
				
			});
			
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
			
			MenuButton btnExit = new MenuButton("EXIT");
			btnExit.setOnMouseClicked(event -> {
				System.exit(0);
				
			});
			
			
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
			
			menuMain.getChildren().addAll(textTitle, btnStart, btnScores, btnControls, btnExit);
			menuScores.getChildren().addAll(lblScores, btnBackScores);
			menuControls.getChildren().addAll(lblControls, btnBackControls);
			
			
			Rectangle bg = new Rectangle (600, 600);
		
			getChildren().addAll(bg, menuMain);
						
			
			
		}
	}
	
	private static class MenuButton extends StackPane {
		private Text text;
		

		
		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(Font.font ("Roboto", 20));
			text.setFill(Color.WHITE);
			
			Rectangle bg = new Rectangle (300, 50);
			bg.setFill(Color.GREY);
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg,text);
			
			setOnMouseEntered(event -> {
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});
			
			setOnMouseExited(event -> {
				bg.setFill(Color.GREY);
				text.setFill(Color.WHITE);				
			});
			
			DropShadow drop = new DropShadow(50 , Color.WHITE);
			drop.setInput(new Glow());
			
			setOnMousePressed(event -> setEffect(drop));
			setOnMouseReleased(event -> setEffect(null));
			
		}
	}
}

