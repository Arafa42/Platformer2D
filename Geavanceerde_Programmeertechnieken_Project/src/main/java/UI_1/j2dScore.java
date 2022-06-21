package UI_1;

import Game.Components.ScoreComponent;
import Game.Entities.AbstractScore;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *j2dScore class extends from AbstractScore.
 * @author Arafa Yoncalik
 */
public class j2dScore extends AbstractScore {

    private final GraphicsContext graphicsContext;
    private final Font font;
    private final BufferedImage coin;
    private final double scale;

    /**
     *j2dScore constructor.
     * @param graphicsContext
     * @param scoreComponent
     * @param scale
     */
    public j2dScore(GraphicsContext graphicsContext, ScoreComponent scoreComponent,double scale){
        super(scoreComponent);
        this.graphicsContext = graphicsContext;
        this.scale = scale;
        font = new Font("Courier New",1,(int)(20*scale));
        String coinImagePath = "src/main/resources/assets/images/SpriteSheets/collectibles/coin.png";
        coin = this.graphicsContext.loadImages(coinImagePath,(int)(30*scale),(int)(30*scale), false);
    }

    /**
     *Does the drawing of the score value.
     */
    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString("x",(int)((graphicsContext.getFrame().getWidth()/20)),(int)(30*scale));
        String scr = String.valueOf(GetCurrentScore());
        g2d.drawString(scr,(int)((graphicsContext.getFrame().getWidth()/15)),(int)(30*scale));
        g2d.drawImage(coin,(int)((graphicsContext.getFrame().getWidth()/60)),(int)(10*scale), null);

    }
}
