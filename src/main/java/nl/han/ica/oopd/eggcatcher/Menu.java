package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;


public class Menu extends GameObject {
    private final EggCatcher game;

    /**
     * Constructor
     *
     * @param game EggCatcher
     */
    public Menu(EggCatcher game) {
        this.game = game;
    }

    /**
     * Update het menu object.
     */
    @Override
    public void update() {}

    /**
     * Tekent het menu.
     *
     * @param g graphics engine.
     */
    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.RIGHT, g.BOTTOM);
        g.textSize(50);
        g.text("Highscore: " + Statistics.getHighscore(), 800, 600);

        g.textAlign(g.CENTER, g.CENTER);
        g.textSize(50);
        g.text("Jouw score: " + Statistics.getLastScore(), 400, 200);

        g.textAlign(g.CENTER, g.CENTER);
        g.textSize(30);
        g.text("Druk op spatie om te starten", 400, 300);
    }

    /**
     * Key press event.
     *
     * @param keyCode Key pressed code.
     * @param key     Key pressed char.
     */
    @Override
    public void keyPressed(int keyCode, char key) {
        game.init();
        game.deleteGameObject(this);
    }
}
