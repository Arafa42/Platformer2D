package Game;

public abstract class AbstractBullet implements Drawable{

    private double x;
    private double y;
    private int r;
    private double dx;
    private double dy;
    private double rad;
    private double speed;
    private int screenWidth;
    private int screenHeight;



    public AbstractBullet(double angle, int x, int y,int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = x;
        this.y = y;
        r = 2;
        rad = Math.toRadians(angle);
        dx = -Math.sin(rad);
        dy = Math.cos(rad);
        speed = 5;

    }


    public boolean update(){
        x += dx * speed;
        y += dy;

        if(x < -r || x > screenWidth + r || y < -r || y > screenHeight + r){return true;}

        return false;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public int getR() {return r;}
}
