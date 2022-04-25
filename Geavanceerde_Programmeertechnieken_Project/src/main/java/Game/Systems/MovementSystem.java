package Game.Systems;

import Game.Components.CollisionComponent;
import Game.Entities.AbstractScore;
import Game.Components.MovementComponent;
import Game.Components.PositionComponent;

public class MovementSystem {


    private CollisionComponent collisionComponent;
    private PositionComponent positionComponent;
    private MovementComponent movementComponent;

    public MovementSystem(CollisionComponent collisionComponent, MovementComponent movementComponent, PositionComponent positionComponent){
        this.collisionComponent = collisionComponent;
        this.movementComponent = movementComponent;
        this.positionComponent = positionComponent;
    }

    private void updateMovement(){
        //checkInAirOnStart((int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);
        movementComponent.setMoving(false);
        //float xSpeed = 0;
        movementComponent.setxSpeed(0);

        //collisionComponent.coinCollisionCheck((int)positionComponent.x,(int)positionComponent.y,(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight,abstractTopBar);

        if(movementComponent.isJump()){jump();movementComponent.setJump(false);}
        if(movementComponent.isLeft()){movementComponent.setxSpeed(-(int)movementComponent.getPlayerSpeed());}
        if(movementComponent.isRight()){movementComponent.setxSpeed((int)movementComponent.getPlayerSpeed());}
//        if(!movementComponent.isInAir()){
//            if(!collisionComponent.IsEntityOnFloor((int)positionComponent.x,(int)positionComponent.y,(int)positionComponent.hitboxWidth,(int)positionComponent.hitboxHeight)){
//                movementComponent.setInAir(true);
//            }
//        }

//        if(movementComponent.isInAir()){
//            if(collisionComponent.CanMoveHere((int)positionComponent.x, (int)(positionComponent.y + movementComponent.getAirSpeed()), (int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight)){
//                positionComponent.y += movementComponent.getAirSpeed();
//                movementComponent.setAirSpeed(movementComponent.getAirSpeed()+movementComponent.getGravity());
//                updateXPos(xSpeed,(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);
//            }
//            else{
//                positionComponent.y = collisionComponent.GetEntityYPosUnderRoofOrAboveFloor((int)positionComponent.x,(int)positionComponent.y,(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight, movementComponent.getAirSpeed());
//                if(movementComponent.getAirSpeed() > 0){resetInAir();}
//                else{movementComponent.setAirSpeed(movementComponent.getFallSpeedAfterCollision());}
//                updateXPos(xSpeed,(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);
//            }
//        }
//        else{updateXPos(xSpeed,(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);}
        movementComponent.setMoving(true);


    }

    public void jump(){
        if(movementComponent.isInAir()){return;}
        movementComponent.setInAir(true);
        movementComponent.setAirSpeed(movementComponent.getJumpSpeed());
    }

//    private void updateXPos(float xSpeed, int width, int height){
//        if(collisionComponent.CanMoveHere(positionComponent.x+xSpeed,positionComponent.y, width, height)){positionComponent.x += xSpeed;}
//        else{positionComponent.x = collisionComponent.GetEntityPosNextToWall((int)positionComponent.x,(int)positionComponent.y, width, height, xSpeed);}
//    }

//    private void resetInAir(){
//        movementComponent.setInAir(false);
//        movementComponent.setAirSpeed(0);
//    }

//    private void checkInAirOnStart( int width, int height){
//        if(!collisionComponent.IsEntityOnFloor((int)positionComponent.x,(int)positionComponent.y,width,height)){movementComponent.setInAir(true);}
//    }

    public void resetPosition(){
        positionComponent.x = 100;
        positionComponent.y = 550;
    }


    public void update( AbstractScore abstractTopBar) { updateMovement(); }

}