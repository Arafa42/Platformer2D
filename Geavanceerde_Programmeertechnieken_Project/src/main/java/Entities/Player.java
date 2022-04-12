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
    private boolean left, up, right, down = false;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    public Player(float x, float y,int width,int height){
        super(x,y,width,height);
        loadAnimations();
        initHitBox(x,y,30*Game.SCALE,32*Game.SCALE);
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[aniIndex][playerAction], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), (int)(72* Game.SCALE), (int)(45*Game.SCALE),  null);
        drawHitBox(g);
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

        if(isMoving){
            playerAction = RUNNING;
        }
        else{
            playerAction = IDLE;
        }

        if(attacking){
            playerAction = ATTACK_1;
        }

        if(startAni != playerAction){
            resetAniTick();
        }

    }

    private void resetAniTick(){
        aniTick = 0;
        aniIndex = 0;
    }


    private void updatePos(){
        isMoving = false;


        if(!left && !right && !up && !down){
            return;
        }

        float xSpeed = 0,ySpeed = 0;

        if(left && !right){
            xSpeed = -playerSpeed;
        }
        else if(right && !left){
            xSpeed = playerSpeed;
        }

        if(up && !down){
            ySpeed = -playerSpeed;
        }
        else if(down && !up){
            ySpeed = playerSpeed;
        }

        if(HelpMethods.CanMoveHere(hitbox.x+xSpeed,hitbox.y+ySpeed, hitbox.width, hitbox.height, levelData)){
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            isMoving = true;
        }

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
    }


    public void resetDirectionBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttack(boolean attacking){
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
