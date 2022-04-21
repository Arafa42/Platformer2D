package Game;

public class EntityComponent {

    public float x;
    public float y;
    public float hitboxWidth;
    public float hitboxHeight;
    public int healthValue;

    public EntityComponent(int x, int y,int hitboxWidth, int hitboxHeight,int healthValue){
        this.healthValue = healthValue;
        this.x = x;
        this.y = y;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
    }

}
