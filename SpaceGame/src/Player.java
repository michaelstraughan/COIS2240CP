
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player
{
	private double x, y, height = 10, width = 10;
	private GraphicsContext graphicsContext;
	long start = System.nanoTime(), finish;
	private Image sprite   = new Image( "file:SpaceShip.png",50,50,false,false );

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
	public Image getSprite()
	{
		return sprite;
	}	
	public void setSprite(Image sprite)
	{
		this.sprite=sprite;
	}	


	public void drawOval()
	{
		graphicsContext.drawImage(getSprite(),getX(), getY());
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

	public void shoot(List<Bullet> bullets)
	{
		finish = System.nanoTime();
		if ((finish - start) / 10000000 >= 50)
		{
			for (int bulletNum = 0; bulletNum < bullets.size(); bulletNum++)
			{
				if (bullets.get(bulletNum).checkFired() == false)
				{
					bullets.get(bulletNum).reshoot(x, y);
					start = System.nanoTime();
					return;
				}
				else
				{
				}
			}

			Bullet bullet = new Bullet(x, y, graphicsContext);
			bullets.add(bullet);
			start = System.nanoTime();
		}
	}

	public void hit(List<Bullet> bullets)
	{
		for (int count = 0; count < bullets.size(); count++)
		{
			if (bullets.get(count).getX() < x+(getWidth()/1.3)&&bullets.get(count).getX() > x-(getWidth()/1.3) && bullets.get(count).getY() == y)
			{
				setY(5);
				bullets.get(count).reset();
				//graphicsContext.setFill(Color.WHITE);
				//graphicsContext.fillOval(getX(), getY(), getHeight(), getWidth());
				//graphicsContext.setFill(Color.BLACK);
			}
		}
	}

}