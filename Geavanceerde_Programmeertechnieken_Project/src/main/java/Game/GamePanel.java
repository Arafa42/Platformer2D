package Game;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;

    public GamePanel() throws IOException {
        mouseInputs = new MouseInputs(this);
        importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() throws IOException {
        InputStream is = getClass().getResourceAsStream("/assets/images/SpriteSheets/player/john_run.png");
        System.out.println(is);
        img  = ImageIO.read(is);
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void change_X_Delta(int value){
        this.xDelta += value;
    }

    public void change_Y_Delta(int value){
        this.yDelta += value;
    }

    public void setRectPos(int x, int y){this.xDelta = x;this.yDelta = y;}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        subImg = img.getSubimage(1*26,0,26,22);
        g.drawImage(subImg, (int)xDelta, (int)yDelta, 52, 44,  null);
    }



    public BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }


}
