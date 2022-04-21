package UI_1;

import Game.AbstractBullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dBullet extends AbstractBullet {
    private final GraphicsContext graphicsContext;
    private final BufferedImage image;

    public j2dBullet(GraphicsContext graphicsContext,double angle, int x, int y,int screenWidth, int screenHeight){
        super(angle, x, y, screenWidth, screenHeight);
        this.graphicsContext = graphicsContext;
        String imagePath = "src/main/resources/assets/images/SpriteSheets/Bullets/bullet.png";
        image = this.graphicsContext.loadImages(imagePath, 10,10, false);
    }


    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        g2d.fillOval(((int)(getX()-getR())+50)-graphicsContext.getCamX(),((int)(getY()-getR())+15)-graphicsContext.getCamY(),3 * getR(),3*getR());
        //g2d.drawImage(image,);
    }

}
