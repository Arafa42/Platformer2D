package Game;

public abstract class AbstractTopBar implements Drawable{

    private int score;
    public AbstractTopBar(int score){
        this.score = score;
    }
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    @Override
    public String toString() {
        return "AbstractTopBar{" +
                "score=" + score +
                '}';
    }
}
