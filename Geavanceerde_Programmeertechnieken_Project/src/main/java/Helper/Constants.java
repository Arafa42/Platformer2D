package Helper;

public class Constants {

    public static class PlayerConstants{
        public static final int RUNNING_LEFT = 2;
        public static final int RUNNING_RIGHT = 3;
        public static final int IDLE = 0;
        public static final int DYING = 7;
        public static final int ATTACK_1_LEFT = 4;
        public static final int ATTACK_1_RIGHT = 5;

        public static int GetSpriteAmount(int player_action){
            switch (player_action){
                case IDLE:
                case ATTACK_1_LEFT:
                case ATTACK_1_RIGHT:
                    return 5;
                case DYING:
                case RUNNING_LEFT:
                case RUNNING_RIGHT:
                    return 8;
                default:
                    return 1;
            }
        }

    }
}
