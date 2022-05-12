package Game.Systems;

import Game.Components.CollisionComponent;
import Game.Components.MovementComponent;
import Game.Components.PositionComponent;
import Game.Game;

public class EnemyMovementSystem {

    private final CollisionComponent collisionComponent;
    private final PositionComponent positionComponent;
    private final MovementComponent movementComponent;


    public EnemyMovementSystem(CollisionComponent collisionComponent, PositionComponent positionComponent, MovementComponent movementComponent){
        this.collisionComponent = collisionComponent;
        this.positionComponent = positionComponent;
        this.movementComponent = movementComponent;
    }



    public void update(){

        //GET THE POSITION OF THE ENEMY
        //LOCATE ITS CURRENT TILE
        // LOCATE IF TILE NEXT UNDER HIM IS AIR OR NOT
        // IF AIR MOVE RIGHT
        // IF NOT MOVE LEFT UNTIL ITS AIR
        // DO OPPOSITE THING SO ENEMY MOVES ACROS ONE PIECE OF PLATFORM WITHOUT FALLING

        MoveEnemy(positionComponent.x,positionComponent.y);

    }


    private void MoveEnemy(float x, float y){
        //if(x < 0.0 || x >= (Game.TILES_SIZE * Game.TILES_IN_WIDTH)){return true;}
        //if(y < 0.0 || y >= (Game.TILES_SIZE * Game.TILES_IN_HEIGHT)){return true;}

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = collisionComponent.getLevelData()[(int) yIndex][(int)xIndex];
        //int nextBelowValue = collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex+1];
        //int previousBelowValue = collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex-1];
        System.out.println("next : " + collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex+1]);
        System.out.println("previous : " + collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex-1]);


//        if(nextBelowValue == 0){
//            positionComponent.x -= 1;
//        }


         if(collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex-1] == 0){
            movementComponent.setRight(true);
            movementComponent.setLeft(false);
         }
         if(collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex+1] == 0){
             movementComponent.setLeft(true);
             movementComponent.setRight(false);
         }
//         if(collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex-1] != 0 && collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex+1] != 0){
//             movementComponent.setLeft(true);
//             movementComponent.setRight(false);
//         }

//         else if(nextBelowValue != 0 && previousBelowValue != 0){
//             //JUST GO RIGHT
//             positionComponent.x += 1;
//         }



//        if(previousBelowValue == 0){
//            positionComponent.x += 10;
//        }
//        else if(previousBelowValue != 0){
//            positionComponent.x -= 10;
//        }


        //if(value!=0 && value != 2 && value != 4 && value != 7 && value != -2){return true;}

        //return false;
    }


}
