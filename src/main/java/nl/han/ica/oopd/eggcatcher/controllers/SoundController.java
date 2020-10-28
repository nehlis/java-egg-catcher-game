package nl.han.ica.oopd.eggcatcher.controllers;

import nl.han.ica.oopd.eggcatcher.EggCatcher;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.sound.Sound;

public class SoundController {
    public static Sound eggFallSound;
    public static Sound eggCatchSound;
    public static Sound backgroundSound;


    /**
     * Initialiseert alle geluiden en speelt het achtergrondsgeluid.
     *
     * @param engine De Game Engine.
     */
    public static void init(GameEngine engine) {
        eggFallSound    = new Sound(engine, "src/main/java/nl/han/ica/oopd/eggcatcher/media/game-over.mp3");
        eggCatchSound   = new Sound(engine, "src/main/java/nl/han/ica/oopd/eggcatcher/media/pop.mp3");
        backgroundSound = new Sound(engine, "src/main/java/nl/han/ica/oopd/eggcatcher/media/eggcatcher.mp3");
        backgroundSound.loop(-1);

        play("background");
    }

    /**
     * Pauzeert een bepaald geluid.
     *
     * @param sound Het desbetreffende geluid.
     */
    public static void pause(Sound sound) {
        sound.pause();
    }

    /**
     * Speelt een bepaald geluid.
     *
     * @param sound Het desbetreffende geluid.
     */
    public static void play(String sound) {
        Sound playable;

        switch (sound) {
            case "background":
                playable = backgroundSound;
                break;
            case "catch":
                playable = eggCatchSound;
                break;
            case "fall":
                playable = eggFallSound;
                break;
            default:
                return;
        }

        playable.cue(0);
        playable.play();
    }

    /**
     * @return Retourneert het achtergrondsgeluid.
     */
    public static Sound getBackgroundSound() {
        return backgroundSound;
    }
}
