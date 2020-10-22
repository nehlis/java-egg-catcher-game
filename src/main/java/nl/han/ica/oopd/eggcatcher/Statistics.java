package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.persistence.FilePersistence;

public class Statistics {
    private static FilePersistence persistence;

    public static void setHighscore(int highscore) {
        persistence = new FilePersistence("main/java/nl/han/ica/oopd/eggcatcher/media/highscore.txt");

        if(persistence.fileExists()) persistence.saveData(String.valueOf(highscore));
    }

    public static int getHighscore() {
        persistence = new FilePersistence("main/java/nl/han/ica/oopd/eggcatcher/media/highscore.txt");

        return persistence.fileExists() ? Integer.parseInt(persistence.loadDataString()) : 0;
    }
}
