import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

// Handles player movement
// Player class controls the main character
public class Player extends DynamicBody {

    // Direction the player is facing (1 = right, -1 = left)
    private int facing = 1;

    // Limits how many times the player can sprint
    private int sprintUses = 100;

    // Constructor: creates the player in the world
    public Player(World world) {

        // Create a rectangular physics body
        super(world, new BoxShape(0.5f, 1f));

        // Add the player sprite image
        addImage(new BodyImage("data/player_knight.png", 2.4f));

        // StepListener runs every frame to control behaviour
        world.addStepListener(new StepListener() {

            @Override
            public void preStep(StepEvent stepEvent) {
                // Prevent the player from rotating (keeps upright)
                setAngularVelocity(0);
                setAngle(0);
            }

            @Override
            public void postStep(StepEvent stepEvent) {
                // Extra safety to keep player upright after physics update
                setAngularVelocity(0);
                setAngle(0);
            }
        });
    }

    // Move player to the left
    public void moveLeft() {
        // Set horizontal velocity (keep vertical velocity unchanged)
        setLinearVelocity(new Vec2(-8f, getLinearVelocity().y));

        // Update facing direction
        facing = -1;
    }

    // Move player to the right
    public void moveRight() {
        // Set horizontal velocity (keep vertical velocity unchanged)
        setLinearVelocity(new Vec2(8f, getLinearVelocity().y));

        // Update facing direction
        facing = 1;
    }

    // Make the player jump
    public void jump() {
        // Apply upward velocity
        setLinearVelocity(new Vec2(getLinearVelocity().x, 12f));
    }

    // Sprint (faster movement in current direction)
    public void sprint() {
        if (sprintUses > 0) {
            // Increase speed in the direction the player is facing
            setLinearVelocity(new Vec2(14f * facing, getLinearVelocity().y));

            // Reduce available sprint uses
            sprintUses--;
        }
    }

    // Returns current facing direction
    public int getFacing() {
        return facing;
    }
}