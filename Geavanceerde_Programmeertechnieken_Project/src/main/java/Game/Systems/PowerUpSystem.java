package Game.Systems;

import Game.Components.*;
import Game.Game;

/**
 *PowerUpSystem class.
 * @author Arafa Yoncalik
 */
public class PowerUpSystem extends Thread {

    private final CollisionComponent collisionComponent;
    private final PositionComponent positionComponent;
    private final MovementComponent movementComponent;
    private final HealthComponent healthComponent;
    private int TILES_SIZE;
    private double scale;

    /**
     *PowerUpSystem constructor.
     * @param collisionComponent
     * @param positionComponent
     * @param movementComponent
     * @param healthComponent
     * @param TILES_SIZE
     * @param scale
     */
    public PowerUpSystem(CollisionComponent collisionComponent,PositionComponent positionComponent,MovementComponent movementComponent, HealthComponent healthComponent,int TILES_SIZE,double scale){
        this.collisionComponent = collisionComponent;
        this.positionComponent = positionComponent;
        this.movementComponent = movementComponent;
        this.healthComponent = healthComponent;
        this.scale = scale;
        this.TILES_SIZE = TILES_SIZE;
    }

    /**
     *update() function calls the coinCollisionCheck() function.
     */
    public void update() {
        coinCollisionCheck((int)positionComponent.x,(int)positionComponent.y);
    }


    /**
     *coinCollisionCheck() function checks if player has collided with a coin or powerup item.
     * @param x
     * @param y
     */
    public void coinCollisionCheck(int x, int y) {
            int row = y / TILES_SIZE;
            int col1 = (x + (int)(30*scale)) / TILES_SIZE;
            int col2 = (x) / TILES_SIZE;
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


    /**
     *ExtraJump() function will give the player a boost in jump height for 10 seconds.
     */
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

    /**
     *ExtraSpeed() function will give the player a boost in speed for 10 seconds.
     */
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

    /**
     *ExtraLife() function will give the player an extra life.
     */
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
