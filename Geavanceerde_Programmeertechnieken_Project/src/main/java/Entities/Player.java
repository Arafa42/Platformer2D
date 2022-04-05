package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 5;
    private int playerAction = DYING;
    public static final int WINDOWSCALE = 1;
    public static final int OBJECTSCALE = 2;
    private boolean isMoving = false, attacking = false;
    private boolean left, up, right, down = false;
    private float playerSpeed = 2.0f;


    public Player(float x, float y){
        super(x,y);
        loadAnimations();
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[aniIndex][playerAction], (int)x, (int)y, 72*OBJECTSCALE, 45*OBJECTSCALE,  null);
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
        if(left && !right){
            x -= playerSpeed;
            isMoving = true;
        }
        else if(right && !left){
            x += playerSpeed;
            isMoving = true;
        }

        if(up && !down){
            y -= playerSpeed;
            isMoving = true;
        }
        else if(down && !up){
            y += playerSpeed;
            isMoving = true;
        }

    }


    private void loadAnimations(){
        InputStream is = getClass().getResourceAsStream("/assets/images/SpriteSheets/Player/Spritesheet.png");
        try{
            BufferedImage img  =  ImageIO.read(is);
            animations = new BufferedImage[8][8];
            for(int i =0; i<animations.length;i++){
                for(int j=0;j< animations[i].length;j++){
                    animations[i][j] = img.getSubimage(i*48, j*48,48,48);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
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
