package UI_2;

import Game.Components.BulletComponent;
import Game.Entities.AbstractBullet;
import java.awt.*;
import java.util.Objects;

/**
 * CubeBullet class extends AbstractBullet.
 * @author Arafa Yoncalik
 */
public class CubeBullet extends AbstractBullet {
    private final GraphicsContext graphicsContext;
    private Color color;
    private final double scale;

    /**
     *CubeBullet constructor.
     * @param graphicsContext
     * @param bulletComponent
     * @param scale
     */
    public CubeBullet(GraphicsContext graphicsContext, BulletComponent bulletComponent,double scale){
        super(bulletComponent);
        this.scale = scale;
        this.graphicsContext = graphicsContext;
        importOutsideSprites();
    }

    /**
     *Determines colors of the bullets based on entity type.
     */
    public void importOutsideSprites(){
        //BULLETS
        if(Objects.equals(GetBulletComponent().getType(), "PLAYER")){
            color = Color.LIGHT_GRAY;
        }
        else if(Objects.equals(GetBulletComponent().getType(), "ENEMY")){
            color = Color.GRAY;
        }
    }

    /**
     * Does the drawing of the bullets.
     */
    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        if (GetBulletComponent().isActive()) {
            g2d.setColor(color);
            g2d.drawRect(((int) (getX() - getR()) + 50) - graphicsContext.getCamX(), ((int) (getY() - getR()) + 15) - graphicsContext.getCamY(), (int)(GetBulletComponent().getHitboxWidth()*scale),(int)(GetBulletComponent().getHitboxHeight()*scale));
        }
    }

}
