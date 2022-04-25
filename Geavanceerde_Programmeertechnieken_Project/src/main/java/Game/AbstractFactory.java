package Game;

import Game.Components.HealthComponent;
import Game.Entities.*;

public abstract class AbstractFactory {
    public abstract AbstractInput createInput();
    public abstract AbstractHealthBar createHealthBar(HealthComponent healthComponent);
    public abstract AbstractBullet createBullet(double angle, int x, int y, int screenWidth, int screenHeight);
    public abstract AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map);
    public abstract void setGameDimensions(int GameCellsX, int GameCellsY);
    public abstract AbstractBackground createBackground();
    public abstract AbstractScore createTopBar(int score);
    public abstract AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE);
    public abstract void render();
}
