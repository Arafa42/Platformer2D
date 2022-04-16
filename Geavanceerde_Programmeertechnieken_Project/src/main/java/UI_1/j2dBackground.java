package UI_1;

import Game.AbstractBackground;
import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dBackground extends AbstractBackground {
    private final GraphicsContext grCtx;
    private final BufferedImage image;

    public j2dBackground(GraphicsContext grCtx) {
        super();
        this.grCtx = grCtx;
        String imagePath = "src/main/resources/assets/images/SpriteSheets/background/background_color.png";
        image = this.grCtx.loadImages(imagePath, this.grCtx.getFrame().getWidth(),this.grCtx.getFrame().getHeight(), false);
    }

    @Override
    public void draw() {
        Graphics2D g2d = grCtx.getG2d();
        g2d.drawImage(image,0,0, null);
    }
}
