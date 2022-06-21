package Game.Entities;

import Game.Drawable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 *AbstractLevel class implements the Drawable interface.
 * @author Arafa Yoncalik
 */
public abstract class AbstractLevel implements Drawable {

    private final int[][] tileMap;

    /**
     *Abstractlevel constructor.
     * @param tileMap
     */
    public AbstractLevel(int[][] tileMap){
        this.tileMap = tileMap;
    }

    /**
     * GetSpriteSheet function.
     * @param fileName
     * @return returns BufferedImage of filename.
     */
    public BufferedImage GetSpriteSheet(String fileName) {
        BufferedImage img = null;
        InputStream is = AbstractLevel.class.getResourceAsStream(fileName);
        try {
            assert is != null;
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    /**
     * GetSpriteSheetIndex function.
     * @param x
     * @param y
     * @return returns index of tilemap.
     */
    public int getSpriteIndex(int x, int y){
        return tileMap[y][x];
    }

}
