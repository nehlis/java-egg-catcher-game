package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;

    /**
     * @param text De tekst die getoont moet worden.
     */
    public TextObject(String text) {
        this.text = text;
    }

    /**
     * @param text De set-bare tekst.
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {
    }

    /**
     * Tekent het tekstobject.
     *
     * @param g De graphics engine.
     */
    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT, g.TOP);
        g.textSize(50);
        g.text(text, getX(), getY());
    }
}
