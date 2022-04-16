package Game;

public class MovementCompnent {

    private float playerSpeed = 3.0f;
    private boolean inAir = false;
    private float airSpeed = 0f;
    private float gravity = 0.1f;
    private float jumpSpeed= -5f;
    private float fallSpeedAfterCollision = 1f;
    private boolean isMoving = false;
    private CollisionComponent collisionComponent;


    public MovementCompnent(CollisionComponent collisionComponent){
        this.collisionComponent = collisionComponent;
    }


    private void updateMovement(EntityComponent entityComponent,int width, int height, int[][] map){
        entityComponent.x = entityComponent.dx;
        entityComponent.y = entityComponent.dy;

        isMoving = false;
        float xSpeed = 0;

        if(!inAir){if(!collisionComponent.IsEntityOnFloor((int)entityComponent.dx,(int)entityComponent.dy,width,height,map)){inAir = true;}}

        if(inAir){
            if(collisionComponent.CanMoveHere((int)entityComponent.dx, (int)(entityComponent.dy + airSpeed), width, height, map)){
                entityComponent.dy += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed,entityComponent,width,height,map);
            }
            else{
                entityComponent.dy = collisionComponent.GetEntityYPosUnderRoofOrAboveFloor((int)entityComponent.dx,(int)entityComponent.dy,width,height, airSpeed);
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
        if(collisionComponent.CanMoveHere(entityComponent.dx+xSpeed,entityComponent.dy, width, height, map)){entityComponent.dx += xSpeed;}
        else{entityComponent.dx = collisionComponent.GetEntityPosNextToWall((int)entityComponent.dx,(int)entityComponent.dy, width, height, xSpeed);}
    }

    private void resetInAir(){inAir = false;airSpeed = 0;}


    public void update(EntityComponent entityComponent,int width, int height, int[][] map) { updateMovement(entityComponent,width,height,map); }

    public void moveLeft(EntityComponent entityComponent){entityComponent.dx -= playerSpeed;}

    public void moveRight(EntityComponent entityComponent){entityComponent.dx += playerSpeed;}





}
