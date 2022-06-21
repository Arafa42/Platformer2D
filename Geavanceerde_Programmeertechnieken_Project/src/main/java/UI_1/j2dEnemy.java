package UI_1;

import Game.Components.PositionComponent;
import Game.Entities.AbstractEnemy;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 *j2dEnemy class extends from AbstractEnemy.
 * @author Arafa Yoncalik
 */
public class j2dEnemy extends AbstractEnemy {

    private final GraphicsContext graphicsContext;
    private BufferedImage[] enemySprite1;
    private BufferedImage[] enemySprite2;
    private int aniTick;
    private int aniIndex;
    PositionComponent m = getPositionComponent();
    private final int hitboxWidth;
    private final int hitboxHeight;
    String type;
    private double scale;

    /**
     *j2dEnemy constructor.
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
    public j2dEnemy(GraphicsContext graphicsContext,int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving, int healthValue,int[][] map,String type,double scale,int areaHitboxWidth,int areaHitboxHeight) {
        super(x, y, hitboxWidth, hitboxHeight, playerSpeed, inAir, airSpeed, gravity, jumpSpeed, fallSpeedAfterCollision, isMoving, healthValue,map,type,areaHitboxWidth,areaHitboxHeight);
        this.graphicsContext = graphicsContext;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.type = type;
        this.scale = scale;
        importOutsideSprites();
    }

    /**
     *importOutsideSprites function creates BufferedImage from file and adds the images to the BufferedImage arrays.
     */
    public void importOutsideSprites(){
        //ENEMIES
        if(Objects.equals(type, "GROUND1")) {
            String enemy1_spritesheet = "/assets/images/SpriteSheets/enemy/ground_enemy_1/groundEnemy1_Spritesheet.png";
            BufferedImage enemy1 = GetSpriteSheet(enemy1_spritesheet);
            enemySprite1 = new BufferedImage[2];
            for (int i = 0; i < 2; i++) {
                enemySprite1[i] = enemy1.getSubimage(369 * i, 0, 369, 369);
            }
        }
        if(Objects.equals(type, "GROUND2")){
            String enemy2_spritesheet = "/assets/images/SpriteSheets/enemy/ground_enemy_2/groundEnemy2_Spritesheet.png";
            BufferedImage enemy2 = GetSpriteSheet(enemy2_spritesheet);
            enemySprite2 = new BufferedImage[2];
            for (int i = 0; i < 2; i++) {
                enemySprite2[i] = enemy2.getSubimage(427 * i, 0, 427, 496);
            }
        }
    }

    /**
     *updateAnimationTick() function updates the image of a spritesheet for animation.
     */
    private void updateAnimationTick() {
        aniTick++;
        int aniSpeed = 15;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 2) {
                aniIndex = 0;
            }
        }
    }

    /**
     *update function calls the updateAnimationTick() function.
     */
    public void update() {
        updateAnimationTick();
    }

    /**
     *Does the drawing of the Enemy.
     */
    @Override
    public void draw() {
        update();
        Graphics2D g2d = graphicsContext.getG2d();
        float yDrawOffset = (float)(8*scale);
        float xDrawOffset = (float)(5*scale);
        if (getMovementComponent().getxSpeed() < 0) {
            if (getEnemyComponent().getActive()) {
                if (Objects.equals(type, "GROUND1")) {
                    g2d.drawImage(enemySprite1[aniIndex], (int) (((m.x - xDrawOffset) - graphicsContext.getCamX())), (int) (((m.y - yDrawOffset) - graphicsContext.getCamY())),(int) (50*scale),(int) (50*scale), null);
                }
                if (Objects.equals(type, "GROUND2")) {
                    g2d.drawImage(enemySprite2[aniIndex], (int) (((m.x - xDrawOffset) - graphicsContext.getCamX())), (int) (((m.y - yDrawOffset) - graphicsContext.getCamY())),(int) (50*scale),(int) (50*scale), null);
                }
                //g2d.setColor(Color.RED);
                //g2d.drawRect((int) m.x - graphicsContext.getCamX(), (int) m.y - graphicsContext.getCamY(), hitboxWidth, hitboxHeight);

                //g2d.setColor(Color.PINK);
                //g2d.drawRect(((int)getEnemyComponent().getAreaX() - graphicsContext.getCamX())  , (int)getEnemyComponent().getAreaY() - graphicsContext.getCamY(), getEnemyComponent().getAreaHitboxWidth(), getEnemyComponent().getAreaHitboxHeight());
            }
        }
        if (getMovementComponent().getxSpeed() > 0) {
            if (getEnemyComponent().getActive()) {
                if (Objects.equals(type, "GROUND1")) {
                    g2d.drawImage(enemySprite1[aniIndex], (int) (((m.x - xDrawOffset +50) - graphicsContext.getCamX())), (int) (((m.y - yDrawOffset) - graphicsContext.getCamY())), -(int) ((50)*scale),(int) (50*scale), null);
                }
                if (Objects.equals(type, "GROUND2")) {
                    g2d.drawImage(enemySprite2[aniIndex], (int) (((m.x - xDrawOffset +50) - graphicsContext.getCamX())), (int) (((m.y - yDrawOffset) - graphicsContext.getCamY())), -(int) ((50)*scale),(int) (50*scale), null);
                }

                //g2d.setColor(Color.RED);
                //g2d.drawRect((int) m.x - graphicsContext.getCamX(), (int) m.y - graphicsContext.getCamY(), hitboxWidth, hitboxHeight);

                //g2d.setColor(Color.PINK);
                //g2d.drawRect(((int)getEnemyComponent().getAreaX() - graphicsContext.getCamX()) , (int)getEnemyComponent().getAreaY() - graphicsContext.getCamY(), getEnemyComponent().getAreaHitboxWidth(), getEnemyComponent().getAreaHitboxHeight());
            }
        }
    }
}
