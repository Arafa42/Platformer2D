package Game.Entities;

import Game.Components.MenuComponent;
import Game.Drawable;

/**
 * AbstractMenu class implements Drawable interface.
 * @author Arafa Yoncalik
 */
public abstract class AbstractMenu implements Drawable {
    private final MenuComponent menuComponent;

    /**
     *AbstractMenu constructor.
     */
    public AbstractMenu(){
        this.menuComponent = new MenuComponent();
    }

    /**
     * MenuComponent getter.
     * @return returns the menu component.
     */
    public MenuComponent getMenuComponent() {return menuComponent;}
}
