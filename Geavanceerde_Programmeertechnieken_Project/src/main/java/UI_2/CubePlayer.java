package UI_2;

import Game.Components.PositionComponent;
import Game.Entities.AbstractPlayer;
import java.awt.*;

/**
 * CubePlayer class extends AbstractPlayer
 * @author Arafa Yoncalik
 */
public class CubePlayer extends AbstractPlayer {

    private final GraphicsContext graphicsContext;
    PositionComponent m = getPositionComponent();
    int hitboxWidth,hitboxHeight;
    private final double scale;

    /**
     *CubePlayer constructor, takes all necessary parameters a player needs.
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
     * @param score
     * @param bulletAngle
     * @param bulletSpeed
     * @param screenWidth
     * @param screenHeight
     * @param bulletRadius
     * @param scale
     */
    public CubePlayer(GraphicsContext graphicsContext, int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving, int healthValue, int[][] map, int score, double bulletAngle, int bulletSpeed, int screenWidth, int screenHeight, int bulletRadius,double scale) {
        super(x, y, hitboxWidth, hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,score,bulletAngle,bulletSpeed,screenWidth,screenHeight,bulletRadius);
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.graphicsContext = graphicsContext;
        this.scale = scale;
    }

    /**
     * Does the drawing of the player.
     */
    @Override
    public void draw() {
        //update();

        //SIDEWAYS CAMERA MOVEMENT
        graphicsContext.setCamX((int)m.x- graphicsContext.getViewPortX()/2);
        graphicsContext.setCamY((int)m.y- graphicsContext.getViewPortY()/2);

        if (graphicsContext.getCamX() > graphicsContext.getOffsetMaxX()){
            graphicsContext.setCamX(graphicsContext.getOffsetMaxX());
        }
        else if (graphicsContext.getCamX() < graphicsContext.getOffsetMinX()){
            graphicsContext.setCamX(graphicsContext.getOffsetMinX());
        }
        if(graphicsContext.getCamY() > graphicsContext.getOffsetMaxY()){
            graphicsContext.setCamY(graphicsContext.getOffsetMaxY());
        }
        else if(graphicsContext.getCamY() < graphicsContext.getOffsetMinY()){
            graphicsContext.setCamY(graphicsContext.getOffsetMinY());
        }

        Graphics2D graphics2D = graphicsContext.getG2d();
        graphics2D.setColor(Color.BLUE);
        graphics2D.drawRect((int)m.x-graphicsContext.getCamX(), (int)m.y-graphicsContext.getCamY(), (int)(hitboxWidth*scale), (int)(hitboxHeight*scale));
    }




}
