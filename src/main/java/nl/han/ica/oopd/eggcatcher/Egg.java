package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopd.eggcatcher.controllers.SoundController;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PGraphics;

import java.util.List;

public class Egg extends GameObject implements ICollidableWithGameObjects {
    private final EggCatcher world;

    /**
     * Constructor
     *
     * @param world     Referentie naar de wereld
     * @param fallSound Geluid dat moet klinken als het ei valt.
     */
    public Egg(EggCatcher world, Sound fallSound, Sound catchSound) {
        this.world = world;
        int size = 50;

        setySpeed(size / 10f);
        setHeight(size);
        setWidth(size);
    }

    @Override
    public void update() {
        if (getY() < world.getHeight() || !GameState.isPlaying()) return;

        SoundController.play(SoundController.getEggFallSound());
        world.deleteAllGameOBjects();
        GameState.died();
        world.reset();
    }

    @Override
    public void draw(PGraphics g) {
        if (!GameState.isPlaying()) return;

        DrawObject.getEggShape(g, x, y);
    }

    public void caught(Egg egg) {
        SoundController.play(SoundController.getEggCatchSound());
        world.increaseEggsCaught();

        Statistics.setHighscore(Math.max(Statistics.getHighscore(), world.getEggsCaught()));
        Statistics.setLastScore(world.getEggsCaught());

        world.deleteGameObject(egg);
        world.dashboardController.refreshDasboardText();
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Player) caught(this);
        }
    }
}
