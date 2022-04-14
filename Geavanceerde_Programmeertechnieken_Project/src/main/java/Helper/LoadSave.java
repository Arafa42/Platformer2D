package Helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;


public class LoadSave {

//    public static final String PLAYER_ATLAS = "/assets/images/SpriteSheets/Player/Spritesheet.png";
//    public static final String LEVEL_ATLAS = "/assets/images/SpriteSheets/tilesets.png";
//
//
//    public static BufferedImage GetSpriteAtlas(String fileName) {
//        BufferedImage img = null;
//        InputStream is = LoadSave.class.getResourceAsStream(fileName);
//        try {
//            img = ImageIO.read(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return img;
//    }
//
//
//    public static int[][] GetLevelData(){
//        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
//
//        int[][] map = {
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                { 2, 4, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 17, 17, 18, 0, 0, 0, 0, 16, 17, 18, 0, 0, 0, 0, 20, 21, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 33, 33, 34, 0, 0, 0, 0, 32, 33, 34, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                { 49, 49, 50, 0, 0, 0, 0, 48, 49, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//        };
//
//        for (int x = 0; x < map.length; x++) {
//            for (int y = 0; y < map[x].length; y++) {
//                if(map[x][y]==0){
//                    levelData[x][y] = 200;
//                }
//                else{
//                    levelData[x][y] = map[x][y];
//                }
//
//            }
//        }
//
//
//        return levelData;
//    }

}
