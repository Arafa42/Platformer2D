package UI_2;

import Game.Components.BulletComponent;
import Game.Entities.AbstractBullet;
import java.awt.*;
import java.util.Objects;

public class CubeBullet extends AbstractBullet {
    private final GraphicsContext graphicsContext;
    private Color color;

    public CubeBullet(GraphicsContext graphicsContext, BulletComponent bulletComponent){
        super(bulletComponent);
        this.graphicsContext = graphicsContext;
        importOutsideSprites();
    }


    public void importOutsideSprites(){
        //BULLETS
        if(Objects.equals(GetBulletComponent().getType(), "PLAYER")){
            color = Color.LIGHT_GRAY;
        }
        else if(Objects.equals(GetBulletComponent().getType(), "ENEMY")){
            color = Color.GRAY;
        }
    }


    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        if (GetBulletComponent().isActive()) {
            g2d.setColor(color);
            g2d.drawRect(((int) (getX() - getR()) + 50) - graphicsContext.getCamX(), ((int) (getY() - getR()) + 15) - graphicsContext.getCamY(), GetBulletComponent().getHitboxWidth(), GetBulletComponent().getHitboxHeight());
        }
    }

}
