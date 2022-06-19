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

public class CubeFactory extends AbstractFactory {

    private final GraphicsContext grCtx;

    public CubeFactory(String graphics_config) throws FileNotFoundException {
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().processConfigFile(graphics_config);
        this.grCtx = new GraphicsContext((int)(data.get("ScreenWidth")), (int)(data.get("ScreenHeight")));
    }

    @Override
    public AbstractInput createInput(InputComponent inputComponent) {
        return new CubeInput(grCtx.getFrame(),inputComponent);
    }

    @Override
    public AbstractHealthBar createHealthBar(HealthComponent healthComponent) {return new CubeHealthBar(grCtx,healthComponent);}


    @Override
    public AbstractBullet createBullet(BulletComponent bulletComponent) {return new CubeBullet(grCtx,bulletComponent);}

    @Override
    public void render() {
        this.grCtx.render();
    }

    @Override
    public AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius) {
        return new CubePlayer(grCtx, x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,score,bulletAngle,bulletSpeed,screenWidth,screenHeight,bulletRadius);
    }

    @Override
    public AbstractEnemy createEnemy(int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving, int healthValue,int[][] map,String type) {
        return new CubeEnemy(grCtx,x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,type);
    }

    @Override
    public AbstractBackground createBackground(String layer1,String layer2) {
        return new CubeBackground(grCtx,layer1,layer2);
    }

    @Override
    public AbstractScore createScoreBar(ScoreComponent scoreComponent) {
        return new CubeScore(grCtx,scoreComponent);
    }

    @Override
    public AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE) {
        return new CubeLevel(grCtx,tileArray, TILES_IN_HEIGHT, TILES_IN_WIDTH, TILES_SIZE);
    }

    @Override
    public AbstractMenu createMenu() {return new CubeMenu(grCtx);}

    @Override
    public void setGameDimensions(int GameCellsX, int GameCellsY) {this.grCtx.setGameDimensions(GameCellsX, GameCellsY);}
}
