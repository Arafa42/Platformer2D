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


    public void moveUp(EntityComponent entityComponent){entityComponent.dy -= playerSpeed;}

    public void moveDown(EntityComponent entityComponent){entityComponent.dy += playerSpeed;}


    public void stopMoving(){
        System.out.println("STOP THE Y SPEED RIGHT THERE........");
    }


}
