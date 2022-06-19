package Game.Components;

public class EnemyComponent {

    private Boolean isActive;
    private final int areaHitboxWidth;
    private final int areaHitboxHeight;
    private float areaX;
    private float areaY;

    public EnemyComponent(Boolean isActive,int areaHitboxWidth,int areaHitboxHeight){
        this.isActive = isActive;
        this.areaHitboxWidth = areaHitboxWidth;
        this.areaHitboxHeight = areaHitboxHeight;
    }

    public Boolean getActive() {return isActive;}
    public void setActive(Boolean active) {isActive = active;}
    public int getAreaHitboxWidth() {return areaHitboxWidth;}
    public void setAreaHitboxWidth(int areaHitboxWidth) {}
    public int getAreaHitboxHeight() {return areaHitboxHeight;}
    public void setAreaHitboxHeight(int areaHitboxHeight) {}
    public float getAreaX() {return areaX;}
    public void setAreaX(float areaX) {this.areaX = areaX;}
    public float getAreaY() {return areaY;}
    public void setAreaY(float areaY) {this.areaY = areaY;}
}
