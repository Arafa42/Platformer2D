package Game.Systems;

import Game.Entities.AbstractPlayer;
import java.util.concurrent.TimeUnit;

/**
 *LevelSystem class.
 * @author Arafa Yoncalik
 */
public class LevelSystem {

    AbstractPlayer abstractPlayer;
    private int TILES_SIZE;
    private double scale;

    /**
     *LevelSystem constructor.
     * @param abstractPlayer
     * @param TILES_SIZE
     * @param scale
     */
    public LevelSystem(AbstractPlayer abstractPlayer,int TILES_SIZE,double scale){
        this.abstractPlayer = abstractPlayer;
        this.TILES_SIZE = TILES_SIZE;
        this.scale = scale;
    }

    /**
     *update function updates the level to load on a level finish.
     */
    public void update(){
        int row = (int) (abstractPlayer.getPositionComponent().y / TILES_SIZE);
        int col1 = (int) ((abstractPlayer.getPositionComponent().x + (int)(30*scale)) / TILES_SIZE);
        int col2 = (int) ((abstractPlayer.getPositionComponent().x) / TILES_SIZE);
        if (abstractPlayer.getCollisionComponent().getLevelData()[row][col1] == 3 || abstractPlayer.getCollisionComponent().getLevelData()[row][col2] == 3) {
            if(abstractPlayer.getLevelComponent().getLevelToLoad() <= 2) {
                SoundSystem.volume = SoundSystem.Volume.HIGH;
                SoundSystem.LEVELPASSED.stopAllPlayingSounds();
                SoundSystem.LEVELPASSED.play(false);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                abstractPlayer.getLevelComponent().setLevelToLoad(abstractPlayer.getLevelComponent().getLevelToLoad() + 1);
                abstractPlayer.getLevelComponent().setSwitchLevel(true);
            }
        }
    }
}
