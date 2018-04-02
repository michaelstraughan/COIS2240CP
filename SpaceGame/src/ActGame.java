import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashSet;

public class ActGame extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	static Scene mainScene;
	static GraphicsContext graphicsContext;
	static int WIDTH = 512;
	static int HEIGHT = 256;
	static Player player;


	static HashSet<String> currentlyActiveKeys;

	@Override
	public void start(Stage mainStage)
	{
		mainStage.setTitle("Event Handling");

		Group root = new Group();
		mainScene = new Scene(root);
		mainStage.setScene(mainScene);

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		prepareActionHandlers();

		graphicsContext = canvas.getGraphicsContext2D();

		loadGraphics();

		/**
		 * Main "game" loop
		 */
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				tickAndRender();
			}
		}.start();

		mainStage.show();
	}

	private static void prepareActionHandlers()
	{
		// use a set so duplicates are not possible
		currentlyActiveKeys = new HashSet<String>();
		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				currentlyActiveKeys.add(event.getCode().toString());
			}
		});
		mainScene.setOnKeyReleased(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				currentlyActiveKeys.remove(event.getCode().toString());
			}
		});
	}

	private static void loadGraphics()
	{
		player = new Player(50, 50);
	}

	private static void tickAndRender()
	{
		// clear canvas
		graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);

		if (currentlyActiveKeys.contains("LEFT"))
		{
			player.moveLeft(graphicsContext);
		} 
		else if (currentlyActiveKeys.contains("RIGHT"))
		{
			player.moveRight(graphicsContext);
		} 
		else if (currentlyActiveKeys.contains("SPACE"))
		{
			player.shoot(graphicsContext);
		} 
		else
		{
			player.drawOval(graphicsContext);
		}
	}
}
