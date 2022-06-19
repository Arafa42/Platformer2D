package Game;

import Game.Components.BulletComponent;
import Game.Components.HealthComponent;
import Game.Components.InputComponent;
import Game.Components.ScoreComponent;
import Game.Entities.*;

public abstract class AbstractFactory {
    public abstract AbstractInput createInput(InputComponent inputComponent);
    public abstract AbstractHealthBar createHealthBar(HealthComponent healthComponent);
    public abstract AbstractBullet createBullet(BulletComponent bulletsComponent);
    public abstract AbstractPlayer createPlayer(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius);
    public abstract AbstractEnemy createEnemy(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,String type);
    public abstract void setGameDimensions(int GameCellsX, int GameCellsY);
    public abstract AbstractBackground createBackground(String bgLayer1,String bgLayer2);
    public abstract AbstractScore createScoreBar(ScoreComponent scoreComponent);
    public abstract AbstractLevel createLevel(int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE);
    public abstract AbstractMenu createMenu();
    public abstract void render();
}
