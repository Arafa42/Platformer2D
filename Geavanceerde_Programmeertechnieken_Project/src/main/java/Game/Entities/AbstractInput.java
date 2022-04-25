package Game.Entities;

public abstract class AbstractInput {
    public enum Inputs {LEFT, RIGHT, JUMPING, ATTACKING, IDLE};
    public abstract Inputs getInput();
}
