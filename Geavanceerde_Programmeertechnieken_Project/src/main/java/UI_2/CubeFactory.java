package UI_2;

import Game.AbstractFactory;
import Game.Components.BulletComponent;
import Game.Components.HealthComponent;
import Game.Components.InputComponent;
import Game.Components.ScoreComponent;
import Game.Entities.*;
import Helper.ConfigFileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 *CubeFactory class extends AbstractFactory.
 * @author Arafa Yoncalik
 */
public class CubeFactory extends AbstractFactory {

    private final GraphicsContext grCtx;

    /**
     *CubeFactory constructor, takes config file as a parameter.
     * @param graphics_config
     * @throws FileNotFoundException
     */
    public CubeFactory(String graphics_config) throws FileNotFoundException {
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().processConfigFile(graphics_config);
        this.grCtx = new GraphicsContext((int)(data.get("ScreenWidth")), (int)(data.get("ScreenHeight")));
    }

    /**
     * Creates the input.
     * @param inputComponent
     * @return returns new CubeInput class instance.
     */
    @Override
    public AbstractInput createInput(InputComponent inputComponent) {return new CubeInput(grCtx.getFrame(),inputComponent);}

    /**
     * Creates the healthbar.
     * @param healthComponent
     * @param scale
     * @return retursn new CubeHealthBar class instance.
     */
    @Override
    public AbstractHealthBar createHealthBar(HealthComponent healthComponent,double scale) {return new CubeHealthBar(grCtx,healthComponent,scale);}

    /**
     * Creates the bullets.
     * @param bulletComponent
     * @param scale
     * @return returns new CubeBullet class instance.
     */
    @Override
    public AbstractBullet createBullet(BulletComponent bulletComponent,double scale) {return new CubeBullet(grCtx,bulletComponent,scale);}

    /**
     * Calls GraphicsContexts render() function.
     */
    @Override
    public void render() {this.grCtx.render();}

    /**
     * Creates the player and takes the necessary parameters the player needs.
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
     * @return returns new CubePlayer class instance.
     */
    @Override
    public AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius,double scale) {
        return new CubePlayer(grCtx, x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,score,bulletAngle,bulletSpeed,screenWidth,screenHeight,bulletRadius,scale);
    }

    /**
     * Creates the enemy.
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
     * @return returns new CubeEnemy class instance.
     */
    @Override
    public AbstractEnemy createEnemy(int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving, int healthValue,int[][] map,String type,double scale,int areaHitboxWidth,int areaHitboxHeight) {
        return new CubeEnemy(grCtx,x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,type,scale,areaHitboxWidth,areaHitboxHeight);
    }

    /**
     * Creates the background.
     * @param layer1
     * @param layer2
     * @return returns new CubeBackground class instance.
     */
    @Override
    public AbstractBackground createBackground(String layer1,String layer2) {return new CubeBackground(grCtx,layer1,layer2);}

    /**
     * Creates the scorebar.
     * @param scoreComponent
     * @param scale
     * @return returns new CubeScore class instance.
     */
    @Override
    public AbstractScore createScoreBar(ScoreComponent scoreComponent,double scale) {return new CubeScore(grCtx,scoreComponent,scale);}

    /**
     * Creates the Level.
     * @param tileArray
     * @param TILES_IN_HEIGHT
     * @param TILES_IN_WIDTH
     * @param TILES_SIZE
     * @return returns new CubeLevel class instance.
     */
    @Override
    public AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE) {return new CubeLevel(grCtx,tileArray, TILES_IN_HEIGHT, TILES_IN_WIDTH, TILES_SIZE);}

    /**
     * Creates the Menu.
     * @param scale
     * @return returns new CubeMenu class instance.
     */
    @Override
    public AbstractMenu createMenu(double scale) {return new CubeMenu(this.grCtx,scale);}

    /**
     * X-Scale getter.
     * @return returns the X-Scale value
     */
    @Override
    public double getScaleX() {return this.grCtx.getScaleX();}

    /**
     *Y-Scale getter.
     * @return Returns the Y-Scale value
     */
    @Override
    public double getScaleY() {return this.grCtx.getScaleY();}

    /**
     *Game dimensions setter.
     * @param GameCellsX
     * @param GameCellsY
     */
    @Override
    public void setGameDimensions(int GameCellsX, int GameCellsY) {this.grCtx.setGameDimensions(GameCellsX, GameCellsY);}
}
