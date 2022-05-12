package Game.Systems;

import Game.Components.CollisionComponent;
import Game.Components.MovementComponent;
import Game.Components.PositionComponent;
import Game.Game;

import java.util.ArrayList;

public class EnemyMovementSystem {

    private final ArrayList<CollisionComponent> collisionComponents;
    private final ArrayList<PositionComponent> positionComponents;
    private final ArrayList<MovementComponent> movementComponents;


    public EnemyMovementSystem(ArrayList<CollisionComponent> collisionComponents, ArrayList<PositionComponent> positionComponents, ArrayList<MovementComponent> movementComponents){
        this.collisionComponents = collisionComponents;
        this.positionComponents = positionComponents;
        this.movementComponents = movementComponents;
    }



    public void update(){

        //GET THE POSITION OF THE ENEMY
        //LOCATE ITS CURRENT TILE
        // LOCATE IF TILE NEXT UNDER HIM IS AIR OR NOT
        // IF AIR MOVE RIGHT
        // IF NOT MOVE LEFT UNTIL ITS AIR
        // DO OPPOSITE THING SO ENEMY MOVES ACROS ONE PIECE OF PLATFORM WITHOUT FALLING

        for (int i =0;i<positionComponents.size();i++){
            //System.out.println("x"+i  +  " : " + positionComponents.get(i).x);
            //MoveEnemy(positionComponents.get(i).x, positionComponents.get(i).y);
                    System.out.println(collisionComponents.get(i).getLevelData()[(int) (positionComponents.get(i).y / Game.TILES_SIZE) + 1][(int) (positionComponents.get(i).x / Game.TILES_SIZE) - 1]);
                    if (collisionComponents.get(i).getLevelData()[(int) (positionComponents.get(i).y / Game.TILES_SIZE) + 1][(int) (positionComponents.get(i).x / Game.TILES_SIZE) - 1] == 0) {
                        //System.out.println(j + " IS TRUE");
                        //movementComponents.get(j).setRight(true);
                        //movementComponents.get(j).setLeft(false);
                        movementComponents.get(i).setxSpeed((int) movementComponents.get(i).getPlayerSpeed());
                    }
                    if (collisionComponents.get(i).getLevelData()[(int) (positionComponents.get(i).y / Game.TILES_SIZE) + 1][(int) (positionComponents.get(i).x / Game.TILES_SIZE) + 1] == 0) {
                        //movementComponents.get(j).setLeft(true);
                        //movementComponents.get(j).setRight(false);
                        movementComponents.get(i).setxSpeed(-(int) movementComponents.get(i).getPlayerSpeed());
                    }
        }

    }


    private void MoveEnemy(float x, float y){
        //if(x < 0.0 || x >= (Game.TILES_SIZE * Game.TILES_IN_WIDTH)){return true;}
        //if(y < 0.0 || y >= (Game.TILES_SIZE * Game.TILES_IN_HEIGHT)){return true;}


        //int value = collisionComponent.getLevelData()[(int) yIndex][(int)xIndex];
        //int nextBelowValue = collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex+1];
        //int previousBelowValue = collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex-1];
        //System.out.println("next : " + collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex+1]);
        //System.out.println("previous : " + collisionComponent.getLevelData()[(int) yIndex+1][(int)xIndex-1]);


//        if(nextBelowValue == 0){
//            positionComponent.x -= 1;
//        }
        //System.out.println(movementComponents.get(i));

        //System.out.println("x"  +  " : " + x);


        for(int i =0;i<collisionComponents.size();i++){
            for(int j=0;j<movementComponents.size();j++) {
                if (collisionComponents.get(i).getLevelData()[(int) (y / Game.TILES_SIZE) + 1][(int) (x / Game.TILES_SIZE) - 1] == 0) {
                    System.out.println(i + " IS TRUE");
                    movementComponents.get(j).setRight(true);
                    movementComponents.get(j).setLeft(false);
                }
                if (collisionComponents.get(i).getLevelData()[(int) (y / Game.TILES_SIZE) + 1][(int) (x / Game.TILES_SIZE) + 1] == 0) {
                    movementComponents.get(j).setLeft(true);
                    movementComponents.get(j).setRight(false);
                }
            }
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
