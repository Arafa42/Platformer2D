package UI_1;

import Game.Components.HealthComponent;
import Game.Entities.AbstractHealthBar;
import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dHealthBar extends AbstractHealthBar {

    private final GraphicsContext graphicsContext;
    private BufferedImage[] healthBarSprite;
    private String healthbar_spritesheet = "/assets/images/SpriteSheets/healthbar/healthbar.png";
    private final double scale;
    public j2dHealthBar(GraphicsContext graphicsContext,HealthComponent healthComponent,double scale) {
        super(healthComponent);
        this.graphicsContext = graphicsContext;
        this.scale = scale;
        importSprites();
    }

    public void importSprites() {
        //HEALTHBAR
        BufferedImage healthbar = GetSpriteSheet(healthbar_spritesheet);
        healthBarSprite = new BufferedImage[8];
        for (int i = 0; i < 6; i++) {
            if (i == 0 || i == 1) {
                healthBarSprite[i] = healthbar.getSubimage(0, 42 * i, 215, 42);
            }
            if (i == 2 || i == 3) {
                healthBarSprite[i] = healthbar.getSubimage(0, 43 * i, 215, 42);
            }
            if (i == 4) {
                healthBarSprite[i] = healthbar.getSubimage(0, 42 * i, 215, 41);
            }
            if (i == 5) {
                healthBarSprite[i] = healthbar.getSubimage(0, 41 * i, 215, 42);
            }
        }

    }

    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        g2d.drawImage(healthBarSprite[GetHealthValue()], (graphicsContext.getFrame().getWidth() / 2) - (int)((healthBarSprite[GetHealthValue()].getWidth() / 2)), (int)(5*scale), null);
    }

}