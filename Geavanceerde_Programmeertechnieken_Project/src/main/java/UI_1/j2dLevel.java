package UI_1;

import Game.Entities.AbstractLevel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *j2dLevel class extends from AbstractLevel class.
 * @author Arafa Yoncalik
 */
public class j2dLevel extends AbstractLevel {

    //LEVEL MAP
    //  0 = NR 200 ON SPRITESHEET WHICH IS EMPTY
    // -2 = COINS
    // -3 = GROUND ENEMY 1
    // -4 = GROUND ENEMY 2
    // -5 = SHOOTING ENEMY (WITH FOLLOWING SOME TYPE OF AI I GUESS)

    private final GraphicsContext graphicsContext;
    private BufferedImage[] levelSprite;
    private BufferedImage[] coinSprite;
    private BufferedImage[] powerUpSprite;
    private int aniTick;
    private int aniIndex;
    private final int TILES_IN_WIDTH, TILES_IN_HEIGHT, TILES_SIZE;

    /**
     *j2dLevel constructor.
     * @param graphicsContext
     * @param tileArray
     * @param TILES_IN_HEIGHT
     * @param TILES_IN_WIDTH
     * @param TILES_SIZE
     */
    public j2dLevel(GraphicsContext graphicsContext, int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE) {
        super(tileArray);
        this.TILES_IN_WIDTH = TILES_IN_WIDTH;
        this.TILES_IN_HEIGHT = TILES_IN_HEIGHT;
        this.TILES_SIZE = TILES_SIZE;
        this.graphicsContext = graphicsContext;
        importOutsideSprites();
    }

    /**
     *importOutsideSprites() function gets the subImages of the BufferedImage of a file and adds them to the levelSprite BufferedImage array.
     */
    public void importOutsideSprites(){
        //TILESET
        String tile_spritesheet = "/assets/images/SpriteSheets/tilesets.png";
        BufferedImage img = GetSpriteSheet(tile_spritesheet);
        levelSprite = new BufferedImage[256];
        for(int i =0;i<16;i++){
            for(int j=0;j<16;j++){
                int index = i*16 + j;
                levelSprite[index] = img.getSubimage(j*32,i*32,32,32);
            }
        }

        //COINS
        String coin_spritesheet = "/assets/images/SpriteSheets/collectibles/coins.png";
        BufferedImage coins = GetSpriteSheet(coin_spritesheet);
        coinSprite = new BufferedImage[8];
        for(int i=0;i<6;i++){
            coinSprite[i] = coins.getSubimage(16*i,0,16,16);
        }

        //POWERUPS
        String powerups_spritesheet = "/assets/images/SpriteSheets/powerups/powerupsprite.png";
        BufferedImage powerup = GetSpriteSheet(powerups_spritesheet);
        powerUpSprite = new BufferedImage[10];
        for(int i=0;i<8;i++){
            powerUpSprite[i] = powerup.getSubimage(95*i,0,95,73);
        }
    }

    /**
     *updateAnimationTick() function updates the animation image of the spriteSheet.
     */
    private void updateAnimationTick() {
        aniTick++;
        int aniSpeed = 10;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 6) {
                aniIndex = 0;
            }
        }
    }

    /**
     *update function calss the updateAnimationTick() function.
     */
    public void update() {
        updateAnimationTick();
    }

    /**
     *Does the drawing of the Level.
     */
    @Override
    public void draw() {
        update();
        Graphics2D g2d = graphicsContext.getG2d();

        //TILEMAP
        for(int i = 0;i<TILES_IN_HEIGHT;i++){
            for(int j =0;j<TILES_IN_WIDTH;j++) {
                int index = getSpriteIndex(j, i);
                if (index != -2 && index != -3 && index != -4 && index != -5 && index != -6 && index != -7) {
                    if (index == 0) {
                        index = 200;
                    }
                    g2d.drawImage(levelSprite[index], (int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())), (int)(TILES_SIZE), (int)(TILES_SIZE), null);
                    //g2d.drawRect((int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE));
                }
                else if(index == -2){
                        g2d.drawImage(coinSprite[aniIndex],(int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE),null);
                        //g2d.drawRect((int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE));
                }
                else if(index == -3){
                    g2d.drawImage(powerUpSprite[7],(int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE),null);
                    //g2d.drawRect((int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE));
                }
                else if(index == -4){
                    g2d.drawImage(powerUpSprite[1],(int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE),null);
                    //g2d.drawRect((int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE));
                }
                else if(index == -5) {
                    g2d.drawImage(powerUpSprite[5],(int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE),null);
                    //g2d.drawRect((int)(((j * TILES_SIZE) - graphicsContext.getCamX())), (int)(((i * TILES_SIZE) - graphicsContext.getCamY())),(int)(TILES_SIZE),(int)(TILES_SIZE));
                }
            }
        }
    }

}