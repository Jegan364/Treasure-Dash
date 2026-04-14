import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.awt.Color;

public class Level1 extends BaseLevel {
    public Level1(Game game) {
        super(game);
        backgroundColour = new Color(135, 206, 235);
        levelTime = 60;

        // Easy platform layout for the first level
        makePlatform(-7f, -7f, 1.8f, 0.3f);
        makePlatform(-2f, -4.5f, 1.8f, 0.3f);
        makePlatform(3f, -2f, 1.8f, 0.3f);
        makePlatform(8f, 0.5f, 1.8f, 0.3f);

        addCoin(-7f, -6f);
        addCoin(-2f, -3.5f);
        addCoin(3f, -1f);

        addEnemy(-1f, -9f, 2.5f);
        addHazard(5f, -10.5f, 1f, 0.3f);

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