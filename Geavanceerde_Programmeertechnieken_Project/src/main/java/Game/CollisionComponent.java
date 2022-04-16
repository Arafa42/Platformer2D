package Game;

import Helper.ConfigFileReader;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class CollisionComponent {

    String configFile;


    public CollisionComponent(String configFile){
        this.configFile = configFile;
    }


    public boolean CanMoveHere(float x,float y, float width, float height, int[][] levelData){
        if(!IsSolid(x,y,levelData)){
            if(!IsSolid(x+width,y+height,levelData)){
                if(!IsSolid(x+width,y,levelData)){
                    if(!IsSolid(x,y+height,levelData)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private boolean IsSolid(float x, float y, int[][] levelData){
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().loadOrCreateConfig(configFile);

        if(x < 0.0 || x >= data.get("ScreenWidth")){
            return true;
        }
        if(y < 0.0 || y >= data.get("ScreenHeight")){
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value  =levelData[(int) yIndex][(int)xIndex];


        if(value!=0 && value != 2 && value != 4 && value != 7){return true;}
        return false;
    }

    public float GetEntityPosNextToWall(int x, int y, int width, int height, Float xSpeed){
        Rectangle2D.Float hitbox = new Rectangle2D.Float();
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
        if(xSpeed > 0){
            //right
            int tileXpos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            System.out.println(tileXpos + xOffset - 1);
            return tileXpos + xOffset - 1;
        }
        else{
            //left
            return currentTile * Game.TILES_SIZE;
        }

    }

    public float GetEntityYPosUnderRoofOrAboveFloor(int x, int y, int width, int height, Float airSpeed){
        Rectangle2D.Float hitbox = new Rectangle2D.Float();
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        if(airSpeed > 0){
            //falling or touching floor
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        }
        else{
            //jumping
            return currentTile * Game.TILES_SIZE;
        }
    }


    public boolean IsEntityOnFloor(int x,int y, int width, int height, int[][] levelData){
        Rectangle2D.Float hitbox = new Rectangle2D.Float();
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
        //System.out.println(hitbox);
        //check below bottomleft and bottomright
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height+1, levelData)){
            if(!IsSolid(hitbox.x + hitbox.width,hitbox.y + hitbox.height+1, levelData)){
                return false;
            }
        }
        return true;
    }


}
