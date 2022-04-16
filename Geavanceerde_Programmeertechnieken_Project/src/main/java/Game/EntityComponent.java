package Game;

public class EntityComponent {

    public float x;
    public float y;
    public float hitboxWidth;
    public float hitboxHeight;
    public float dx;
    public float dy;

    public EntityComponent(int x, int y,int hitboxWidth, int hitboxHeight){
        this.x = x;
        this.y = y;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
    }

}
