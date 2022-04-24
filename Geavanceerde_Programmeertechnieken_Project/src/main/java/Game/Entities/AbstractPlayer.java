package Game.Entities;

import Game.AbstractElement;

public abstract class AbstractPlayer extends AbstractElement {

    public AbstractPlayer(int x, int y, int hitboxWidth, int hitboxHeight, float playerSpeed,boolean inAir, float airSpeed, float gravity,float jumpSpeed, float fallSpeedAfterCollision, boolean isMoving){
        super(x,y,hitboxWidth,hitboxHeight,playerSpeed,inAir,airSpeed,gravity,jumpSpeed,fallSpeedAfterCollision,isMoving);
    }
}
