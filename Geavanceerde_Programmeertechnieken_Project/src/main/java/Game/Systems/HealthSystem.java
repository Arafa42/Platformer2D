package Game.Systems;

import Game.Components.CollisionComponent;
import Game.Components.HealthComponent;
import Game.Components.MovementComponent;
import Game.Components.PositionComponent;
import Game.Game;

public class HealthSystem {


    private PositionComponent positionComponent;
    private MovementComponent movementComponent;
    private CollisionComponent collisionComponent;
    private HealthComponent healthComponent;

    public HealthSystem(PositionComponent positionComponent, MovementComponent movementComponent, CollisionComponent collisionComponent, HealthComponent healthComponent){
        this.positionComponent = positionComponent;
        this.movementComponent = movementComponent;
        this.collisionComponent = collisionComponent;
        this.healthComponent = healthComponent;
    }

    public void update(){
        statusCheck();
        checkHealthOnFall((int)positionComponent.y, (int)positionComponent.hitboxHeight, movementComponent.getAirSpeed());
        checkHealthOnCollisionWithEnemyOrEnemyBullet();
    }

    private void checkHealthOnCollisionWithEnemyOrEnemyBullet(){
        //GET POSITION OF PLAYER
        // GET WHAT TILE HE IS ON IN MAP
        // GET POSITION OF ALL ENEMIES
        // GET ALL TILES THE ENEMIES ARE ON
        //IF PLAYER TILE IS SAME AS AN ENEMY PLAYER LIFE POINTS GO -1

    }

    private void checkHealthOnFall(int y, int height, float airSpeed){
        int currentTile = (int) (y / Game.TILES_SIZE);
        if(airSpeed > 0){
            //FALLING OR TOUCHING FLOOR
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - height);
            if(currentTile >= Game.TILES_IN_HEIGHT-1){
                //FALLING ON GROUND = -1 HEALTH
                //IF HEALTH VALUE < 5 BECAUSE ELSE YOU DIED
                if(healthComponent.getHealthValue() < 5) {
                    //System.out.println(collisionComponent.getTimesFell());
                    healthComponent.setHealthValue(collisionComponent.getTimesFell()+1);
                    collisionComponent.setDidFall(true);
                    //System.out.println("CollSys 87 : " + healthComponent.getHealthValue());
                }
                else if(healthComponent.getHealthValue() == 5){
                    collisionComponent.setDidFall(false);
                    collisionComponent.setTimesFell(0);
                    healthComponent.setHealthValue(collisionComponent.getTimesFell());
                }
            }
        }
    }

    private void statusCheck(){
        //FELL ON GROUND
        //ALSO IN COLLISION CLASS A CHECK WHEN FELL ON GROUND
        if((healthComponent.getHealthValue() > 0 && healthComponent.getHealthValue() < 6) && collisionComponent.isDidFall()){
            resetPosition();
            collisionComponent.setTimesFell(collisionComponent.getTimesFell()+1);
            collisionComponent.setDidFall(false);
        }
        //IF ENEMY HIT PLAYER
        //IF ENEMY BULLET HIT PLAYER
        //IF PLAYER BULLET HIT ENEMY
    }

    private void resetPosition(){
        positionComponent.x = 100;
        positionComponent.y = 550;
    }

}
