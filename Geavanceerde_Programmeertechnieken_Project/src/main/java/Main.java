import UI_1.j2dFactory;
import Game.AbstractFactory;
import java.io.IOException;
import Game.Game;
import UI_2.CubeFactory;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        AbstractFactory j2dFactory = new j2dFactory("graphics_config.txt");
        Game game = Game.getInstance(j2dFactory,"graphics_config.txt");
        game.run();
        //AbstractFactory cubeFactory = new CubeFactory("graphics_config.txt");
        //Game game2 = Game.getInstance(cubeFactory,"graphics_config.txt");
        //game2.run();
    }

    public Main(){}


}
