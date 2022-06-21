package Game.Components;

/**
 *CollisionComponent class.
 * @author Arafa Yoncalik
 */
public class CollisionComponent {

    private int timesFell = 0;
    private boolean didFall = false;
    private final int[][] levelData;

    /**
     * CollisionComponent constructor.
     * @param levelData
     */
    public CollisionComponent(int[][] levelData){
        this.levelData = levelData;
    }

    public int getTimesFell() {return timesFell;}
    public void setTimesFell(int timesFell) {this.timesFell = timesFell;}
    public boolean isDidFall() {return didFall;}
    public void setDidFall(boolean didFall) {this.didFall = didFall;}
    public int[][] getLevelData() {return levelData;}
}
