package Game.Entities;

import Game.Drawable;

public abstract class AbstractScore implements Drawable {

    private int score;
    public AbstractScore(int score){
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
