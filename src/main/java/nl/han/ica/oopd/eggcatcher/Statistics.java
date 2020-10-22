package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.persistence.FilePersistence;

public class Statistics {
    public static int highscore;

    public static void setHighscore(int _highscore) {
        highscore = _highscore;
        saveHighscore();
    }

    public static int getHighscore() {
        return highscore;
    }

    public static void saveHighscore() {
        FilePersistence persistence = new FilePersistence("main/java/nl/han/ica/oopd/waterworld/media/highscore.txt");
        if (persistence.fileExists()) {
            persistence.saveData(String.valueOf(highscore));
        }
    }
}
