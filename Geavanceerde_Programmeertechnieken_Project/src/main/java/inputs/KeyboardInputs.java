package inputs;

import Game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> gamePanel.change_Y_Delta(-15);
            case KeyEvent.VK_DOWN -> gamePanel.change_Y_Delta(15);
            case KeyEvent.VK_LEFT -> gamePanel.change_X_Delta(-15);
            case KeyEvent.VK_RIGHT -> gamePanel.change_X_Delta(15);
        }
    }

}
