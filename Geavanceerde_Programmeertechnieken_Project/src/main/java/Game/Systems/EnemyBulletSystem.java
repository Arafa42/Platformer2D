package Game.Systems;

import Game.AbstractFactory;
import Game.Components.BulletComponent;
import Game.Drawable;
import Game.Entities.AbstractBullet;
import Game.Entities.AbstractEnemy;

import java.util.ArrayList;

public class EnemyBulletSystem {

    private ArrayList<AbstractBullet> bullets;
    private long firingTimer = System.nanoTime();
    private long firingDelay = 1000;
    private int screenWidth,screenHeight;
    ArrayList<Drawable> drawables;
    AbstractFactory factory;
    ArrayList<AbstractEnemy> enemies;

    public EnemyBulletSystem(ArrayList<AbstractBullet> abstractBullets, ArrayList<AbstractEnemy> enemies, int screenWidth, int screenHeight, ArrayList<Drawable> drawables, AbstractFactory factory){
        this.bullets = abstractBullets;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.drawables = drawables;
        this.factory = factory;
        this.enemies = enemies;
    }

    public void update(){
        updateBulletMovement();
        fire();
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

    private void fire(){
        long elapsed = (System.nanoTime() - firingTimer) / 1000000;
        if(elapsed > firingDelay) {
            for (int i = 0; i < enemies.size(); i++) {
                bullets.add(factory.createBullet(new BulletComponent(enemies.get(i).getPositionComponent().x, enemies.get(i).getPositionComponent().y, 25, 16, 270, 5, screenWidth, screenHeight, 2)));
                firingTimer = System.nanoTime();
                //System.out.println(bullets.size());
                drawables.addAll(bullets);
            }
        }
    }

}
