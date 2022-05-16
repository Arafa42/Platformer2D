package UI_2;

import Game.Entities.AbstractLevel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CubeLevel extends AbstractLevel {

    //LEVEL MAP
    //  0 = NR 200 ON SPRITESHEET WHICH IS EMPTY
    // -2 = COINS
    // -3 = GROUND ENEMY 1
    // -4 = GROUND ENEMY 2

    private final GraphicsContext graphicsContext;
    private final int TILES_IN_WIDTH, TILES_IN_HEIGHT, TILES_SIZE;

    public CubeLevel(GraphicsContext graphicsContext, int[][] tileArray, int TILES_IN_HEIGHT, int TILES_IN_WIDTH, int TILES_SIZE) {
        super(tileArray);
        this.TILES_IN_WIDTH = TILES_IN_WIDTH;
        this.TILES_IN_HEIGHT = TILES_IN_HEIGHT;
        this.TILES_SIZE = TILES_SIZE;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();

        //TILEMAP
        for(int i = 0;i<TILES_IN_HEIGHT;i++){
            for(int j =0;j<TILES_IN_WIDTH;j++) {
                int index = getSpriteIndex(j, i);
                if (index != -2 && index != -3 && index != -4 && index != -5 && index != -6 && index != -7) {
                    if (index == 0) {
                        index = 200;
                    }
                    g2d.setColor(Color.BLACK);
                    if(index !=200 && index != 2 && index != 4 && index != 7 && index != -2 && index != -3 && index != -4 && index != -5 && index != 3 && index != -6 && index != -7 && index != 64 && index != 100 && index != 101 && index != 102 && index != 116 && index != 117 && index!=118) {
                        g2d.fillRect((j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(), TILES_SIZE, TILES_SIZE);
                    }
                    if(index == 3){
                        g2d.setColor(Color.WHITE);
                        g2d.fillRect((j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(), TILES_SIZE, TILES_SIZE);
                    }
                }
                else if(index == -2){
                        g2d.setColor(Color.YELLOW);
                        g2d.drawRect((j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(),TILES_SIZE,TILES_SIZE);
                }
                else if(index == -3){
                    g2d.setColor(Color.CYAN);
                    g2d.drawRect((j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(),TILES_SIZE,TILES_SIZE);
                }
                else if(index == -4){
                    g2d.setColor(Color.ORANGE);
                    g2d.drawRect((j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(),TILES_SIZE,TILES_SIZE);
                }
                else if(index == -5) {
                    g2d.setColor(Color.RED);
                    g2d.drawRect((j * TILES_SIZE) - graphicsContext.getCamX(), (i * TILES_SIZE) - graphicsContext.getCamY(),TILES_SIZE,TILES_SIZE);
                }
            }
        }
    }

}