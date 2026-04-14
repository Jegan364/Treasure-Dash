import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

// Portal class represents the level exit
public class Portal extends StaticBody {

    // Tracks whether the portal is unlocked
    private boolean unlocked = false;

    // Constructor: creates the portal in the world
    public Portal(World world) {

        // Create a rectangular physics body (tall shape for easy collision)
        super(world, new BoxShape(1.0f, 1.8f));

        // Add portal image (currently locked version)
        addImage(new BodyImage("data/portal_locked.png", 3.5f));
    }

    // Unlock the portal (called when all coins are collected)
    public void unlock() {

        // Set portal state to unlocked
        unlocked = true;

        // Image is not changed here to avoid missing file errors
        // (can be upgraded later to swap to a glowing portal image)
    }

    // Returns true if the portal is unlocked
    public boolean isUnlocked() {
        return unlocked;
    }
}