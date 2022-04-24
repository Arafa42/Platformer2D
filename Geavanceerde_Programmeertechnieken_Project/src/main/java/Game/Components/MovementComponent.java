package Game.Components;

public class MovementComponent {

    private  float playerSpeed;
    private  boolean inAir;
    private  float airSpeed;
    private  float gravity;
    private  float jumpSpeed;
    private  float fallSpeedAfterCollision;
    private  boolean isMoving;
    private boolean left,right,jump;
    private int xSpeed;

    public MovementComponent(float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving) {
        this.playerSpeed = playerSpeed;
        this.inAir = inAir;
        this.airSpeed = airSpeed;
        this.gravity = gravity;
        this.jumpSpeed = jumpSpeed;
        this.fallSpeedAfterCollision = fallSpeedAfterCollision;
        this.isMoving = isMoving;
    }


    public float getPlayerSpeed() {return playerSpeed;}
    public void setPlayerSpeed(float playerSpeed) {this.playerSpeed = playerSpeed;}
    public boolean isInAir() {return inAir;}
    public void setInAir(boolean inAir) {this.inAir = inAir;}
    public float getAirSpeed() {return airSpeed;}
    public void setAirSpeed(float airSpeed) {this.airSpeed = airSpeed;}
    public float getGravity() {return gravity;}
    public void setGravity(float gravity) {this.gravity = gravity;}
    public float getJumpSpeed() {return jumpSpeed;}
    public void setJumpSpeed(float jumpSpeed) {this.jumpSpeed = jumpSpeed;}
    public float getFallSpeedAfterCollision() {return fallSpeedAfterCollision;}
    public void setFallSpeedAfterCollision(float fallSpeedAfterCollision) {this.fallSpeedAfterCollision = fallSpeedAfterCollision;}
    public boolean isMoving() {return isMoving;}
    public void setMoving(boolean moving) {isMoving = moving;}
    public boolean isLeft() {return left;}
    public void setLeft(boolean left) {this.left = left;}
    public boolean isRight() {return right;}
    public void setRight(boolean right) {this.right = right;}
    public boolean isJump() {return jump;}
    public void setJump(boolean jump) {this.jump = jump;}
    public int getxSpeed() {return xSpeed;}
    public void setxSpeed(int xSpeed) {this.xSpeed = xSpeed;}
}
