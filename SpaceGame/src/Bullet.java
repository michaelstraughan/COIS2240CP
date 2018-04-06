import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
 * -----------------------------------------------------------------------------------
 * Bullet class, used to create a single bullet. bullet can be moved up or down depending on if it is a character/enemy firing. Has two states: fired and not fired. 
 * When it is 'fired' it is rendered. It becomes non fired only if it hits an enemy or is past the boundary.
 * -----------------------------------------------------------------------------------
 */
public class Bullet extends Enemy
{
	//The Bullet can be in two states, fired and not fired.
	private boolean fired = false;
	int direction;

	/*
	 * -----------------------------------------------------------------------------------
	 * Bullet Constructor, takes the current x and y from the shooter as well as the scene to be drawn on
	 * -----------------------------------------------------------------------------------
	 */
	public Bullet(double x, double y, GraphicsContext graphicsContext, int direction, boolean isEnemy)
	{
		//+15 pixels makes the bullet shoot from the center of the Image
		super(x + 15, y, graphicsContext);

		this.direction = direction;
		fired = true; //bullet becomes 'fired' the moment its created
		Image sprite = new Image("file:Bullet.png", 20, 20, false, false); //the bullet sprite
		setisEnemy(isEnemy);
		setSprite(sprite);
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Move bullet method. takes the height of the scene so it does not go out of bounds either at the top or bottom.
	 * If it is too high (0 is the top in this case) it will be classified as not fired(reset) and then be recycled and re-fired later. 
	 * -----------------------------------------------------------------------------------
	 */
	public void move(int HEIGHT)
	{
		int direction;
		if (getisEnemy() == false)
		{
			direction = -5;
		}
		else
		{
			direction = 5;
		}
		if (getY() > (HEIGHT - HEIGHT) && getY() < HEIGHT && fired == true)
		{
			setY(getY() + direction); //-1 indicates 1 unit moved upwards
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
	public void fire(double x, double y, int direction, boolean isEnemy)
	{
		setX(x + 15);
		setY(y);
		fired = true;
		this.direction = direction;
		setisEnemy(isEnemy);
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
