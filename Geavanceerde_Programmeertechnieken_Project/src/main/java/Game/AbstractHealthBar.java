package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractHealthBar implements Drawable{

    private int healthValue = 0;

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

    public int getHealthValue() {return healthValue;}
    public void setHealthValue(int healthValue) {this.healthValue = healthValue;}
}
