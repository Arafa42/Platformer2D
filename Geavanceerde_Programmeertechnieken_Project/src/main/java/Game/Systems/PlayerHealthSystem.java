package Game.Systems;
import Game.Entities.AbstractBullet;
import Game.Entities.AbstractEnemy;
import Game.Entities.AbstractPlayer;
import Game.Game;
import java.util.ArrayList;

/**
 *PlayerHealthSystem class.
 * @author Arafa Yoncalik
 */
public class PlayerHealthSystem {

    ArrayList<AbstractEnemy> enemies;
    AbstractPlayer player;
    ArrayList<AbstractBullet> enemyBullets;
    private int TILES_SIZE,TILES_IN_HEIGHT;
    private int xResetPos, yResetPos;

    /**
     *PlayerHealthSystem constructor.
     * @param enemies
     * @param player
     * @param enemyBullets
     * @param TILES_SIZE
     * @param TILES_IN_HEIGHT
     * @param xReseetPos
     * @param yResetPos
     */
    public PlayerHealthSystem(ArrayList<AbstractEnemy> enemies, AbstractPlayer player, ArrayList<AbstractBullet> enemyBullets, int TILES_SIZE,int TILES_IN_HEIGHT,int xReseetPos, int yResetPos){
        this.enemies = enemies;
        this.player = player;
        this.enemyBullets = enemyBullets;
        this.TILES_SIZE = TILES_SIZE;
        this.TILES_IN_HEIGHT = TILES_IN_HEIGHT;
        this.xResetPos = xReseetPos;
        this.yResetPos = yResetPos;
    }

    /**
     *update() function checks the health of the player by calling multiple functions.
     */
    public void update(){
        statusCheck();
        checkHealthOnFall((int)player.getPositionComponent().y, player.getMovementComponent().getAirSpeed());
        checkHealthOnCollisionWithEnemy();
        checkHealthOnCollisionWithEnemyBullet();
    }

    /**
     * checks the health of a player on collision with an enemy.
     */
    private void checkHealthOnCollisionWithEnemy(){
        for (AbstractEnemy enemy : enemies) {
            if (isIntersect(player.getPositionComponent().x, player.getPositionComponent().y, player.getPositionComponent().hitboxWidth, player.getPositionComponent().hitboxHeight, enemy.getPositionComponent().x, enemy.getPositionComponent().y, enemy.getPositionComponent().hitboxWidth, enemy.getPositionComponent().hitboxHeight)) {
                player.getHealthComponent().setHealthValue(player.getCollisionComponent().getTimesFell() + 1);
                player.getCollisionComponent().setDidFall(true);
            }
        }
    }

    /**
     * checks the health of a player on collision with an enemy bullet.
     */
    private void checkHealthOnCollisionWithEnemyBullet(){
        for(int i =0;i<enemyBullets.size();i++){
                if(isIntersect((float)enemyBullets.get(i).getX()+50,(float)enemyBullets.get(i).getY(),enemyBullets.get(i).GetBulletComponent().getHitboxWidth(), enemyBullets.get(i).GetBulletComponent().getHitboxHeight(),player.getPositionComponent().x,player.getPositionComponent().y,player.getPositionComponent().hitboxWidth,player.getPositionComponent().hitboxHeight)){
                    //PLAYER DEAD
                    SoundSystem.volume = SoundSystem.Volume.HIGH;
                    SoundSystem.PLAYERISHIT.play(false);
                    player.getHealthComponent().setHealthValue(player.getCollisionComponent().getTimesFell()+1);
                    player.getCollisionComponent().setDidFall(true);
                    //BULLET REMOVAL
                    enemyBullets.get(i).GetBulletComponent().setActive(false);
                    enemyBullets.remove(i);
                }
        }
    }

    /**
     *isIntersect function checks on an intersect with two rectangles.
     * @param Ax
     * @param Ay
     * @param Aw
     * @param Ah
     * @param Bx
     * @param By
     * @param Bw
     * @param Bh
     * @return
     */
    private boolean isIntersect(float Ax, float Ay, float Aw, float Ah, float Bx, float By, float Bw, float Bh)
    {return Bx + Bw > Ax && By + Bh > Ay && Ax + Aw > Bx && Ay + Ah > By;}


    /**
     *checks the health of the player on falling below the platform.
     * @param y
     * @param airSpeed
     */
    private void checkHealthOnFall(int y, float airSpeed){
        int currentTile = y / TILES_SIZE;
        if(airSpeed > 0){
            //FALLING OR TOUCHING FLOOR
            if(currentTile >= TILES_IN_HEIGHT-1){
                //FALLING ON GROUND = -1 HEALTH
                //IF HEALTH VALUE < 5 BECAUSE ELSE YOU DIED
                if(player.getHealthComponent().getHealthValue() < 5) {
                    SoundSystem.volume = SoundSystem.Volume.HIGH;
                    SoundSystem.PLAYERISHIT.play(false);
                    player.getHealthComponent().setHealthValue(player.getCollisionComponent().getTimesFell()+1);
                    player.getCollisionComponent().setDidFall(true);
                }
                else if(player.getHealthComponent().getHealthValue() == 5){
                    player.getCollisionComponent().setDidFall(false);
                    player.getCollisionComponent().setTimesFell(0);
                    player.getHealthComponent().setHealthValue(player.getCollisionComponent().getTimesFell());
                }
            }
        }
    }

    /**
     * statusCheck() function will check how many lives the player has left.
     */
    private void statusCheck(){
        if((player.getHealthComponent().getHealthValue() > 0 && player.getHealthComponent().getHealthValue() < 6) && player.getCollisionComponent().isDidFall()){
            resetPosition();
            player.getCollisionComponent().setTimesFell(player.getCollisionComponent().getTimesFell()+1);
            player.getCollisionComponent().setDidFall(false);
        }
    }

    /**
     * resetPosition() will reset the players position if he/she fell below the platform.
     */
    private void resetPosition(){
        player.getPositionComponent().x = xResetPos;
        player.getPositionComponent().y = yResetPos;
    }

}
