package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopd.eggcatcher.controllers.SoundController;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Egg extends GameObject implements ICollidableWithGameObjects {
    private final EggCatcher world;

    /**
     * Constructor
     *
     * @param world Referentie naar de wereld
     */
    public Egg(EggCatcher world) {
        this.world = world;
        int size = 50;

        setySpeed(size / 10f);
        setHeight(size);
        setWidth(size);
    }

    /**
     * Runt wanneer het ei object update.
     */
    @Override
    public void update() {
        if (getY() < world.getHeight() || !GameState.isPlaying()) return;

        SoundController.play("fall");
        world.deleteAllGameOBjects();
        GameState.died();
        world.reset();
    }

    /**
     * Tekent het ei object.
     */
    @Override
    public void draw(PGraphics g) {
        if (!GameState.isPlaying()) return;

        DrawObject.getEggShape(g, x, y);
    }


    /**
     * Vangt het ei.
     *
     * @param egg Het ei.
     */
    public void caught(Egg egg) {
        SoundController.play("catch");
        world.increaseEggsCaught();

        Statistics.setHighscore(Math.max(Statistics.getHighscore(), world.getEggsCaught()));
        Statistics.setLastScore(world.getEggsCaught());

        world.deleteGameObject(egg);
    }

    /**
     * Laat de items botsen.
     *
     * @param collidedGameObjects Gebotst item.
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Player) caught(this);
        }
    }
}
