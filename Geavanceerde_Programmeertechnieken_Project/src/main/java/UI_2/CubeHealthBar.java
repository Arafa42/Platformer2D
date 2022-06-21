package UI_2;

import Game.Components.HealthComponent;
import Game.Entities.AbstractHealthBar;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *CubeHealthBar Class extends from AbstractHealthBar.
 * @author Arafa Yoncalik
 */
public class CubeHealthBar extends AbstractHealthBar {

    private final GraphicsContext graphicsContext;
    private final double scale;

    /**
     *CubeHealthBar constructor.
     * @param graphicsContext
     * @param healthComponent
     * @param scale
     */
    public CubeHealthBar(GraphicsContext graphicsContext, HealthComponent healthComponent,double scale) {
        super(healthComponent);
        this.scale = scale;
        this.graphicsContext = graphicsContext;
    }

    /**
     *Does the drawing of the HealthBar.
     */
    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        //g2d.drawImage(healthBarSprite[GetHealthValue()], (graphicsContext.getFrame().getWidth() / 2) - (healthBarSprite[GetHealthValue()].getWidth() / 2), 5, null);
        g2d.setColor(Color.GREEN);
        g2d.fillRect( (graphicsContext.getFrame().getWidth() / 2) - (200 / 2), (int)(5*scale), 200-(GetHealthValue()*40),(int)(30*scale));



    }

}