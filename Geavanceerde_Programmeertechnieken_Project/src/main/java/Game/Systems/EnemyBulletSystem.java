package Game.Systems;

import Game.AbstractFactory;
import Game.Components.BulletComponent;
import Game.Drawable;
import Game.Entities.AbstractBullet;
import Game.Entities.AbstractEnemy;
import Game.Entities.AbstractPlayer;
import java.util.ArrayList;

public class EnemyBulletSystem {

    private final ArrayList<AbstractBullet> bullets;
    private long firingTimer = System.nanoTime();
    private final int screenWidth;
    private final int screenHeight;
    ArrayList<Drawable> drawables;
    AbstractFactory factory;
    ArrayList<AbstractEnemy> enemies;
    AbstractPlayer player;

    public EnemyBulletSystem(ArrayList<AbstractBullet> abstractBullets, ArrayList<AbstractEnemy> enemies, int screenWidth, int screenHeight, ArrayList<Drawable> drawables, AbstractFactory factory, AbstractPlayer player){
        this.bullets = abstractBullets;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.drawables = drawables;
        this.factory = factory;
        this.enemies = enemies;
        this.player = player;
    }

    public void update(){
        updateBulletMovement();
        fireCheck();
    }

    private void fireCheck(){
        for(int i =0;i<enemies.size();i++) {

            float enemyAreaX = enemies.get(i).getPositionComponent().x - (enemies.get(i).getEnemyComponent().getAreaHitboxWidth()/2)+enemies.get(i).getPositionComponent().hitboxWidth/2;
            float enemyAreaY = enemies.get(i).getPositionComponent().y - enemies.get(i).getEnemyComponent().getAreaHitboxHeight()/2 + enemies.get(i).getPositionComponent().hitboxHeight/2;
            enemies.get(i).getEnemyComponent().setAreaX(enemyAreaX);
            enemies.get(i).getEnemyComponent().setAreaY(enemyAreaY);
            if (isIntersect(player.getPositionComponent().x, player.getPositionComponent().y, player.getPositionComponent().hitboxWidth, player.getPositionComponent().hitboxHeight,enemyAreaX,enemyAreaY,enemies.get(i).getEnemyComponent().getAreaHitboxWidth(),enemies.get(i).getEnemyComponent().getAreaHitboxHeight())) {
                fire();
            }
        }
    }

    private boolean isIntersect(float Ax, float Ay, float Aw, float Ah, float Bx, float By, float Bw, float Bh)
    {return Bx + Bw > Ax && By + Bh > Ay && Ax + Aw > Bx && Ay + Ah > By;}

    private void updateBulletMovement(){
        //System.out.println(bullets.size());
        for(int i=0;i<bullets.size();i++) {
            bullets.get(i).GetBulletComponent().setX(bullets.get(i).GetBulletComponent().getX() + (bullets.get(i).GetBulletComponent().getDx() * bullets.get(i).GetBulletComponent().getSpeed()));
            if (bullets.get(i).GetBulletComponent().getX() < -bullets.get(i).GetBulletComponent().getR()-100 || bullets.get(i).GetBulletComponent().getX() - 1000 > bullets.get(i).GetBulletComponent().getScreenWidth() + bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getY() < -bullets.get(i).GetBulletComponent().getR() || bullets.get(i).GetBulletComponent().getY() > bullets.get(i).GetBulletComponent().getScreenHeight() + bullets.get(i).GetBulletComponent().getR()) {
                bullets.get(i).GetBulletComponent().setActive(false);
                bullets.remove(i);
            }
        }
    }

    private void fire(){

        long elapsed = (System.nanoTime() - firingTimer) / 1000000;
        long firingDelay = 1000;
        if(elapsed > firingDelay) {
            for (AbstractEnemy enemy : enemies) {
                //BULLET DIRECTION
                if (enemy.getMovementComponent().getxSpeed() > 0) {
                    SoundSystem.volume = SoundSystem.Volume.HIGH;
                    SoundSystem.ENEMYBULLET.play(false);
                    bullets.add(factory.createBullet(new BulletComponent("ENEMY", enemy.getPositionComponent().x, enemy.getPositionComponent().y, 25, 16, 270, 3, screenWidth, screenHeight, 2),factory.getScaleY()));
                }
                if (enemy.getMovementComponent().getxSpeed() < 0) {
                    SoundSystem.volume = SoundSystem.Volume.HIGH;
                    SoundSystem.ENEMYBULLET.play(false);
                    bullets.add(factory.createBullet(new BulletComponent("ENEMY", enemy.getPositionComponent().x - 50, enemy.getPositionComponent().y, 25, 16, 90, 3, screenWidth, screenHeight, 2),factory.getScaleY()));
                }
                firingTimer = System.nanoTime();
                //System.out.println(bullets.size());
                drawables.addAll(bullets);
            }
        }
    }

}
