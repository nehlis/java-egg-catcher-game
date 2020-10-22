package nl.han.ica.oopd.eggcatcher;


import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Menu extends GameObject  {

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {


        g.textAlign(g.RIGHT,g.BOTTOM);
        g.textSize(50);
        g.text("Highscore: " + Statistics.getHighscore(),800,600);

        g.textAlign(g.CENTER, g.CENTER);
        g.textSize(50);
        g.text("Jouw score: " + Statistics.getHighscore(), 400, 200);

        g.textAlign(g.CENTER, g.CENTER);
        g.textSize(50);
        g.text("Start", 400, 300);

        g.textAlign(g.CENTER, g.CENTER);
        g.textSize(50);
        g.text("Exit", 400, 400);
    }
}
