package Game.Components;

/**
 *InputComponent class
 * @author Arafa Yoncalik
 */
public class InputComponent {
    private boolean left,right,jumping,attacking,idle,up,down,enter,escape;

    public boolean isLeft() {return left;}
    public void setLeft(boolean left) {this.left = left;}
    public boolean isRight() {return right;}
    public void setRight(boolean right) {this.right = right;}
    public boolean isJumping() {return jumping;}
    public void setJumping(boolean jumping) {this.jumping = jumping;}
    public boolean isAttacking() {return attacking;}
    public void setAttacking(boolean attacking) {this.attacking = attacking;}
    public boolean isIdle() {return idle;}
    public void setIdle(boolean idle) {this.idle = idle;}
    public boolean isUp() {return up;}
    public void setUp(boolean up) {this.up = up;}
    public boolean isDown() {return down;}
    public void setDown(boolean down) {this.down = down;}
    public boolean isEnter() {return enter;}
    public void setEnter(boolean enter) {this.enter = enter;}
    public boolean isEscape() {return escape;}
    public void setEscape(boolean escape) {this.escape = escape;}
}
