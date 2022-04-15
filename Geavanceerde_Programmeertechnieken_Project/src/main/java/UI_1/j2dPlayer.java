package UI_1;

import Game.AbstractInput;
import Game.AbstractPlayer;
import Game.EntityComponent;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static Helper.Constants.PlayerConstants.GetSpriteAmount;
import static Helper.Constants.PlayerConstants.*;

public class j2dPlayer extends AbstractPlayer {

    private final GraphicsContext graphicsContext;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 5;
    private int playerAction = IDLE;
    EntityComponent m = getEntityComponent();


    public j2dPlayer(GraphicsContext graphicsContext, int x, int y) {
        super(x, y);
        this.graphicsContext = graphicsContext;
        loadAnimations();
    }

    public void update() {
        updateAnimationTick();
        setAnimation();
    }


    @Override
    public void draw() {
        Graphics2D graphics2D = graphicsContext.getG2d();
        graphics2D.drawImage(animations[playerAction][aniIndex], (int) m.x, (int) m.y, (int)(90), (int)(63), null);
        update();
    }


    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    private void setAnimation() {
        int startAni = playerAction;
        if((getDirection() == AbstractInput.Inputs.LEFT)){playerAction = RUNNING_LEFT;}
        else if((getDirection() == AbstractInput.Inputs.RIGHT)){playerAction = RUNNING_RIGHT;}
        else{playerAction = IDLE;}

        if((getDirection() == AbstractInput.Inputs.ATTACKING) && (getDirection() == AbstractInput.Inputs.LEFT)){playerAction = ATTACK_1_LEFT;}
        if((getDirection() == AbstractInput.Inputs.ATTACKING) && (getDirection() == AbstractInput.Inputs.RIGHT)){playerAction = ATTACK_1_RIGHT;}
        if((getDirection() == AbstractInput.Inputs.ATTACKING)){
            playerAction = ATTACK_1_RIGHT;
        }

        if(startAni != playerAction){resetAniTick();}
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }


    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/assets/images/SpriteSheets/Player/Spritesheet.png");
        try {
            assert is != null;
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[8][8];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 48, j * 48, 48, 48);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
