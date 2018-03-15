import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
/*
 * -----------------------------------------------------------------------------------
 * StarrySky Class. Creates an array of stars for the background and will move them.
 * -----------------------------------------------------------------------------------
 */
public class StarrySky
{
	Star[] stars = new Star[100];
    GraphicsContext gc;
    Scene theScene;
	public StarrySky()
	{
	}
	/*
	 * -----------------------------------------------------------------------------------
	 * StarrySky Constructor. Accepts the GraphicsContext the stars will be drawn on and the scene for the dimensions
	 * -----------------------------------------------------------------------------------
	 */
	public StarrySky(GraphicsContext gc,Scene theScene)
	{
		this.gc=gc;
		this.theScene=theScene;
		//For loop creates 100 stars
		for (int arrayCount = 0; arrayCount < stars.length; arrayCount++)
		{
			stars[arrayCount]=new Star(this.gc, this.theScene);
			stars[arrayCount].drawStar();
		}
	}
	/*
	 * -----------------------------------------------------------------------------------
	 * moveSky Method, will loop and move every star and then redraw them
	 * -----------------------------------------------------------------------------------
	 */
	public void moveSky()
	{
		for (int arrayCount = 0; arrayCount < stars.length; arrayCount++)
		{
			stars[arrayCount].moveStar(1, -1);//these parameters change the x and y value. In this case, x +1, y-1
			stars[arrayCount].drawStar();
		}
		
	}
}
