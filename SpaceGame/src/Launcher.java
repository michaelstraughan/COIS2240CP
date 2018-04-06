import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Launcher extends Application {

	Stage gameWindow;
	Scene gameField;
	//GameMenu mainMenu;
	
	public static void main(String[] args)
	{
		launch(args);
		
	}
	
	@Override
	public void start(Stage gameStage) throws Exception {
		
	
		//Label label1 = new Label("test");
		//Button button1 = new Button ("go");
		
		//VBox gameMenu = new VBox(10);
		//gameMenu.getChildren().addAll(label1, button1);
		
		//mainMenu = new Scene(MainMenuGUI.GameMenu.class);

		Pane menuPane = new Pane();
		menuPane.setPrefSize(600, 600);
		//menuPane.getChildren().addAll(mainMenu);
		
		Scene menuScene = new Scene(menuPane);

		
		//mainMenu = new GameMenu();

		

		
		gameStage.setTitle("SPACING OUT");
		gameStage.setResizable(false);
		gameStage.setScene(menuScene);
		gameStage.show();
		
	}

}
