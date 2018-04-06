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

	private GameMenu mainMenu;
	
	@Override
	public void start(Stage mainMenuStage) throws Exception {
		mainMenuStage.setTitle("SPACING OUT");
		
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
			
			VBox menu0 = new VBox(10);
			menu0.setAlignment(Pos.CENTER);
			menu0.setTranslateX(150);
			
			VBox menu1 = new VBox(10);
			menu1.setAlignment(Pos.CENTER);
			menu1.setTranslateX(150);
			menu1.setTranslateY(1000);
			
			VBox menu2 = new VBox(10);
			menu2.setAlignment(Pos.CENTER);
			menu2.setTranslateX(150);
			menu2.setTranslateY(1000);
			
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
			
			menu0.setMargin(textTitle, new Insets(100,0,100,0));
			menu1.setMargin(lblScores, new Insets(100,0,100,0));			
			menu2.setMargin(lblControls, new Insets(100,0,100,0));
			
			MenuButton btnStart = new MenuButton("START");
			btnStart.setOnMouseClicked(event -> {

				mainMenu.setVisible(false);
				
			});
			
			MenuButton btnScores = new MenuButton("SCORES");
			btnScores.setOnMouseClicked(event -> {
				getChildren().add(menu1);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
				tt.setToY(1000);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
				tt1.setToY(0);
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt -> {
				getChildren().remove(menu0);
				});
				
			});
			
			MenuButton btnControls = new MenuButton("CONTROLS");
			btnControls.setOnMouseClicked(event -> {
				getChildren().add(menu2);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
				tt.setToY(1000);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
				tt1.setToY(0);
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt -> {
				getChildren().remove(menu0);
				});
				
			});
			
			MenuButton btnExit = new MenuButton("EXIT");
			btnExit.setOnMouseClicked(event -> {
				System.exit(0);
				
			});
			
			
			MenuButton btnBack = new MenuButton("BACK");
			btnBack.setOnMouseClicked(event -> {
				getChildren().add(menu0);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
				tt.setToY(1000);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
				tt1.setToY(0);
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt -> {
				getChildren().remove(menu1);
				});
				
			});
			
			
			MenuButton btnBack2 = new MenuButton("BACK");
			btnBack2.setOnMouseClicked(event -> {
				getChildren().add(menu0);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
				tt.setToY(1000);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
				tt1.setToY(0);
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt -> {
				getChildren().remove(menu2);
				});
				
			});
			
			menu0.getChildren().addAll(textTitle, btnStart, btnScores, btnControls, btnExit);
			menu1.getChildren().addAll(lblScores, btnBack);
			menu2.getChildren().addAll(lblControls, btnBack2);
			
			
			Rectangle bg = new Rectangle (600, 600);
		
			getChildren().addAll(bg, menu0);
						
			
			
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

