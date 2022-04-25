package Game.Components;

public class HealthComponent {

    private int healthValue;


    public HealthComponent(int healthValue){
        this.healthValue = healthValue;
    }

    public int getHealthValue() {return healthValue;}
    public void setHealthValue(int healthValue) {this.healthValue = healthValue;}
}
