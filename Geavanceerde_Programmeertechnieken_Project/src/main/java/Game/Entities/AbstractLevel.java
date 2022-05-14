package Game.Entities;

import Game.Drawable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractLevel implements Drawable {

    private final int[][] tileMap;

    public AbstractLevel(int[][] tileMap){
        this.tileMap = tileMap;
    }

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

    public int getSpriteIndex(int x, int y){
        return tileMap[y][x];
    }

}
