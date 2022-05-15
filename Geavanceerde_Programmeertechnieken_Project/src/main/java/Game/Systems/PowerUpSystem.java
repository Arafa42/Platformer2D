package Game.Systems;

import Game.Components.*;
import Game.Game;

import java.util.Timer;
import java.util.TimerTask;

public class PowerUpSystem extends Thread {


    private CollisionComponent collisionComponent;
    private PositionComponent positionComponent;
    private MovementComponent movementComponent;
    private HealthComponent healthComponent;

    public PowerUpSystem(CollisionComponent collisionComponent,PositionComponent positionComponent,MovementComponent movementComponent, HealthComponent healthComponent){
        this.collisionComponent = collisionComponent;
        this.positionComponent = positionComponent;
        this.movementComponent = movementComponent;
        this.healthComponent = healthComponent;
    }

    public void update() {
        coinCollisionCheck((int)positionComponent.x,(int)positionComponent.y);
    }


    public void coinCollisionCheck(int x, int y) {
            int row = (int) (y / Game.TILES_SIZE);
            int col1 = (int) ((x + 30) / Game.TILES_SIZE);
            int col2 = (int) ((x) / Game.TILES_SIZE);
            //JUMP
            if (collisionComponent.getLevelData()[row][col1] == -3) {
                collisionComponent.getLevelData()[row][col1] = 0;
                extraJump();
            }
            if (collisionComponent.getLevelData()[row][col2] == -3) {
                collisionComponent.getLevelData()[row][col2] = 0;
                extraJump();
            }
            //SPEED
            if (collisionComponent.getLevelData()[row][col1] == -4) {
                collisionComponent.getLevelData()[row][col1] = 0;
                extraSpeed();
            }
            if (collisionComponent.getLevelData()[row][col2] == -4) {
                collisionComponent.getLevelData()[row][col2] = 0;
                extraSpeed();
            }
            //LIFE
            if (collisionComponent.getLevelData()[row][col1] == -5) {
                collisionComponent.getLevelData()[row][col1] = 0;
                extraLife();
            }
            if (collisionComponent.getLevelData()[row][col2] == -5) {
                collisionComponent.getLevelData()[row][col2] = 0;
                extraLife();
            }
    }


    private void extraJump() {
        SoundSystem.volume = SoundSystem.Volume.HIGH;
        SoundSystem.POWERUP.play(false);
        float prevJumpSpeed = movementComponent.getJumpSpeed();
        movementComponent.setJumpSpeed(movementComponent.getJumpSpeed()-3);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        movementComponent.setJumpSpeed(prevJumpSpeed);
                    }
                },
                10000
        );
    }

    private void extraSpeed(){
        SoundSystem.volume = SoundSystem.Volume.HIGH;
        SoundSystem.POWERUP.play(false);
        float prevPlayerSpeed = movementComponent.getPlayerSpeed();
        movementComponent.setPlayerSpeed(movementComponent.getPlayerSpeed()+2);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        movementComponent.setPlayerSpeed(prevPlayerSpeed);
                    }
                },
                10000
        );
    }

    private void extraLife(){
        SoundSystem.volume = SoundSystem.Volume.HIGH;
        SoundSystem.POWERUP.play(false);
        int currHealthValueOfPlayer = healthComponent.getHealthValue();
        if(currHealthValueOfPlayer != 0){
            healthComponent.setHealthValue(currHealthValueOfPlayer-1);
            collisionComponent.setTimesFell(collisionComponent.getTimesFell()-1);
        }
    }


}
