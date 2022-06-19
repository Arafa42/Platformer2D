package Game;
import Game.Components.*;
import Game.Entities.*;
import Game.Systems.*;
import Helper.ConfigFileReader;
import Helper.Levels;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Game {
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
    private AbstractMenu menu;
    //SYSTEMS INIT
    private PlayerMovementSystem playerMovementSystem;
    private CollisionSystem collisionSystem;
    private CollisionSystem collisionSystem2;
    private CollisionSystem collisionSystem3;
    private PlayerBulletSystem bulletSystem;
    private EnemyBulletSystem bulletSystem2;
    private EnemyMovementSystem enemyMovementSystem;
    private LevelSystem levelSystem;
    private CoinSystem coinSystem;
    private PlayerHealthSystem healthSystem;
    private PowerUpSystem powerUpSystem;
    private EnemyHealthSystem enemyHealthSystem;
    private InputSystem inputSystemM;
    private InputSystem inputSystemG;

    //ARRAYLIST COMPONENTS
    private ArrayList<MovementComponent> movementComponents;
    private ArrayList<PositionComponent> positionComponents;
    private ArrayList<CollisionComponent> collisionComponents;
    //ARRAYLIST ENTITIES
    private ArrayList<AbstractEnemy> enemies;
    //DRAWABLES ARRAY
    private ArrayList<Drawable> drawables;
    private ArrayList<Drawable> menuDrawables;
    //LEVEL
    Levels levels;
    //BG LAYERS
    private String bgLayer1;
    private String bgLayer2;
    //OTHER INITS
    private final int FPS_SET = 60;
    private final int UPS_SET = 60;
    private Thread gameThread;
    private boolean inMenu = true;
    int map[][];
    String configFile;
    HashMap<String, Integer> data;
    public final static int TILES_DEFAULT_SIZE = 48;
    public final static int TILES_IN_WIDTH = 42;
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
        initMenu();
        //initGame(1);
        //startGameLoop();
    }

    private void initMenu(){
        SoundSystem.volume = SoundSystem.Volume.LOW;
        SoundSystem.MENUMUSIC.play(true);
        input = factory.createInput(new InputComponent());
        player = factory.createPlayer(100, 550,30,35,3.0f,false,0f,0.3f,-12f,1f,false,0,map,0,270,5,data.get("ScreenWidth"),data.get("ScreenHeight"),2);
        inputSystemM = new InputSystem(input.getInputComponent(),player,input.getPressedKeyInps());
        menu = factory.createMenu();
        menuDrawables = new ArrayList<Drawable>();
        menuDrawables.add(menu);
        //startGameLoop();

    }

    private void initGame(int levelToLoad) {

        SoundSystem.volume = SoundSystem.Volume.LOW;
        if(levelToLoad == 1){
            bgLayer1 = "src/main/resources/assets/images/SpriteSheets/background/sky_cloud.png";
            bgLayer2 = "src/main/resources/assets/images/SpriteSheets/background/mountain2.png";
            SoundSystem.LEVEL1.stopAllPlayingSounds();
            SoundSystem.LEVEL1.play(true);
        }
        else if(levelToLoad == 2){
            bgLayer1 = "src/main/resources/assets/images/SpriteSheets/background/level2/sky_lightened.png";
            bgLayer2 = "src/main/resources/assets/images/SpriteSheets/background/level2/glacial_mountains.png";
            SoundSystem.LEVEL2.stopAllPlayingSounds();
            SoundSystem.LEVEL2.play(true);
        }
        else if(levelToLoad == 3){
            bgLayer1 = "src/main/resources/assets/images/SpriteSheets/background/level3/far-buildings.png";
            bgLayer2 = "src/main/resources/assets/images/SpriteSheets/background/level3/foreground.png";
            SoundSystem.LEVEL3.stopAllPlayingSounds();
            SoundSystem.LEVEL3.play(true);
        }

        movementComponents = new ArrayList<>();
        collisionComponents = new ArrayList<>();
        positionComponents = new ArrayList<>();
        enemies = new ArrayList<>();
        //FIRING BULLET
        firingTimer = System.nanoTime();
        firingDelay = 200;
        //LEVEL & ENTITY DATA
        levels = new Levels();
        this.map = levels.getLevel(levelToLoad);
        //ENEMY COORDINATES CHECK
        enemyCoordsCheck();
        level = factory.createLevel(map,TILES_IN_HEIGHT,TILES_IN_WIDTH,TILES_SIZE);
        player = factory.createPlayer(100, 550,30,35,3.0f,false,0f,0.3f,-12f,1f,false,0,map,0,270,5,data.get("ScreenWidth"),data.get("ScreenHeight"),2);
        player.getLevelComponent().setLevelToLoad(levelToLoad);
        input = factory.createInput(input.getInputComponent());
        playerBullets = new ArrayList<AbstractBullet>();
        enemyBullets = new ArrayList<AbstractBullet>();
        scoreBar = factory.createScoreBar(player.getScoreComponent());
        healthBar = factory.createHealthBar(player.getHealthComponent());
        background = factory.createBackground(bgLayer1,bgLayer2);
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
        inputSystemG = new InputSystem(input.getInputComponent(),player,input.getPressedKeyInps());
        collisionSystem = new CollisionSystem(player.getCollisionComponent(),player.getPositionComponent(),player.getMovementComponent());
        collisionSystem2 = new CollisionSystem(enemy.getCollisionComponent(),enemy.getPositionComponent(),enemy.getMovementComponent());
        collisionSystem3 = new CollisionSystem(enemy2.getCollisionComponent(),enemy2.getPositionComponent(),enemy2.getMovementComponent());
        playerMovementSystem = new PlayerMovementSystem(player.getMovementComponent());
        bulletSystem = new PlayerBulletSystem(playerBullets);
        enemyMovementSystem  = new EnemyMovementSystem(collisionComponents,positionComponents,movementComponents);
        coinSystem = new CoinSystem(player.getCollisionComponent(),player.getScoreComponent(),player.getPositionComponent());
        powerUpSystem = new PowerUpSystem(player.getCollisionComponent(),player.getPositionComponent(),player.getMovementComponent(),player.getHealthComponent());
        //ADD ENEMIES
        enemies.add(enemy);
        enemies.add(enemy2);
        healthSystem = new PlayerHealthSystem(enemies,player,enemyBullets);
        enemyHealthSystem = new EnemyHealthSystem(playerBullets,enemies);
        bulletSystem2 = new EnemyBulletSystem(enemyBullets,enemies,data.get("ScreenWidth"),data.get("ScreenHeight"),drawables, factory,player);
        levelSystem = new LevelSystem(player);
    }



    private void enemyCoordsCheck(){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j] == -6){enemy = factory.createEnemy(j*48, i*48,40,35,1f,false,0f,0.3f,-12f,1f,false,0,map,EnemyType.GROUND2.toString());}
                if(map[i][j] == -7){enemy2 = factory.createEnemy(j*48, i*48,40,35,1f,false,0f,0.3f,-12f,1f,false,0,map,EnemyType.GROUND1.toString());}
            }
        }
    }

    public void run() throws InterruptedException {
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
                if(!inMenu) {
                    //CHECK FOR LEVEL CHANGE
                    if (player.getLevelComponent().isSwitchLevel()) {
                        player.getLevelComponent().setSwitchLevel(false);
                        initGame(player.getLevelComponent().getLevelToLoad());
                    }
                    //DEAD CHECK (IF PLAYER LOST 5 HEARTS => RESET LEVEL)
                    if (player.getHealthComponent().getHealthValue() == 5) {
                        SoundSystem.volume = SoundSystem.Volume.HIGH;
                        SoundSystem.PLAYERDEAD.play(false);
                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        initGame(player.getLevelComponent().getLevelToLoad());
                    }
                    //INPUTS
                    if(input.inputAvailable()) {
                       checkMovement();
                       player.setDirection(AbstractInput.Inputs.IDLE);
                    }
                    //SYSTEMS UPDATE
                    systemsUpdate();
                }
                else{
                    if(input.inputAvailable()) {
                        inputSystemM.update();
                        checkMenuInput();
                    }
                }
                ups++;
                deltaU--;
            }
            if(deltaF >= 1){
                if(!inMenu) {
                    //DRAW
                    for (Drawable drawable : drawables) {
                        drawable.draw();
                    }
                }
                else{
                    for(Drawable drawable:menuDrawables){
                        drawable.draw();
                    }
                }
                //RENDER
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

    private void systemsUpdate(){
        inputSystemG.update();
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
        levelSystem.update();
    }


    private void checkMovement() {
                if (input.getInputComponent().isAttacking()) {
                    //FIRE BULLETS
                    long elapsed = (System.nanoTime() - firingTimer) / 1000000;
                    if (elapsed > firingDelay) {
                        SoundSystem.volume = SoundSystem.Volume.HIGH;
                        SoundSystem.PLAYERBULLET.play(false);
                        if(player.getMovementComponent().isLeft()){
                            playerBullets.add(factory.createBullet(new BulletComponent("PLAYER", player.getPositionComponent().x-100, player.getPositionComponent().y, 25, 16, -270, 5, data.get("ScreenWidth"), data.get("ScreenHeight"), 2)));
                        }
                        else if(player.getMovementComponent().isRight()){
                            playerBullets.add(factory.createBullet(new BulletComponent("PLAYER", player.getPositionComponent().x, player.getPositionComponent().y, 25, 16, 270, 5, data.get("ScreenWidth"), data.get("ScreenHeight"), 2)));
                        }
                        else{
                            playerBullets.add(factory.createBullet(new BulletComponent("PLAYER", player.getPositionComponent().x, player.getPositionComponent().y, 25, 16, 270, 5, data.get("ScreenWidth"), data.get("ScreenHeight"), 2)));
                        }
                        firingTimer = System.nanoTime();
                        drawables.addAll(playerBullets);
                    }
                }
                if (input.getInputComponent().isEscape()) {
                    SoundSystem.LEVEL1.stopAllPlayingSounds();
                    inMenu = true;
                    initMenu();
                    try {
                        Thread.sleep(120);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
    }

    private void checkMenuInput(){
                if (input.getInputComponent().isDown()) {
                    if (menu.getMenuComponent().getCurrentSelection() < 2) {
                        menu.getMenuComponent().setCurrentSelection(menu.getMenuComponent().getCurrentSelection() + 1);
                        try {
                            Thread.sleep(120);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (input.getInputComponent().isUp()) {
                    if (menu.getMenuComponent().getCurrentSelection() > 0) {
                        menu.getMenuComponent().setCurrentSelection(menu.getMenuComponent().getCurrentSelection() - 1);
                        try {
                            Thread.sleep(120);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (input.getInputComponent().isEnter()) {
                    String currOpt = (menu.getMenuComponent().getOptions()[menu.getMenuComponent().getCurrentSelection()]);
                    if (Objects.equals(currOpt, "PLAY")) {
                        SoundSystem.volume = SoundSystem.Volume.HIGH;
                        SoundSystem.BUTTONCLICK.play(false);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        inMenu = false;
                        initGame(1);
                    } else if (Objects.equals(currOpt, "HELP")) {
                        SoundSystem.volume = SoundSystem.Volume.HIGH;
                        SoundSystem.BUTTONCLICK.play(false);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("HELP IS ON THE WAY...");
                    } else if (Objects.equals(currOpt, "QUIT")) {
                        SoundSystem.volume = SoundSystem.Volume.HIGH;
                        SoundSystem.BUTTONCLICK.play(false);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.exit(1);
                    }
                }
    }

}