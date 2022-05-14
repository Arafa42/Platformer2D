package Game.Systems;

import Game.Entities.AbstractBullet;

import java.util.ArrayList;

public class BulletSystem {

    private ArrayList<AbstractBullet> bullets;

    public BulletSystem(ArrayList<AbstractBullet> abstractBullets){
        this.bullets = abstractBullets;
    }

    public void update(){
        updateBulletMovement();
    }

    private void updateBulletMovement(){
        //System.out.println(bullets.size());
        for(int i=0;i<bullets.size();i++) {
                bullets.get(i).GetBulletComponent().setX(bullets.get(i).GetBulletComponent().getX() + (bullets.get(i).GetBulletComponent().getDx() * bullets.get(i).GetBulletComponent().getSpeed()));
                if (bullets.get(i).GetBulletComponent().getX() < -bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getX() - 550 > bullets.get(i).GetBulletComponent().getScreenWidth() + bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getY() < -bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getY() > bullets.get(i).GetBulletComponent().getScreenHeight() + bullets.get(i).GetBulletComponent().getR()) {
                    bullets.remove(i);
                }
            }
    }

}
