package UI_1;

import Game.Entities.AbstractInput;
import Game.Entities.AbstractPlayer;
import Game.Components.PositionComponent;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static Helper.Constants.PlayerConstants.GetSpriteAmount;
import static Helper.Constants.PlayerConstants.*;

/**
 *j2dPlayer class extends from AbstractPlayer class.
 * @author Arafa Yoncalik
 */
public class j2dPlayer extends AbstractPlayer {

    private final GraphicsContext graphicsContext;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 5;
    private float xDrawOffset = 30;
    private float yDrawOffset = 8;
    private int playerAction = IDLE;
    PositionComponent m = getPositionComponent();
    int x,y,hitboxWidth,hitboxHeight;
    private final double scale;

    /**
     *j2dPlayer constructor.
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
    public j2dPlayer(GraphicsContext graphicsContext, int x, int y, int hitboxWidth, int hitboxHeight,float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius,double scale) {
        super(x, y, hitboxWidth, hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,score,bulletAngle,bulletSpeed,screenWidth,screenHeight,bulletRadius);
        //this.x = x;
        //this.y = y;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.graphicsContext = graphicsContext;
        this.scale = scale;
        loadAnimations();
    }

    /**
     *update function, calls the updateAnimationTick() and setAnimation() functions.
     */
    public void update() {
        updateAnimationTick();
        setAnimation();
    }

    /**
     * Does the drawing of the player.
     */
    @Override
    public void draw() {
        update();

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
        graphics2D.drawImage(animations[playerAction][aniIndex], (int) (m.x - xDrawOffset*scale)-graphicsContext.getCamX(), (int) (m.y - yDrawOffset*scale)-graphicsContext.getCamY(), (int)(90*scale), (int)(63*scale), null);
        //graphics2D.setColor(Color.RED);
        //graphics2D.drawRect((int)m.x-graphicsContext.getCamX(), (int)m.y-graphicsContext.getCamY(), (int)(hitboxWidth), (int)(hitboxHeight));
    }

    /**
     * updateAnimationTick function updates the animation image of the spritesheet.
     */
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    /**
     * setAnimation functions sets the animation depending on user input.
     */
    private void setAnimation() {
        int startAni = playerAction;
        if((getDirection() == AbstractInput.Inputs.LEFT)){playerAction = RUNNING_LEFT;}
        else if((getDirection() == AbstractInput.Inputs.RIGHT)){playerAction = RUNNING_RIGHT;}
        else{playerAction = IDLE;}
        if((getDirection() == AbstractInput.Inputs.LEFT) && getMovementComponent().isAttacking()){playerAction = ATTACK_1_LEFT;}
        else if((getDirection() == AbstractInput.Inputs.RIGHT) && getMovementComponent().isAttacking()){playerAction = ATTACK_1_RIGHT;}
        else if(getMovementComponent().isAttacking()){playerAction = ATTACK_1_RIGHT;}
        if(startAni != playerAction){resetAniTick();}
    }

    /**
     * The animation image counter gets reset.
     */
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * loadAnimations() function takes a spritesheet and loads the subImages in the animations BufferedImage array.
     */
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/assets/images/SpriteSheets/Player/Spritesheet.png");
        try {
            assert is != null;
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[8][8];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 48, j * 48, 48, 48);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
