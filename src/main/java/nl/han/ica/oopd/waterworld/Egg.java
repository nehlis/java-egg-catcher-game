package nl.han.ica.oopd.waterworld;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PGraphics;

import java.util.List;

/**
 * @author Ralph Niels
 * Bel-klasse
 */
public class Egg extends GameObject implements ICollidableWithGameObjects {

    private final Sound      fallSound;
    private       EggCatcher world;
    private       int        eggSize;

    /**
     * Constructor
     *
     * @param eggSize   Afmeting van de bel
     * @param world     Referentie naar de wereld
     * @param fallSound Geluid dat moet klinken als het ei valt.
     */
    public Egg(int eggSize, EggCatcher world, Sound fallSound) {
        this.eggSize = eggSize;
        this.fallSound = fallSound;
        this.world = world;
        setySpeed(-eggSize / 10f);
        /* De volgende regels zijn in een zelfgekend object nodig
            om collisiondetectie mogelijk te maken.
         */
        setHeight(eggSize);
        setWidth(eggSize);
    }

    @Override
    public void update() {
        if (getY() <= 100) {
            world.deleteGameObject(this);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.ellipseMode(g.CORNER); // Omdat cirkel anders vanuit midden wordt getekend en dat problemen geeft bij collisiondetectie
        g.stroke(0, 50, 200, 100);
        g.fill(0, 50, 200, 50);
        g.ellipse(getX(), getY(), eggSize, eggSize);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Swordfish) {
                fallSound.rewind();
                fallSound.play();
                world.deleteGameObject(this);
                world.increaseBubblesPopped();
            }
        }
    }
}
