import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage mainMenuStage) throws Exception {
		mainMenuStage.setTitle("Game Title Placeholder");
		
		StackPane mainMenu = new StackPane();
		Text textTitle = new Text("GAME TITLE PLACEHOLDER");
		textTitle.setFont(Font.font ("Roboto", 40));
		textTitle.setTextAlignment(TextAlignment.CENTER);
		
		
		mainMenu.getChildren().addAll(textTitle);
		
		Scene scene = new Scene(mainMenu, 600, 600);
		mainMenuStage.setScene(scene);
		mainMenuStage.show();
	} 
	
}
