import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ActGame extends Application
{
	
	static Scene mainScene;
	static GraphicsContext graphicsContext;
	static int WIDTH = 600;
	static int HEIGHT = 600;
	static HashSet<String> currentlyActiveKeys;
	static Level level;
	
	@Override
	public void start(Stage mainStage)
	{
		mainStage.setTitle("Event Handling");

		
		
		Group root = new Group();
		mainScene = new Scene(root,WIDTH,HEIGHT,Color.BLACK);
		mainStage.setScene(mainScene);

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		prepareActionHandlers();

		graphicsContext = canvas.getGraphicsContext2D();

		level = new Level(graphicsContext,mainScene,WIDTH, HEIGHT);

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
	private static void tickAndRender()
	{
		// clear canvas
		graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
	Level.update();
		if (currentlyActiveKeys.contains("LEFT"))
		{
			if (currentlyActiveKeys.contains("SPACE"))
			{
				Level.playerShoot();
			}
			Level.player.moveLeft();
		}
		else if (currentlyActiveKeys.contains("RIGHT"))
		{
			if (currentlyActiveKeys.contains("SPACE"))
			{
				Level.playerShoot();
			}
			Level.player.moveRight();
		}
		else if (currentlyActiveKeys.contains("SPACE"))
		{
			Level.playerShoot();
		}
		else
		{
			Level.player.drawObject();
		}
		
	}


}
