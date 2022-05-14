package Game;
import Game.Components.BulletComponent;
import Game.Components.CollisionComponent;
import Game.Components.MovementComponent;
import Game.Components.PositionComponent;
import Game.Entities.*;
import Game.Systems.*;
import Helper.ConfigFileReader;
import Helper.Levels;
import java.util.ArrayList;
import java.util.HashMap;

public class Game implements Runnable{

    //FACTORY INIT
    private final AbstractFactory factory;
    //ENTITIES INIT
    private AbstractInput input;
    private AbstractBackground background;
    private AbstractPlayer player;
    private ArrayList<AbstractBullet> playerBullets;
    private ArrayList<AbstractBullet> enemyBullets;
    private AbstractHealthBar healthBar;
    private AbstractLevel level;
    private AbstractScore scoreBar;
    private AbstractEnemy enemy;
    private AbstractEnemy enemy2;
    //SYSTEMS INIT
    private PlayerMovementSystem playerMovementSystem;
    private CollisionSystem collisionSystem;
    private CollisionSystem collisionSystem2;
    private CollisionSystem collisionSystem3;
    private PlayerBulletSystem bulletSystem;
    private EnemyBulletSystem bulletSystem2;
    private EnemyMovementSystem enemyMovementSystem;
    private CoinSystem coinSystem;
    private PlayerHealthSystem healthSystem;
    private PowerUpSystem powerUpSystem;
    private EnemyHealthSystem enemyHealthSystem;
    //ARRAYLIST COMPONENTS
    private ArrayList<MovementComponent> movementComponents;
    private ArrayList<PositionComponent> positionComponents;
    private ArrayList<CollisionComponent> collisionComponents;
    //ARRAYLIST ENTITIES
    private ArrayList<AbstractEnemy> enemies;
    //DRAWABLES ARRAY
    private ArrayList<Drawable> drawables;
    //OTHER INITS
    private final int FPS_SET = 60;
    private final int UPS_SET = 60;
    private Thread gameThread;
    int map[][];
    String configFile;
    HashMap<String, Integer> data;
    public final static int TILES_DEFAULT_SIZE = 48;
    public final static int TILES_IN_WIDTH = 30;
    public final static int TILES_IN_HEIGHT = 16;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE);
    private long firingTimer;
    private long firingDelay;
    private enum EnemyType{
        GROUND1,
        GROUND2,
    }

    public Game(AbstractFactory abstractFactory,final String configFile) {
        data = ConfigFileReader.getConfigFileReaderInstance().loadOrCreateConfig(configFile);
        this.factory = abstractFactory;
        this.configFile = configFile;
        initGame();
        startGameLoop();
    }

    private void initGame() {
        movementComponents = new ArrayList<>();
        collisionComponents = new ArrayList<>();
        positionComponents = new ArrayList<>();
        enemies = new ArrayList<>();
        //FIRING BULLET
        firingTimer = System.nanoTime();
        firingDelay = 200;
        //LEVEL & ENTITY DATA
        Levels levels = new Levels();
        this.map = levels.getLevel(1);
        input = factory.createInput();
        level = factory.createLevel(map,TILES_IN_HEIGHT,TILES_IN_WIDTH,TILES_SIZE);
        enemy = factory.createEnemy(800, 450,40,35,1f,false,0f,0.3f,-12f,1f,false,0,map,EnemyType.GROUND2.toString());
        enemy2 = factory.createEnemy(400, 450,40,35,1f,false,0f,0.3f,-12f,1f,false,0,map,EnemyType.GROUND1.toString());
        player = factory.createPlayer(100, 550,30,35,3.0f,false,0f,0.3f,-12f,1f,false,0,map,0,270,5,data.get("ScreenWidth"),data.get("ScreenHeight"),2);
        playerBullets = new ArrayList<AbstractBullet>();
        enemyBullets = new ArrayList<AbstractBullet>();
        scoreBar = factory.createScoreBar(player.getScoreComponent());
        healthBar = factory.createHealthBar(player.getHealthComponent());
        background = factory.createBackground();
        //DRAWABLES
        drawables = new ArrayList<Drawable>();
        drawables.add(background);
        drawables.add(level);
        drawables.add(scoreBar);
        drawables.add(healthBar);
        drawables.add(player);
        drawables.addAll(playerBullets);
        drawables.addAll(enemyBullets);
        drawables.add(enemy);
        drawables.add(enemy2);
        //ARRAYLIST ADD COMPONENTS
        movementComponents.add(enemy.getMovementComponent());
        movementComponents.add(enemy2.getMovementComponent());
        collisionComponents.add(enemy.getCollisionComponent());
        collisionComponents.add(enemy2.getCollisionComponent());
        positionComponents.add(enemy.getPositionComponent());
        positionComponents.add(enemy2.getPositionComponent());
        //SYSTEMS
        collisionSystem = new CollisionSystem(player.getCollisionComponent(),player.getPositionComponent(),player.getMovementComponent());
        collisionSystem2 = new CollisionSystem(enemy.getCollisionComponent(),enemy.getPositionComponent(),enemy.getMovementComponent());
        collisionSystem3 = new CollisionSystem(enemy2.getCollisionComponent(),enemy2.getPositionComponent(),enemy2.getMovementComponent());
        playerMovementSystem = new PlayerMovementSystem(player.getMovementComponent(),player.getPositionComponent());
        bulletSystem = new PlayerBulletSystem(playerBullets);
        enemyMovementSystem  = new EnemyMovementSystem(collisionComponents,positionComponents,movementComponents);
        System.out.println(enemy.getMovementComponent().isRight());
        coinSystem = new CoinSystem(player.getCollisionComponent(),player.getScoreComponent(),player.getPositionComponent());
        powerUpSystem = new PowerUpSystem(player.getCollisionComponent(),player.getPositionComponent(),player.getMovementComponent(),player.getHealthComponent());
        //ADD ENEMIES
        enemies.add(enemy);
        enemies.add(enemy2);
        healthSystem = new PlayerHealthSystem(enemies,player,enemyBullets);
        enemyHealthSystem = new EnemyHealthSystem(playerBullets,enemies);
        bulletSystem2 = new EnemyBulletSystem(enemyBullets,enemies,data.get("ScreenWidth"),data.get("ScreenHeight"),drawables, factory,player);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
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
                //DEAD CHECK (IF PLAYER LOST 5 HEARTS => RESET LEVEL)
                if(player.getHealthComponent().getHealthValue() == 5){initGame();}
                //INPUTS
                AbstractInput.Inputs inputs = input.getInput();
                if (inputs != null) {checkMovement(inputs);player.setDirection(inputs);}
                //SYSTEMS UPDATE
                systemsUpdate();

                ups++;
                deltaU--;
            }
            if(deltaF >= 1){
                //DRAW
                for (Drawable drawable : drawables) {drawable.draw();}
                //RENDER
                factory.render();
                //System.out.println(fps);
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

    private void systemsUpdate(){
        collisionSystem.updateCollision();
        collisionSystem2.updateCollision();
        collisionSystem3.updateCollision();
        powerUpSystem.update();
        playerMovementSystem.update();
        coinSystem.update();
        bulletSystem.update();
        bulletSystem2.update();
        enemyMovementSystem.update();
        healthSystem.update();
        enemyHealthSystem.update();
    }


    private void checkMovement(AbstractInput.Inputs inputs) {
        if (player.getMovementComponent().isMoving() && inputs == AbstractInput.Inputs.LEFT) {
            player.getMovementComponent().setLeft(true);
        }
        else if (player.getMovementComponent().isMoving() && inputs == AbstractInput.Inputs.RIGHT) {
            player.getMovementComponent().setRight(true);
        }
        else if(inputs == AbstractInput.Inputs.JUMPING){
            player.getMovementComponent().setJump(true);
        }
        else if(inputs == AbstractInput.Inputs.ATTACKING){
            //FIRE BULLETS
            long elapsed = (System.nanoTime() - firingTimer) / 1000000;
            if(elapsed > firingDelay){
                playerBullets.add(factory.createBullet(new BulletComponent(player.getPositionComponent().x,player.getPositionComponent().y, 25,16,270,5,data.get("ScreenWidth"),data.get("ScreenHeight"),2)));
                firingTimer = System.nanoTime();
                //System.out.println(playerBullets.size());
                drawables.addAll(playerBullets);
            }
        }
        else{
            player.getMovementComponent().setLeft(false);
            player.getMovementComponent().setRight(false);
        }
    }

}