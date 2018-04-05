import javafx.application.Application;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuGUI extends Application {

	private GameMenu mainMenu;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new Pane();
		root.setPrefSize(400, 400);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		mainMenu = new GameMenu();
		
		root.getChildren().addAll(mainMenu);
			
		}
	


	private class GameMenu extends Parent {
		public GameMenu() {
			VBox menu0 = new VBox(10);
			VBox menu1 = new VBox(10);
			
			MenuButton btnStart = new MenuButton("Start");
			btnStart.setOnMouseClicked(event -> {
				
				
			});
			
			MenuButton btnScores = new MenuButton("Scores");
			btnScores.setOnMouseClicked(event -> {
				
				
			});
			
			MenuButton btnControls = new MenuButton("Controls");
			btnScores.setOnMouseClicked(event -> {
				
				
			});
			
			MenuButton btnExit = new MenuButton("Exit");
			btnExit.setOnMouseClicked(event -> {
				System.exit(0);
				
			});
			
			menu0.getChildren().addAll(btnStart, btnScores, btnControls, btnExit);
			
			Rectangle bg = new Rectangle (400, 400);
		
			getChildren().addAll(bg, menu0);
			
			MenuButton btnBack = new MenuButton("Back");
			btnBack.setOnMouseClicked(event -> {
				
			});
		}
	}
	
	private static class MenuButton extends StackPane {
		private Text text;
		
		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont().font(10));
			text.setFill(Color.WHITE);
			
			Rectangle bg = new Rectangle (200, 20);
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

