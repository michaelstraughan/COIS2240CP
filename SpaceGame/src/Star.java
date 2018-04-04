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
	int x, y;
	GraphicsContext gc;
	Scene theScene;

	public Star()
	{

	}

	/*
	 * -----------------------------------------------------------------------------------
	 * Star constructor, it accepts the GraphicContext on which it will be drawn and the Scene to get measurements
	 * -----------------------------------------------------------------------------------
	 */
	public Star(GraphicsContext gc, Scene theScene)
	{
		this.gc = gc;
		this.theScene = theScene;

		//X and Y will be  randomly generated inside the dimensions of the Scene

		x = (int) (Math.random() * theScene.getWidth());
		y = (int) (Math.random() * theScene.getHeight());
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
		if (this.x < theScene.getWidth() && this.y <theScene.getHeight())
		{
			this.x += x;
			this.y += y;
		}
		//If the X or Y are not on the scene it will reset the x and y to a randomly generated point along the x or y axis
		else
		{

			//double randomAxis = (Math.random() * 2);
			//generates a number between 0 and 2, if the number lower than one then it will move the star to a random point
			//along the y axis
			//if (randomAxis <= 1)
			//{
				this.x = (int) (Math.random() * theScene.getWidth());
				this.y = 0;
			//}
			//else, the point will be along the x axis
			//else
			//{
			//	this.x = 0;
			//	this.y = (int) (Math.random() * theScene.getHeight());
			//}
		}
	}
}
