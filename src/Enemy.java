import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

// Enemy follows the player and jumps when needed
// Enemy class that follows and attacks the player
public class Enemy extends DynamicBody {

    // Reference to the player (so enemy can track them)
    private Player player;

    // Movement speed of the enemy
    private float speed;

    // Cooldown to prevent constant jumping
    private int jumpCooldown = 0;

    // Constructor: creates enemy in the world
    public Enemy(World world, Player player, float speed) {

        // Create a box-shaped physics body
        super(world, new BoxShape(0.5f, 0.5f));

        this.player = player;
        this.speed = speed;

        // Add enemy sprite image
        addImage(new BodyImage("data/enemy.png", 1.5f));

        // StepListener runs code every frame (game loop)
        world.addStepListener(new StepListener() {

            @Override
            public void preStep(StepEvent stepEvent) {

                // Get enemy position
                float enemyX = getPosition().x;
                float enemyY = getPosition().y;

                // Get player position
                float playerX = Enemy.this.player.getPosition().x;
                float playerY = Enemy.this.player.getPosition().y;

                // Keep current vertical speed (so gravity still works)
                float currentYSpeed = getLinearVelocity().y;

                // ===== HORIZONTAL MOVEMENT =====
                // Move left or right toward the player
                if (playerX < enemyX - 0.2f) {
                    setLinearVelocity(new Vec2(-Enemy.this.speed, currentYSpeed));
                } else if (playerX > enemyX + 0.2f) {
                    setLinearVelocity(new Vec2(Enemy.this.speed, currentYSpeed));
                } else {
                    // Stop if close enough
                    setLinearVelocity(new Vec2(0f, currentYSpeed));
                }

                // ===== JUMP COOLDOWN =====
                // Reduce cooldown each frame
                if (jumpCooldown > 0) {
                    jumpCooldown--;
                }

                // ===== JUMP LOGIC =====
                // Enemy jumps if player is above it
                if (playerY > enemyY + 1.5f &&
                        jumpCooldown == 0 &&
                        Math.abs(currentYSpeed) < 0.1f) {

                    // Apply upward force
                    applyImpulse(new Vec2(0f, 8f));

                    // Reset cooldown so it doesn't spam jump
                    jumpCooldown = 50;
                }

                // ===== KEEP ENEMY UPRIGHT =====
                // Prevent spinning
                setAngularVelocity(0);
                setAngle(0);
            }

            @Override
            public void postStep(StepEvent stepEvent) {

                // Extra safety: keep enemy upright after physics update
                setAngularVelocity(0);
                setAngle(0);
            }
        });
    }
}