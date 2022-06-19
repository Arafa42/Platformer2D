package Game.Systems;

import Game.Entities.AbstractBullet;
import java.util.ArrayList;

public class PlayerBulletSystem {

    private final ArrayList<AbstractBullet> bullets;

    public PlayerBulletSystem(ArrayList<AbstractBullet> abstractBullets){
        this.bullets = abstractBullets;
    }

    public void update(){
        updateBulletMovement();
    }

    private void updateBulletMovement(){
        for(int i=0;i<bullets.size();i++) {
            if (bullets.get(i).GetBulletComponent().getAngle() > 0) {
                bullets.get(i).GetBulletComponent().setX(bullets.get(i).GetBulletComponent().getX() + (bullets.get(i).GetBulletComponent().getDx() * bullets.get(i).GetBulletComponent().getSpeed()));
                if (bullets.get(i).GetBulletComponent().getX() < -bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getX() - 1000 > bullets.get(i).GetBulletComponent().getScreenWidth() + bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getY() < -bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getY() > bullets.get(i).GetBulletComponent().getScreenHeight() + bullets.get(i).GetBulletComponent().getR()) {
                    bullets.get(i).GetBulletComponent().setActive(false);
                    bullets.remove(i);
                }
            }
            else{
                bullets.get(i).GetBulletComponent().setX(bullets.get(i).GetBulletComponent().getX() + (bullets.get(i).GetBulletComponent().getDx() * bullets.get(i).GetBulletComponent().getSpeed()));
                if (bullets.get(i).GetBulletComponent().getX() < -bullets.get(i).GetBulletComponent().getR()-100 || bullets.get(i).GetBulletComponent().getX() - 1000 > bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getY() < -bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getY() > bullets.get(i).GetBulletComponent().getScreenHeight() + bullets.get(i).GetBulletComponent().getR()) {
                    bullets.get(i).GetBulletComponent().setActive(false);
                    bullets.remove(i);
                }
            }
        }
    }
}
