package Game;

import Helper.ConfigFileReader;
import Helper.Levels;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class CollisionComponent {

    private final String configFile;
    private final AbstractHealthBar abstractHealthBar;
    private int currScore = 0;
    private int timesFell = 0;
    private boolean didFall = false;
    private final int[][] levelData;

    public CollisionComponent(String configFile, AbstractTopBar abstractTopBar, AbstractHealthBar abstractHealthBar,int[][] levelData){
        this.levelData = levelData;
        this.configFile = configFile;
        this.abstractHealthBar = abstractHealthBar;
        this.abstractHealthBar.setHealthValue(0);
        currScore = abstractTopBar.getScore();
    }


    public boolean CanMoveHere(float x,float y, float width, float height){
        if(!IsSolid(x,y)){
            if(!IsSolid(x+width,y+height)){
                if(!IsSolid(x+width,y)){
                    if(!IsSolid(x,y+height)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private boolean IsSolid(float x, float y){
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().loadOrCreateConfig(configFile);

        if(x < 0.0 || x >= (Game.TILES_SIZE * Game.TILES_IN_WIDTH)){
            return true;
        }
        if(y < 0.0 || y >= (Game.TILES_SIZE * Game.TILES_IN_HEIGHT)){
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value  =levelData[(int) yIndex][(int)xIndex];


        if(value!=0 && value != 2 && value != 4 && value != 7 && value != -2){return true;}
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
            if(currentTile >= Game.TILES_IN_HEIGHT-1){
                //IF HEALTH VALUE < 5 BECAUSE ELSE YOU DIED
                if(abstractHealthBar.getHealthValue() < 5) {
                    System.out.println(abstractHealthBar.getHealthValue());
                    abstractHealthBar.setHealthValue(timesFell += 1);
                    setDidFall(true);
                }
                else if(abstractHealthBar.getHealthValue() == 5){
                    setDidFall(false);
                    timesFell = 0;
                    abstractHealthBar.setHealthValue(timesFell);
                    //REDRAW LEVEL

                }
            }

            return tileYPos + yOffset - 1;
        }
        else{
            //jumping
            return currentTile * Game.TILES_SIZE;
        }
    }


    public boolean IsEntityOnFloor(int x,int y, int width, int height){
        Rectangle2D.Float hitbox = new Rectangle2D.Float();
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
        //check below bottomleft and bottomright
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height+1)){
            if(!IsSolid(hitbox.x + hitbox.width,hitbox.y + hitbox.height+1)){
                return false;
            }
        }
        return true;
    }


    public void coinCollisionCheck(int x, int y, int width, int height,AbstractTopBar abstractTopBar) {
        Rectangle2D.Float hitbox = new Rectangle2D.Float();
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
        int row = (int) (hitbox.y / Game.TILES_SIZE);
        int col1 = (int) ((hitbox.x+35) / Game.TILES_SIZE);
        int col2 = (int) ((hitbox.x) / Game.TILES_SIZE);
        if ((!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1)) && CanMoveHere(x,y,width,height)) {

            if( levelData[row][col1] == -2){
                abstractTopBar.setScore(currScore+=1);
                levelData[row][col1] = 0;
            }
            if( levelData[row][col2] == -2){
                abstractTopBar.setScore(currScore+=1);
                levelData[row][col2] = 0;
            }

        }

    }

    public boolean isDidFall() {
        return didFall;
    }

    public void setDidFall(boolean didFall) {
        this.didFall = didFall;
    }
}