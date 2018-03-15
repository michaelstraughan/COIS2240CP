import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class SpaceGame extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Space Game");
        Pane root = new Pane();
        Scene theScene = new Scene (root,500,500,Color.BLACK);
        primaryStage.setScene(theScene);
        Canvas canvas = new Canvas (theScene.getWidth(),theScene.getHeight()) ;
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        MainMenu mainMenu= new MainMenu(gc, theScene);
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	// Clear the canvas
                gc.clearRect(0, 0, theScene.getWidth(),theScene.getHeight());

                // background image clears canvas
                
                mainMenu.animateMenu();
               
            }
        }.start();
        primaryStage.show();
      
    }
}