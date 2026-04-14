import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

public class Coin extends StaticBody {

    // Tracks whether the coin has already been collected
    private boolean collected = false;

    // Constructor: creates a coin in the world
    public Coin(World world) {
        // Create a circular physics body with radius 0.35
        super(world, new CircleShape(0.35f));

        // Add the coin image (sprite)
        addImage(new BodyImage("data/coin.png", 0.9f));
    }

    // Returns true if the coin has been collected
    public boolean isCollected() {
        return collected;
    }

    // Updates the collected state (used when player picks it up)
    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}