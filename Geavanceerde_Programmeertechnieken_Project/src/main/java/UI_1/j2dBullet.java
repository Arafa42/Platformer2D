package UI_1;

import Game.Components.BulletComponent;
import Game.Entities.AbstractBullet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class j2dBullet extends AbstractBullet {
    private final GraphicsContext graphicsContext;
    private int aniTick, aniIndex, aniSpeed = 10;
    private BufferedImage[] bulletSprite;
    private String bullet_spritesheet = "/assets/images/SpriteSheets/Bullets/bulletsSpriteSheet.png";


    public j2dBullet(GraphicsContext graphicsContext, BulletComponent bulletComponent){
        super(bulletComponent);
        this.graphicsContext = graphicsContext;
        importOutsideSprites();
    }


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

    public void updateAni() {
        updateAnimationTick();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    @Override
    public void draw() {
        updateAni();
        Graphics2D g2d = graphicsContext.getG2d();
        //g2d.fillOval(((int)(getX()-getR())+50)-graphicsContext.getCamX(),((int)(getY()-getR())+15)-graphicsContext.getCamY(),3 * getR(),3*getR());
        if (GetBulletComponent().isActive()) {
            g2d.drawImage(bulletSprite[aniIndex], ((int) (getX() - getR()) + 50) - graphicsContext.getCamX(), ((int) (getY() - getR()) + 15) - graphicsContext.getCamY(), null);
            //g2d.setColor(Color.RED);
            //g2d.drawRect(((int) (getX() - getR()) + 50) - graphicsContext.getCamX(), ((int) (getY() - getR()) + 15) - graphicsContext.getCamY(), GetBulletComponent().getHitboxWidth(), GetBulletComponent().getHitboxHeight());
        }
    }

}
