package Game.Components;

/**
 *ScoreComponent Class.
 * @author Arafa Yoncalik
 */
public class ScoreComponent {
    private int score;

    /**
     *ScoreComponent constructor.
     * @param score
     */
    public ScoreComponent(int score){
        this.score = score;
    }
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
}
