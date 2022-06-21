package UI_2;

import Game.Components.InputComponent;
import Game.Entities.AbstractInput;
import UI_1.j2dInput;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *CubeInput class extends from AbstractInput.
 * @author Arafa Yoncalik
 */
public class CubeInput extends AbstractInput {

    ArrayList<Inputs> pressedKeysInp;
    private ArrayList<Integer> pressedKeyCodes = new ArrayList();

    /**
     *CubeInput constructor.
     * @param frame
     * @param inputComponent
     */
    public CubeInput(JFrame frame, InputComponent inputComponent) {
        super(inputComponent);
        pressedKeysInp = new ArrayList<>();
        frame.addKeyListener(new CubeInput.KeyInputAdapter());
    }

    /**
     *
     * @return returns the pressed keys in a list.
     */
    @Override
    public ArrayList<Inputs> getPressedKeyInps() {return pressedKeysInp;}

    /**
     *
     * @return returns a boolean true if the pressed keys list is not empty.
     */
    @Override
    public boolean inputAvailable() {return !pressedKeysInp.isEmpty();}

    /**
     * KeyInputAdapter class implements KeyListener Interface.
     */
    class KeyInputAdapter implements KeyListener {

        /**
         * Not used.
         * @param e the event to be processed
         */
        @Override
        public void keyTyped(KeyEvent e) {}

        /**
         *Keypressed function checks if keycode matches a KeyEvent and adds the enum type to the list with the keycode number.
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
         * On all pressed keys release, remove all items from the pressed key inputs and codes list.
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
     * Adds enum type and keycode to lists.
     * @param inp
     * @param keycodes
     */
    void addToList(Inputs inp,Integer keycodes){
        if(!pressedKeysInp.contains(inp)){pressedKeysInp.add(inp);pressedKeyCodes.add(keycodes);}
    }


}
