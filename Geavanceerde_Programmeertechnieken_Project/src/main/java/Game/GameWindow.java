package Game;

import javax.swing.*;

public class GameWindow extends JFrame {

    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel){

        jFrame = new JFrame();
        jFrame.setSize(1920, 1080);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.add(gamePanel);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }

}
