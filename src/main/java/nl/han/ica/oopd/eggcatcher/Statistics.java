package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.persistence.FilePersistence;

public class Statistics {
    private static       FilePersistence persistence;
    private static final String          path = "main/java/nl/han/ica/oopd/eggcatcher/media/highscore.txt";

    /**
     * @param highscore
     * Zet de nieuwe waarde van de highscore.
     */
    public static void setHighscore(int highscore) {
        persistence = new FilePersistence(path);

        if (persistence.fileExists()) persistence.saveData(String.valueOf(highscore));
    }

    /**
     * @return
     * De waarde van de highscore.
     */
    public static int getHighscore() {
        persistence = new FilePersistence(path);

        return persistence.fileExists() ? Integer.parseInt(persistence.loadDataString()) : 0;
    }
}
