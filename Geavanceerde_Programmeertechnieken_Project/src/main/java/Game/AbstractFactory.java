package Game;

import Game.Components.BulletComponent;
import Game.Components.HealthComponent;
import Game.Components.InputComponent;
import Game.Components.ScoreComponent;
import Game.Entities.*;

/**
 * AbstractFactory class, contains abstract functions to create entities.
 * @author Arafa Yoncalik
 */
public abstract class AbstractFactory {
    /**
     *createInput() function.
     * @param inputComponent
     * @return returns an AbstractInput
     */
    public abstract AbstractInput createInput(InputComponent inputComponent);

    /**
     *createHealthBar() function
     * @param healthComponent
     * @param scale
     * @return returns an AbstractHealthBar
     */
    public abstract AbstractHealthBar createHealthBar(HealthComponent healthComponent,double scale);

    /**
     *createBullet() function
     * @param bulletsComponent
     * @param scale
     * @return returns an AbstractBullet
     */
    public abstract AbstractBullet createBullet(BulletComponent bulletsComponent,double scale);

    /**
     *createPlayer() function
     * @param x
     * @param y
     * @param hitboxWidth
     * @param hitboxHeight
     * @param playerSpeed
     * @param inAir
     * @param airSpeed
     * @param gravity
     * @param jumpSpeed
     * @param fallSpeedAfterCollision
     * @param isMoving
     * @param healthValue
     * @param map
     * @param score
     * @param bulletAngle
     * @param bulletSpeed
     * @param screenWidth
     * @param screenHeight
     * @param bulletRadius
     * @param scale
     * @return returns an AbstractPlayer
     */
    public abstract AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius,double scale);

    /**
     *createEnemy() function
     * @param x
     * @param y
     * @param hitboxWidth
     * @param hitboxHeight
     * @param playerSpeed
     * @param inAir
     * @param airSpeed
     * @param gravity
     * @param jumpSpeed
     * @param fallSpeedAfterCollision
     * @param isMoving
     * @param healthValue
     * @param map
     * @param type
     * @param scale
     * @param areaHitboxWidth
     * @param areaHitboxHeight
     * @return returns an AbstractEnemy
     */
    public abstract AbstractEnemy createEnemy(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,String type,double scale,int areaHitboxWidth,int areaHitboxHeight);

    /**
     * setGameDimensions() function
     * @param GameCellsX
     * @param GameCellsY
     */
    public abstract void setGameDimensions(int GameCellsX, int GameCellsY);

    /**
     *createBackground() function
     * @param bgLayer1
     * @param bgLayer2
     * @return returns an AbstractBackground
     */
    public abstract AbstractBackground createBackground(String bgLayer1,String bgLayer2);

    /**
     *createScorebar() function
     * @param scoreComponent
     * @param scale
     * @return returns an AbstractScore
     */
    public abstract AbstractScore createScoreBar(ScoreComponent scoreComponent,double scale);

    /**
     *createLevel() function
     * @param tileArray
     * @param TILES_IN_HEIGHT
     * @param TILES_IN_WIDTH
     * @param TILES_SIZE
     * @return returns an AbstractLevel
     */
    public abstract AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE);

    /**
     *createMenu() function
     * @param scale
     * @return returns an AbstractMenu
     */
    public abstract AbstractMenu createMenu(double scale);

    public abstract double getScaleX();
    public abstract double getScaleY();
    public abstract void render();

}
