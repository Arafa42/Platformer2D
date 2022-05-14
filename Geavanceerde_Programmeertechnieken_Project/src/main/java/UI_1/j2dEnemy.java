package UI_1;

import Game.Components.PositionComponent;
import Game.Entities.AbstractEnemy;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class j2dEnemy extends AbstractEnemy {

    private final GraphicsContext graphicsContext;
    private BufferedImage enemySprite1[];
    private BufferedImage enemySprite2[];
    private String enemy1_spritesheet = "/assets/images/SpriteSheets/enemy/ground_enemy_1/groundEnemy1_Spritesheet.png";
    private String enemy2_spritesheet = "/assets/images/SpriteSheets/enemy/ground_enemy_2/groundEnemy2_Spritesheet.png";
    private int aniTick, aniIndex, aniSpeed = 15;
    private float xDrawOffset = 5;
    private float yDrawOffset = 8;
    PositionComponent m = getPositionComponent();
    private int hitboxWidth,hitboxHeight;
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
            BufferedImage enemy1 = GetSpriteSheet(enemy1_spritesheet);
            enemySprite1 = new BufferedImage[2];
            for (int i = 0; i < 2; i++) {
                enemySprite1[i] = enemy1.getSubimage(369 * i, 0, 369, 369);
            }
        }
        if(Objects.equals(type, "GROUND2")){
            BufferedImage enemy2 = GetSpriteSheet(enemy2_spritesheet);
            enemySprite2 = new BufferedImage[2];
            for (int i = 0; i < 2; i++) {
                enemySprite2[i] = enemy2.getSubimage(427 * i, 0, 427, 496);
            }
        }
    }

    private void updateAnimationTick() {
        aniTick++;
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
        if (getEnemyComponent().getActive()) {
            if(Objects.equals(type, "GROUND1")) {
                g2d.drawImage(enemySprite1[aniIndex], (int) (m.x - xDrawOffset) - graphicsContext.getCamX(), (int) (m.y - yDrawOffset) - graphicsContext.getCamY(), (int) (50), (int) (50), null);
        }
        if(Objects.equals(type, "GROUND2")) {
                g2d.drawImage(enemySprite2[aniIndex], (int) (m.x - xDrawOffset) - graphicsContext.getCamX(), (int) (m.y - yDrawOffset) - graphicsContext.getCamY(), (int) (50), (int) (50), null);
        }
            g2d.setColor(Color.RED);
            g2d.drawRect((int) m.x - graphicsContext.getCamX(), (int) m.y - graphicsContext.getCamY(), (int) hitboxWidth, (int) hitboxHeight);
        }
    }
}
