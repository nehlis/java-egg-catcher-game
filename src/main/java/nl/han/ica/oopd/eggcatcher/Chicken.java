package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public class Chicken extends AnimatedSpriteObject {
    /**
     * Constructor
     */
    public Chicken() {
        super(new Sprite("src/main/java/nl/han/ica/oopd/eggcatcher/media/player.png"), 1);
        setCurrentFrameIndex(0);
        setFriction(0.05f);
    }

    @Override
    public void update() {}
}
