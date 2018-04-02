
import javafx.scene.canvas.GraphicsContext;

public class Player
{
	private double x, y;

	public Player(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public void drawOval(GraphicsContext graphicsContext)
	{
		graphicsContext.fillOval(getX(), getY(), 10, 10);
	}

	public void moveRight(GraphicsContext graphicsContext)
	{
		setX(getX() + 1);
		drawOval(graphicsContext);
	}

	public void moveLeft(GraphicsContext graphicsContext)
	{
		setX(getX() - 1);
		drawOval(graphicsContext);
	}

	public void shoot(GraphicsContext graphicsContext)
	{
		Bullet bullet = new Bullet(getX(),getY());
	}

}