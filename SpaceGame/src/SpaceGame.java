

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
        GridPane root2 = new GridPane();
        root.getChildren().add(root2);
        
        //Button buttontest = new Button("CLICK");
        //buttontest.setLayoutX(500);
        //buttontest.setLayoutY(250);
        
        Scene theScene = new Scene (root,500,500,Color.BLACK);
       
        Canvas canvas = new Canvas (theScene.getWidth(),theScene.getHeight()) ;
        //buttontest.setPrefSize(10, 10);
        //root2.add(buttontest,5,0);
        root.getChildren().add(canvas);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        MainMenu mainMenu= new MainMenu(gc, theScene);
        
        primaryStage.setScene(theScene);

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