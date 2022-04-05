package utils;

import java.awt.image.BufferedImage;

public class Constants {

    public static class Direction{
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
    }


    public static class PlayerConstants{
        public static final int RUNNING = 3;
        public static final int IDLE = 0;
        public static final int DYING = 7;
        public static final int ATTACK_1 = 5;

        public static int GetSpriteAmount(int player_action){
            switch (player_action){
                case IDLE:
                case ATTACK_1:
                    return 5;
                case DYING:
                case RUNNING:
                    return 8;
                default:
                    return 1;
            }
        }

    }


}
