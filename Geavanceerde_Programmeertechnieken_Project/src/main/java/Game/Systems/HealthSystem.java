package Game.Systems;

import Game.Entities.AbstractEnemy;
import Game.Entities.AbstractPlayer;
import Game.Game;

import java.util.ArrayList;

public class HealthSystem {

    ArrayList<AbstractEnemy> enemies;
    AbstractPlayer player;

    public HealthSystem(ArrayList<AbstractEnemy> enemies, AbstractPlayer player){
        this.enemies = enemies;
        this.player = player;
    }

    public void update(){
        statusCheck();
        checkHealthOnFall((int)player.getPositionComponent().y, (int)player.getPositionComponent().hitboxHeight, player.getMovementComponent().getAirSpeed());
        checkHealthOnCollisionWithEnemyOrEnemyBullet();
    }

    private void checkHealthOnCollisionWithEnemyOrEnemyBullet(){
        for(int i =0;i<enemies.size();i++){
            if(isIntersect(player.getPositionComponent().x,player.getPositionComponent().y,player.getPositionComponent().hitboxWidth,player.getPositionComponent().hitboxHeight,enemies.get(i).getPositionComponent().x,enemies.get(i).getPositionComponent().y,enemies.get(i).getPositionComponent().hitboxWidth,enemies.get(i).getPositionComponent().hitboxHeight)){
                player.getHealthComponent().setHealthValue(player.getCollisionComponent().getTimesFell()+1);
                player.getCollisionComponent().setDidFall(true);
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
        //FELL ON GROUND
        //ALSO IN COLLISION CLASS A CHECK WHEN FELL ON GROUND
        if((player.getHealthComponent().getHealthValue() > 0 && player.getHealthComponent().getHealthValue() < 6) && player.getCollisionComponent().isDidFall()){
            resetPosition();
            player.getCollisionComponent().setTimesFell(player.getCollisionComponent().getTimesFell()+1);
            player.getCollisionComponent().setDidFall(false);
        }
        //IF ENEMY HIT PLAYER
        //IF ENEMY BULLET HIT PLAYER
        //IF PLAYER BULLET HIT ENEMY
    }

    private void resetPosition(){
        player.getPositionComponent().x = 100;
        player.getPositionComponent().y = 550;
    }

}
