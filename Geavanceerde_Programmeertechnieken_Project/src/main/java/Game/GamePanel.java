package Game;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Direction.*;
import static utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    public static final int WINDOWSCALE = 1;
    public static final int OBJECTSCALE = 2;
    private Game game;


    public GamePanel(Game game) throws IOException {
        this.game = game;
        //mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        //addMouseListener(new MouseInputs(this));
        //addMouseMotionListener(mouseInputs);
    }



    private void setPanelSize(){
        Dimension size = new Dimension(Game.GAME_WIDTH * WINDOWSCALE,Game.GAME_HEIGHT * WINDOWSCALE);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    public void updateGame(){
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }


    public Game getGame(){
        return game;
    }


}
