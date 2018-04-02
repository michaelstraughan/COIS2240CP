import javafx.scene.canvas.GraphicsContext;

public class Bullet extends Player
{
	private double height = 5, width = 5;
	private boolean fired = false;
	private GraphicsContext graphicsContext;

	public Bullet(double x, double y, GraphicsContext graphicsContext)
	{
		super(x, y, graphicsContext);
		setHeight(height);
		setWidth(width);
		fired = true;
	}

	public void moveUp(int HEIGHT)
	{
		if (getY() > (HEIGHT-HEIGHT))
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
	public void reset(double x, double y)
	{
		setX(x);
		setY(y);
	 fired=true;
	}
}
