package Game.Systems;

import Game.Entities.AbstractPlayer;
import Game.Game;

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
            abstractPlayer.getLevelComponent().setLevelToLoad(abstractPlayer.getLevelComponent().getLevelToLoad()+1);
            abstractPlayer.getLevelComponent().setSwitchLevel(true);
        }
    }
}
