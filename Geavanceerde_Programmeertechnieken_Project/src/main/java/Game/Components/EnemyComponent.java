package Game.Components;

public class EnemyComponent {
    private Boolean isActive;
    public EnemyComponent(Boolean isActive){this.isActive = isActive;}
    public Boolean getActive() {return isActive;}
    public void setActive(Boolean active) {isActive = active;}
}
