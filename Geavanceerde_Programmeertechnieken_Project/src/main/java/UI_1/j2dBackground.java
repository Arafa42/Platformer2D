package UI_1;

import Game.AbstractBackground;
import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dBackground extends AbstractBackground {
    private final GraphicsContext grCtx;
    private final BufferedImage image;
    private final BufferedImage image3;
    private int backgroundX = 0;

    public j2dBackground(GraphicsContext grCtx) {
        super();
        this.grCtx = grCtx;
        String imagePath = "src/main/resources/assets/images/SpriteSheets/background/sky_cloud.png";
        String imagePath3 = "src/main/resources/assets/images/SpriteSheets/background/mountain2.png";
        image = this.grCtx.loadImages(imagePath, this.grCtx.getFrame().getWidth(),this.grCtx.getFrame().getHeight(), false);
        image3 = this.grCtx.loadImages(imagePath3, this.grCtx.getFrame().getWidth(),this.grCtx.getFrame().getHeight()/2, false);
    }

    @Override
    public void draw() {

        if(backgroundX < grCtx.getCamX() - grCtx.getFrame().getWidth()){backgroundX += grCtx.getFrame().getWidth();}
        if(backgroundX > grCtx.getCamX() + grCtx.getFrame().getWidth()){backgroundX -= grCtx.getFrame().getWidth();}

        int newX = backgroundX - grCtx.getCamX();
        int mountainY = grCtx.getFrame().getHeight()/2;
        int bufferX = 0;

        if(backgroundX > grCtx.getCamX()){bufferX = backgroundX - grCtx.getFrame().getWidth() - grCtx.getCamX();}
        else{bufferX = backgroundX + grCtx.getFrame().getWidth() - grCtx.getCamX();}

        Graphics2D g2d = grCtx.getG2d();
        g2d.drawImage(image,0,0, null);
        g2d.drawImage(image3,newX,mountainY, null);
        g2d.drawImage(image3,bufferX,mountainY, null);
    }
}