package Game.Components;

public class BulletComponent {
    private double x;
    private double y;
    private int r;
    private double dx;
    private double dy;
    private double rad;
    private double speed;
    private int screenWidth;
    private int screenHeight;
    private double angle;


    public BulletComponent(double x,double y,double angle,double speed, int screenWidth, int screenHeight,int r){
        this.x = x;
        this.y = y;
        this.r = r;
        this.angle = angle;
        this.rad = Math.toRadians(angle);
        this.dx = -Math.sin(rad);
        this.dy = Math.cos(rad);
        this.speed = speed;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }


    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    public double getDx() {return dx;}
    public void setDx(double dx) {this.dx = dx;}
    public double getDy() {return dy;}
    public void setDy(double dy) {this.dy = dy;}
    public double getRad() {return rad;}
    public void setRad(double rad) {this.rad = rad;}
    public double getSpeed() {return speed;}
    public void setSpeed(double speed) {this.speed = speed;}
    public int getScreenWidth() {return screenWidth;}
    public void setScreenWidth(int screenWidth) {this.screenWidth = screenWidth;}
    public int getScreenHeight() {return screenHeight;}
    public void setScreenHeight(int screenHeight) {this.screenHeight = screenHeight;}
    public int getR() {return r;}
    public void setR(int r) {this.r = r;}
    public double getAngle() {return angle;}
    public void setAngle(double angle) {this.angle = angle;}

    public BulletComponent getNewBulletComponent(){
        return new BulletComponent(x,y,angle,speed,screenWidth,screenHeight,r);
    }

}
