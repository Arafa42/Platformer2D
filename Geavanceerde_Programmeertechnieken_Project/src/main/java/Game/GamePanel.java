package Game;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Direction.*;
import static utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 5;
    private int playerAction = DYING;
    private int playerDir = -1;
    public static final int WINDOWSCALE = 1;
    public static final int OBJECTSCALE = 2;
    private boolean isMoving = false;


    public GamePanel() throws IOException {
        mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations(){
        animations = new BufferedImage[8][8];

        for(int i =0; i<animations.length;i++){
            for(int j=0;j< animations[i].length;j++){
                animations[i][j] = img.getSubimage(i*48, j*48,48,48);
            }
        }

    }

    private void importImg() throws IOException {
        InputStream is = getClass().getResourceAsStream("/assets/images/SpriteSheets/Player/Spritesheet.png");
        try{
            img  =  ImageIO.read(is);
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

    private void setPanelSize(){
        Dimension size = new Dimension(1440 * WINDOWSCALE,900 * WINDOWSCALE);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction){
        this.playerDir = direction;
        isMoving = true;
    }

    public void setMoving(boolean moving){
        this.isMoving = moving;
    }

    public void setRectPos(int x, int y){this.xDelta = x;this.yDelta = y;}

    private void updateAnimationTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                    aniIndex = 0;
            }
        }
    }

    private void setAnimation(){
        if(isMoving){
            playerAction = RUNNING;
        }
        else{
            playerAction = IDLE;
        }
    }

    private void updatePos(){
        if(isMoving){
            switch (playerDir){
                case LEFT:
                    xDelta -=5;
                    break;
                case UP:
                    yDelta -=5;
                    break;
                case RIGHT:
                    xDelta +=5;
                    break;
                case DOWN:
                    yDelta +=5;
                    break;

            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePos();
        g.drawImage(animations[aniIndex][playerAction], (int)xDelta, (int)yDelta, 72*OBJECTSCALE, 45*OBJECTSCALE,  null);
    }




}
