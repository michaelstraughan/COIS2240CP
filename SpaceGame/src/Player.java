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
   int sceneWidth;
	public Player(double x, double y, GraphicsContext graphicsContext, int sceneWidth)
	{
		super(x, y, graphicsContext);
		this.sceneWidth=sceneWidth;
		Image sprite   = new Image(  "file:SpaceShip.png",50,50,false,false ); //its sprite is AlienShip
		setSprite(sprite);
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
	}
	public void moveRight()
	{
		if(getX()<sceneWidth-getSprite().getWidth()){
		setX(getX() + 1);
		}
		drawObject();
	}
	public void moveLeft()
	{
		if(getX()>0){
		setX(getX() - 1);
		}
		drawObject();
	}
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
					
					bullets.get(bulletNum).fire(getX(), getY(), -1); //fire method will fire an already created bullet object
					start = System.nanoTime(); //starts timer again, generating the first time variable
					return; //exits method
				}
			}

			Bullet bullet = new Bullet(getX(), getY(), getGraphicsContext(),-1); //if no bullet has been found that is set to not fired it will create a new bullet
			bullets.add(bullet); //this new bullet will be added to the bullet list
			
			start = System.nanoTime(); //starts timer again, generating the first time variable
		}
	}

}