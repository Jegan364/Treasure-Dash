import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.awt.Color;

public class Level3 extends BaseLevel {
    public Level3(Game game) {
        super(game);
        backgroundColour = new Color(30, 30, 80);
        levelTime = 50;

        // Hardest level: more scattered and less predictable platform layout
        makePlatform(-9f, -5.5f, 1.2f, 0.3f);
        makePlatform(-5.5f, -1.5f, 1.5f, 0.3f);
        makePlatform(-1f, -6.0f, 1.0f, 0.3f);
        makePlatform(2.5f, -2.5f, 1.6f, 0.3f);
        makePlatform(6f, 1.5f, 1.1f, 0.3f);
        makePlatform(9.5f, -3.5f, 1.0f, 0.3f);

        addCoin(-9f, -4.5f);
        addCoin(-5.5f, -0.5f);
        addCoin(-1f, -5f);
        addCoin(6f, 2.5f);
        addCoin(9.5f, -2.5f);

        addEnemy(-6f, -9f, 3.5f);
        addEnemy(1f, -9f, 3.5f);

        addHazard(-3f, -10.5f, 1.2f, 0.3f);
        addHazard(4f, -10.5f, 1.4f, 0.3f);
        addHazard(8f, -10.5f, 1.0f, 0.3f);

        // Moved to a cleaner but still challenging spot
        placePortal(10f, -8.5f);
    }

    private void makePlatform(float x, float y, float w, float h) {
        Shape shape = new BoxShape(w, h);
        StaticBody platform = new StaticBody(this, shape);
        platform.setPosition(new Vec2(x, y));

        // 🔥 Stone platform look
        platform.setFillColor(new java.awt.Color(110, 85, 60));
        platform.setLineColor(new java.awt.Color(50, 35, 20));
    }
}