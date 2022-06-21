package UI_2;

import Game.Components.PositionComponent;
import Game.Entities.AbstractEnemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 *CubeEnemy class extends AbstractEnemy.
 * @author Arafa Yoncalik
 */
public class CubeEnemy extends AbstractEnemy {

    private final GraphicsContext graphicsContext;
    PositionComponent m = getPositionComponent();
    private final int hitboxWidth;
    private final int hitboxHeight;
    private String type;
    private double scale;

    /**
     *CubeEnemy constructor.
     * @param graphicsContext
     * @param x
     * @param y
     * @param hitboxWidth
     * @param hitboxHeight
     * @param playerSpeed
     * @param inAir
     * @param airSpeed
     * @param gravity
     * @param jumpSpeed
     * @param fallSpeedAfterCollision
     * @param isMoving
     * @param healthValue
     * @param map
     * @param type
     * @param scale
     * @param areaHitboxWidth
     * @param areaHitboxHeight
     */
    public CubeEnemy(GraphicsContext graphicsContext, int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving, int healthValue, int[][] map, String type,double scale,int areaHitboxWidth,int areaHitboxHeight) {
        super(x, y, hitboxWidth, hitboxHeight, playerSpeed, inAir, airSpeed, gravity, jumpSpeed, fallSpeedAfterCollision, isMoving, healthValue,map,type,areaHitboxWidth,areaHitboxHeight);
        this.graphicsContext = graphicsContext;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.scale = scale;
        this.type = type;
    }

    /**
     *Does the drawing of the enemy.
     */
    @Override
    public void draw() {
        //update();
        Graphics2D g2d = graphicsContext.getG2d();
        if (getMovementComponent().getxSpeed() < 0) {
            if (getEnemyComponent().getActive()) {
                if (Objects.equals(type, "GROUND1")) {
                    g2d.setColor(Color.MAGENTA);
                    g2d.drawRect((int) m.x - graphicsContext.getCamX(), (int) m.y - graphicsContext.getCamY(), (int)(hitboxWidth*scale),  (int)(hitboxHeight*scale));
                }
                if (Objects.equals(type, "GROUND2")) {
                    g2d.setColor(Color.PINK);
                    g2d.drawRect((int) m.x - graphicsContext.getCamX(), (int) m.y - graphicsContext.getCamY(), (int)(hitboxWidth*scale),  (int)(hitboxHeight*scale));
                }
                //g2d.drawRect(((int)getEnemyComponent().getAreaX() - graphicsContext.getCamX())  , (int)getEnemyComponent().getAreaY() - graphicsContext.getCamY(), getEnemyComponent().getAreaHitboxWidth(), getEnemyComponent().getAreaHitboxHeight());
            }
        }
        if (getMovementComponent().getxSpeed() > 0) {
            if (getEnemyComponent().getActive()) {
                if (Objects.equals(type, "GROUND1")) {
                    g2d.setColor(Color.MAGENTA);
                    g2d.drawRect((int) m.x - graphicsContext.getCamX(), (int) m.y - graphicsContext.getCamY(),(int)( hitboxWidth*scale),  (int)(hitboxHeight*scale));

                }
                if (Objects.equals(type, "GROUND2")) {
                    g2d.setColor(Color.PINK);
                    g2d.drawRect((int) m.x - graphicsContext.getCamX(), (int) m.y - graphicsContext.getCamY(), (int)(hitboxWidth*scale),  (int)(hitboxHeight*scale));
                }
            }
            //g2d.drawRect(((int)getEnemyComponent().getAreaX() - graphicsContext.getCamX())  , (int)getEnemyComponent().getAreaY() - graphicsContext.getCamY(), getEnemyComponent().getAreaHitboxWidth(), getEnemyComponent().getAreaHitboxHeight());
        }
    }
}
