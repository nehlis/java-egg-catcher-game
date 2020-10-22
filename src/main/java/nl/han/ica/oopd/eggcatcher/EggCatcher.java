package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopd.eggcatcher.tiles.BoardsTile;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;
import processing.core.PGraphics;

public class EggCatcher extends GameEngine {
    private TextObject dashboardText;
    private Sound      eggFallSound;
    private Sound      eggCatchSound;
    private int        eggsCaught;
    private int        worldWidth;
    private int        worldHeight;


    public static void main(String[] args) {
        String[]   processingArgs = {"nl.han.ica.oopd.eggcatcher.EggCatcher"};
        EggCatcher mySketch       = new EggCatcher();

        PApplet.runSketch(processingArgs, mySketch);
    }

    /**
     * In deze methode worden de voor het spel
     * noodzakelijke zaken geïnitialiseerd
     */
    @Override
    public void setupGame() {
        this.worldWidth = 800;
        this.worldHeight = 600;


        menuDashboard(worldWidth,worldHeight);

        //initializeSound();
        //createDashboard(worldWidth, worldHeight);
        //initializeTileMap();

        //createObjects();
        //createEggSpawner();

        createViewWithoutViewport(worldWidth, worldHeight);
    }

    /**
     * Initialiseert geluid
     */
    private void initializeSound() {
        Sound backgroundSound = new Sound(this, "src/main/java/nl/han/ica/oopd/eggcatcher/media/waterworld.mp3");
        backgroundSound.loop(-1);

        eggFallSound = new Sound(this, "src/main/java/nl/han/ica/oopd/eggcatcher/media/game-over.mp3");
        eggCatchSound = new Sound(this, "src/main/java/nl/han/ica/oopd/eggcatcher/media/pop.mp3");
    }

    /**
     * Creeërt de view zonder viewport
     *
     * @param screenWidth  Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
     */
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/oopd/eggcatcher/media/background.jpg"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    /**
     * Maakt de spelobjecten aan
     */
    private void createObjects() {
        Player player = new Player(this);
        addGameObject(player, (float) this.worldWidth / 2, this.worldHeight - 75);
    }

    /**
     * Maakt de spawner voor de bellen aan
     */
    public void createEggSpawner() {
        new EggSpawner(this, eggFallSound, eggCatchSound, 1);
    }

    /**
     * Maakt het dashboard aan
     *
     * @param dashboardWidth  Gewenste breedte van dashboard
     * @param dashboardHeight Gewenste hoogte van dashboard
     */
    private void menuDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        Menu menu = new Menu();
        dashboard.addGameObject(menu);
        addDashboard(dashboard);
    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("Aantal gevangen eieren:");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    /**
     * Initialiseert de tilemap
     */
    private void initializeTileMap() {
        Sprite               boardsSprite  = new Sprite("src/main/java/nl/han/ica/oopd/eggcatcher/media/boards-tile.jpg");
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardsSprite);

        TileType[] tileTypes = {boardTileType};
        int        tileSize  = 50;
        int[][] tilesMap = {
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    @Override
    public void update() {
    }

    /**
     * Vernieuwt het dashboard
     */
    private void refreshDasboardText() {
        dashboardText.setText("Aantal gevangen eieren: " + eggsCaught);
    }

    /**
     * Verhoogt de teller voor het aantal
     * geknapte bellen met 1
     */
    public void increaseEggsCaught() {
        eggsCaught++;

        if (Statistics.getHighscore() < eggsCaught) {
            Statistics.setHighscore(eggsCaught);
        }

        Statistics.setLastScore(eggsCaught);

        refreshDasboardText();
    }

    public float getThirdOfWorldSize() {
        return (float) worldWidth / 3;
    }
}
