package Game;

public class MovementCompnent {

    private float playerSpeed = 3.0f;
    private boolean inAir = false;
    private float airSpeed = 0f;
    private float gravity = 0.1f;
    private float jumpSpeed= -6f;
    private float fallSpeedAfterCollision = 1f;
    private boolean isMoving = false;
    private CollisionComponent collisionComponent;
    private boolean left = false;
    private boolean right = false;


    public MovementCompnent(CollisionComponent collisionComponent){
        this.collisionComponent = collisionComponent;
    }

    private void updateMovement(EntityComponent entityComponent, int width, int height, int[][] map){
        checkInAirOnStart(entityComponent,width,height,map);
        isMoving = false;

        float xSpeed = 0;
        if(left){xSpeed -=playerSpeed;}
        if(right){xSpeed += playerSpeed;}
        if(!inAir){ if(!collisionComponent.IsEntityOnFloor((int)entityComponent.x,(int)entityComponent.y,width,height,map)){inAir = true;}}

        if(inAir){
            if(collisionComponent.CanMoveHere((int)entityComponent.x, (int)(entityComponent.y + airSpeed), width, height, map)){
                entityComponent.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed,entityComponent,width,height,map);
            }
            else{
                entityComponent.y = collisionComponent.GetEntityYPosUnderRoofOrAboveFloor((int)entityComponent.x,(int)entityComponent.y,width,height, airSpeed);
                if(airSpeed > 0){resetInAir();}
                else{airSpeed = fallSpeedAfterCollision;}
                updateXPos(xSpeed,entityComponent,width,height,map);
            }
        }
        else{updateXPos(xSpeed,entityComponent,width,height,map);}
        isMoving = true;
    }

    public void jump(){
        if(inAir){return;}
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void updateXPos(float xSpeed,EntityComponent entityComponent, int width, int height, int[][] map){
        if(collisionComponent.CanMoveHere(entityComponent.x+xSpeed,entityComponent.y, width, height, map)){entityComponent.x += xSpeed;}
        else{entityComponent.x = collisionComponent.GetEntityPosNextToWall((int)entityComponent.x,(int)entityComponent.y, width, height, xSpeed);}
    }

    private void resetInAir(){inAir = false;airSpeed = 0;}

    private void checkInAirOnStart(EntityComponent entityComponent,int width, int height, int[][] map){
        if(!collisionComponent.IsEntityOnFloor((int)entityComponent.x,(int)entityComponent.y,width,height,map)){inAir = true;}
    }


    public void update(EntityComponent entityComponent,int width, int height, int[][] map) { updateMovement(entityComponent,width,height,map); }
    public Boolean getIsMoving(){return isMoving;}
    public void setLeft(boolean left) {this.left = left;}
    public void setRight(boolean right) {this.right = right;}


}
