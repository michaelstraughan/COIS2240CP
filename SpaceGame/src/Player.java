
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class Player
{
	private double x, y, height = 10, width = 10;
	private GraphicsContext graphicsContext;

	public Player(double x, double y, GraphicsContext graphicsContext)
	{
		this.x = x;
		this.y = y;
		this.graphicsContext = graphicsContext;
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

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public void drawOval()
	{
		graphicsContext.fillOval(getX(), getY(), getHeight(), getWidth());
	}

	public void moveRight()
	{
		setX(getX() + 1);
		drawOval();
	}

	public void moveLeft()
	{
		setX(getX() - 1);
		drawOval();
	}


}