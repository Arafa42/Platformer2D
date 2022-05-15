package Game.Systems;

import Game.Components.MovementComponent;
import Game.Components.PositionComponent;

public class PlayerMovementSystem {

    private MovementComponent movementComponent;

    public PlayerMovementSystem(MovementComponent movementComponent){
        this.movementComponent = movementComponent;
    }

    private void updateMovement(){
        movementComponent.setMoving(false);
        movementComponent.setxSpeed(0);
        if(movementComponent.isJump()){jump();movementComponent.setJump(false);}
        if(movementComponent.isLeft()){
            movementComponent.setxSpeed(-(int)movementComponent.getPlayerSpeed());
            //positionComponent.x += movementComponent.getxSpeed();
        }
        if(movementComponent.isRight()){movementComponent.setxSpeed(
                (int)movementComponent.getPlayerSpeed());
                //positionComponent.x += movementComponent.getxSpeed();
        }
        movementComponent.setMoving(true);
    }

    public void jump(){
        if(movementComponent.isInAir()){return;}
        movementComponent.setInAir(true);
        movementComponent.setAirSpeed(movementComponent.getJumpSpeed());
    }

    public void update() { updateMovement(); }

}