package UI_1;

import Game.AbstractInput;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class j2dInput extends AbstractInput {

    private static HashMap<String, Boolean> keyValues;

    public j2dInput(JFrame frame) {
        frame.addKeyListener(new KeyInputAdapter());
        keyValues = new HashMap<>();
        keyValues.put("LEFT",false);
        keyValues.put("RIGHT",false);
        keyValues.put("ATTACKING",false);
        keyValues.put("JUMPING",false);
        //System.out.println(keyValues);
    }

    @Override
    public Inputs getInput() {
        for (Map.Entry<String, Boolean> me : keyValues.entrySet()) {
            if(me.getKey().equals("LEFT") && me.getValue().equals(true)){return Inputs.LEFT;}
            if(me.getKey().equals("RIGHT") && me.getValue().equals(true)){return Inputs.RIGHT;}
            if(me.getKey().equals("ATTACKING") && me.getValue().equals(true)){return Inputs.ATTACKING;}
            if(me.getKey().equals("JUMPING") && me.getValue().equals(true)){return Inputs.JUMPING;}
        }
        return Inputs.IDLE;

        //CURRENTLY ONE INPUT IS DETECTED NEED TO ADD FUNCTIONALITY TO DETECT 2 INPUTS LIKE RUNNING AND SHOOTING

    }


    static class KeyInputAdapter extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> keyValues.put("LEFT",false);
                case KeyEvent.VK_RIGHT -> keyValues.put("RIGHT",false);
                case KeyEvent.VK_X -> keyValues.put("ATTACKING",false);
                case KeyEvent.VK_SPACE -> keyValues.put("JUMPING",false);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> keyValues.put("LEFT",true);
                case KeyEvent.VK_RIGHT -> keyValues.put("RIGHT",true);
                case KeyEvent.VK_X -> keyValues.put("ATTACKING",true);
                case KeyEvent.VK_SPACE -> keyValues.put("JUMPING",true);
            }
        }
    }


}
