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

        g.textAlign(g.CENTER, g.CENTER);
        g.textSize(50);
        g.text("start", 400, 300);
    }
}
