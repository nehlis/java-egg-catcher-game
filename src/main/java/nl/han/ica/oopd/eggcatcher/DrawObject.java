package nl.han.ica.oopd.eggcatcher;

import processing.core.PGraphics;

public class DrawObject {
    /**
     * Retourneert een ei vorm.
     *
     * @param g Graphics engine.
     * @param x x as.
     * @param y y as.
     */
    public static void getEggShape(PGraphics g, float x, float y) {
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
}
