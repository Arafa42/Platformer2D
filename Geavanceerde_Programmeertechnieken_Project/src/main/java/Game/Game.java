package Game;
import Helper.ConfigFileReader;
import Helper.Levels;
import java.util.ArrayList;
import java.util.HashMap;

public class Game implements Runnable{

    private final AbstractFactory factory;
    private AbstractInput input;
    private AbstractBackground background;
    private ArrayList<Drawable> drawables;
    private AbstractPlayer player;
    private ArrayList<AbstractBullet> bullets;
    private AbstractHealthBar healthBar;
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
    int map[][];
    String configFile;

    private long firingTimer;
    private long firingDelay;


    public Game(AbstractFactory abstractFactory,final String configFile) {
        this.factory = abstractFactory;
        this.configFile = configFile;
        initGame();
        startGameLoop();
    }

    private void initGame() {
        HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().loadOrCreateConfig(configFile);

        firingTimer = System.nanoTime();
        firingDelay = 200;

        Levels levels = new Levels();
        this.map = levels.getLevel(1);
        input = factory.createInput();
        level = factory.createLevel(map,TILES_IN_HEIGHT,TILES_IN_WIDTH,TILES_SIZE);
        topBar = factory.createTopBar(score);
        player = factory.createPlayer(100, 550,30,35,0);
        bullets = new ArrayList<AbstractBullet>();
        healthBar = factory.createHealthBar();
        background = factory.createBackground();

        drawables = new ArrayList<Drawable>();
        drawables.add(background);
        drawables.add(level);
        drawables.add(topBar);
        drawables.add(healthBar);
        drawables.add(player);
        drawables.addAll(bullets);

        collisionComponent = new CollisionComponent(configFile,topBar,healthBar,map);
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

                statusCheck();

                AbstractInput.Inputs inputs = input.getInput();
                if (inputs != null) {
                    checkMovement(inputs);
                    System.out.println(bullets.size());
                    player.setDirection(inputs);
                }

                EntityComponent entityComponent = player.getEntityComponent();
                movementCompnent.update(entityComponent,(int)entityComponent.hitboxWidth,(int)entityComponent.hitboxHeight,topBar);


                for(int i =0;i < bullets.size();i++){
                    boolean remove = bullets.get(i).update();
                    if(remove){
                        bullets.remove(i);
                        i--;
                    }
                }

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

    private void statusCheck(){
        //FELL ON GROUND
        //ALSO IN COLLISION CLASS A CHECK WHEN FELL ON GROUND
        if((healthBar.getHealthValue() > 0 && healthBar.getHealthValue() < 6) && collisionComponent.isDidFall()){
            movementCompnent.resetPosition(player.getEntityComponent());
            collisionComponent.setDidFall(false);
        }
        //DEAD -> RESET LEVEL
        if(healthBar.getHealthValue() == 5){
            initGame();
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
        else if(inputs == AbstractInput.Inputs.ATTACKING){
            long elapsed = (System.nanoTime() - firingTimer) / 1000000;
            if(elapsed > firingDelay){
                HashMap<String, Integer> data = ConfigFileReader.getConfigFileReaderInstance().loadOrCreateConfig(configFile);
                bullets.add(factory.createBullet(270,(int)player.getEntityComponent().x,(int)player.getEntityComponent().y,data.get("ScreenWidth"),data.get("ScreenHeight")));
                firingTimer = System.nanoTime();
                drawables.addAll(bullets);
            }
        }
        else{
            movementCompnent.setLeft(false);
            movementCompnent.setRight(false);
        }

    }

}



