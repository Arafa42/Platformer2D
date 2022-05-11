package Game.Entities;

import Game.Components.*;
import Game.Drawable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class AbstractEnemy implements Drawable {

    private final PositionComponent positionComponent;
    private final MovementComponent movementComponent;
    private final CollisionComponent collisionComponent;
    private final HealthComponent healthComponent;
    private final ArrayList<BulletComponent> bulletsComponent;
    private String type;


    public AbstractEnemy(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,String type){
        this.positionComponent = new PositionComponent(x,y,hitboxWidth,hitboxHeight);
        this.movementComponent = new MovementComponent(playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving);
        this.healthComponent = new HealthComponent(healthValue);
        this.collisionComponent = new CollisionComponent(map);
        this.bulletsComponent = new ArrayList<BulletComponent>();
        this.type = type;
    }


    public BufferedImage GetSpriteSheet(String fileName) {
        BufferedImage img = null;
        InputStream is = AbstractLevel.class.getResourceAsStream(fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public PositionComponent getPositionComponent() {
        return positionComponent;
    }
    public MovementComponent getMovementComponent(){ return movementComponent;}
    public CollisionComponent getCollisionComponent(){return collisionComponent;}
    public ArrayList<BulletComponent> getBulletsComponent(){return bulletsComponent;}
    public void addBulletsComponent(BulletComponent bulletComponent){bulletsComponent.add(bulletComponent);}
    public HealthComponent getHealthComponent(){return healthComponent;}
    public String getType() {return type;}
}
