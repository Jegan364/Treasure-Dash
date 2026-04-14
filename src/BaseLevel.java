import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.Color;
import java.util.ArrayList;

public class BaseLevel extends World {

    protected final Game game;
    protected final Player player;
    protected final Portal portal;

    protected final ArrayList<Coin> coins = new ArrayList<>();
    protected final ArrayList<Enemy> enemies = new ArrayList<>();
    protected final ArrayList<Hazard> hazards = new ArrayList<>();
    protected final ArrayList<Projectile> projectiles = new ArrayList<>();

    protected Color backgroundColour;
    protected int levelTime;
    protected boolean portalReached = false;

    protected int totalCoins = 0;
    protected int collectedCoins = 0;

    public BaseLevel(Game game) {
        super();
        this.game = game;

        setGravity(20f);

        // ===== GROUND =====
        Shape groundShape = new BoxShape(12f, 0.5f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -11f));

        // 🔥 Dark stone ground
        ground.setFillColor(new Color(60, 50, 40));
        ground.setLineColor(new Color(30, 25, 20));

        // ===== WALLS =====
        Shape wallShape = new BoxShape(0.5f, 12f);

        StaticBody leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-12.5f, 0f));

        StaticBody rightWall = new StaticBody(this, wallShape);
        rightWall.setPosition(new Vec2(12.5f, 0f));

        // 🔥 Dark dungeon walls
        Color wallFill = new Color(50, 50, 50);
        Color wallLine = new Color(20, 20, 20);

        leftWall.setFillColor(wallFill);
        leftWall.setLineColor(wallLine);

        rightWall.setFillColor(wallFill);
        rightWall.setLineColor(wallLine);

        // ===== PLAYER =====
        player = new Player(this);
        player.setPosition(new Vec2(-10f, -9f));

        // ===== PORTAL =====
        portal = new Portal(this);
    }

    // ===== COINS =====
    protected void addCoin(float x, float y) {
        Coin coin = new Coin(this);
        coin.setPosition(new Vec2(x, y));
        coins.add(coin);
        totalCoins++;

        coin.addCollisionListener(e -> {
            if (e.getOtherBody() == player && !coin.isCollected()) {
                coin.setCollected(true);
                game.addScore(10);
                collectedCoins++;
                coin.destroy();
                checkPortalUnlock();
            }
        });
    }

    // ===== ENEMIES =====
    protected void addEnemy(float x, float y, float speed) {
        Enemy enemy = new Enemy(this, player, speed);
        enemy.setPosition(new Vec2(x, y));
        enemies.add(enemy);

        enemy.addCollisionListener(e -> {
            if (e.getOtherBody() == player) {
                game.loseLife();
            }
        });
    }

    // ===== HAZARDS =====
    protected void addHazard(float x, float y, float width, float height) {
        Hazard hazard = new Hazard(this, width, height);
        hazard.setPosition(new Vec2(x, y));
        hazards.add(hazard);

        hazard.addCollisionListener(e -> {
            if (e.getOtherBody() == player) {
                game.loseLife();
            }
        });
    }

    // ===== PORTAL =====
    protected void placePortal(float x, float y) {
        portal.setPosition(new Vec2(x, y));

        portal.addCollisionListener(e -> {
            if (e.getOtherBody() == player && portal.isUnlocked()) {
                portalReached = true;
                game.nextLevel();
            }
        });
    }

    protected void checkPortalUnlock() {
        if (collectedCoins == totalCoins) {
            portal.unlock();
            System.out.println("Portal unlocked");
        }
    }

    // ===== PROJECTILES =====
    public void shootProjectile() {
        Projectile p = new Projectile(this, player.getFacing());
        Vec2 playerPos = player.getPosition();

        float offsetX = 1.5f * player.getFacing();
        p.setPosition(new Vec2(playerPos.x + offsetX, playerPos.y + 0.2f));

        projectiles.add(p);

        p.addCollisionListener(e -> {

            // If projectile hits an enemy
            if (e.getOtherBody() instanceof Enemy) {
                Enemy enemy = (Enemy) e.getOtherBody();
                enemy.destroy();              // remove enemy
                game.addScore(20);            // add score
                p.destroy();                  // destroy projectile
                return;
            }

            // If it hits anything else (walls, ground, etc.)
            p.destroy();
        });
    }

    // ===== GETTERS =====
    public Player getPlayer() {
        return player;
    }

    public Color getBackgroundColour() {
        return backgroundColour;
    }

    public int getLevelTime() {
        return levelTime;
    }

    public boolean isPortalReached() {
        return portalReached;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public int getCollectedCoins() {
        return collectedCoins;
    }
}