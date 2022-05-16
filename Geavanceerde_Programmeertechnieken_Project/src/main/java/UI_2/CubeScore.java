package UI_2;

import Game.Components.ScoreComponent;
import Game.Entities.AbstractScore;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CubeScore extends AbstractScore {


    private final GraphicsContext graphicsContext;
    private Font font;

    public CubeScore(GraphicsContext graphicsContext, ScoreComponent scoreComponent){
        super(scoreComponent);
        this.graphicsContext = graphicsContext;
        font = new Font("Courier New",1,20);
    }

    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString("x",graphicsContext.getFrame().getWidth()/20,30);
        String scr = String.valueOf(GetCurrentScore());
        g2d.drawString(scr,graphicsContext.getFrame().getWidth()/15,30);
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(graphicsContext.getFrame().getWidth()/60,10,30,30);
    }
}
