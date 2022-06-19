package Game.Systems;

import Game.Components.InputComponent;
import Game.Components.MovementComponent;
import Game.Entities.AbstractInput;
import Game.Entities.AbstractPlayer;

import java.util.ArrayList;

public class InputSystem {

    private final InputComponent inputComponent;
    private final ArrayList<AbstractInput.Inputs> currentInps;
    private final AbstractPlayer abstractPlayer;

    public InputSystem(InputComponent inputComponent, AbstractPlayer abstractPlayer, ArrayList<AbstractInput.Inputs> currentInps){
        this.inputComponent = inputComponent;
        this.currentInps = currentInps;
        this.abstractPlayer = abstractPlayer;
    }

    public void update(){
        getAllUpdatedInputValues();
    }

    private void getAllUpdatedInputValues(){

        abstractPlayer.getMovementComponent().setLeft(false);
        abstractPlayer.getMovementComponent().setRight(false);
        abstractPlayer.setDirection(AbstractInput.Inputs.IDLE);

        if(currentInps.contains(AbstractInput.Inputs.JUMPING) && currentInps.contains(AbstractInput.Inputs.LEFT)){abstractPlayer.getMovementComponent().setLeft(true);abstractPlayer.getMovementComponent().setJump(true);abstractPlayer.setDirection(AbstractInput.Inputs.LEFT);}
        if(currentInps.contains(AbstractInput.Inputs.JUMPING) && currentInps.contains(AbstractInput.Inputs.RIGHT)){abstractPlayer.getMovementComponent().setRight(true);abstractPlayer.getMovementComponent().setJump(true);abstractPlayer.setDirection(AbstractInput.Inputs.RIGHT);}
        if(currentInps.contains(AbstractInput.Inputs.JUMPING)){abstractPlayer.getMovementComponent().setJump(true);abstractPlayer.setDirection(AbstractInput.Inputs.JUMPING);}

        for (AbstractInput.Inputs currentInp : currentInps) {
            inputComponent.setDown(currentInp == AbstractInput.Inputs.DOWN);
            inputComponent.setUp(currentInp == AbstractInput.Inputs.UP);
            inputComponent.setJumping(currentInp == AbstractInput.Inputs.JUMPING);
            inputComponent.setEnter(currentInp == AbstractInput.Inputs.ENTER);
            inputComponent.setEscape(currentInp == AbstractInput.Inputs.ESCAPE);
            if (currentInp == AbstractInput.Inputs.ATTACKING) {inputComponent.setAttacking(true);abstractPlayer.setDirection(AbstractInput.Inputs.ATTACKING);}else{inputComponent.setAttacking(false);}
            if (currentInp == AbstractInput.Inputs.LEFT) {inputComponent.setLeft(true);abstractPlayer.getMovementComponent().setLeft(true);abstractPlayer.setDirection(AbstractInput.Inputs.LEFT);}
            if (currentInp == AbstractInput.Inputs.RIGHT) {inputComponent.setRight(true);abstractPlayer.getMovementComponent().setRight(true);abstractPlayer.setDirection(AbstractInput.Inputs.RIGHT);}
        }
    }

}