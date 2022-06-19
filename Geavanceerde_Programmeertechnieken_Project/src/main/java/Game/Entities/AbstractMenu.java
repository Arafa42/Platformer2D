package Game.Entities;

import Game.Components.MenuComponent;
import Game.Drawable;

public abstract class AbstractMenu implements Drawable {
    private final MenuComponent menuComponent;
    public AbstractMenu(){
        this.menuComponent = new MenuComponent();
    }
    public MenuComponent getMenuComponent() {return menuComponent;}
}
