package UI_1;

import Game.Entities.AbstractBackground;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *j2dBackground extends AbstractBackground.
 * @author Arafa Yoncalik
 */
public class j2dBackground extends AbstractBackground {
    private final GraphicsContext grCtx;
    private final BufferedImage image;
    private final BufferedImage image2;
    private int backgroundX = 0;
    double layer2Y = 0;


    /**
     *j2dBackground constructor.
     * @param grCtx
     * @param bgLayer1
     * @param bgLayer2
     */
    public j2dBackground(GraphicsContext grCtx,String bgLayer1,String bgLayer2) {
        super(bgLayer1,bgLayer2);
        this.grCtx = grCtx;
        String imagePath = bgLayer1;
        String imagePath2 = bgLayer2;
        image = this.grCtx.loadImages(imagePath, this.grCtx.getFrame().getWidth(),this.grCtx.getFrame().getHeight(), false);
        image2 = this.grCtx.loadImages(imagePath2, this.grCtx.getFrame().getWidth(),this.grCtx.getFrame().getHeight()/2, false);
    }

    /**
     *Does the background drawing.
     */
    @Override
    public void draw() {

        //PARALLAX BG
        if(backgroundX < grCtx.getCamX() - grCtx.getFrame().getWidth()){backgroundX += grCtx.getFrame().getWidth();}
        if(backgroundX > grCtx.getCamX() + grCtx.getFrame().getWidth()){backgroundX -= grCtx.getFrame().getWidth();}
        int newX = backgroundX - grCtx.getCamX();
        int mountainY = grCtx.getFrame().getHeight()/2;
        layer2Y = grCtx.getFrame().getHeight()/1.5;
        int bufferX = 0;
        if(backgroundX > grCtx.getCamX()){bufferX = backgroundX - grCtx.getFrame().getWidth() - grCtx.getCamX();}
        else{bufferX = backgroundX + grCtx.getFrame().getWidth() - grCtx.getCamX();}

        //RAIN EFFECT
//        if(rainY <= grCtx.getFrame().getHeight()-400){
//            rainY += 50;
//        }
//        else{
//            rainY = 0;
//        }


        Graphics2D g2d = grCtx.getG2d();
        g2d.drawImage(image,newX,0, null);
        g2d.drawImage(image,bufferX,0, null);
        g2d.drawImage(image2,newX,mountainY, null);
        g2d.drawImage(image2,bufferX,mountainY, null);
    }
}