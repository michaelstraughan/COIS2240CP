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
	static long start, finish;

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
		start = System.nanoTime();
	}

	private static void tickAndRender()
	{
		// clear canvas
		graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);

		if (currentlyActiveKeys.contains("LEFT"))
		{
			if (currentlyActiveKeys.contains("SPACE"))
			{
				shoot();
			}
			player.moveLeft();
		}
		else if (currentlyActiveKeys.contains("RIGHT"))
		{
			if (currentlyActiveKeys.contains("SPACE"))
			{
				shoot();
			}
			player.moveRight();
		}
		else if (currentlyActiveKeys.contains("SPACE"))
		{
			player.drawOval();
			shoot();
		}
		else
		{
			player.drawOval();
		}
		updateBullets();
	}

	public static void shoot()
	{
		int bulletNum = 0;
		finish = System.nanoTime();
		if ((finish - start) / 10000000 >= 50)
		{
			while (bulletNum < bullets.size())
			{
				if (bullets.get(bulletNum).checkFired() == false)
				{
					bullets.get(bulletNum).reset(player.getX(),player.getY());
					start = System.nanoTime();
					System.out.print("\n" + bullets.size());
					return;
				}
				else
				{
				}
				bulletNum++;
			}
			
			Bullet bullet = new Bullet(player.getX(), player.getY(), graphicsContext);
			bullets.add(bullet);
			start = System.nanoTime();
		}
	}

	public static void updateBullets()
	{
		int bulletNum = 0;
		while (bulletNum < bullets.size())
		{
			
			bullets.get(bulletNum).moveUp(HEIGHT);
			bulletNum++;
		}
	}
}
