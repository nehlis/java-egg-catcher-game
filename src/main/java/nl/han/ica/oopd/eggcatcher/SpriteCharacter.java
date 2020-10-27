package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public class SpriteCharacter extends AnimatedSpriteObject {
    private final EggCatcher world;

    /**
     * @param world De game.
     * @param image Afbeelding van de sprite character.
     */
    SpriteCharacter(EggCatcher world, String image) {
        super(new Sprite(image), 1);
        this.world = world;
        setCurrentFrameIndex(0);
        setFriction(0.05f);
    }

    @Override
    public void update() {
    }

    /**
     * @return De wereld.
     */
    public EggCatcher getWorld() {
        return world;
    }
}
