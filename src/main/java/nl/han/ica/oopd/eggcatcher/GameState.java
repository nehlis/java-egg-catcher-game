package nl.han.ica.oopd.eggcatcher;

public class GameState {
    private static boolean playing = false;

    public static boolean isPlaying() {
        return playing;
    }

    public static void plays() {
        playing = true;
    }

    public static void died() {
        playing = false;
    }
}
