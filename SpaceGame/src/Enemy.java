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

	private double x, y, height, width;//Location, size
	private boolean hitStatus = false, isEnemy = true;
	private GraphicsContext graphicsContext; //scene for drawing
	long start = System.nanoTime(), finish; // timer to restrict shooting, starts at the creation of the player

	private Image sprite; //sprite of player
	/*
	 * -----------------------------------------------------------------------------------
	 * Enemy constructor, sets the x and y coordinates as well as the scene it will be drawn on
	 * -----------------------------------------------------------------------------------
	 */

	public Enemy(double x, double y, GraphicsContext graphicsContext)
	{
		this.x = x;
		this.y = y;
		sprite = new Image("file:AlienShip.png", 50, 50, false, false);
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
		this.setGraphicsContext(graphicsContext);

	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Getter method for X coordinate
	 * -----------------------------------------------------------------------------------
	 */
	public double getX()
	{
		return x;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Setter method for X coordinate
	 * -----------------------------------------------------------------------------------
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Getter method for Y coordinates
	 * -----------------------------------------------------------------------------------
	 */
	public double getY()
	{
		return y;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Setter method for Y coordinates
	 * -----------------------------------------------------------------------------------
	 */
	public void setY(double y)
	{
		this.y = y;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Getter method for image sprite
	 * -----------------------------------------------------------------------------------
	 */
	public Image getSprite()
	{
		return sprite;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Setter method for image sprite
	 * -----------------------------------------------------------------------------------
	 */
	public void setSprite(Image sprite)
	{
		this.sprite = sprite;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Drawing object method, draws onto scene at Location
	 * -----------------------------------------------------------------------------------
	 */
	public void drawObject()
	{
		if (hitStatus == false)
		{
			getGraphicsContext().drawImage(getSprite(), getX(), getY());
		}
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Move Right method, moves the enemy to the right and calls the draw method
	 * -----------------------------------------------------------------------------------
	 */

	public void move(double moveX,double moveY)
	{
		x=x+moveX;
		y=y+moveY;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Shoot method, accepts the bullet list from the level to assess if a bullet will be generated or recycled.
	 * -----------------------------------------------------------------------------------
	 */
	public void shoot(List<Bullet> bullets)
	{
		double shootProb=(Math.random());
		if(shootProb<0.0003333){
		finish = System.nanoTime(); //generates the second time variable

		if ((finish - start) / 10000000 >= 50) // if the time from the last shot is greater than or equal to 50 milliseconds, restricts the players shot
		{
			for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++) //this for loop will loop through the entire bullet list
			{
				if (bullets.get(bulletNum).checkFired() == false) //if a bullet was set to not fired (Would have been set by the constructor or reset by Bullet methods)
				{

					bullets.get(bulletNum).fire(x, y, 1,isEnemy); //fire method will fire an already created bullet object
					start = System.nanoTime(); //starts timer again, generating the first time variable
					return; //exits method
				}
			}

			Bullet bullet = new Bullet(x, y, getGraphicsContext(), 1,isEnemy); //if no bullet has been found that is set to not fired it will create a new bullet
			bullets.add(bullet); //this new bullet will be added to the bullet list

			start = System.nanoTime(); //starts timer again, generating the first time variable
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

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public boolean getisEnemy()
	{
		return isEnemy;
	}

	public void setisEnemy(boolean isEnemy)
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

}
