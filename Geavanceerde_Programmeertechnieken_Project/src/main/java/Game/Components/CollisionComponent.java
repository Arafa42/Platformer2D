package Game.Components;

public class CollisionComponent {

    private int currScore = 0;
    private int timesFell = 0;
    private boolean didFall = false;
    private final int[][] levelData;

    public CollisionComponent(int[][] levelData){
        this.levelData = levelData;
    }

    public int getCurrScore() {return currScore;}
    public void setCurrScore(int currScore) {this.currScore = currScore;}
    public int getTimesFell() {return timesFell;}
    public void setTimesFell(int timesFell) {this.timesFell = timesFell;}
    public boolean isDidFall() {return didFall;}
    public void setDidFall(boolean didFall) {this.didFall = didFall;}
    public int[][] getLevelData() {return levelData;}
}
