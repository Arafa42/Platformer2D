package UI_1;

import Game.Entities.AbstractMenu;

import javax.swing.*;
import java.awt.*;

public class j2dMenu extends AbstractMenu {

    private final GraphicsContext graphicsContext;

    public j2dMenu(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,graphicsContext.getFrame().getWidth(),graphicsContext.getFrame().getHeight());
        g2d.setColor(new Color(238, 39, 39, 255));
        g2d.drawString("2D PLATFORMER",graphicsContext.getFrame().getWidth()/2-180,(graphicsContext.getFrame().getHeight()/2-300));
        for(int i =0;i< getMenuComponent().getOptions().length;i++){
            if(i== getMenuComponent().getCurrentSelection()){
                g2d.setColor(new Color(122, 122, 115, 221));
            }else{
                g2d.setColor(Color.BLACK);
            }
            Font font = new Font("Arial",Font.BOLD,48);
            g2d.setFont(font);
            FontMetrics metrics = g2d.getFontMetrics(font);
            g2d.drawString(getMenuComponent().getOptions()[i],(graphicsContext.getFrame().getWidth()-metrics.stringWidth(getMenuComponent().getOptions()[i]))/2,(graphicsContext.getFrame().getHeight()/2-150)+i*100);
        }


    }

}
