import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

import java.awt.Color;

public class Hazard extends StaticBody {

    public Hazard(World world, float width, float height) {
        super(world, new BoxShape(width, height));

        // Hide the physics box
        setFillColor(null);
        setLineColor(null);

        // Spike sprite
        addImage(new BodyImage("data/spikes.png", 1.2f));
    }
}