
public class Location
{
	private double x, y, height, width;

	public Location(double x, double y, double height, double width)
	{
		setX(x);
		setY(y);
		setHeight(height);
		setWidth(width);
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public void setX(double x)
	{
		this.x = x;
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

	public Location getTopLeftCorner()
	{
		Location topLeftCorner = new Location(getX(), getY(), 1, 1);
		return topLeftCorner;
	}

	public Location getTopRightCorner()
	{
		Location topRightCorner = new Location(getX() + getWidth(), getY(), 1, 1);
		return topRightCorner;
	}

	public Location getBottomLeftCorner()
	{
		Location bottomLeftCorner = new Location(getX(), getY() + getHeight(), 1, 1);
		return bottomLeftCorner;
	}

	public Location getBottomRightCorner()
	{
		Location bottomRightCorner = new Location(getX() + getWidth(), getY() + getHeight(), 1, 1);
		return bottomRightCorner;
	}

	public boolean checkLocation(Location location)
	{
		boolean share;
		if (checkPoint(location.getTopLeftCorner()) == true)
		{
			share=true;
		}
		else if (checkPoint(location.getTopRightCorner()) == true)
		{
			share=true;
		}
		else if (checkPoint(location.getBottomLeftCorner()) == true)
		{
			share=true;
		}
		else if (checkPoint(location.getBottomRightCorner()) == true)
		{
			share=true;
		}
		else
		{
			share=false;
		}
		return share;
	}

	public boolean checkPoint(Location point)
	{
		if (point.getX() < getX() + getWidth() && point.getX() > getX() && point.getY() > getY()
				&& point.getY() < getY() + getHeight())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
