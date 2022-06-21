package Game.Systems;

import Game.Entities.AbstractBullet;
import Game.Entities.AbstractEnemy;
import java.util.ArrayList;

/**
 *EnemyHealthSystem class.
 * @author Arafa Yoncalik
 */
public class EnemyHealthSystem {

    private final ArrayList<AbstractBullet> playerBullets;
    private final ArrayList<AbstractEnemy> enemies;

    /**
     *EnemyHealthSystem constructor.
     * @param playerBullets
     * @param enemies
     */
    public EnemyHealthSystem(ArrayList<AbstractBullet> playerBullets, ArrayList<AbstractEnemy> enemies){
        this.playerBullets = playerBullets;
        this.enemies = enemies;
    }

    /**
     *update function calls the checkPlayerBulletEnemyCollision() function.
     */
    public void update(){
        checkPlayerBulletEnemyCollision();
    }

    /**
     *checkPlayerBulletEnemyCollision() function checks if the players bullet collided with the enemies.
     */
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

    /**
     *isIntersect() function checks if two rectangles intersect with eachother.
     * @param Ax
     * @param Ay
     * @param Aw
     * @param Ah
     * @param Bx
     * @param By
     * @param Bw
     * @param Bh
     * @return returns a boolean value.
     */
    private boolean isIntersect(float Ax, float Ay, float Aw, float Ah, float Bx, float By, float Bw, float Bh)
    {return Bx + Bw > Ax && By + Bh > Ay && Ax + Aw > Bx && Ay + Ah > By;}

}