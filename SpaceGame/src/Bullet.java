import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
 * -----------------------------------------------------------------------------------
 * Bullet class, used to create a single bullet. bullet can be moved up or down depending on if it is a character/enemy firing. Has two states: fired and not fired. 
 * When it is 'fired' it is rendered. It becomes non fired only if it hits an enemy or is past the boundary.
 * -----------------------------------------------------------------------------------
 */
public class Bullet extends Player
{
	//The Bullet can be in two states, fired and not fired.
	private boolean fired = false;

	/*
	 * -----------------------------------------------------------------------------------
	 * Bullet Constructor, takes the current x and y from the shooter as well as the scene to be drawn on
	 * -----------------------------------------------------------------------------------
	 */
	public Bullet(double x, double y, GraphicsContext graphicsContext)
	{
		super(x, y, graphicsContext);

		fired = true; //bullet becomes 'fired' the moment its created

		Image sprite = new Image("file:Bullet.png", 20, 20, false, false); //the bullet sprite
		setSprite(sprite);
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Move bullet method. takes the height of the scene so it does not go out of bounds either at the top or bottom.
	 * If it is too high (0 is the top in this case) it will be classified as not fired(reset) and then be recycled and re-fired later. 
	 * -----------------------------------------------------------------------------------
	 */
	public void moveUp(int HEIGHT)
	{
		if (getY() > (HEIGHT - HEIGHT) && fired == true)
		{
			setY(getY() - 1); //-1 indicates 1 unit moved upwards
			drawObject();
		}
		else
		{
			fired = false;
		}
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * checkFired is the getter method for the state of fired
	 * -----------------------------------------------------------------------------------
	 */
	public boolean checkFired()
	{
		return fired;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * fire method, takes a bullet on the scene and fires it from x, y location
	 * -----------------------------------------------------------------------------------
	 */
	public void fire(double x, double y)
	{
		setX(x);
		setY(y);
		fired = true;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * reset method resets the fired variable to false, and will be dealt within next frame render if statements
	 * -----------------------------------------------------------------------------------
	 */
	public void reset()
	{
		fired = false;
	}
}
