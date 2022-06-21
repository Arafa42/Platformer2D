package UI_1;

import Game.*;
import Game.Components.BulletComponent;
import Game.Components.HealthComponent;
import Game.Components.InputComponent;
import Game.Components.ScoreComponent;
import Game.Entities.*;
import Helper.ConfigFileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 *j2dFactory extends from AbstractFactory.
 * @author Arafa Yoncalik
 */
public class j2dFactory extends AbstractFactory {

    private final GraphicsContext grCtx;

    /**
     *j2dFactory constructor.
     * @param graphics_config
     * @throws FileNotFoundException
     */
    public j2dFactory(String graphics_config) throws FileNotFoundException {
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().processConfigFile(graphics_config);
        this.grCtx = new GraphicsContext((int)(data.get("ScreenWidth")), (int)(data.get("ScreenHeight")));
    }

    /**
     *createInput function.
     * @param inputComponent
     * @return returns a new j2dInput class instance
     */
    @Override
    public AbstractInput createInput(InputComponent inputComponent) {return new j2dInput(grCtx.getFrame(),inputComponent);}

    /**
     *createHealthBar function.
     * @param healthComponent
     * @param scale
     * @return returns a new j2dHealthbar class instance
     */
    @Override
    public AbstractHealthBar createHealthBar(HealthComponent healthComponent,double scale) {return new j2dHealthBar(grCtx,healthComponent,scale);}

    /**
     *createBullet function.
     * @param bulletComponent
     * @param scale
     * @return returns a new j2dBullet class instance
     */
    @Override
    public AbstractBullet createBullet(BulletComponent bulletComponent,double scale) {return new j2dBullet(grCtx,bulletComponent,scale);}

    /**
     *render function calls the render function of the graphicsContext.
     */
    @Override
    public void render() {this.grCtx.render();}

    /**
     *createPlayer function.
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
     * @return returns a new j2dPlayer class instance
     */
    @Override
    public AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius,double scale) {
        return new j2dPlayer(grCtx, x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,score,bulletAngle,bulletSpeed,screenWidth,screenHeight,bulletRadius,scale);
    }

    /**
     *createEnemy function
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
     * @return returns a new j2dEnemy class instance
     */
    @Override
    public AbstractEnemy createEnemy(int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving, int healthValue,int[][] map,String type,double scale,int areaHitboxWidth,int areaHitboxHeight) {
        return new j2dEnemy(grCtx,x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,type,scale,areaHitboxWidth,areaHitboxHeight);
    }

    /**
     *createBackground function.
     * @param layer1
     * @param layer2
     * @return returns a new j2dBackground class instance
     */
    @Override
    public AbstractBackground createBackground(String layer1,String layer2) {return new j2dBackground(grCtx,layer1,layer2);}

    /**
     * createScoreBar function.
     * @param scoreComponent
     * @param scale
     * @return returns a new j2dScore class instance
     */
    @Override
    public AbstractScore createScoreBar(ScoreComponent scoreComponent,double scale) {return new j2dScore(grCtx,scoreComponent,scale);}

    /**
     *createLevel function.
     * @param tileArray
     * @param TILES_IN_HEIGHT
     * @param TILES_IN_WIDTH
     * @param TILES_SIZE
     * @return returns a new j2dLevel class instance
     */
    @Override
    public AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE) {
        return new j2dLevel(grCtx,tileArray, TILES_IN_HEIGHT, TILES_IN_WIDTH, TILES_SIZE);
    }

    /**
     *createMenu function.
     * @param scale
     * @return returns a new j2dMenu class instance
     */
    @Override
    public AbstractMenu createMenu(double scale) {return new j2dMenu(grCtx,scale);}

    /**
     *getScaleX getter.
     * @return returns getScaleX value from graphicsContext.
     */
    @Override
    public double getScaleX() {return this.grCtx.getScaleX();}

    /**
     *getScaleY getter
     * @return returns getScaleY value from graphicsContext.
     */
    @Override
    public double getScaleY() {return this.grCtx.getScaleY();}

    /**
     *setGameDimensions() function.
     * @param GameCellsX
     * @param GameCellsY
     */
    @Override
    public void setGameDimensions(int GameCellsX, int GameCellsY) {this.grCtx.setGameDimensions(GameCellsX, GameCellsY);}
}
