import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/*
 * -----------------------------------------------------------------------------------
 * Player Class, creates a player
 * -----------------------------------------------------------------------------------
 */
public class Player extends Enemy
{
	public Player(double x, double y, GraphicsContext graphicsContext)
	{
		super(x, y, graphicsContext);
		setObjectSprite(new Image(  "file:SpaceShip.png",50,50,false,false ));
		setIsEnemy(false);
		getLocation().setHeight(getObjectSprite().getHeight());
		getLocation().setWidth(getObjectSprite().getWidth());
	}
	public void moveRight()
	{
		if(getLocation().getX()<550){
			getLocation().setX(getLocation().getX() + 3);
		}
		drawObject();
	}
	public void moveLeft()
	{
		if(getLocation().getX()>0){
			getLocation().setX(getLocation().getX() - 3);
		}
		drawObject();
	}
	public void shoot(List<Bullet> bullets)
	{
		
		finish = System.nanoTime(); //generates the second time variable
		
		if ((finish - start) / 10000000 >= 50) // if the time from the last shot is greater than or equal to 50 milliseconds, restricts the players shot
		{
			for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++) //this for loop will loop through the entire bullet list
			{
				if (bullets.get(bulletNum).getIsFired() == false) //if a bullet was set to not fired (Would have been set by the constructor or reset by Bullet methods)
				{
					
					bullets.get(bulletNum).fire(getLocation().getX(), getLocation().getY(), -1,getIsEnemy()); //fire method will fire an already created bullet object
					start = System.nanoTime(); //starts timer again, generating the first time variable
					return; //exits method
				}
			}

			Bullet bullet = new Bullet(getLocation().getX(), getLocation().getY(), getGraphicsContext(),-1,getIsEnemy()); //if no bullet has been found that is set to not fired it will create a new bullet
			bullets.add(bullet); //this new bullet will be added to the bullet list
			
			start = System.nanoTime(); //starts timer again, generating the first time variable
		}
	}

}