package Game.Entities;

import Game.Entities.Elements.AbstractElement;

public abstract class AbstractPlayer extends AbstractElement {

    public AbstractPlayer(int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving,int healthValue,int[][] map,int score,double bulletAngle,int bulletSpeed,int screenWidth,int screenHeight,int bulletRadius){
        super(x,y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving,healthValue,map,score,bulletAngle,bulletSpeed,screenWidth,screenHeight,bulletRadius);
    }
}
