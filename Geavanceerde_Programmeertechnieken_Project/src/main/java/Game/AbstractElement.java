package Game;

public abstract class AbstractElement implements Drawable{

    private final EntityComponent entityComponent;
    private AbstractInput.Inputs direction;

    public AbstractElement(int x, int y, int hitboxWidth, int hitboxHeight){this.entityComponent = new EntityComponent(x,y,hitboxWidth,hitboxHeight);}
    public EntityComponent getEntityComponent() {
        return entityComponent;
    }
    public void setDirection(AbstractInput.Inputs direction) {
        this.direction = direction;
    }
    public AbstractInput.Inputs getDirection() {return direction;}

}