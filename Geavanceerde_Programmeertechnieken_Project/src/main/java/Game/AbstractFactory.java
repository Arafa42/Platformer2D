package Game;

import UI_1.GraphicsContext;

public abstract class AbstractFactory {
    public abstract AbstractInput createInput();
    public abstract AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight);
    public abstract void setGameDimensions(int GameCellsX, int GameCellsY);
    public abstract AbstractBackground createBackground();
    public abstract AbstractCamera createCamera();
    public abstract AbstractLevel createLevel( int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE);
    public abstract void render();
}
