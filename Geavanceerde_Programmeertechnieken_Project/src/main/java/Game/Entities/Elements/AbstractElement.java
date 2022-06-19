package Game.Entities.Elements;

import Game.Components.*;
import Game.Drawable;
import Game.Entities.AbstractInput;

import java.util.ArrayList;

public abstract class AbstractElement implements Drawable {

    private final PositionComponent positionComponent;
    private final MovementComponent movementComponent;
    private final CollisionComponent collisionComponent;
    private final HealthComponent healthComponent;
    private final ScoreComponent scoreComponent;
    private final LevelComponent levelComponent;
    private final ArrayList<BulletComponent> bulletsComponent;

    private AbstractInput.Inputs direction;

    public AbstractElement(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius){
        this.positionComponent = new PositionComponent(x,y,hitboxWidth,hitboxHeight);
        this.movementComponent = new MovementComponent(playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving);
        this.healthComponent = new HealthComponent(healthValue);
        this.collisionComponent = new CollisionComponent(map);
        this.scoreComponent = new ScoreComponent(score);
        this.bulletsComponent = new ArrayList<BulletComponent>();
        this.levelComponent = new LevelComponent(false,1);
    }

    public PositionComponent getPositionComponent() {
        return positionComponent;
    }
    public MovementComponent getMovementComponent(){ return movementComponent;}
    public CollisionComponent getCollisionComponent(){return collisionComponent;}
    public ArrayList<BulletComponent> getBulletsComponent(){return bulletsComponent;}
    public void addBulletsComponent(BulletComponent bulletComponent){bulletsComponent.add(bulletComponent);}
    public ScoreComponent getScoreComponent(){return scoreComponent;}
    public LevelComponent getLevelComponent() {return levelComponent;}
    public HealthComponent getHealthComponent(){return healthComponent;}
    public void setDirection(AbstractInput.Inputs direction) {
        this.direction = direction;
    }
    public AbstractInput.Inputs getDirection() {
    if(direction == null){return AbstractInput.Inputs.IDLE;}
    return direction;
    }
}
