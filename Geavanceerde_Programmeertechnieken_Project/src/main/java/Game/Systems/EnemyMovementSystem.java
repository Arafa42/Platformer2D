package Game.Systems;

import Game.Components.CollisionComponent;
import Game.Components.MovementComponent;
import Game.Components.PositionComponent;
import java.util.ArrayList;

public class EnemyMovementSystem {

    private final ArrayList<CollisionComponent> collisionComponents;
    private final ArrayList<PositionComponent> positionComponents;
    private final ArrayList<MovementComponent> movementComponents;
    private final int TILES_SIZE;

    public EnemyMovementSystem(ArrayList<CollisionComponent> collisionComponents, ArrayList<PositionComponent> positionComponents, ArrayList<MovementComponent> movementComponents,int TILE_SIZE){
        this.collisionComponents = collisionComponents;
        this.positionComponents = positionComponents;
        this.movementComponents = movementComponents;
        this.TILES_SIZE = TILE_SIZE;
    }

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
