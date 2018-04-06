import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
 * -----------------------------------------------------------------------------------
 * Enemy class, creates the user. Is the super class to bullet and player. Has a location x,y , a graphics context for a scene,
 * an Image sprite.
 * -----------------------------------------------------------------------------------
 */
public class Enemy
{

	private Location location;
	private boolean hitStatus = false, isEnemy = true;
	private GraphicsContext graphicsContext; //scene for drawing
	long start = System.nanoTime(), finish; // timer to restrict shooting, starts at the creation of the player

	private Image objectSprite;
	private Image explosion= new Image("file:explosion.png", 50, 50, false, false); //sprite of player
	/*
	 * -----------------------------------------------------------------------------------
	 * Enemy constructor, sets the x and y coordinates as well as the scene it will be drawn on
	 * -----------------------------------------------------------------------------------
	 */

	public Enemy(double x, double y, GraphicsContext graphicsContext)
	{
		setObjectSprite(new Image("file:AlienShip.png", 50, 50, false, false));
		
		setLocation(new Location(x, y,getObjectSprite().getHeight(),getObjectSprite().getWidth()));
		
		this.setGraphicsContext(graphicsContext);
	}
	/*
	 * -----------------------------------------------------------------------------------
	 * Getter method for object sprite
	 * -----------------------------------------------------------------------------------
	 */
	public Image getObjectSprite()
	{
		return objectSprite;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Setter method for image sprite
	 * -----------------------------------------------------------------------------------
	 */
	public void setObjectSprite(Image objectSprite)
	{
		this.objectSprite = objectSprite;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Drawing object method, draws onto scene at Location
	 * -----------------------------------------------------------------------------------
	 */
	public void drawObject()
	{
		finish = System.nanoTime();
		if (hitStatus == false)
		{
			getGraphicsContext().drawImage(getObjectSprite(), getLocation().getX(), getLocation().getY());
		}
		else if ((finish - start) / 10000000 <= 10)
		{
			drawExplosion();
		}
		else
		{
			if (isEnemy==false){
				for (double wait = 0; wait <= 500000000; wait++)
				{
				}
				setHitStatus(false);
			}
			
		}
	}
	public void drawExplosion()
	{
			getGraphicsContext().drawImage(getExplosion(), getLocation().getX(), getLocation().getY());
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Move Right method, moves the enemy to the right and calls the draw method
	 * -----------------------------------------------------------------------------------
	 */

	public void move(double moveX, double moveY)
	{
		getLocation().setX(getLocation().getX()+moveX);
		getLocation().setY(getLocation().getY()+moveY);
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Shoot method, accepts the bullet list from the level to assess if a bullet will be generated or recycled.
	 * -----------------------------------------------------------------------------------
	 */
	public void shoot(List<Bullet> bullets)
	{
		double shootProb = (Math.random());
		if (hitStatus == false)
		{
			if (shootProb < 0.0003333)
			{
				finish = System.nanoTime(); //generates the second time variable

				if ((finish - start) / 10000000 >= 50) // if the time from the last shot is greater than or equal to 50 milliseconds, restricts the players shot
				{
					for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++) //this for loop will loop through the entire bullet list
					{
						if (bullets.get(bulletNum).getIsFired() == false) //if a bullet was set to not fired (Would have been set by the constructor or reset by Bullet methods)
						{

							bullets.get(bulletNum).fire(getLocation().getX(), getLocation().getY(), 1, isEnemy); //fire method will fire an already created bullet object
							start = System.nanoTime(); //starts timer again, generating the first time variable
							return; //exits method
						}
					}

					Bullet bullet = new Bullet(getLocation().getX(), getLocation().getY(), getGraphicsContext(), 1, isEnemy); //if no bullet has been found that is set to not fired it will create a new bullet
					bullets.add(bullet); //this new bullet will be added to the bullet list

					start = System.nanoTime(); //starts timer again, generating the first time variable
				}
			}
		}
	}

	public void setHitStatus(boolean hitStatus)
	{
		this.hitStatus = hitStatus;
	}

	public boolean getHitStatus()
	{
		return hitStatus;
	}

	public boolean getIsEnemy()
	{
		return isEnemy;
	}

	public void setIsEnemy(boolean isEnemy)
	{
		this.isEnemy = isEnemy;
	}

	public GraphicsContext getGraphicsContext()
	{
		return graphicsContext;
	}

	public void setGraphicsContext(GraphicsContext graphicsContext)
	{
		this.graphicsContext = graphicsContext;
	}

	public void startTime()
	{
		start = System.nanoTime();
	}

	public Image getExplosion()
	{
		return explosion;
	}

	public void setExplosion(Image explosion)
	{
		this.explosion = explosion;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

}
