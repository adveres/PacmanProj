package PacmanProj;

import sun.audio.*;
import java.io.*;

/**
 * Class to play audio files for Pacman
 * 
 * @author atv5011
 */
class Audio {

    private InputStream in;
    private AudioStream as;

    private static final String SOUND_INTRO_SONG = "AudioFolder\\intro.wav";
    private static final String SOUND_MUNCH = "AudioFolder\\munch A.wav";
    private static final String SOUND_DEATH = "AudioFolder\\death 1.wav";

    /**
     * Plays Pacman's intro music
     */
    public void playIntroMusic() {
        try {
            in = new FileInputStream(SOUND_INTRO_SONG);
            as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Plays the munching sound when called
     */
    public void playMunch() {
        try {
            in = new FileInputStream(SOUND_MUNCH);
            as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Plays the sound of Pacman's demise when called
     */
    public void playDeath() {
        try {
            in = new FileInputStream(SOUND_DEATH);
            as = new AudioStream(in);
            AudioPlayer.player.start(as);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Stops playing whatever current audio is playing
     */
    public void stopMusic() {
        AudioPlayer.player.stop(as);
    }

}
