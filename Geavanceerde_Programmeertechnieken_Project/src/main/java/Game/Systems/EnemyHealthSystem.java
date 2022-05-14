package Game.Systems;

import Game.Entities.AbstractBullet;
import Game.Entities.AbstractEnemy;
import java.util.ArrayList;

public class EnemyHealthSystem {

    private ArrayList<AbstractBullet> abstractBullets;
    private ArrayList<AbstractEnemy> enemies;

    public EnemyHealthSystem(ArrayList<AbstractBullet> abstractBullets, ArrayList<AbstractEnemy> enemies){
        this.abstractBullets = abstractBullets;
        this.enemies = enemies;
    }


    public void update(){
        checkPlayerBulletEnemyCollision();
    }

    private void checkPlayerBulletEnemyCollision() {

            for (int i = 0; i < abstractBullets.size(); i++) {
                for (int j = 0; j < enemies.size(); j++) {
                    if (isIntersect((float) abstractBullets.get(i).GetBulletComponent().getX(), (float) abstractBullets.get(i).GetBulletComponent().getY(), abstractBullets.get(i).GetBulletComponent().getHitboxWidth(), abstractBullets.get(i).GetBulletComponent().getHitboxHeight(), enemies.get(j).getPositionComponent().x, enemies.get(j).getPositionComponent().y, enemies.get(j).getPositionComponent().hitboxWidth, enemies.get(j).getPositionComponent().hitboxHeight)) {
                            enemies.get(j).getEnemyComponent().setActive(false);
                            enemies.remove(j);
                            abstractBullets.get(i).GetBulletComponent().setActive(false);
                            abstractBullets.remove(i);
                        }
                }
            }
    }

    private boolean isIntersect(float Ax, float Ay, float Aw, float Ah, float Bx, float By, float Bw, float Bh)
    {return Bx + Bw > Ax && By + Bh > Ay && Ax + Aw > Bx && Ay + Ah > By;}

}