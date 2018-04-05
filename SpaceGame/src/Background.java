import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
/*
 * -----------------------------------------------------------------------------------
 * Background Class. Creates an array of stars for the background and will move them.
 * -----------------------------------------------------------------------------------
 */
public class Background //StarBackground
{
	Star[] stars = new Star[100];
    GraphicsContext gc;
    int height, width;
	/*
	 * -----------------------------------------------------------------------------------
	 * Background Constructor. Accepts the GraphicsContext the stars will be drawn on and the dimensions
	 * -----------------------------------------------------------------------------------
	 */
	public Background(GraphicsContext gc,int height, int width)
	{
		this.gc=gc;
		this.height=height;
		this.width=width;
		//For loop creates 100 stars
		for (int arrayCount = 0; arrayCount < stars.length; arrayCount++)
		{
			stars[arrayCount]=new Star(this.gc, height,width);
			stars[arrayCount].drawStar();
		}
	}
	/*
	 * -----------------------------------------------------------------------------------
	 * moveSky Method, will loop and move every star and then redraw them
	 * -----------------------------------------------------------------------------------
	 */
	public void animateBackground(int x, int y)
	{
		for (int arrayCount = 0; arrayCount < stars.length; arrayCount++)
		{
			stars[arrayCount].moveStar(x, y);
			stars[arrayCount].drawStar();
		}
		
	}
}
