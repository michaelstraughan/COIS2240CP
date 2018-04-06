import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScoreTracker {

	
	private String highScore = "";

	
	public void test()
	{
		if (highScore.equals(""))
		{
			//initiate
			highScore = ScoreTracker.GetHighScore();
		}
	}
	
	public static String GetHighScore() {
	
		FileReader readFile = null;
		BufferedReader reader = null;
		
		try
		{
		readFile = new FileReader("highscore.dat");
		reader = new BufferedReader(readFile);
		return reader.readLine();
		
		}
		catch (Exception e)
		{
			return "0";
		}
		finally
		{
			try {
				if (reader != null)
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
}


