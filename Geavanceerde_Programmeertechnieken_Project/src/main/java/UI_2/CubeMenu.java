package UI_2;

import Game.Entities.AbstractMenu;

import java.awt.*;

public class CubeMenu extends AbstractMenu {

    private final GraphicsContext graphicsContext;
    private final double scale;

    public CubeMenu(GraphicsContext graphicsContext,double scale){
        this.graphicsContext = graphicsContext;
        this.scale = scale;
    }

    @Override
    public void draw() {
        Graphics2D g2d = graphicsContext.getG2d();
        Font font = new Font("Arial",Font.BOLD,(int)(48*scale));
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics(font);

        g2d.setColor(Color.white);
        g2d.fillRect(0,0,graphicsContext.getFrame().getWidth(),graphicsContext.getFrame().getHeight());
        g2d.setColor(new Color(238, 39, 39, 255));
        g2d.drawString("CUBE PLATFORMER",(graphicsContext.getFrame().getWidth()-metrics.stringWidth("CUBE PLATFORMER"))/2,(graphicsContext.getFrame().getHeight()/2-(int)(300*scale)));
        for(int i =0;i< getMenuComponent().getOptions().length;i++){
            if(i== getMenuComponent().getCurrentSelection()){
                g2d.setColor(new Color(122, 122, 115, 221));
            }else{
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString(getMenuComponent().getOptions()[i],(graphicsContext.getFrame().getWidth()-metrics.stringWidth(getMenuComponent().getOptions()[i]))/2,(graphicsContext.getFrame().getHeight()/2-(int)(150*scale))+i*(int)(100*scale));
        }


    }

}
