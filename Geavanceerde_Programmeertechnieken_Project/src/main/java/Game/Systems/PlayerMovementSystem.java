package Game.Systems;

import Game.Components.MovementComponent;

/**
 *PlayerMovementSystem class.
 * @author Arafa Yoncalik
 */
public class PlayerMovementSystem {

    private final MovementComponent movementComponent;

    /**
     *PlayerMovementSystem constructor, takes a MovementComponent as parameter.
     * @param movementComponent
     */
    public PlayerMovementSystem(MovementComponent movementComponent){this.movementComponent = movementComponent;}

    /**
     *updateMovement() function will update the movement of the player depending on an action.
     */
    private void updateMovement(){
        movementComponent.setMoving(true);
        if(movementComponent.isJump()){jump();movementComponent.setJump(false);}
        if(movementComponent.isJump() && movementComponent.isLeft() && movementComponent.isMoving()){
            movementComponent.setxSpeed(-(int)movementComponent.getPlayerSpeed());
            movementComponent.setRight(false);
            jump();movementComponent.setJump(false);
        }
        if(movementComponent.isJump() && movementComponent.isRight() && movementComponent.isMoving()){
            jump();movementComponent.setJump(false);
            movementComponent.setxSpeed((int)movementComponent.getPlayerSpeed());
            movementComponent.setLeft(false);
        }
        if(movementComponent.isLeft() && movementComponent.isMoving()){
            movementComponent.setxSpeed(-(int)movementComponent.getPlayerSpeed());
            movementComponent.setRight(false);
        }
        else if(movementComponent.isRight() && movementComponent.isMoving()){
            movementComponent.setxSpeed((int)movementComponent.getPlayerSpeed());
            movementComponent.setLeft(false);
        }
        else{
            movementComponent.setxSpeed(0);
            movementComponent.setLeft(false);
            movementComponent.setRight(false);
            movementComponent.setMoving(false);
        }
    }

    /**
     *jump() function will make the player jump and play the jump sound.
     */
    public void jump(){
        if(movementComponent.isInAir()){return;}
        movementComponent.setInAir(true);
        SoundSystem.volume = SoundSystem.Volume.HIGH;
        SoundSystem.JUMP.play(false);
        movementComponent.setAirSpeed(movementComponent.getJumpSpeed());
    }

    /**
     *update() function calls the updateMovement() function.
     */
    public void update() { updateMovement(); }

}