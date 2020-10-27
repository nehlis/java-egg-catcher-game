package nl.han.ica.oopd.eggcatcher;

public class GameState {
    private static boolean playing = false;

    /**
     * @return Of de speler aan het spelen is.
     */
    public static boolean isPlaying() {
        return playing;
    }

    /**
     * Begint het spel.
     */
    public static void plays() {
        playing = true;
    }

    /**
     * Stopt het spel.
     */
    public static void died() {
        playing = false;
    }
}
