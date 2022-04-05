package Game;

import Entities.Player;

import java.awt.*;
import java.io.IOException;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 60;
    private Player player;

    public Game() throws IOException {
        initAll();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }


    private void initAll(){
        player = new Player(200,200);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void update(){
        player.update();
    }

    public void render(Graphics g){
        player.render(g);
    }


    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timerUpdate =  1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int fps = 0;
        int ups = 0;
        long lastCheck = 0L;
        double deltaU = 0;
        double deltaF = 0;


        while (true){
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                update();
                ups++;
                deltaU--;
            }

            if(deltaF >= 1){
                gamePanel.repaint();
                fps++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >=1000){
                lastCheck = System.currentTimeMillis();
                fps = 0;
                ups = 0;
            }

        }

    }

    public void windowFocusLost(){
        player.resetDirectionBooleans();
    }

    public Player getPlayer(){
        return player;
    }


}
