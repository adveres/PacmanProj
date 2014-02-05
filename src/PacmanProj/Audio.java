package PacmanProj;


import  sun.audio.*;
import  java.io.*;


/**
 *
 * @author atv5011
 */
public class Audio {

      InputStream in;
      AudioStream as;

      /**
       * Plays Pacman's intro music
       */
    public void playIntroMusic()
      {
          try
          {
            in = new FileInputStream("AudioFolder\\intro.wav");
            as = new AudioStream(in);
            AudioPlayer.player.start(as);    
          }
          catch(IOException e)
          {
              System.err.println(e);
          }
      }
    /**
     * Plays the munching sound when called
     */
    public void playMunch()
    {
        try
        {
          in = new FileInputStream("AudioFolder\\munch A.wav");
          as = new AudioStream(in);
          AudioPlayer.player.start(as);    
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
    /**
     * Plays the sound of Pacman's demise when called
     */
    public void playDeath()
    {
        try
        {
          in = new FileInputStream("AudioFolder\\death 1.wav");
          as = new AudioStream(in);
          AudioPlayer.player.start(as);    
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
    /**
     * Stops playing whatever current audio is playing
     */
    public void stopMusic()
    {
        AudioPlayer.player.stop(as);
    }

                

       // AudioPlayer.player.stop(as); 

}
