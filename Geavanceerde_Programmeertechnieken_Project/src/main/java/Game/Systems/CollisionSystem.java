package Game.Systems;

import Game.Components.CollisionComponent;
import Game.Components.MovementComponent;
import Game.Components.PositionComponent;
import Game.Entities.AbstractScore;
import Game.Components.HealthComponent;
import Game.Game;

public class CollisionSystem {

    private CollisionComponent collisionComponent;
    private HealthComponent healthComponent;
    private PositionComponent positionComponent;
    private MovementComponent movementComponent;

    public CollisionSystem(CollisionComponent collisionComponent, HealthComponent healthComponent,PositionComponent positionComponent,MovementComponent movementComponent){
        this.collisionComponent = collisionComponent;
        this.healthComponent = healthComponent;
        this.positionComponent = positionComponent;
        this.movementComponent = movementComponent;
    }


    public void updateCollision(AbstractScore abstractScore){

        checkInAirOnStart((int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);
        coinCollisionCheck((int)positionComponent.x,(int)positionComponent.y,(int)positionComponent.hitboxWidth,(int)positionComponent.hitboxHeight,abstractScore);
        if(!movementComponent.isInAir()){
            if(!IsEntityOnFloor((int)positionComponent.x,(int)positionComponent.y,(int)positionComponent.hitboxWidth,(int)positionComponent.hitboxHeight)){
                movementComponent.setInAir(true);
            }
        }

        if(movementComponent.isInAir()){
            if(CanMoveHere((int)positionComponent.x, (int)(positionComponent.y + movementComponent.getAirSpeed()), (int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight)){
                positionComponent.y += movementComponent.getAirSpeed();
                movementComponent.setAirSpeed(movementComponent.getAirSpeed()+movementComponent.getGravity());
                updateXPos(movementComponent.getxSpeed(),(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);
            }
            else{
                positionComponent.y = GetEntityYPosUnderRoofOrAboveFloor((int)positionComponent.x,(int)positionComponent.y,(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight, movementComponent.getAirSpeed());
                if(movementComponent.getAirSpeed() > 0){resetInAir();}
                else{movementComponent.setAirSpeed(movementComponent.getFallSpeedAfterCollision());}
                updateXPos(movementComponent.getxSpeed(),(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);
            }
        }
        else{updateXPos(movementComponent.getxSpeed(),(int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);}

    }


    public boolean CanMoveHere(float x,float y, float width, float height){
        if(!IsSolid(x,y)){
            if(!IsSolid(x+width,y+height)){
                if(!IsSolid(x+width,y)){
                    if(!IsSolid(x,y+height)){
                        return true;
                    }
                }
            }
        }
        return false;
    }



        private void updateXPos(float xSpeed, int width, int height){
        if(CanMoveHere(positionComponent.x+xSpeed,positionComponent.y, width, height)){positionComponent.x += xSpeed;}
        else{positionComponent.x = GetEntityPosNextToWall((int)positionComponent.x,(int)positionComponent.y, width, height, xSpeed);}
    }

    private void resetInAir(){
        movementComponent.setInAir(false);
        movementComponent.setAirSpeed(0);
    }

    private void checkInAirOnStart( int width, int height){
        if(!IsEntityOnFloor((int)positionComponent.x,(int)positionComponent.y,width,height)){movementComponent.setInAir(true);}
    }


    private boolean IsSolid(float x, float y){

        if(x < 0.0 || x >= (Game.TILES_SIZE * Game.TILES_IN_WIDTH)){
            return true;
        }
        if(y < 0.0 || y >= (Game.TILES_SIZE * Game.TILES_IN_HEIGHT)){
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = collisionComponent.getLevelData()[(int) yIndex][(int)xIndex];


        if(value!=0 && value != 2 && value != 4 && value != 7 && value != -2){return true;}
        return false;
    }

    public float GetEntityPosNextToWall(int x, int y, int width, int height, Float xSpeed){

        int currentTile = (int)(x / Game.TILES_SIZE);
        if(xSpeed > 0){
            //right
            int tileXpos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - width);
            return tileXpos + xOffset - 1;
        }
        else{
            //left
            return currentTile * Game.TILES_SIZE;
        }

    }

    public float GetEntityYPosUnderRoofOrAboveFloor(int x, int y, int width, int height, Float airSpeed){

        int currentTile = (int) (y / Game.TILES_SIZE);
        if(airSpeed > 0){
            //FALLING OR TOUCHING FLOOR
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - height);
            if(currentTile >= Game.TILES_IN_HEIGHT-1){
                //FALLING ON GROUND = -1 HEALTH
                //IF HEALTH VALUE < 5 BECAUSE ELSE YOU DIED
                if(healthComponent.getHealthValue() < 5) {
                    System.out.println(collisionComponent.getTimesFell());
                    healthComponent.setHealthValue(collisionComponent.getTimesFell()+1);
                    collisionComponent.setDidFall(true);
                    System.out.println("CollSys 87 : " + healthComponent.getHealthValue());
                }
                else if(healthComponent.getHealthValue() == 5){
                    collisionComponent.setDidFall(false);
                    collisionComponent.setTimesFell(0);
                    healthComponent.setHealthValue(collisionComponent.getTimesFell());
                }
            }

            return tileYPos + yOffset - 1;
        }
        else{
            //jumping
            return currentTile * Game.TILES_SIZE;
        }
    }


    public boolean IsEntityOnFloor(int x,int y, int width, int height){

        //check below bottomleft and bottomright
        if(!IsSolid(x, y + height+1)){
            if(!IsSolid(x + width,y + height+1)){
                return false;
            }
        }
        return true;
    }


    public void coinCollisionCheck(int x, int y, int width, int height, AbstractScore abstractTopBar) {

        int row = (int) (y / Game.TILES_SIZE);
        int col1 = (int) ((x+30) / Game.TILES_SIZE);
        int col2 = (int) ((x) / Game.TILES_SIZE);
        if ((!IsSolid(x, y + height + 1)) && CanMoveHere(x,y,width,height)) {

            if( collisionComponent.getLevelData()[row][col1] == -2){
                abstractTopBar.setScore(collisionComponent.getCurrScore()+1);
                collisionComponent.getLevelData()[row][col1] = 0;
            }
            if( collisionComponent.getLevelData()[row][col2] == -2){
                abstractTopBar.setScore(collisionComponent.getCurrScore()+1);
                collisionComponent.getLevelData()[row][col2] = 0;
            }
        }

    }

}