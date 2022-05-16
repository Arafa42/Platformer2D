package UI_2;

import Game.Entities.AbstractInput;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class CubeInput extends AbstractInput {

    private static HashMap<String, Boolean> keyValues;

    public CubeInput(JFrame frame) {
        frame.addKeyListener(new KeyInputAdapter());
        keyValues = new HashMap<>();
        keyValues.put("LEFT",false);
        keyValues.put("RIGHT",false);
        keyValues.put("UP",false);
        keyValues.put("DOWN",false);
        keyValues.put("ENTER",false);
        keyValues.put("ATTACKING",false);
        keyValues.put("JUMPING",false);
        keyValues.put("ESCAPE",false);
        //System.out.println(keyValues);
    }

    @Override
    public Inputs getInput() {
        for (Map.Entry<String, Boolean> me : keyValues.entrySet()) {
            if(me.getKey().equals("LEFT") && me.getValue().equals(true)){return Inputs.LEFT;}
            if(me.getKey().equals("RIGHT") && me.getValue().equals(true)){return Inputs.RIGHT;}
            if (me.getKey().equals("UP") && me.getValue().equals(true)) {return Inputs.UP;}
            if (me.getKey().equals("DOWN") && me.getValue().equals(true)) {return Inputs.DOWN;}
            if (me.getKey().equals("ENTER") && me.getValue().equals(true)) {return Inputs.ENTER;}
            if(me.getKey().equals("ATTACKING") && me.getValue().equals(true)){return Inputs.ATTACKING;}
            if(me.getKey().equals("JUMPING") && me.getValue().equals(true)){return Inputs.JUMPING;}
            if(me.getKey().equals("ESCAPE") && me.getValue().equals(true)){return Inputs.ESCAPE;}

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
                case KeyEvent.VK_DOWN -> keyValues.put("DOWN",false);
                case KeyEvent.VK_UP -> keyValues.put("UP",false);
                case KeyEvent.VK_ENTER -> keyValues.put("ENTER",false);
                case KeyEvent.VK_ESCAPE -> keyValues.put("ESCAPE",false);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> keyValues.put("LEFT",true);
                case KeyEvent.VK_RIGHT -> keyValues.put("RIGHT",true);
                case KeyEvent.VK_X -> keyValues.put("ATTACKING",true);
                case KeyEvent.VK_SPACE -> keyValues.put("JUMPING",true);
                case KeyEvent.VK_UP -> keyValues.put("UP",true);
                case KeyEvent.VK_DOWN -> keyValues.put("DOWN",true);
                case KeyEvent.VK_ENTER -> keyValues.put("ENTER",true);
                case KeyEvent.VK_ESCAPE -> keyValues.put("ESCAPE",true);

            }
        }
    }


}
