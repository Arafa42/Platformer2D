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

public class j2dFactory extends AbstractFactory {

    private final GraphicsContext grCtx;

    public j2dFactory(String graphics_config) throws FileNotFoundException {
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().processConfigFile(graphics_config);
        this.grCtx = new GraphicsContext((int)(data.get("ScreenWidth")), (int)(data.get("ScreenHeight")));
    }
    @Override
    public AbstractInput createInput(InputComponent inputComponent) {return new j2dInput(grCtx.getFrame(),inputComponent);}
    @Override
    public AbstractHealthBar createHealthBar(HealthComponent healthComponent,double scale) {return new j2dHealthBar(grCtx,healthComponent,scale);}
    @Override
    public AbstractBullet createBullet(BulletComponent bulletComponent,double scale) {return new j2dBullet(grCtx,bulletComponent,scale);}
    @Override
    public void render() {this.grCtx.render();}
    @Override
    public AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius,double scale) {
        return new j2dPlayer(grCtx, x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,score,bulletAngle,bulletSpeed,screenWidth,screenHeight,bulletRadius,scale);
    }
    @Override
    public AbstractEnemy createEnemy(int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving, int healthValue,int[][] map,String type,double scale) {
        return new j2dEnemy(grCtx,x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,type,scale);
    }
    @Override
    public AbstractBackground createBackground(String layer1,String layer2) {return new j2dBackground(grCtx,layer1,layer2);}
    @Override
    public AbstractScore createScoreBar(ScoreComponent scoreComponent,double scale) {return new j2dScore(grCtx,scoreComponent,scale);}
    @Override
    public AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE) {
        return new j2dLevel(grCtx,tileArray, TILES_IN_HEIGHT, TILES_IN_WIDTH, TILES_SIZE);
    }
    @Override
    public AbstractMenu createMenu() {return new j2dMenu(grCtx);}
    @Override
    public double getScaleX() {return this.grCtx.getScaleX();}
    @Override
    public double getScaleY() {return this.grCtx.getScaleY();}
    @Override
    public void setGameDimensions(int GameCellsX, int GameCellsY) {this.grCtx.setGameDimensions(GameCellsX, GameCellsY);}
}
