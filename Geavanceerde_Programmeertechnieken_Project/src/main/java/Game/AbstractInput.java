package Game;

public abstract class AbstractInput {
    public enum Inputs {LEFT, RIGHT, UP, DOWN, JUMPING, ATTACKING, IDLE};
    public abstract Inputs getInput();
}
