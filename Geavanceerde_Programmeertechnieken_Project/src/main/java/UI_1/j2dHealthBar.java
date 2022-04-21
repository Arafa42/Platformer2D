package UI_1;

import Game.AbstractHealthBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dHealthBar extends AbstractHealthBar {

    private final GraphicsContext graphicsContext;
    private BufferedImage[] healthBarSprite;
    private String healthbar_spritesheet = "/assets/images/SpriteSheets/healthbar/healthbar.png";

    public j2dHealthBar(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
        importSprites();
    }

    public void importSprites(){
        //HEALTHBAR
        BufferedImage healthbar = GetSpriteSheet(healthbar_spritesheet);
        healthBarSprite = new BufferedImage[8];
        for(int i=0;i<6;i++){
            if(i == 0 || i == 1){
                healthBarSprite[i] = healthbar.getSubimage(0,42*i,215,42);
            }
            if(i == 2 || i == 3){
                healthBarSprite[i] = healthbar.getSubimage(0,43*i,215,42);
            }
            if(i == 4){
                healthBarSprite[i] = healthbar.getSubimage(0,42*i,215,41);
            }
            if(i == 5){
                healthBarSprite[i] = healthbar.getSubimage(0,41*i,215,42);
            }
        }

    }


    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        int index = getHealthValue();
        g2d.drawImage(healthBarSprite[index],(graphicsContext.getFrame().getWidth()/2) - (healthBarSprite[index].getWidth()/2),5,null);

    }

}
