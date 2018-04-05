import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
 * -----------------------------------------------------------------------------------
 * Star class, used to create a single star.
 * -----------------------------------------------------------------------------------
 */
public class Star
{
	int x, y, height, width;
	GraphicsContext gc;

	/*
	 * -----------------------------------------------------------------------------------
	 * Star constructor, it accepts the GraphicContext on which it will be drawn and the Scene to get measurements
	 * -----------------------------------------------------------------------------------
	 */
	public Star(GraphicsContext gc, int height, int width)
	{
		this.gc = gc;
		this.height=height;
		this.width=width;

		//X and Y will be  randomly generated inside the dimensions of the Scene

		x = (int) (Math.random() * width);
		y = (int) (Math.random() * height);
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * DrawStar method. Will draw a 1x1 white Star at its current x and y location
	 * -----------------------------------------------------------------------------------
	 */
	public void drawStar()
	{
		gc.setFill(Color.WHITE);
		gc.fillOval(x, y, 1, 1);
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * MoveStar method. this will accept two values that will be added to the X and Y variables. If the value of the 
	 * the current x and y is greater than that of the scene it will randomly generate the star along the x or y axis.
	 * -----------------------------------------------------------------------------------
	 */
	public void moveStar(int x, int y)
	{
		//If the X and Y value are on theScene (in this case, y=0 is the top of the scene) it will add the parameters
		//to the x and y.
		if (this.x < width && this.y < height)
		{
			this.x += x;
			this.y += y;
		}
		//If the X or Y are not on the scene it will reset the x and y to a randomly generated point along the x or y axis
		else
		{
			//spawns along the top screen specifically. Changing this will change which side they spawn from.
			this.x = (int) (Math.random() * width);
			this.y = 0;
		}
	}
}
