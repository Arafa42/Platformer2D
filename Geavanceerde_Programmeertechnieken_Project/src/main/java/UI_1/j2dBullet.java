package UI_1;

import Game.Components.BulletComponent;
import Game.Entities.AbstractBullet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * j2dBullet class extends AbstractBullet.
 * @author Arafa Yoncalik
 */
public class j2dBullet extends AbstractBullet {
    private final GraphicsContext graphicsContext;
    private int aniTick, aniIndex, aniSpeed = 10;
    private BufferedImage[] bulletSprite;
    private String bullet_spritesheet = "/assets/images/SpriteSheets/Bullets/bulletsSpriteSheet.png";
    private final double scale;
    public j2dBullet(GraphicsContext graphicsContext, BulletComponent bulletComponent,double scale){
        super(bulletComponent);
        this.graphicsContext = graphicsContext;
        this.scale = scale;
        importOutsideSprites();
    }

    /**
     * Gets the bullet spritesheet and cuts the necessary images for the player and enemy, finally adds them to the bulletSprite BufferedImage array.
     */
    public void importOutsideSprites(){
        //BULLETS
        BufferedImage bullets = GetSpriteSheet(bullet_spritesheet);
        BufferedImage newBullets1 = null;
        BufferedImage newBullets2 = null;
        BufferedImage newBullets3 = null;
        BufferedImage newBullets4 = null;

        if(Objects.equals(GetBulletComponent().getType(), "PLAYER")){
             newBullets1 = bullets.getSubimage(100,5,100,16);
             newBullets2 = bullets.getSubimage(100,25,100,16);
             newBullets3 = bullets.getSubimage(100,45,100,16);
             newBullets4 = bullets.getSubimage(100,65,100,16);
        }
        else if(Objects.equals(GetBulletComponent().getType(), "ENEMY")){
             newBullets1 = bullets.getSubimage(200,5,100,16);
             newBullets2 = bullets.getSubimage(200,25,100,16);
             newBullets3 = bullets.getSubimage(200,45,100,16);
             newBullets4 = bullets.getSubimage(200,65,100,16);
        }

        bulletSprite = new BufferedImage[4];

        int rand = getRandomNumber(1,5);

        for(int i=0;i<4;i++){
            if(rand == 1){
                assert newBullets1 != null;
                bulletSprite[i] = newBullets1.getSubimage(25*i,0,25,16);}
            if(rand == 2){
                assert newBullets2 != null;
                bulletSprite[i] = newBullets2.getSubimage(25*i,0,25,16);}
            if(rand == 3){
                assert newBullets3 != null;
                bulletSprite[i] = newBullets3.getSubimage(25*i,0,25,16);}
            if(rand == 4){
                assert newBullets4 != null;
                bulletSprite[i] = newBullets4.getSubimage(25*i,0,25,16);}
        }
    }


    /**
     * Update the animation counter.
     */
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 4) {
                aniIndex = 0;
            }
        }
    }

    /**
     * Calls the updateAnimationTick() function.
     */
    public void updateAni() {
        updateAnimationTick();
    }

    /**
     * Randomiser for random bullet color.
     * @param min
     * @param max
     * @return returns random value between a minimum and maximum number.
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    /**
     * Does the drawing of the Bullet.
     */
    @Override
    public void draw() {
        updateAni();
        Graphics2D g2d = graphicsContext.getG2d();
        //g2d.fillOval(((int)(getX()-getR())+50)-graphicsContext.getCamX(),((int)(getY()-getR())+15)-graphicsContext.getCamY(),3 * getR(),3*getR());
        if (GetBulletComponent().isActive()) {
            g2d.drawImage(bulletSprite[aniIndex], ((int) (getX() - getR()) + 50) - graphicsContext.getCamX(), ((int) (getY() - getR()) + 15) - graphicsContext.getCamY(),(int)(20*scale),(int)(20*scale), null);
            //g2d.setColor(Color.RED);
            //g2d.drawRect(((int) (getX() - getR()) + 50) - graphicsContext.getCamX(), ((int) (getY() - getR()) + 15) - graphicsContext.getCamY(), GetBulletComponent().getHitboxWidth(), GetBulletComponent().getHitboxHeight());
        }
    }

}
