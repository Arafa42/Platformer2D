package Game.Systems;

import Game.Entities.AbstractBullet;
import Game.Entities.AbstractEnemy;
import java.util.ArrayList;

public class EnemyHealthSystem {

    private ArrayList<AbstractBullet> playerBullets;
    private ArrayList<AbstractEnemy> enemies;

    public EnemyHealthSystem(ArrayList<AbstractBullet> playerBullets, ArrayList<AbstractEnemy> enemies){
        this.playerBullets = playerBullets;
        this.enemies = enemies;
    }


    public void update(){
        checkPlayerBulletEnemyCollision();
    }

    private void checkPlayerBulletEnemyCollision() {

            for (int i = 0; i < playerBullets.size(); i++) {
                for (int j = 0; j < enemies.size(); j++) {
                    if (isIntersect((float) playerBullets.get(i).GetBulletComponent().getX(), (float) playerBullets.get(i).GetBulletComponent().getY(), playerBullets.get(i).GetBulletComponent().getHitboxWidth(), playerBullets.get(i).GetBulletComponent().getHitboxHeight(), enemies.get(j).getPositionComponent().x, enemies.get(j).getPositionComponent().y, enemies.get(j).getPositionComponent().hitboxWidth, enemies.get(j).getPositionComponent().hitboxHeight)) {
                            SoundSystem.volume = SoundSystem.Volume.HIGH;
                            SoundSystem.ENEMYISHIT.play(false);
                            enemies.get(j).getEnemyComponent().setActive(false);
                            enemies.remove(j);
                            playerBullets.get(i).GetBulletComponent().setActive(false);
                            playerBullets.remove(i);
                        }
                }
            }
    }

    private boolean isIntersect(float Ax, float Ay, float Aw, float Ah, float Bx, float By, float Bw, float Bh)
    {return Bx + Bw > Ax && By + Bh > Ay && Ax + Aw > Bx && Ay + Ah > By;}

}