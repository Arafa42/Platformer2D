package UI_1;

import Game.AbstractLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Helper.Constants.PlayerConstants.GetSpriteAmount;

public class j2dLevel extends AbstractLevel {

    private GraphicsContext graphicsContext;
    private BufferedImage[] levelSprite;
    private BufferedImage[] coinSprite;
    private int aniTick, aniIndex, aniSpeed = 10;
    private String tile_spritesheet = "/assets/images/SpriteSheets/tilesets.png";
    private String coin_spritesheet = "/assets/images/SpriteSheets/collectibles/coins.png";
    private int TILES_IN_WIDTH, TILES_IN_HEIGHT, TILES_SIZE;

    public j2dLevel(GraphicsContext graphicsContext, int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE) {
        super(tileArray, TILES_IN_HEIGHT, TILES_IN_WIDTH, TILES_SIZE);
        this.TILES_IN_WIDTH = TILES_IN_WIDTH;
        this.TILES_IN_HEIGHT = TILES_IN_HEIGHT;
        this.TILES_SIZE = TILES_SIZE;
        this.graphicsContext = graphicsContext;
        importOutsideSprites();
    }




    public void importOutsideSprites(){
        //TILESET
        BufferedImage img = GetSpriteSheet(tile_spritesheet);
        levelSprite = new BufferedImage[256];
        for(int i =0;i<16;i++){
            for(int j=0;j<16;j++){
                int index = i*16 + j;
                levelSprite[index] = img.getSubimage(j*32,i*32,32,32);
            }
        }


        //COINS
        BufferedImage coins = GetSpriteSheet(coin_spritesheet);
        coinSprite = new BufferedImage[8];
        for(int i=0;i<7;i++){
            coinSprite[i] = coins.getSubimage(16*i,0,16,16);
        }

    }


    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 8) {
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

        //TILEMAP
        for(int i = 0;i<TILES_IN_HEIGHT;i++){
            for(int j =0;j<TILES_IN_WIDTH;j++) {
                int index = getSpriteIndex(j, i);
                if (index != -2) {
                    if (index == 0) {
                        index = 200;
                    }
                    g2d.drawImage(levelSprite[index], (j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(), TILES_SIZE, TILES_SIZE, null);
                }
                if(index == -2){
                        g2d.drawImage(coinSprite[aniIndex],(j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(),TILES_SIZE,TILES_SIZE,null);
                        //g2d.drawRect((j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(),TILES_SIZE,TILES_SIZE);
                }
//                else if(index == -2){
//                    g2d.drawImage(coinSprite[index],(j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(), TILES_SIZE, TILES_SIZE, null);
//                }
            }
        }




    }




}
