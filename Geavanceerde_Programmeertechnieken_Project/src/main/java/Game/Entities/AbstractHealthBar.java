package Game.Entities;

import Game.Components.HealthComponent;
import Game.Drawable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractHealthBar implements Drawable {

    private HealthComponent healthComponent;

    public AbstractHealthBar(HealthComponent healthComponent){
        this.healthComponent = healthComponent;
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

    public int GetHealthValue(){
        return healthComponent.getHealthValue();
    }




}
