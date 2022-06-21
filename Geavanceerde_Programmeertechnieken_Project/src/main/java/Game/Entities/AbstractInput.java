package Game.Entities;

import Game.Components.InputComponent;
import java.util.ArrayList;

/**
 *AbstractInput class.
 * @author Arafa Yoncalik
 */
public abstract class AbstractInput {

    private final InputComponent inputComponent;

    /**
     *AbstractInput constructor.
     * @param inputComponent
     */
    public AbstractInput(InputComponent inputComponent){
        this.inputComponent = new InputComponent();
    }

    /**
     *Inputs enum (LEFT,RIGHT,JUMPING,ATTACKING,ATTACKING_LEFT,ATTACKING_RIGHT,IDLE,UP,DOWN,ENTER,SPACE)
     */
    public enum Inputs {LEFT, RIGHT, JUMPING,ATTACKING, ATTACKING_LEFT,ATTACKING_RIGHT, IDLE, UP, DOWN, ENTER,ESCAPE};

    /**
     * getPressedKeyInps() getter.
     * @return returns the list of pressed key inputs.
     */
    public abstract ArrayList<Inputs> getPressedKeyInps();

    /**
     * inputAvailable() getter.
     * @return returns boolean of availability of any input.
     */
    public abstract boolean inputAvailable();

    /**
     * getInputComponent() getter.
     * @return returns the input component.
     */
    public InputComponent getInputComponent() {return inputComponent;}
}
