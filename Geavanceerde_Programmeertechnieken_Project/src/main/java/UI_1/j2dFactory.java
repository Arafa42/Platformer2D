package UI_1;

import Game.*;
import Game.Components.HealthComponent;
import Game.Components.ScoreComponent;
import Game.Entities.*;
import Helper.ConfigFileReader;
import java.util.HashMap;

public class j2dFactory extends AbstractFactory {

    private final GraphicsContext grCtx;

    public j2dFactory(String graphics_config) {
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().loadOrCreateConfig(graphics_config);
        this.grCtx = new GraphicsContext((int)(data.get("ScreenWidth")), (int)(data.get("ScreenHeight")));
    }

    @Override
    public AbstractInput createInput() {
        return new j2dInput(grCtx.getFrame());
    }

    @Override
    public AbstractHealthBar createHealthBar(HealthComponent healthComponent) {return new j2dHealthBar(grCtx,healthComponent);}


    @Override
    public AbstractBullet createBullet(double angle, int x, int y, int screenWidth, int screenHeight) {return new j2dBullet(grCtx,angle,x,y,screenWidth,screenHeight);}

    @Override
    public void render() {
        this.grCtx.render();
    }

    @Override
    public AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score) {
        return new j2dPlayer(grCtx, x, y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,score);
    }

    @Override
    public AbstractBackground createBackground() {
        return new j2dBackground(grCtx);
    }

    @Override
    public AbstractScore createScoreBar(ScoreComponent scoreComponent) {
        return new j2dScore(grCtx,scoreComponent);
    }

    @Override
    public AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE) {
        return new j2dLevel(grCtx,tileArray, TILES_IN_HEIGHT, TILES_IN_WIDTH, TILES_SIZE);
    }

    @Override
    public void setGameDimensions(int GameCellsX, int GameCellsY) {this.grCtx.setGameDimensions(GameCellsX, GameCellsY);}
}
