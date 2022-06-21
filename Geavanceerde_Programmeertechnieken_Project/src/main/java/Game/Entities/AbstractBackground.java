package Game.Entities;

import Game.Drawable;

/**
 *AbstractBackground class, implements the Drawable Interface.
 * @author Arafa Yoncalik
 */
public abstract class AbstractBackground implements Drawable {

    private String layer1;
    private String layer2;

    /**
     *AbstractBackground constructor.
     * @param layer1
     * @param layer2
     */
    public AbstractBackground(String layer1, String layer2){
        this.layer1 = layer1;
        this.layer2 = layer2;
    }

    public String getLayer1() {return layer1;}
    public void setLayer1(String layer1) {this.layer1 = layer1;}
    public String getLayer2() {return layer2;}
    public void setLayer2(String layer2) {this.layer2 = layer2;}
}
