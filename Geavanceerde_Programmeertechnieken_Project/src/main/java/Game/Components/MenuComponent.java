package Game.Components;

/**
 *MenuComponent class.
 * @author Arafa Yoncalik
 */
public class MenuComponent {
    private final String[] options = {"PLAY","HELP","QUIT"};
    private int currentSelection = 0;
    public MenuComponent(){}
    public String[] getOptions() {return options;}
    public int getCurrentSelection() {return currentSelection;}
    public void setCurrentSelection(int currentSelection) {this.currentSelection = currentSelection;}
}
