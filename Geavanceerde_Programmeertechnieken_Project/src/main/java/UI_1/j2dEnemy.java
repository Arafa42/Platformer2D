package UI_1;

import Game.Components.PositionComponent;
import Game.Entities.AbstractEnemy;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

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

    public j2dEnemy(GraphicsContext graphicsContext,int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed, boolean inAir, float airSpeed, float gravity, float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving, int healthValue,int[][] map,String type) {
        super(x, y, hitboxWidth, hitboxHeight, playerSpeed, inAir, airSpeed, gravity, jumpSpeed, fallSpeedAfterCollision, isMoving, healthValue,map,type);
        this.graphicsContext = graphicsContext;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.type = type;
        importOutsideSprites();
    }

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

    public void update() {
        updateAnimationTick();
    }

    @Override
    public void draw() {
        update();
        Graphics2D g2d = graphicsContext.getG2d();
        float yDrawOffset = 8;
        float xDrawOffset = 5;
        if (getMovementComponent().getxSpeed() < 0) {
            if (getEnemyComponent().getActive()) {
                if (Objects.equals(type, "GROUND1")) {
                    g2d.drawImage(enemySprite1[aniIndex], (int) (m.x - xDrawOffset) - graphicsContext.getCamX(), (int) (m.y - yDrawOffset) - graphicsContext.getCamY(), 50, 50, null);
                }
                if (Objects.equals(type, "GROUND2")) {
                    g2d.drawImage(enemySprite2[aniIndex], (int) (m.x - xDrawOffset) - graphicsContext.getCamX(), (int) (m.y - yDrawOffset) - graphicsContext.getCamY(), 50, 50, null);
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
                    g2d.drawImage(enemySprite1[aniIndex], (int) (m.x - xDrawOffset +50) - graphicsContext.getCamX(), (int) (m.y - yDrawOffset) - graphicsContext.getCamY(), -(int) (50), 50, null);
                }
                if (Objects.equals(type, "GROUND2")) {
                    g2d.drawImage(enemySprite2[aniIndex], (int) (m.x - xDrawOffset +50) - graphicsContext.getCamX(), (int) (m.y - yDrawOffset) - graphicsContext.getCamY(), -(int) (50), 50, null);
                }
                //g2d.setColor(Color.RED);
                //g2d.drawRect((int) m.x - graphicsContext.getCamX(), (int) m.y - graphicsContext.getCamY(), hitboxWidth, hitboxHeight);

                //g2d.setColor(Color.PINK);
                //g2d.drawRect(((int)getEnemyComponent().getAreaX() - graphicsContext.getCamX()) , (int)getEnemyComponent().getAreaY() - graphicsContext.getCamY(), getEnemyComponent().getAreaHitboxWidth(), getEnemyComponent().getAreaHitboxHeight());
            }
        }
    }
}
