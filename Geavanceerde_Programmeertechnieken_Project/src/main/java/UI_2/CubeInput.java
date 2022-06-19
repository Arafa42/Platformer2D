package UI_2;

import Game.Components.InputComponent;
import Game.Entities.AbstractInput;
import UI_1.j2dInput;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CubeInput extends AbstractInput {

    ArrayList<Inputs> pressedKeysInp;
    private ArrayList<Integer> pressedKeyCodes = new ArrayList();


    public CubeInput(JFrame frame, InputComponent inputComponent) {
        super(inputComponent);
        pressedKeysInp = new ArrayList<>();
        frame.addKeyListener(new CubeInput.KeyInputAdapter());
    }

    @Override
    public ArrayList<Inputs> getPressedKeyInps() {return pressedKeysInp;}

    @Override
    public boolean inputAvailable() {return !pressedKeysInp.isEmpty();}

    class KeyInputAdapter implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

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

    void addToList(Inputs inp,Integer keycodes){
        if(!pressedKeysInp.contains(inp)){pressedKeysInp.add(inp);pressedKeyCodes.add(keycodes);}
    }


}
