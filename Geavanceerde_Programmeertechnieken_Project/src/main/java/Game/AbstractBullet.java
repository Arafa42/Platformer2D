package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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

    public BufferedImage GetSpriteSheet(String fileName) {
        BufferedImage img = null;
        InputStream is = AbstractLevel.class.getResourceAsStream(fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }


    public boolean update(){
        x += dx * speed;

        //y += dy;

        if(x < -r || x-550 > screenWidth + r || y < -r || y > screenHeight + r){return true;}

        return false;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public int getR() {return r;}
}
