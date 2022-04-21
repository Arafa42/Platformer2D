package Game;

import UI_1.GraphicsContext;

public abstract class AbstractFactory {
    public abstract AbstractInput createInput();
    public abstract AbstractHealthBar createHealthBar();
    public abstract AbstractBullet createBullet(double angle, int x, int y,int screenWidth, int screenHeight);
    public abstract AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight, int healthValue);
    public abstract void setGameDimensions(int GameCellsX, int GameCellsY);
    public abstract AbstractBackground createBackground();
    public abstract AbstractTopBar createTopBar(int score);
    public abstract AbstractLevel createLevel( int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE);
    public abstract void render();
}
