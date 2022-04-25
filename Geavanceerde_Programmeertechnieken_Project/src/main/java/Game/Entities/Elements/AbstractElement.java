package Game.Entities.Elements;

import Game.Components.*;
import Game.Drawable;
import Game.Entities.AbstractInput;

public abstract class AbstractElement implements Drawable {

    private final PositionComponent positionComponent;
    private final MovementComponent movementComponent;
    private final CollisionComponent collisionComponent;
    private final HealthComponent healthComponent;
    private final ScoreComponent scoreComponent;
    private AbstractInput.Inputs direction;


    public AbstractElement(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score){
        this.positionComponent = new PositionComponent(x,y,hitboxWidth,hitboxHeight);
        this.movementComponent = new MovementComponent(playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving);
        this.healthComponent = new HealthComponent(healthValue);
        this.collisionComponent = new CollisionComponent(map);
        this.scoreComponent = new ScoreComponent(score);
    }


    public PositionComponent getPositionComponent() {
        return positionComponent;
    }
    public MovementComponent getMovementComponent(){ return movementComponent;}
    public CollisionComponent getCollisionComponent(){return collisionComponent;}
    public ScoreComponent getScoreComponent(){return scoreComponent;}
    public HealthComponent getHealthComponent(){return healthComponent;}
    public void setDirection(AbstractInput.Inputs direction) {
        this.direction = direction;
    }
    public AbstractInput.Inputs getDirection() {return direction;}

}
