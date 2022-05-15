package Game.Entities;

import Game.Drawable;

public abstract class AbstractBackground implements Drawable {

    private String layer1;
    private String layer2;

    public AbstractBackground(String layer1, String layer2){
        this.layer1 = layer1;
        this.layer2 = layer2;
    }

    public String getLayer1() {return layer1;}
    public void setLayer1(String layer1) {this.layer1 = layer1;}
    public String getLayer2() {return layer2;}
    public void setLayer2(String layer2) {this.layer2 = layer2;}
}
