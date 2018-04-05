import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class Level
{
	static Player player;
	static List<Bullet> bullets;
	static Enemy[] enemies;
	static int WIDTH, HEIGHT, enemyDirection=0;
	static Background Background;

	public Level(GraphicsContext graphicsContext, Scene mainScene, int WIDTH, int HEIGHT)
	{
		player = new Player(WIDTH / 2, HEIGHT - 100, graphicsContext,WIDTH);
		bullets = new ArrayList<Bullet>();
		enemies = new Enemy[50];
		layoutEnemies(graphicsContext);
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		Background = new Background(graphicsContext, HEIGHT, WIDTH);

	}

	public static void update()
	{
		updateBackground();
		updateBullets();
		updateEnemies();
		
	}

	public static void updateEnemies()
	{
		//enemyDirection=Enemy.move(enemies,enemyDirection);
		for (int count = 0; count < enemies.length; count++)
		{
			enemyDirection=enemies[count].moveEnemy(enemyDirection,count);
			enemies[count].checkHitStatus(bullets);
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
	public static void layoutEnemies(GraphicsContext graphicsContext)
	{
		int x=45,y=25,startX=x;
		enemies[0]=new Enemy(x,y, graphicsContext);
		for (int count = 1; count < enemies.length; count++)
		{
			if(count%10==0){
				x=startX;
				y=y+50;
			}
			else{
			x=x+50;
			}
			enemies[count]=new Enemy(x,y, graphicsContext);
		}
	}

}
