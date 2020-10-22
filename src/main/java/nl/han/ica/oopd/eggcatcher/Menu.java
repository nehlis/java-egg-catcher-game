package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;


public class Menu extends GameObject {

    EggCatcher game;

    public Menu(EggCatcher game) {
        this.game = game;

    }

    @Override
    public void update() {

    }

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

    @Override
    public void keyPressed(int keyCode, char key) {
        System.out.println("test");
        game.init();
        game.deleteGameObject(this);
    }
}
