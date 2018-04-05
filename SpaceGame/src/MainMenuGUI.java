import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
			
			
			Text textTitle = new Text("SPACING OUT");
			textTitle.setFont(Font.font ("Roboto", 40));
			textTitle.setFill(Color.WHITE);
			
			menu0.setMargin(textTitle, new Insets(100,0,100,0));
			
			MenuButton btnStart = new MenuButton("START");
			btnStart.setOnMouseClicked(event -> {
				
				
			});
			
			MenuButton btnScores = new MenuButton("SCORES");
			btnScores.setOnMouseClicked(event -> {
				
				
			});
			
			MenuButton btnControls = new MenuButton("CONTROLS");
			btnScores.setOnMouseClicked(event -> {
				
				
			});
			
			MenuButton btnExit = new MenuButton("EXIT");
			btnExit.setOnMouseClicked(event -> {
				System.exit(0);
				
			});
			
			menu0.getChildren().addAll(textTitle, btnStart, btnScores, btnControls, btnExit);
			
			
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
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

