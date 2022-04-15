package Game;

public class MovementCompnent {

    private float playerSpeed = 3.0f;


    private void updateMovement(EntityComponent entityComponent){
        entityComponent.x = entityComponent.dx;
        entityComponent.y = entityComponent.dy;
    }


    public void update(EntityComponent entityComponent) { updateMovement(entityComponent); }


    public void moveLeft(EntityComponent entityComponent){entityComponent.dx -= playerSpeed;}

    public void moveRight(EntityComponent entityComponent){entityComponent.dx += playerSpeed;}


}
