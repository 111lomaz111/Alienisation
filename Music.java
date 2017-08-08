import java.awt.event.KeyEvent;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Music {
	
	private Clip musicBg;
	
	public void playMusic(KeyEvent e)
	{
		String filename = "Assety/Audio/music.wav";
		    try
		    {
		        musicBg = AudioSystem.getClip();
		        musicBg.open(AudioSystem.getAudioInputStream(new File(filename)));
		        musicBg.start();
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
	}		
	
	public void throwSound(String filename)
	{
	    try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	        clip.start();
	    }
	    catch (Exception exc)
	    {	    	
	        exc.printStackTrace(System.out);
	    }
	}
}
