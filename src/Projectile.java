import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

// Projectile class represents bullets/shots fired by the player
public class Projectile extends DynamicBody {

    // Constructor: creates a projectile in the world
    public Projectile(World world, int direction) {

        // Create a small rectangular physics body
        super(world, new BoxShape(0.2f, 0.1f));

        // Remove gravity so projectile moves straight (does not fall)
        setGravityScale(0);

        // Set horizontal speed based on player direction
        // direction = 1 (right), -1 (left)
        setLinearVelocity(new Vec2(20f * direction, 0f));
    }
}