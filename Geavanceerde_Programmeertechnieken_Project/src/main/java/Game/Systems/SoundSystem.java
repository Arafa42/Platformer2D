package Game.Systems;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public enum SoundSystem {
        LEVEL1("\\src\\main\\resources\\assets\\audio\\GameAudio\\TV-Blonde-Ocarina.wav"),
        LEVEL2("\\src\\main\\resources\\assets\\audio\\GameAudio\\fusion42.wav"),
        LEVEL3("\\src\\main\\resources\\assets\\audio\\GameAudio\\Sheikh.wav"),
        ENEMYISHIT("\\src\\main\\resources\\assets\\audio\\GameAudio\\enemyHit.wav"),
        PLAYERBULLET("\\src\\main\\resources\\assets\\audio\\GameAudio\\playerShooting.wav"),
        JUMP("\\src\\main\\resources\\assets\\audio\\GameAudio\\jump.wav"),
        POWERUP("\\src\\main\\resources\\assets\\audio\\GameAudio\\powerup.wav"),
        COINSCOLLECTED("\\src\\main\\resources\\assets\\audio\\GameAudio\\coinCollected.wav"),
        PLAYERDEAD("\\src\\main\\resources\\assets\\audio\\GameAudio\\playerDead.wav"),
        LEVELPASSED("\\src\\main\\resources\\assets\\audio\\GameAudio\\LevelComplete.wav"),
        PLAYERISHIT("\\src\\main\\resources\\assets\\audio\\GameAudio\\playerHurt.wav"),
        GAMEOVER("\\src\\main\\resources\\assets\\audio\\GameAudio\\GameOver.wav"),
        ENEMYBULLET("\\src\\main\\resources\\assets\\audio\\GameAudio\\enemyShooting.wav"),
        MENUMUSIC("\\src\\main\\resources\\assets\\audio\\GameAudio\\menuMusic.wav"),
        BUTTONCLICK("\\src\\main\\resources\\assets\\audio\\GameAudio\\buttonClick.wav");


        public static enum Volume {MUTE, LOW, MEDIUM, HIGH}
        public static Volume volume = Volume.HIGH;
        private Clip clip;

        SoundSystem(String soundFileName) {
            try {
                Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
                File file = new File(path + soundFileName);
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(ais);

            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }

        public void play(Boolean loop) {
            if (volume != Volume.MUTE) {
                if (clip.isRunning())
                    clip.stop();
                clip.setFramePosition(0);
                if(volume == Volume.LOW) {
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-15.0f);
                }
                if(volume == Volume.MEDIUM) {
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-10.0f);
                }
                clip.start();
                if (loop){clip.loop(Clip.LOOP_CONTINUOUSLY);}
            }

        }

        public void stop() {clip.stop();clip.setFramePosition(0);}

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

        public void mute() {volume = Volume.MUTE;}
    }