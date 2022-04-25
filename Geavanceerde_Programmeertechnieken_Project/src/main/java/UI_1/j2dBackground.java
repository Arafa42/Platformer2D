package UI_1;

import Game.Entities.AbstractBackground;
import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dBackground extends AbstractBackground {
    private final GraphicsContext grCtx;
    private final BufferedImage image;
    //private final BufferedImage image2;
    private final BufferedImage image3;
    private int backgroundX = 0;
    int rainY = 0;


    public j2dBackground(GraphicsContext grCtx) {
        super();
        this.grCtx = grCtx;
        String imagePath = "src/main/resources/assets/images/SpriteSheets/background/sky_cloud.png";
        //String imagePath2 = "src/main/resources/assets/images/SpriteSheets/background/rain.png";
        String imagePath3 = "src/main/resources/assets/images/SpriteSheets/background/mountain2.png";
        image = this.grCtx.loadImages(imagePath, this.grCtx.getFrame().getWidth(),this.grCtx.getFrame().getHeight(), false);
        //image2 = this.grCtx.loadImages(imagePath2, this.grCtx.getFrame().getWidth(),this.grCtx.getFrame().getHeight()/3, false);
        image3 = this.grCtx.loadImages(imagePath3, this.grCtx.getFrame().getWidth(),this.grCtx.getFrame().getHeight()/2, false);
    }

    @Override
    public void draw() {

        //PARALLAX BG
        if(backgroundX < grCtx.getCamX() - grCtx.getFrame().getWidth()){backgroundX += grCtx.getFrame().getWidth();}
        if(backgroundX > grCtx.getCamX() + grCtx.getFrame().getWidth()){backgroundX -= grCtx.getFrame().getWidth();}
        int newX = backgroundX - grCtx.getCamX();
        int mountainY = grCtx.getFrame().getHeight()/2;
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
        g2d.drawImage(image,0,0, null);
        //g2d.drawImage(image2,0,rainY, null);
        g2d.drawImage(image3,newX,mountainY, null);
        g2d.drawImage(image3,bufferX,mountainY, null);
    }
}