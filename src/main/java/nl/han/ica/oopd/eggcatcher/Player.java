package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopd.eggcatcher.tiles.BoardsTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import processing.core.PVector;

import java.util.List;

public class Player extends SpriteCharacter implements ICollidableWithTiles {
    private final int        size = 25;

    Player(EggCatcher world) {
        super(world, "src/main/java/nl/han/ica/oopd/eggcatcher/media/player.png");
    }

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
        PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof BoardsTile) {
                if (CollisionSide.TOP.equals(ct.getCollisionSide())) {
                    try {
                        vector = getWorld().getTileMap().getTilePixelLocation(ct.getTile());
                        setY(vector.y - getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.RIGHT.equals(ct.getCollisionSide())) {
                    try {
                        vector = getWorld().getTileMap().getTilePixelLocation(ct.getTile());
                        getWorld().getTileMap().setTile((int) vector.x / 50, (int) vector.y / 50, -1);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
