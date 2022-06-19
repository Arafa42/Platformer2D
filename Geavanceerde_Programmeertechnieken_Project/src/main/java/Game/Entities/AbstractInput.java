package Game.Entities;

import Game.Components.InputComponent;
import java.util.ArrayList;

public abstract class AbstractInput {

    private final InputComponent inputComponent;
    public AbstractInput(InputComponent inputComponent){
        this.inputComponent = new InputComponent();
    }
    public enum Inputs {LEFT, RIGHT, JUMPING, ATTACKING, IDLE, UP, DOWN, ENTER,ESCAPE};
    public abstract ArrayList<Inputs> getPressedKeyInps();
    public abstract boolean inputAvailable();
    public InputComponent getInputComponent() {return inputComponent;}
}
