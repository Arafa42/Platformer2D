package Game;

public abstract class AbstractElement implements Drawable{

    private final MovementComponent movementComponent;
    private AbstractInput.Inputs direction;
    public AbstractElement(int x, int y){this.movementComponent = new MovementComponent(x,y);}
    public MovementComponent getMovementComponent() {
        return movementComponent;
    }
    public void setDirection(AbstractInput.Inputs direction) {
        this.direction = direction;
    }
    public AbstractInput.Inputs getDirection() {return direction;}

}
