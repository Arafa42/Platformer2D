package Game;
import Helper.ConfigFileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Game implements Runnable{

    private final AbstractFactory factory;
    private AbstractInput input;
    private AbstractBackground background;
    private ArrayList<Drawable> drawables;
    private static AbstractPlayer player;
    private AbstractLevel level;
    private AbstractTopBar topBar;
    private MovementCompnent movementCompnent;
    private CollisionComponent collisionComponent;

    private final int FPS_SET = 60;
    private final int UPS_SET = 60;
    private Thread gameThread;
    public final static int TILES_DEFAULT_SIZE = 48;
    public final static int TILES_IN_WIDTH = 30;
    public final static int TILES_IN_HEIGHT = 16;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE);
    private int score = 0;

    String configFile;

    int[][] map = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            { 2, 4, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 17, 17, 18, 0, 0, 0, 0, 16, 17, 18, 0, 0, 0, 0, 20, 21, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 33, 33, 34, 0, 0, 0, 0, 32, 33, 34, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 49, 49, 50, 0, 0, 0, 0, 48, 49, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public Game(AbstractFactory abstractFactory,final String configFile) {
        this.factory = abstractFactory;
        this.configFile = configFile;
        initGame();
        startGameLoop();
    }

    private void initGame() {
        input = factory.createInput();
        level = factory.createLevel(map,TILES_IN_HEIGHT,TILES_IN_WIDTH,TILES_SIZE);
        topBar = factory.createTopBar(score);
        player = factory.createPlayer(100, 550,35,35);
        background = factory.createBackground();

        drawables = new ArrayList<Drawable>();
        drawables.add(background);
        drawables.add(level);
        drawables.add(topBar);
        drawables.add(player);



        collisionComponent = new CollisionComponent(configFile,topBar);
        movementCompnent = new MovementCompnent(collisionComponent);

    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().loadOrCreateConfig(configFile);
        factory.setGameDimensions((int)(data.get("ScreenWidth")), (int)(data.get("ScreenHeight")));

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
                AbstractInput.Inputs inputs = input.getInput();
                if (inputs != null) {
                    checkMovement(inputs);
                    player.setDirection(inputs);
                }

                EntityComponent entityComponent = player.getEntityComponent();
                movementCompnent.update(entityComponent,(int)entityComponent.hitboxWidth,(int)entityComponent.hitboxHeight,map);

                ups++;
                deltaU--;
            }

            if(deltaF >= 1){

                for (Drawable drawable : drawables) {
                    drawable.draw();
                }


                factory.render();

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



    private void checkMovement(AbstractInput.Inputs inputs) {
        if (movementCompnent.getIsMoving() && inputs == AbstractInput.Inputs.LEFT) {
            movementCompnent.setLeft(true);
        } else if (movementCompnent.getIsMoving() && inputs == AbstractInput.Inputs.RIGHT) {
            movementCompnent.setRight(true);
        }
        else if(inputs == AbstractInput.Inputs.JUMPING){
            movementCompnent.jump();
        }
        else{
            movementCompnent.setLeft(false);
            movementCompnent.setRight(false);
        }

    }

}



