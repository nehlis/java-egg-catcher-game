package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopd.eggcatcher.tiles.BoardsTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;


/**
 * @author Ralph Niels
 * De spelerklasse (het paarse visje)
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithTiles {

    final         int        size = 25;
    private final EggCatcher world;

    /**
     * Constructor
     *
     * @param world Referentie naar de wereld
     */
    public Player(EggCatcher world) {
        super(new Sprite("src/main/java/nl/han/ica/oopd/eggcatcher/media/player.png"), 1);
        this.world = world;
        setCurrentFrameIndex(0);
        setFriction(0.05f);
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
        if (getX() >= world.width - size) {
            setxSpeed(0);
            setX(world.width - size);
        }
        if (getY() >= world.height - size) {
            setySpeed(0);
            setY(world.height - size);
        }

    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 10;
        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
        }
        if (keyCode == world.RIGHT) {
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
                        vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                        setY(vector.y - getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (CollisionSide.RIGHT.equals(ct.getCollisionSide())) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                        world.getTileMap().setTile((int) vector.x / 50, (int) vector.y / 50, -1);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
