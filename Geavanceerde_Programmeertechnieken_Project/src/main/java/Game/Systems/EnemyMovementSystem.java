package Game.Systems;

import Game.Components.CollisionComponent;
import Game.Components.MovementComponent;
import Game.Components.PositionComponent;
import java.util.ArrayList;

/**
 *EnemyMovementSystem class.
 * @author Arafa Yoncalik
 */
public class EnemyMovementSystem {

    private final ArrayList<CollisionComponent> collisionComponents;
    private final ArrayList<PositionComponent> positionComponents;
    private final ArrayList<MovementComponent> movementComponents;
    private final int TILES_SIZE;

    /**
     *EnemyMovementSystem constructor.
     * @param collisionComponents
     * @param positionComponents
     * @param movementComponents
     * @param TILE_SIZE
     */
    public EnemyMovementSystem(ArrayList<CollisionComponent> collisionComponents, ArrayList<PositionComponent> positionComponents, ArrayList<MovementComponent> movementComponents,int TILE_SIZE){
        this.collisionComponents = collisionComponents;
        this.positionComponents = positionComponents;
        this.movementComponents = movementComponents;
        this.TILES_SIZE = TILE_SIZE;
    }

    /**
     *update functions updates the enemy movement depending on its position.
     */
    public void update(){
        for (int i =0;i<positionComponents.size();i++){
                    if (collisionComponents.get(i).getLevelData()[(int) (positionComponents.get(i).y / TILES_SIZE) + 1][(int) (positionComponents.get(i).x / TILES_SIZE) - 1] == 0) {
                        movementComponents.get(i).setxSpeed((int) movementComponents.get(i).getPlayerSpeed());
                    }
                    if (collisionComponents.get(i).getLevelData()[(int) (positionComponents.get(i).y / TILES_SIZE) + 1][(int) (positionComponents.get(i).x / TILES_SIZE) + 1] == 0) {
                        movementComponents.get(i).setxSpeed(-(int) movementComponents.get(i).getPlayerSpeed());
                    }
        }
    }
}
