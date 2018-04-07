import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Level
{
	static Player player;
	static List<Bullet> bullets;
	static Enemy[] enemies;
	static int WIDTH, HEIGHT, score = 0, lives = 3, enemyDirection = 0;
	static Background Background;
	static GraphicsContext graphicsContext;
	static Boolean gameOver = false;
	private static String highScore = "0";

	public Level(GraphicsContext graphicsContext, int WIDTH, int HEIGHT)
	{
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.graphicsContext = graphicsContext;

		player = new Player(WIDTH / 2, HEIGHT - 100, graphicsContext);
		bullets = new ArrayList<Bullet>();
		enemies = new Enemy[50];
		layoutEnemies();

		Background = new Background(graphicsContext, HEIGHT, WIDTH);
		Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
		graphicsContext.setFont(theFont);
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.setLineWidth(1);

	}

	public static void update()
	{
		if (gameOver == false)
		{
			updateBackground();
			updateInformation();
			updateBullets();
			checkHitStatus();
			updateEnemies();
		}
		else
		{
			gameOver();
			
			CheckScore();
			if (highScore.equals("0"))
			{
				//initiate
				highScore = Level.GetHighScore();
			}
			
			
		}

	}
	
	public static void CheckScore()
	{
		if (score > Integer.parseInt(highScore))
		{
			highScore = String.valueOf(score);
			File scoreFile = new File("highscore.dat");
			if (!scoreFile.exists())
			{
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try
			{
				writeFile = new FileWriter(scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(highScore);
			}
			catch (Exception e)
			{
				
			}
			finally
			{
				if (writer != null)
				{
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static String GetHighScore() {
		
		FileReader readFile = null;
		BufferedReader reader = null;
		
		try
		{
		readFile = new FileReader("highscore.dat");
		reader = new BufferedReader(readFile);
		return reader.readLine();
		
		}
		catch (Exception e)
		{
			return "0";
		}
		finally
		{
			try {
				if (reader != null)
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	private static void updateInformation()
	{
		graphicsContext.fillText("Score: " + score, 10, 20);
		graphicsContext.fillText("Lives: " + lives, 500, 20);

	}

	public static void updateEnemies()
	{
		double moveX, moveY;

		for (int count = 0; count < enemies.length; count++)
		{

			if (count == 0)
			{
				if (enemies[count].getLocation().getX() < 0)
				{
					enemyDirection = 1;
				}
				else if (enemies[count].getLocation().getX() > 100)
				{
					enemyDirection = 0;
				}
			}
			if (enemyDirection == 0)
			{
				moveX = -1;
				moveY = 0.1;
			}
			else
			{
				moveX = 1;
				moveY = 0.1;
			}

			enemies[count].shoot(bullets);
			enemies[count].move(moveX, moveY);
			enemies[count].drawObject();
		}

	}

	public static void updateBullets()
	{
		for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++)
		{

			bullets.get(bulletNum).move(HEIGHT);
		}
	}

	public static void updateBackground()
	{
		Background.animateBackground(0, 1);
	}

	public static void playerShoot()
	{
		player.shoot(bullets);
	}

	public static void layoutEnemies()
	{
		int x = 45, y = 25, startX = x;
		enemies[0] = new Enemy(x, y, graphicsContext);
		for (int count = 1; count < enemies.length; count++)
		{
			if (count % 10 == 0)
			{
				x = startX;
				y = y + 50;
			}
			else
			{
				x = x + 50;
			}
			enemies[count] = new Enemy(x, y, graphicsContext);
		}
	}

	public static void checkHitStatus()
	{
		for (int count2 = 0; count2 < bullets.size(); count2++) //will cycle through the bullet list
		{
			// this if statement takes the bullets location and determines if it shares a domain with the image and makes the enemy disappear
			if (bullets.get(count2).getIsEnemy() == true && bullets.get(count2).getIsFired() == true
					&& player.getHitStatus() == false
					&&bullets.get(count2).getLocation().checkLocation(player.getLocation())==true)
			{

				player.setHitStatus(true);
				if (lives > 1)
				{
					lives = lives - 1;
				}
				else
				{
					gameOver = true;
				}
				for (int count = 0; count < bullets.size(); count++)
				{
					bullets.get(count).reset();
				}
				player.startTime();
				bullets.get(count2).reset();
			}
		}
		for (int count = 0; count < enemies.length; count++)
		{
			for (int count2 = 0; count2 < bullets.size(); count2++) //will cycle through the bullet list
			{
				// this if statement takes the bullets location and determines if it shares a domain with the image and makes the enemy disappear
				if (bullets.get(count2).getIsEnemy() == false && bullets.get(count2).getIsFired() == true
						&& enemies[count].getHitStatus() == false
						&& enemies[count].getLocation().checkLocation(bullets.get(count2).getLocation())==true)
				{
					enemies[count].startTime();
					enemies[count].setHitStatus(true);
					score = score + 50;
					bullets.get(count2).reset();
				}
			}
		}
		for (int count = 0; count < enemies.length; count++)
		{// this if statement takes the bullets location and determines if it shares a domain with the image and makes the enemy disappear
			if (enemies[count].getHitStatus() == false && enemies[count].getLocation().getY() > player.getLocation().getY() + 50)
			{
				gameOver = true;
			}
		}
		int check = 0;
		for (int count = 0; count < enemies.length; count++)
		{
			if (enemies[count].getHitStatus() == true)
			{
				check++;

			}
		}
		if (check == enemies.length)
		{
			for (int count = 0; count < bullets.size(); count++)
			{
				bullets.get(count).reset();
			}
			layoutEnemies();
			lives++;

		}

	}

	public static void gameOver()
	{

	}

}
