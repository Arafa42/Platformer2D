package Game.Components;

public class LevelComponent {
    private boolean switchLevel;
    private int levelToLoad;
    public LevelComponent(boolean switchLevel, int levelToLoad){this.switchLevel = switchLevel;this.levelToLoad = levelToLoad;}
    public boolean isSwitchLevel() {return switchLevel;}
    public void setSwitchLevel(boolean switchLevel) {this.switchLevel = switchLevel;}
    public int getLevelToLoad() {return levelToLoad;}
    public void setLevelToLoad(int levelToLoad) {this.levelToLoad = levelToLoad;}
}
