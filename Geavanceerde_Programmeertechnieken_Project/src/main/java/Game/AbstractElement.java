package Game;

import Game.Components.MovementComponent;
import Game.Components.PositionComponent;

public abstract class AbstractElement implements Drawable{

    private final PositionComponent positionComponent;
    private final MovementComponent movementComponent;

    public AbstractElement(int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving){
        this.positionComponent = new PositionComponent(x,y,hitboxWidth,hitboxHeight);
        this.movementComponent = new MovementComponent(playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving);
    }


    private AbstractInput.Inputs direction;
    public PositionComponent getPositionComponent() {
        return positionComponent;
    }
    public MovementComponent getMovementComponent(){ return movementComponent;}
    public void setDirection(AbstractInput.Inputs direction) {
        this.direction = direction;
    }
    public AbstractInput.Inputs getDirection() {return direction;}

}
