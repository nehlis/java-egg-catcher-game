package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;

import java.util.List;

public class Player extends SpriteCharacter implements ICollidableWithTiles {
    private final int size = 25;

    /**
     * Constructor
     *
     * @param world EggCatcher
     */
    Player(EggCatcher world) {
        super(world, "src/main/java/nl/han/ica/oopd/eggcatcher/media/player.png");
    }

    /**
     * Runt wanneer de speler update.
     */
    @Override
    public void update() {
        if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= getWorld().width - size) {
            setxSpeed(0);
            setX(getWorld().width - size);
        }
        if (getY() >= getWorld().height - size) {
            setySpeed(0);
            setY(getWorld().height - size);
        }

    }

    /**
     * Key press event.
     *
     * @param keyCode Key pressed code.
     * @param key     Key pressed char.
     */
    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 10;
        if (keyCode == getWorld().LEFT || key == 'a') {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
        }
        if (keyCode == getWorld().RIGHT || key == 'd') {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(0);
        }
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
    }
}
