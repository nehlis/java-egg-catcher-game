package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.persistence.FilePersistence;

public class Statistics {
    private static       FilePersistence persistence;
    private static final String          highscorePath = "main/java/nl/han/ica/oopd/eggcatcher/media/highscore.txt";
    private static final String          lastScorePath = "main/java/nl/han/ica/oopd/eggcatcher/media/jouw-score.txt";

    /**
     * @param highscore Zet de nieuwe waarde van de highscore.
     */
    public static void setHighscore(int highscore) {
        persistence = new FilePersistence(highscorePath);

        if (persistence.fileExists()) persistence.saveData(String.valueOf(highscore));
    }

    /**
     * @param lastScore Zet de nieuwe waarde van de laatste score.
     */
    public static void setLastScore(int lastScore) {
        persistence = new FilePersistence(lastScorePath);

        if (persistence.fileExists()) persistence.saveData(String.valueOf(lastScore));
    }

    /**
     * @return De waarde van de highscore.
     */
    public static int getHighscore() {
        persistence = new FilePersistence(highscorePath);

        return persistence.fileExists() ? Integer.parseInt(persistence.loadDataString()) : 0;
    }

    /**
     * @return De waarde van de laatste score.
     */
    public static int getLastScore() {
        persistence = new FilePersistence(lastScorePath);

        return persistence.fileExists() ? Integer.parseInt(persistence.loadDataString()) : 0;
    }
}
