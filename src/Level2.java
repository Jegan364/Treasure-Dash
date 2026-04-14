import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.awt.Color;

public class Level2 extends BaseLevel {
    public Level2(Game game) {
        super(game);
        backgroundColour = new Color(230, 190, 120);
        levelTime = 55;

        // More random and uneven than level 1
        makePlatform(-8.5f, -6.5f, 1.3f, 0.3f);
        makePlatform(-4.5f, -2.5f, 1.7f, 0.3f);
        makePlatform(0.5f, -5.5f, 1.4f, 0.3f);
        makePlatform(4.5f, -1.5f, 1.8f, 0.3f);
        makePlatform(8.5f, 2.0f, 1.2f, 0.3f);

        addCoin(-8.5f, -5.5f);
        addCoin(-4.5f, -1.5f);
        addCoin(0.5f, -4.5f);
        addCoin(8.5f, 3.0f);

        addEnemy(-2f, -9f, 3.0f);
        addEnemy(5f, -9f, 3.0f);

        addHazard(-0.5f, -10.5f, 1.2f, 0.3f);
        addHazard(6.5f, -10.5f, 1.0f, 0.3f);

        placePortal(10f, -9f);
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