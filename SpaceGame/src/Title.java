import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*
 * -----------------------------------------------------------------------------------
 * Title class, creates a title that can be moved (sliding from the left), shook, and placed.
 * -----------------------------------------------------------------------------------
 */
public class Title
{
	int x, y, shakeMax = 0;
	GraphicsContext gc;
	Scene theScene;
	String text;

	/*
	 * -----------------------------------------------------------------------------------
	 * Title Constructor. takes GraphicsContext where the title will be drawn, the Scene dimensions, the actual 
	 * title text and its x and y location
	 * -----------------------------------------------------------------------------------
	 */
	public Title(GraphicsContext gc, Scene theScene, String text, int y, int x)
	{
		this.gc = gc;
		this.theScene = theScene;
		this.text = text;
		this.x = x;
		this.y = y;
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * SlideTitle method. will slide the title from its current location to another x location that is the maxX. Once it
	 * is at that location (or past) it will shake the title for aesthetics
	 * -----------------------------------------------------------------------------------
	 */
	public void slideTitle(int maxX)
	{
		if (x >= maxX)//if the title has passed the maxX
		{
			Font theFont = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 48);
			gc.setFont(theFont);
			gc.fillText(text, x, y);
			shakeTitle(maxX);
		} else
		{
			if (x >= ((maxX) - 90)&&x<(maxX-70))
			{
				//if the title is within 90 units of its max, it will initiate the "thud" sound
				//in anticipation of its stopping and shaking
				Music.playBang();
			}
			gc.setFill(Color.WHITE);
			Font theFont = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 48);
			gc.setFont(theFont);
			gc.fillText(text, x, y);
			x += 5; //moving the title by this amount
		}

	}

	/*
	 * -----------------------------------------------------------------------------------
	 * ShakeTitle method. will get the maxX, and shake (m) units, (shakeMax) times
	 * -----------------------------------------------------------------------------------
	 */
	public void shakeTitle(int maxX)
	{
		if (shakeMax <= 3) //if the animation hasn't been run through three times
		{
			if (x >= maxX && x < maxX + 10)//inbetween the max and the max + 10
			{
				x += 5; //move the title + 5x
			} else if (x >= (maxX) + 10) //greater than the max + 10
			{
				x -= 5;//move the title -5x
			}
			shakeMax++; // once the animation has been done, count + 1 
		} else //if the animation has been run more than 3 times, it will set the title to maxX therefore it will not move.
		{
			x = (maxX);
		}

	}
}
