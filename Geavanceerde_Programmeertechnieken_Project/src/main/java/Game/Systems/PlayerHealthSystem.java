package Game.Systems;

import Game.Entities.AbstractBullet;
import Game.Entities.AbstractEnemy;
import Game.Entities.AbstractPlayer;
import Game.Game;
import java.util.ArrayList;

public class PlayerHealthSystem {

    ArrayList<AbstractEnemy> enemies;
    AbstractPlayer player;
    ArrayList<AbstractBullet> enemyBullets;

    public PlayerHealthSystem(ArrayList<AbstractEnemy> enemies, AbstractPlayer player, ArrayList<AbstractBullet> enemyBullets){
        this.enemies = enemies;
        this.player = player;
        this.enemyBullets = enemyBullets;
    }

    public void update(){
        statusCheck();
        checkHealthOnFall((int)player.getPositionComponent().y, (int)player.getPositionComponent().hitboxHeight, player.getMovementComponent().getAirSpeed());
        checkHealthOnCollisionWithEnemy();
        checkHealthOnCollisionWithEnemyBullet();
    }

    private void checkHealthOnCollisionWithEnemy(){
        for(int i =0;i<enemies.size();i++){
            if(isIntersect(player.getPositionComponent().x,player.getPositionComponent().y,player.getPositionComponent().hitboxWidth,player.getPositionComponent().hitboxHeight,enemies.get(i).getPositionComponent().x,enemies.get(i).getPositionComponent().y,enemies.get(i).getPositionComponent().hitboxWidth,enemies.get(i).getPositionComponent().hitboxHeight)){
                player.getHealthComponent().setHealthValue(player.getCollisionComponent().getTimesFell()+1);
                player.getCollisionComponent().setDidFall(true);
            }
        }
    }

    private void checkHealthOnCollisionWithEnemyBullet(){
        for(int i =0;i<enemyBullets.size();i++){
                if(isIntersect((float)enemyBullets.get(i).getX()+50,(float)enemyBullets.get(i).getY(),enemyBullets.get(i).GetBulletComponent().getHitboxWidth(), enemyBullets.get(i).GetBulletComponent().getHitboxHeight(),player.getPositionComponent().x,player.getPositionComponent().y,player.getPositionComponent().hitboxWidth,player.getPositionComponent().hitboxHeight)){
                    //PLAYER DEAD
                    player.getHealthComponent().setHealthValue(player.getCollisionComponent().getTimesFell()+1);
                    player.getCollisionComponent().setDidFall(true);
                    //BULLET REMOVAL
                    enemyBullets.get(i).GetBulletComponent().setActive(false);
                    enemyBullets.remove(i);
                }
        }
    }

    private boolean isIntersect(float Ax, float Ay, float Aw, float Ah, float Bx, float By, float Bw, float Bh)
    {return Bx + Bw > Ax && By + Bh > Ay && Ax + Aw > Bx && Ay + Ah > By;}


    private void checkHealthOnFall(int y, int height, float airSpeed){
        int currentTile = (int) (y / Game.TILES_SIZE);
        if(airSpeed > 0){
            //FALLING OR TOUCHING FLOOR
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - height);
            if(currentTile >= Game.TILES_IN_HEIGHT-1){
                //FALLING ON GROUND = -1 HEALTH
                //IF HEALTH VALUE < 5 BECAUSE ELSE YOU DIED
                if(player.getHealthComponent().getHealthValue() < 5) {
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

    private void statusCheck(){
        if((player.getHealthComponent().getHealthValue() > 0 && player.getHealthComponent().getHealthValue() < 6) && player.getCollisionComponent().isDidFall()){
            resetPosition();
            player.getCollisionComponent().setTimesFell(player.getCollisionComponent().getTimesFell()+1);
            player.getCollisionComponent().setDidFall(false);
        }
    }

    private void resetPosition(){
        player.getPositionComponent().x = 100;
        player.getPositionComponent().y = 550;
    }

}
