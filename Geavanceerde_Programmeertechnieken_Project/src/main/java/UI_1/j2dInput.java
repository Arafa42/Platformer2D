package UI_1;

import Game.Components.InputComponent;
import Game.Entities.AbstractInput;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 *j2dInput class extends AbstractInput.
 * @author Arafa Yoncalik
 */
public class j2dInput extends AbstractInput {

    ArrayList<Inputs> pressedKeysInp;
    private ArrayList<Integer> pressedKeyCodes = new ArrayList();

    /**
     *j2dInput constructor.
     * @param frame
     * @param inputComponent
     */
    public j2dInput(JFrame frame, InputComponent inputComponent) {
        super(inputComponent);
        pressedKeysInp = new ArrayList<>();
        frame.addKeyListener(new KeyInputAdapter());
    }

    /**
     *getPressedKeyInps getter.
     * @return returns the pressedKeyInp arraylist.
     */
    @Override
    public ArrayList<Inputs> getPressedKeyInps() {return pressedKeysInp;}

    /**
     *inputAvailable getter
     * @return returns a true boolean if pressedKeyInp list is not empty.
     */
    @Override
    public boolean inputAvailable() {return !pressedKeysInp.isEmpty();}

    /**
     *KeyInputAdapter class implements the KeyListener interface.
     */
     class KeyInputAdapter implements KeyListener {

        /**
         *Not used.
         * @param e the event to be processed
         */
        @Override
        public void keyTyped(KeyEvent e) {}

        /**
         *If a key is pressed, add its enum type value and keycode to the list.
         * @param e the event to be processed
         */
        @Override
        public synchronized void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> addToList(Inputs.UP,e.getKeyCode());
                    case KeyEvent.VK_LEFT -> addToList(Inputs.LEFT,e.getKeyCode());
                    case KeyEvent.VK_DOWN -> addToList(Inputs.DOWN,e.getKeyCode());
                    case KeyEvent.VK_RIGHT -> addToList(Inputs.RIGHT,e.getKeyCode());
                    case KeyEvent.VK_X -> addToList(Inputs.ATTACKING,e.getKeyCode());
                    case KeyEvent.VK_SPACE -> addToList(Inputs.JUMPING,e.getKeyCode());
                    case KeyEvent.VK_ENTER ->addToList(Inputs.ENTER,e.getKeyCode());
                    case KeyEvent.VK_ESCAPE -> addToList(Inputs.ESCAPE,e.getKeyCode());
                }
            }

        /**
         *On key release remove key value and code from lists.
         * @param e the event to be processed
         */
         @Override
         public synchronized void keyReleased(KeyEvent e) {
                for(int i =0;i<pressedKeyCodes.size();i++){
                    if(e.getKeyCode() == pressedKeyCodes.get(i)){
                        pressedKeyCodes.remove(i);
                        pressedKeysInp.remove(i);
                    }
                }
         }
     }

    /**
     *addToList function, adds input and keycode value to their lists.
     * @param inp
     * @param keycodes
     */
     void addToList(Inputs inp,Integer keycodes){
        if(!pressedKeysInp.contains(inp)){pressedKeysInp.add(inp);pressedKeyCodes.add(keycodes);}
     }

}


