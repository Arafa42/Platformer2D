package Game.Components;

/**
 *PositionComponent class.
 * @author Arafa Yoncalik
 */
public class PositionComponent {

    public float x;
    public float y;
    public float hitboxWidth;
    public float hitboxHeight;

    /**
     *PositionComponent constructor.
     * @param x
     * @param y
     * @param hitboxWidth
     * @param hitboxHeight
     */
    public PositionComponent(int x, int y, int hitboxWidth, int hitboxHeight){
        this.x = x;
        this.y = y;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
    }

    public float getX() {return x;}
    public void setX(float x) {this.x = x;}
    public float getY() {return y;}
    public void setY(float y) {this.y = y;}
}
