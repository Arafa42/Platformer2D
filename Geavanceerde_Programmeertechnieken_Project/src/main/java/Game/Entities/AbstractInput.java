package Game.Entities;

public abstract class AbstractInput {
    public enum Inputs {LEFT, RIGHT, JUMPING, ATTACKING, IDLE, UP, DOWN, ENTER,ESCAPE};
    public abstract Inputs getInput();
}
