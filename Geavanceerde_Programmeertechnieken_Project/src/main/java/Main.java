import UI_1.j2dFactory;
import Game.AbstractFactory;
import java.io.IOException;
import Game.Game;

public class Main {

    public static void main(String args[]) throws IOException {
        Main main = new Main();
        main.run();
    }

    public Main(){}
    public void run() throws IOException {
        AbstractFactory a = new j2dFactory("graphics_config.txt");
        new Game(a,"graphics_config.txt");
    }
}
