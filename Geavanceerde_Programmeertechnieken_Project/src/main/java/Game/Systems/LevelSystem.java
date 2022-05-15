package Game.Systems;

import Game.Entities.AbstractPlayer;
import Game.Game;

import java.util.concurrent.TimeUnit;

public class LevelSystem {

    AbstractPlayer abstractPlayer;

    public LevelSystem(AbstractPlayer abstractPlayer){
        this.abstractPlayer = abstractPlayer;
    }

    public void update(){
        int row = (int) (abstractPlayer.getPositionComponent().y / Game.TILES_SIZE);
        int col1 = (int) ((abstractPlayer.getPositionComponent().x + 30) / Game.TILES_SIZE);
        int col2 = (int) ((abstractPlayer.getPositionComponent().x) / Game.TILES_SIZE);
        if (abstractPlayer.getCollisionComponent().getLevelData()[row][col1] == 3 || abstractPlayer.getCollisionComponent().getLevelData()[row][col2] == 3) {
            if(abstractPlayer.getLevelComponent().getLevelToLoad() <= 2) {
                SoundSystem.volume = SoundSystem.Volume.HIGH;
                SoundSystem.LEVELPASSED.stopAllPlayingSounds();
                SoundSystem.LEVELPASSED.play(false);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                abstractPlayer.getLevelComponent().setLevelToLoad(abstractPlayer.getLevelComponent().getLevelToLoad() + 1);
                abstractPlayer.getLevelComponent().setSwitchLevel(true);
            }
            else{
                //System.out.println("FINAL LEVEL REACHED");
            }
        }
    }
}
