package UI_1;

import Game.AbstractTopBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dTopBar extends AbstractTopBar {


    private final GraphicsContext graphicsContext;
    private Font font;
    private final BufferedImage coin;



    public j2dTopBar(GraphicsContext graphicsContext,int score){
        super(score);
        this.graphicsContext = graphicsContext;
        font = new Font("Courier New",1,20);
        String coinImagePath = "src/main/resources/assets/images/SpriteSheets/collectibles/coin.png";
        coin = this.graphicsContext.loadImages(coinImagePath,30,30, false);
    }

    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString("x",graphicsContext.getFrame().getWidth()/20,30);
        String scr = String.valueOf(getScore());
        g2d.drawString(scr,graphicsContext.getFrame().getWidth()/15,30);
        g2d.drawImage(coin,graphicsContext.getFrame().getWidth()/60,10, null);

    }
}
