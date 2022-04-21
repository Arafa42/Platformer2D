package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public abstract class AbstractLevel implements Drawable {

    private int[][] tileMap;
    private final int TILES_IN_WIDTH;
    private final int TILES_IN_HEIGHT;
    private final int TILES_SIZE;


    public AbstractLevel(int[][] tileMap, int TILES_IN_WIDTH, int TILES_IN_HEIGHT, int TILES_SIZE){
        this.tileMap = tileMap;
        this.TILES_IN_WIDTH = TILES_IN_WIDTH;
        this.TILES_IN_HEIGHT = TILES_IN_HEIGHT;
        this.TILES_SIZE = TILES_SIZE;
    }

    public BufferedImage GetSpriteSheet(String fileName) {
        BufferedImage img = null;
        InputStream is = AbstractLevel.class.getResourceAsStream(fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }


    public int getSpriteIndex(int x, int y){
        return tileMap[y][x];
    }

    public void setTileMap(int[][] tileMap) {this.tileMap = tileMap;}
    public int[][] getTileMap() {return tileMap;}

}
