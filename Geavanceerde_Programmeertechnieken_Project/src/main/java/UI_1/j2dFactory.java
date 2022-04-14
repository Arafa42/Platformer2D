package UI_1;

import Game.*;
import Helper.ConfigFileReader;
import java.util.HashMap;

public class j2dFactory extends AbstractFactory {

    private final GraphicsContext grCtx;

    public j2dFactory(String graphics_config) {
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().loadOrCreateConfig(graphics_config);
        this.grCtx = new GraphicsContext((int)(data.get("ScreenWidth")*Game.SCALE), (int)(data.get("ScreenHeight")*Game.SCALE));
    }

    @Override
    public AbstractInput createInput() {
        return new j2dInput(grCtx.getFrame());
    }

    @Override
    public void render() {
        this.grCtx.render();
    }

    @Override
    public AbstractPlayer createPlayer( int x, int y) {return new j2dPlayer(grCtx, x, y);}

    @Override
    public AbstractBackground createBackground() {
        return new j2dBackground(grCtx);
    }

    @Override
    public AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT,int TILES_IN_WIDTH, int TILES_SIZE) {
        return new j2dLevel(grCtx,tileArray, TILES_IN_HEIGHT, TILES_IN_WIDTH, TILES_SIZE);
    }

    @Override
    public void setGameDimensions(int GameCellsX, int GameCellsY) {this.grCtx.setGameDimensions(GameCellsX, GameCellsY);}
}
