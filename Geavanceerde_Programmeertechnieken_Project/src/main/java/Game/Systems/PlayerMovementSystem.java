package Game.Systems;

import Game.Components.MovementComponent;

public class PlayerMovementSystem {

    private final MovementComponent movementComponent;

    public PlayerMovementSystem(MovementComponent movementComponent){this.movementComponent = movementComponent;}

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

    public void jump(){
        if(movementComponent.isInAir()){return;}
        movementComponent.setInAir(true);
        SoundSystem.volume = SoundSystem.Volume.HIGH;
        SoundSystem.JUMP.play(false);
        movementComponent.setAirSpeed(movementComponent.getJumpSpeed());
    }

    public void update() { updateMovement(); }

}