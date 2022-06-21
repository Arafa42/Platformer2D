package Game.Entities;

import Game.Components.ScoreComponent;
import Game.Drawable;

/**
 *AbstractScore Class, implements the Drawable interface.
 * @author Arafa Yoncalik
 */
public abstract class AbstractScore implements Drawable{
    private final ScoreComponent scoreComponent;

    /**
     * AbstractScore constructor.
     * @param scoreComponent
     */
    public AbstractScore(ScoreComponent scoreComponent){
        this.scoreComponent = scoreComponent;
    }

    /**
     *GetCurrentScore getter.
     * @return returns the score from the score component.
     */
    public int GetCurrentScore(){return scoreComponent.getScore();}

}
