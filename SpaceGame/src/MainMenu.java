
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/*
 * -----------------------------------------------------------------------------------
 * MainMenu class. used to create the background and title for the main menu.
 * -----------------------------------------------------------------------------------
 */
public class MainMenu
{
	StarrySky sky;
	GraphicsContext gc;
	Scene theScene;
	Title topTitle, bottomTitle;

	/*
	 * -----------------------------------------------------------------------------------
	 * MainMenu constructor, takes the GraphicsContext and the Scene
	 * -----------------------------------------------------------------------------------
	 */
	public MainMenu(GraphicsContext gc, Scene theScene)
	{
		//Music.playMenuMusic();
		this.gc = gc;
		this.theScene = theScene;
		sky = new StarrySky(gc, theScene);
		//topTitle = new Title(gc, theScene, "Space", 50, (int) (theScene.getWidth() - theScene.getWidth() * 2));
		//bottomTitle = new Title(gc, theScene, "Game", 100, (int) (theScene.getWidth() - theScene.getWidth() * 2));
	}

	/*
	 * -----------------------------------------------------------------------------------
	 * AnimateMenu Method, will do all menu animations for objects.
	 * -----------------------------------------------------------------------------------
	 */
	public void animateMenu()
	{
		sky.moveSky(0,1);
		//topTitle.slideTitle((int) (theScene.getWidth() * 0.2));
		//if (topTitle.x >= theScene.getWidth() * 0.2)
		//{
		//	bottomTitle.slideTitle((int) (theScene.getWidth() * 0.2 + 50));
		//}
	}
}
