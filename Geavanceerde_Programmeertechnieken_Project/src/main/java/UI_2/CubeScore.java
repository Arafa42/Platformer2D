package UI_2;

import Game.Components.ScoreComponent;
import Game.Entities.AbstractScore;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *CubeScore class extends the AbstractScore class
 * @author Arafa Yoncalik
 */
public class CubeScore extends AbstractScore {


    private final GraphicsContext graphicsContext;
    private final Font font;
    private final double scale;

    /**
     * CubeScore constructor
     * @param graphicsContext
     * @param scoreComponent
     * @param scale
     */
    public CubeScore(GraphicsContext graphicsContext, ScoreComponent scoreComponent,double scale){
        super(scoreComponent);
        this.graphicsContext = graphicsContext;
        this.scale = scale;
        font = new Font("Courier New",1,(int)(20*scale));
    }

    /**
     * Does the drawing of the Score.
     */
    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString("x",graphicsContext.getFrame().getWidth()/20,(int)(30*scale));
        String scr = String.valueOf(GetCurrentScore());
        g2d.drawString(scr,graphicsContext.getFrame().getWidth()/15,(int)(30*scale));
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(graphicsContext.getFrame().getWidth()/60,10,(int)(30*scale),(int)(30*scale));
    }
}
