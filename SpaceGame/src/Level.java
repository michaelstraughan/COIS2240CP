import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class Level
{
	static Player player;
	static List<Bullet> bullets;
	static List<Enemy> enemies;
	static int WIDTH, HEIGHT;
	static Background Background;
	public Level(GraphicsContext graphicsContext, Scene mainScene, int WIDTH, int HEIGHT)
	{
		player = new Player(WIDTH/2, HEIGHT-100, graphicsContext);
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		this.WIDTH=WIDTH;
		this.HEIGHT=HEIGHT;
		Background= new Background(graphicsContext, HEIGHT,WIDTH);
		
	}
	public static void update(){
		updateBullets();
		updateEnemies();
		updateBackground();
	}
	public static void updateEnemies()
	{
		for (int random = 0; random < enemies.size(); random++)
		{
			enemies.get(random).checkHitStatus(bullets);
			enemies.get(random).drawObject();
			
		}
	}
	public static void updateBullets()
	{
		for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++)
		{

			bullets.get(bulletNum).moveUp(HEIGHT);
		}
	}
	public static void updateBackground()
	{
		Background.animateBackground(0,1);
	}
	public static void playerShoot(){
		player.shoot(bullets);
	}
	
}
