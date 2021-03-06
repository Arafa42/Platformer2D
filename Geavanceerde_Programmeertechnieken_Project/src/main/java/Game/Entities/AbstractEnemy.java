package Game.Entities;

import Game.Components.*;
import Game.Drawable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *AbstractEnemy class implements the Drawable Interface.
 * @author Arafa Yoncalik
 */
public abstract class AbstractEnemy implements Drawable {

    private final PositionComponent positionComponent;
    private final MovementComponent movementComponent;
    private final CollisionComponent collisionComponent;
    private final HealthComponent healthComponent;
    private final ArrayList<BulletComponent> bulletsComponent;
    private final EnemyComponent enemyComponent;
    private final String type;

    /**
     * AbstractEnemy constructor. Creates instances of components for the enemies.
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
     * @param areaHitboxWidth
     * @param areaHitboxHeight
     */
    public AbstractEnemy(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,String type,int areaHitboxWidth,int areaHitboxHeight){
        this.positionComponent = new PositionComponent(x,y,hitboxWidth,hitboxHeight);
        this.movementComponent = new MovementComponent(playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving);
        this.healthComponent = new HealthComponent(healthValue);
        this.collisionComponent = new CollisionComponent(map);
        this.bulletsComponent = new ArrayList<BulletComponent>();
        this.enemyComponent = new EnemyComponent(true,areaHitboxWidth,areaHitboxHeight);
        this.type = type;
    }

    /**
     *GetSpriteSheet function.
     * @param fileName
     * @return returns the BufferedImage of a filename;
     */
    public BufferedImage GetSpriteSheet(String fileName) {
        BufferedImage img = null;
        InputStream is = AbstractLevel.class.getResourceAsStream(fileName);
        try {
            assert is != null;
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public PositionComponent getPositionComponent() {return positionComponent;}
    public MovementComponent getMovementComponent(){ return movementComponent;}
    public CollisionComponent getCollisionComponent(){return collisionComponent;}
    public ArrayList<BulletComponent> getBulletsComponent(){return bulletsComponent;}
    public void addBulletsComponent(BulletComponent bulletComponent){bulletsComponent.add(bulletComponent);}
    public HealthComponent getHealthComponent(){return healthComponent;}
    public EnemyComponent getEnemyComponent() {return enemyComponent;}
    public String getType() {return type;}
}
