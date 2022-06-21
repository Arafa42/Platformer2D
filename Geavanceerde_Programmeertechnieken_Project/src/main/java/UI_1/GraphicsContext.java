package UI_1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *GraphicsContext.
 * @author Arafa Yoncalik
 */
public class GraphicsContext {
    private int ScreenWidth;
    private int ScreenHeight;
    private double defaultScaleWidth = 1280.0;
    private double defaultScaleHeight = 800.0;
    private int camX = 0;
    private int camY = 0;
    private JFrame frame;
    private JPanel panel;
    private BufferedImage g2dimage;
    private Graphics2D g2d;
    private int size;
    private double scaleX, scaleY;
    private int viewPortX = 1008;
    private int viewPortY = 800;
    private int offsetMaxX = 2016 - viewPortX;
    private int offsetMaxY = 800 - viewPortY;
    private int offsetMinX = 0;
    private int offsetMinY = 0;

    /**
     *resizeImage() function resizes the image.
     * @param originalImage
     * @param targetWidth
     * @param targetHeight
     * @return returns the resized image
     */
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    /**
     *loadImages function().
     * @param path
     * @param width
     * @param height
     * @param sprite
     * @return returns loaded and resized image.
     */
    public BufferedImage loadImages(String path, int width, int height, boolean sprite) {
        BufferedImage image = null;
        BufferedImage resizedImage = null;
        try {
            image = ImageIO.read(new File(path));
            resizedImage = sprite ? resizeImage(image, size * width, size * height) : resizeImage(image, width, height);
        } catch (IOException e) {
            System.out.println("Unable to load or resize (specific) image: " + path);
        }
        return resizedImage;
    }

    /**
     *GraphicsContext constructor.
     * @param screenWidth
     * @param screenHeight
     */
    public GraphicsContext(int screenWidth, int screenHeight) {
        ScreenWidth = screenWidth;
        ScreenHeight = screenHeight;
        frame = new JFrame();
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        frame.setFocusable(true);
        frame.add(panel);
        frame.setTitle("Platformer Game 1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(ScreenWidth, ScreenHeight);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     *render function calls the repaint function of the jPanel.
     */
    public void render() {panel.repaint();}

    /**
     * doDrawing() function does the drawing of the images.
     * @param g
     */
    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();

        if(g2dimage==null){
            return;
        }
        graph2d.drawImage(g2dimage, 0, 0,g2dimage.getWidth(),g2dimage.getHeight(),null );   // copy buffered image
        graph2d.translate(-camX,-camY);
        graph2d.dispose();
    }

    /**
     * sets the game dimensions.
     * @param GameCellsX
     * @param GameCellsY
     */
    public void setGameDimensions(int GameCellsX, int GameCellsY) {
        size = Math.min(ScreenWidth/GameCellsX, ScreenHeight/GameCellsY);
        scaleX = GameCellsX/defaultScaleWidth;
        scaleY = GameCellsY/defaultScaleHeight;
        frame.setLocation(50,50);
        frame.setSize(ScreenWidth, ScreenHeight);
        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
    }


    public Graphics2D getG2d() {return g2d;}
    public JFrame getFrame() {return frame;}
    public int getSize() {return size;}
    public double getScaleX(){return scaleX;}
    public double getScaleY(){return scaleY;}
    public int getCamX() {return camX;}
    public void setCamX(int camX) {this.camX = camX;}
    public int getCamY() {return camY;}
    public void setCamY(int camY) {this.camY = camY;}
    public int getOffsetMaxX() {return offsetMaxX;}
    public void setOffsetMaxX(int offsetMaxX) {this.offsetMaxX = offsetMaxX;}
    public int getOffsetMaxY() {return offsetMaxY;}
    public void setOffsetMaxY(int offsetMaxY) {this.offsetMaxY = offsetMaxY;}
    public int getOffsetMinX() {return offsetMinX;}
    public void setOffsetMinX(int offsetMinX) {this.offsetMinX = offsetMinX;}
    public int getOffsetMinY() {return offsetMinY;}
    public void setOffsetMinY(int offsetMinY) {this.offsetMinY = offsetMinY;}
    public int getViewPortX() {return viewPortX;}
    public void setViewPortX(int viewPortX) {this.viewPortX = viewPortX;}
    public int getViewPortY() {return viewPortY;}
    public void setViewPortY(int viewPortY) {this.viewPortY = viewPortY;}


}
