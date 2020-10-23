package nl.han.ica.oopd.eggcatcher;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.sound.Sound;

public class SoundController {
    public static Sound eggFallSound;
    public static Sound eggCatchSound;
    public static Sound backgroundSound;

    public void init(GameEngine engine) {
        eggFallSound    = new Sound(engine, "src/main/java/nl/han/ica/oopd/eggcatcher/media/game-over.mp3");
        eggCatchSound   = new Sound(engine, "src/main/java/nl/han/ica/oopd/eggcatcher/media/pop.mp3");

        backgroundSound = new Sound(engine, "src/main/java/nl/han/ica/oopd/eggcatcher/media/waterworld.mp3");
        backgroundSound.loop(-1);
    }

    public static Sound getEggCatchSound() {
        return eggCatchSound;
    }

    public static Sound getEggFallSound() {
        return eggFallSound;
    }

    public static Sound getBackgroundSound() {
        return backgroundSound;
    }
}
