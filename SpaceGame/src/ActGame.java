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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
	static List<Bullet> bullets;
	static List<Enemy> enemies;

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
		player = new Player(50, 200, graphicsContext);
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		for (int random = 0; random < 10; random++)
		{
			Enemy enemy = new Enemy(random * 50, 20, graphicsContext);
			enemies.add(enemy);
		}
	}

	private static void tickAndRender()
	{
		// clear canvas
		graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
		updateBullets();
		updateEnemies();
		if (currentlyActiveKeys.contains("LEFT"))
		{
			if (currentlyActiveKeys.contains("SPACE"))
			{
				player.shoot(bullets);
			}
			player.moveLeft();
		}
		else if (currentlyActiveKeys.contains("RIGHT"))
		{
			if (currentlyActiveKeys.contains("SPACE"))
			{
				player.shoot(bullets);
			}
			player.moveRight();
		}
		else if (currentlyActiveKeys.contains("SPACE"))
		{
			player.drawOval();
			player.shoot(bullets);
		}
		else
		{
			player.drawOval();
		}
		
	}

	public static void updateEnemies()
	{
		for (int random = 0; random < enemies.size(); random++)
		{
			enemies.get(random).hit(bullets);
			enemies.get(random).drawOval();
			
		}
	}

	public static void updateBullets()
	{
		for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++)
		{

			bullets.get(bulletNum).moveUp(HEIGHT);
		}
	}
}
