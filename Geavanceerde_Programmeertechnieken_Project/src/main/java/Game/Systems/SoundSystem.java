package Game.Systems;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;



    public enum SoundSystem {
        LEVEL1("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\TV-Blonde-Ocarina.wav"),
        LEVEL2("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\fusion42.wav"),
        LEVEL3("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\Sheikh.wav"),
        ENEMYISHIT("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\enemyHit.wav"),
        PLAYERBULLET("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\playerShooting.wav"),
        JUMP("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\jump.wav"),
        POWERUP("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\powerup.wav"),
        COINSCOLLECTED("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\coinCollected.wav"),
        PLAYERDEAD("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\playerDead.wav"),
        LEVELPASSED("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\LevelComplete.wav"),
        PLAYERISHIT("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\playerHurt.wav"),
        GAMEOVER("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\GameOver.wav"),
        ENEMYBULLET("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\enemyShooting.wav"),
        MENUMUSIC("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\menuMusic.wav"),
        BUTTONCLICK("C:\\Users\\arafa\\OneDrive\\Documenten\\GitHub\\Platformer2D\\Geavanceerde_Programmeertechnieken_Project\\src\\main\\resources\\assets\\audio\\GameAudio\\buttonClick.wav");



        // Nested class for specifying volume
        public static enum Volume {
            MUTE, LOW, MEDIUM, HIGH
        }

        public static Volume volume = Volume.HIGH;

        // Each sound effect has its own clip, loaded with its own sound file.
        private Clip clip;

        // Constructor to construct each element of the enum with its own sound file.
        SoundSystem(String soundFileName) {
            try {
                // Use URL (instead of File) to read from disk and JAR.
                //URL url = this.getClass().getResource(soundFileName);
                File file = new File(soundFileName);
                // Set up an audio input stream piped from the sound file.
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                // Get a clip resource.
                clip = AudioSystem.getClip();
                // Open audio clip and load samples from the audio input stream.
                clip.open(audioInputStream);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        // Play or Re-play the sound effect from the beginning, by rewinding.
        public void play(Boolean loop) {
            if (volume != Volume.MUTE) {
                if (clip.isRunning())
                    clip.stop();   // Stop the player if it is still running
                clip.setFramePosition(0); // rewind to the beginning
                //VOLUME
                if(volume == Volume.LOW) {
                    FloatControl gainControl =
                            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-15.0f);
                }
                if(volume == Volume.MEDIUM) {
                    FloatControl gainControl =
                            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-10.0f);
                }
                clip.start();     // Start playing
                if (loop)//Loop if loop parameter is true
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        }

        public void stop() //stop playing and rewind to be played again from the beginning
        {
            clip.stop();
            clip.setFramePosition(0);
        }

        public void stopAllPlayingSounds(){
            SoundSystem.LEVEL1.stop();
            SoundSystem.LEVEL2.stop();
            SoundSystem.LEVEL3.stop();
            SoundSystem.COINSCOLLECTED.stop();
            SoundSystem.ENEMYISHIT.stop();
            SoundSystem.PLAYERBULLET.stop();
            SoundSystem.JUMP.stop();
            SoundSystem.PLAYERDEAD.stop();
            SoundSystem.LEVELPASSED.stop();
            SoundSystem.POWERUP.stop();
            SoundSystem.PLAYERISHIT.stop();
            SoundSystem.ENEMYBULLET.stop();
            SoundSystem.GAMEOVER.stop();
            SoundSystem.MENUMUSIC.stop();
            SoundSystem.BUTTONCLICK.stop();
        }


        public void mute() //don't play sounds(Mute Sound is selected from Options menu)
        {
            volume = Volume.MUTE;
        }

    }

