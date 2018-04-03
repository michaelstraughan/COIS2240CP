import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends Player
{
	private double height = 5, width = 5;
	private boolean fired = false;
	public Bullet(double x, double y, GraphicsContext graphicsContext)
	{
		super(x, y, graphicsContext);
		setHeight(height);
		setWidth(width);
		fired = true;
		Image sprite   = new Image( "file:Bullet.png",20,20,false,false );
		setSprite(sprite);
	}

	public void moveUp(int HEIGHT)
	{
		if (getY() > (HEIGHT-HEIGHT)&&fired==true)
		{
			setY(getY() - 1);
			drawOval();
		} else
		{
			fired=false;
		}
	}

	public boolean checkFired()
	{
		return fired;
	}
	public void reshoot(double x, double y)
	{
		setX(x);
		setY(y);
	 fired=true;
	}
	public void reset()
	{
	 fired=false;
	}
}
