package Game.Entities;

import Game.Components.HealthComponent;
import Game.Drawable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 *AbstractHealthBar implements the Drawable interface.
 * @author Arafa Yoncalik
 */
public abstract class AbstractHealthBar implements Drawable {

    private final HealthComponent healthComponent;

    /**
     *AbstractHealthBar constructor takes a HealthComponent as parameter.
     * @param healthComponent
     */
    public AbstractHealthBar(HealthComponent healthComponent){
        this.healthComponent = healthComponent;
    }

    /**
     *GetSpriteSheet functions.
     * @param fileName
     * @return returns a BufferedImage of a filename.
     */
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

    /**
     * GetHealthValue getter.
     * @return returns the health value from the health component.
     */
    public int GetHealthValue(){
        return healthComponent.getHealthValue();
    }

}