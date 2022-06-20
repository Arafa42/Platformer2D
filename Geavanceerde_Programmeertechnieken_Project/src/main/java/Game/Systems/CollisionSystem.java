package Game.Systems;

import Game.Components.*;

public class CollisionSystem {

    private final CollisionComponent collisionComponent;
    private final PositionComponent positionComponent;
    private final MovementComponent movementComponent;
    private final int TILES_SIZE;
    private final int TILES_IN_WIDTH;
    private final int TILES_IN_HEIGHT;

    public CollisionSystem(CollisionComponent collisionComponent,PositionComponent positionComponent,MovementComponent movementComponent,int TILE_SIZE,int TILES_IN_WIDTH, int TILES_IN_HEIGHT){
        this.collisionComponent = collisionComponent;
        this.positionComponent = positionComponent;
        this.movementComponent = movementComponent;
        this.TILES_SIZE = TILE_SIZE;
        this.TILES_IN_WIDTH = TILES_IN_WIDTH;
        this.TILES_IN_HEIGHT = TILES_IN_HEIGHT;
    }

    public void updateCollision(){
        checkInAirOnStart((int)positionComponent.hitboxWidth, (int)positionComponent.hitboxHeight);
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
        if(!IsSolid(x,y)){if(!IsSolid(x+width,y+height)){if(!IsSolid(x+width,y)){if(!IsSolid(x,y+height)){return true;}}}}
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
        if(x < 0.0 || x >= (TILES_SIZE * TILES_IN_WIDTH)){return true;}
        if(y < 0.0 || y >= (TILES_SIZE * TILES_IN_HEIGHT)){return true;}

        float xIndex = x / TILES_SIZE;
        float yIndex = y / TILES_SIZE;

        int value = collisionComponent.getLevelData()[(int) yIndex][(int)xIndex];
        return value != 0 && value != 2 && value != 4 && value != 7 && value != -2 && value != -3 && value != -4 && value != -5 && value != 3 && value != -6 && value != -7 && value != 64 && value != 100 && value != 101 && value != 102 && value != 116 && value != 117 && value != 118;
    }

    public float GetEntityPosNextToWall(int x, int y, int width, int height, Float xSpeed){
        int currentTile = (int)(x / TILES_SIZE);
        if(xSpeed > 0){
            int tileXpos = currentTile * TILES_SIZE;
            int xOffset = (int)(TILES_SIZE - width);
            return tileXpos + xOffset - 1;
        }
        else{return currentTile * TILES_SIZE;}
    }

    public float GetEntityYPosUnderRoofOrAboveFloor(int x, int y, int width, int height, Float airSpeed){
        int currentTile = (int) (y / TILES_SIZE);
        if(airSpeed > 0){
            int tileYPos = currentTile * TILES_SIZE;
            int yOffset = (int)(TILES_SIZE - height);
            return tileYPos + yOffset - 1;
        }
        else{
            return currentTile * TILES_SIZE;
        }
    }

    public boolean IsEntityOnFloor(int x,int y, int width, int height){
        //check below bottomleft and bottomright
        if(!IsSolid(x, y + height+1)){
            return IsSolid(x + width, y + height + 1);
        }
        return true;
    }

}