package Game.Components;

/**
 *HealthComponent class
 * @author Arafa Yoncalik
 */
public class HealthComponent {
    private int healthValue;

    /**
     *HealthComponent constructor.
     * @param healthValue
     */
    public HealthComponent(int healthValue){
        this.healthValue = healthValue;
    }
    public int getHealthValue() {return healthValue;}
    public void setHealthValue(int healthValue) {this.healthValue = healthValue;}
}
