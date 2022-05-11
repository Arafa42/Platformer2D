package Game.Entities;

import Game.Components.BulletComponent;
import Game.Drawable;
import Game.Systems.BulletSystem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class AbstractBullet implements Drawable {

    BulletComponent bulletComponent;

    public AbstractBullet(BulletComponent bulletComponent){
        this.bulletComponent = bulletComponent;
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


    public BulletComponent GetBulletComponent(){return bulletComponent;}
    public double getX(){return bulletComponent.getX();}
    public double getY(){return bulletComponent.getY();}
    public int getR(){return bulletComponent.getR();}


}
