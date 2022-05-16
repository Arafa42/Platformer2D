package UI_2;

import Game.Components.HealthComponent;
import Game.Entities.AbstractHealthBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CubeHealthBar extends AbstractHealthBar {

    private final GraphicsContext graphicsContext;

    public CubeHealthBar(GraphicsContext graphicsContext, HealthComponent healthComponent) {
        super(healthComponent);
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        //g2d.drawImage(healthBarSprite[GetHealthValue()], (graphicsContext.getFrame().getWidth() / 2) - (healthBarSprite[GetHealthValue()].getWidth() / 2), 5, null);
        g2d.setColor(Color.GREEN);
        g2d.fillRect( (graphicsContext.getFrame().getWidth() / 2) - (200 / 2), 5, 200-(GetHealthValue()*40),30);
    }

}