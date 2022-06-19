import UI_1.j2dFactory;
import Game.AbstractFactory;
import java.io.IOException;
import Game.Game;
import UI_2.CubeFactory;

public class Main {

    public static void main(String args[]) throws IOException {
        //AbstractFactory j2dFactory = new j2dFactory("graphics_config.txt");
        //new Game(j2dFactory,"graphics_config.txt");
        AbstractFactory cubeFactory = new CubeFactory("graphics_config.txt");
        new Game(cubeFactory,"graphics_config.txt");
    }

    public Main(){}


}
