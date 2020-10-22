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
        if (getY() < world.getHeight() || !GameState.isPlaying()) return;

        fallSound.cue(0);
        fallSound.play();
        world.deleteAllGameOBjects();
        GameState.died();
    }

    @Override
    public void draw(PGraphics g) {
        if (!GameState.isPlaying()) return;

        g.noStroke();
        g.fill(255);
        g.pushMatrix();
        g.translate(x, y);
        g.beginShape();
        g.vertex(0, -100);
        g.bezierVertex(25, -100, 40, -65, 40, -40);
        g.bezierVertex(40, -15, 25, 0, 0, 0);
        g.bezierVertex(-25, 0, -40, -15, -40, -40);
        g.bezierVertex(-40, -65, -25, -100, 0, -100);
        g.scale((float) 0.5);
        g.endShape();
        g.popMatrix();
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
