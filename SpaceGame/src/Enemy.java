import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Player
{

	public Enemy(double x, double y, GraphicsContext graphicsContext)
	{
		super(x, y, graphicsContext);
		Image sprite   = new Image( "file:AlienShip.png",50,50,false,false );
		setSprite(sprite);
	}
	

}
