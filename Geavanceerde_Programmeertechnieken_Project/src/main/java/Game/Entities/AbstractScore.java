package Game.Entities;

import Game.Components.ScoreComponent;
import Game.Drawable;

public abstract class AbstractScore implements Drawable {

    private ScoreComponent scoreComponent;

    public AbstractScore(ScoreComponent scoreComponent){
        this.scoreComponent = scoreComponent;
    }

    public int GetCurrentScore(){return scoreComponent.getScore();}

}
