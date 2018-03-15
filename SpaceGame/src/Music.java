import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
public class Music
{
	static public void playMenuMusic()
	{
		Media sound= new Media(new File("Redbone.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	static public void playBang()
	{
		Media sound= new Media(new File("Loud_Bang.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	static public void stopMenuMusic()
	{
	}
}
