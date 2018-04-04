import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/*
 * -----------------------------------------------------------------------------------
 * Player class, creates the user. Is the super class to bullets and enemies. Has a location x,y , a graphics context for a scene,
 * an Image sprite. and two
 * -----------------------------------------------------------------------------------
 */
public class Player
{
	private double x, y; //Location
	
	private boolean hitStatus = false;
	private GraphicsContext graphicsContext; //scene for drawing
	
	long start = System.nanoTime(), finish; // timer to restrict shooting, starts at the creation of the player
	
	private Image sprite   = new Image( "file:SpaceShip.png",50,50,false,false ); //sprite of player
	/*
	 * -----------------------------------------------------------------------------------
	 * Player constructor, sets the x and y cordinates as well as the scene it will be drawn on
	 * -----------------------------------------------------------------------------------
	 */
	public Player(double x, double y, GraphicsContext graphicsContext)
	{
		this.x = x;
		this.y = y;
		
		this.graphicsContext = graphicsContext;
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
		this.sprite=sprite;
	}	

	/*
	 * -----------------------------------------------------------------------------------
	 * Drawing object method, draws onto scene at Location
	 * -----------------------------------------------------------------------------------
	 */
	public void drawObject()
	{
		if(hitStatus==false){
		graphicsContext.drawImage(getSprite(),getX(), getY());
		}
	}
	/*
	 * -----------------------------------------------------------------------------------
	 * Move Right method, moves the player to the right and calls the draw method
	 * -----------------------------------------------------------------------------------
	 */
	public void moveRight()
	{
		setX(getX() + 1);
		
		drawObject();
	}
	/*
	 * -----------------------------------------------------------------------------------
	 * Move Left method, moves the player to the left and calls the draw method
	 * -----------------------------------------------------------------------------------
	 */
	public void moveLeft()
	{
		setX(getX() - 1);
		
		drawObject();
	}
	/*
	 * -----------------------------------------------------------------------------------
	 * Shoot method, accepts the bullet list from the level to assess if a bullet will be generated or recycled.
	 * -----------------------------------------------------------------------------------
	 */
	public void shoot(List<Bullet> bullets)
	{
		drawObject();
		
		finish = System.nanoTime(); //generates the second time variable
		
		if ((finish - start) / 10000000 >= 50) // if the time from the last shot is greater than or equal to 50 milliseconds, restricts the players shot
		{
			for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++) //this for loop will loop through the entire bullet list
			{
				if (bullets.get(bulletNum).checkFired() == false) //if a bullet was set to not fired (Would have been set by the constructor or reset by Bullet methods)
				{
					bullets.get(bulletNum).fire(x, y); //fire method will fire an already created bullet object
					start = System.nanoTime(); //starts timer again, generating the first time variable
					return; //exits method
				}
			}

			Bullet bullet = new Bullet(x, y, graphicsContext); //if no bullet has been found that is set to not fired it will create a new bullet
			bullets.add(bullet); //this new bullet will be added to the bullet list
			
			start = System.nanoTime(); //starts timer again, generating the first time variable
		}
	}
	/*
	 * -----------------------------------------------------------------------------------
	 * CheckHitStatus method, cycles through the bullets list and determines if any have hit the player
	 * -----------------------------------------------------------------------------------
	 */
	public void checkHitStatus(List<Bullet> bullets)
	{
		for (int count = 0; count < bullets.size(); count++) //will cycle through the bullet list
		{
			// this if statement takes the bullets location and determines if it shares a domain with the image and makes the enemy disappear
			if (hitStatus==false&&bullets.get(count).getX() < x+(sprite.getWidth()/2)&&bullets.get(count).getX() > x-(sprite.getWidth()/2) && bullets.get(count).getY() == y)
				
			{
				setHitStatus(true);
				bullets.get(count).reset();
			}
		}
	}
	public void setHitStatus(boolean hitStatus)
	{
		this.hitStatus= hitStatus;
	}

}