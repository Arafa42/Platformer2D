package Game.Systems;

import Game.Components.CollisionComponent;
import Game.Components.PositionComponent;
import Game.Components.ScoreComponent;

/**
 *CoinSystem.
 * @author Arafa Yoncalik
 */
public class CoinSystem {

    private final CollisionComponent collisionComponent;
    private final ScoreComponent scoreComponent;
    private final PositionComponent positionComponent;
    private int TILES_SIZE;
    private double scale;

    /**
     *CoinSystem constructor, takes the necessary components as parameters.
     * @param collisionComponent
     * @param scoreComponent
     * @param positionComponent
     * @param TILE_SIZE
     * @param scale
     */
    public CoinSystem(CollisionComponent collisionComponent, ScoreComponent scoreComponent,PositionComponent positionComponent,int TILE_SIZE,double scale){
        this.collisionComponent = collisionComponent;
        this.scoreComponent  = scoreComponent;
        this.positionComponent = positionComponent;
        this.scale = scale;
        this.TILES_SIZE = TILE_SIZE;
    }

    /**
     *update() function calls the coinCollisionCheck() function.
     */
    public void update(){
        coinCollisionCheck((int)positionComponent.x,(int)positionComponent.y);
    }

    /**
     *checks for collision with a coin.
     * @param x
     * @param y
     */
    public void coinCollisionCheck(int x, int y) {
        if (scoreComponent != null) {
            int row = y / TILES_SIZE;
            int col1 = (x + (int)(30*scale)) / TILES_SIZE;
            int col2 = (x) / TILES_SIZE;
                if (collisionComponent.getLevelData()[row][col1] == -2) {
                    SoundSystem.volume = SoundSystem.Volume.HIGH;
                    SoundSystem.COINSCOLLECTED.play(false);
                    scoreComponent.setScore(scoreComponent.getScore() + 1);
                    collisionComponent.getLevelData()[row][col1] = 0;
                }
                if (collisionComponent.getLevelData()[row][col2] == -2) {
                    SoundSystem.volume = SoundSystem.Volume.HIGH;
                    SoundSystem.COINSCOLLECTED.play(false);
                    scoreComponent.setScore(scoreComponent.getScore() + 1);
                    collisionComponent.getLevelData()[row][col2] = 0;
                }
        }
    }

}
