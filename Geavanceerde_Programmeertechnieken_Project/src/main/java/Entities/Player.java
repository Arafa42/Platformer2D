package Entities;

import Game.Game;
import utils.HelpMethods;
import utils.LoadSave;
import java.awt.*;
import java.awt.image.BufferedImage;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 5;
    private int playerAction = DYING;
    private boolean isMoving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    private float xDrawOffset = 23 * Game.SCALE;
    private float yDrawOffset = 8 * Game.SCALE;

    //jump & gravity
    private float airSpeed = 0f;
    private float gravity = 0.05f * Game.SCALE;
    private float jumpSpeed= -3f * Game.SCALE;
    private float fallSpeedAfterCollision = 1f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y,int width,int height){
        super(x,y,width,height);
        loadAnimations();
        initHitBox(x,y,27*Game.SCALE,27*Game.SCALE);
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
         g.drawImage(animations[aniIndex][playerAction], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height,  null);
        //drawHitBox(g);
    }


    public void setRectPos(int x, int y){this.x = x;this.y = y;}

    private void updateAnimationTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation(){
        int startAni = playerAction;
        if(isMoving && isLeft()){playerAction = RUNNING_LEFT;}
        else if(isMoving && isRight()){playerAction = RUNNING_RIGHT;}
        else{playerAction = IDLE;}

        if(attacking && isLeft()){playerAction = ATTACK_1_LEFT;}
        else if(attacking && isRight()){playerAction = ATTACK_1_RIGHT;}
        else if(attacking){
            playerAction = ATTACK_1_RIGHT;
        }

        if(startAni != playerAction){resetAniTick();}
    }

    private void resetAniTick(){
        aniTick = 0;
        aniIndex = 0;
    }


    private void updatePos(){
        isMoving = false;
        if(jump){jump();}
        if(!left && !right && !inAir){return;}
        float xSpeed = 0;
        if(left){xSpeed -=playerSpeed;}
        if(right){xSpeed += playerSpeed;}
        if(!inAir){if(!HelpMethods.IsEntityOnFloor(hitbox,levelData)){inAir = true;}}

        if(inAir){
            if(HelpMethods.CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)){
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            }
            else{
                hitbox.y = HelpMethods.GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if(airSpeed > 0){resetInAir();}
                else{airSpeed = fallSpeedAfterCollision;}
                updateXPos(xSpeed);
            }
        }
        else{updateXPos(xSpeed);}
        isMoving = true;
    }

    private void jump(){
        if(inAir){return;}
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir(){inAir = false;airSpeed = 0;}

    private void updateXPos(float xSpeed){
    if(HelpMethods.CanMoveHere(hitbox.x+xSpeed,hitbox.y, hitbox.width, hitbox.height, levelData)){hitbox.x += xSpeed;}
    else{hitbox.x = HelpMethods.GetEntityPosNextToWall(hitbox, xSpeed);}
}



    private void loadAnimations(){
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
            animations = new BufferedImage[8][8];
            for(int i =0; i<animations.length;i++){
                for(int j=0;j< animations[i].length;j++){
                    animations[i][j] = img.getSubimage(i*48, j*48,48,48);
                }
            }
        }


    public void loadLevelData(int[][] levelData){
            this.levelData= levelData;
            if(!HelpMethods.IsEntityOnFloor(hitbox,levelData)){
                inAir = true;
            }
    }


    public void resetDirectionBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttack(boolean attacking){this.attacking = attacking;}
    public boolean isLeft() {return left;}
    public void setLeft(boolean left) {this.left = left;}
    public boolean isUp() {return up;}
    public void setUp(boolean up) {this.up = up;}
    public boolean isRight() {return right;}
    public void setRight(boolean right) {this.right = right;}
    public boolean isDown() {return down;}
    public void setDown(boolean down) {this.down = down;}
    public boolean isJump(){return jump;}
    public void setJump(boolean jump){this.jump = jump;}
}
