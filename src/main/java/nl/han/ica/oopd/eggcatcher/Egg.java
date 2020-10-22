package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PGraphics;

import java.util.List;

public class Egg extends GameObject implements ICollidableWithGameObjects {

    private final Sound      fallSound;
    private final Sound      catchSound;
    private final EggCatcher world;
    private final int        size = 50;

    /**
     * Constructor
     *
     * @param world     Referentie naar de wereld
     * @param fallSound Geluid dat moet klinken als het ei valt.
     */
    public Egg(EggCatcher world, Sound fallSound, Sound catchSound) {
        this.fallSound = fallSound;
        this.catchSound = catchSound;
        this.world = world;

        setySpeed(size / 10f);
        setHeight(size);
        setWidth(size);
    }

    @Override
    public void update() {
        System.out.println(getHeight());
        if (getY() >= world.getHeight()) {
            fallSound.cue(0);
            fallSound.play();
            world.deleteGameObject(this);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.ellipseMode(g.CORNER);
        g.stroke(0, 50, 200, 100);
        g.fill(206, 93, 219);
        g.ellipse(getX(), getY(), size, size);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Player) {
                catchSound.cue(0);
                catchSound.play();
                world.increaseEggsCaught();
                world.deleteGameObject(this);
            }
        }
    }
}
