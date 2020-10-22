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

import javax.swing.*;

public class EggCatcher extends GameEngine {
    private TextObject dashboardText;
    private Sound backgroundSound;
    private Sound eggFallSound;
    private Sound eggCatchSound;
    private int eggsCaught = 0;
    private int worldWidth;
    private int worldHeight;
    private Menu menu;
    private Dashboard dashboardMenu;
    private Dashboard dashboardGame;
    private EggSpawner eggSpawner;
    private ImageIcon gameIcon;


    public static void main(String[] args) {
        String[] processingArgs = {"EggCatcher The Game"};
        EggCatcher mySketch = new EggCatcher();

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
        gameIcon = new ImageIcon(loadBytes("src/main/java/nl/han/ica/oopd/eggcatcher/media/egg.png"));
        frame.setIconImage(gameIcon.getImage());

        menuDashboard(worldWidth, worldHeight);
        createViewWithoutViewport(worldWidth, worldHeight);
    }

    public void startGame() {
        createDashboard(worldWidth, worldHeight);
        initializeTileMap();
        initializeSound();
        createObjects();
        createEggSpawner();
    }

    /**
     * Initialiseert geluid
     */
    private void initializeSound() {
        backgroundSound = new Sound(this, "src/main/java/nl/han/ica/oopd/eggcatcher/media/waterworld.mp3");
        backgroundSound.loop(-1);

        eggFallSound = new Sound(this, "src/main/java/nl/han/ica/oopd/eggcatcher/media/game-over.mp3");
        eggCatchSound = new Sound(this, "src/main/java/nl/han/ica/oopd/eggcatcher/media/pop.mp3");
    }

    private void pauseMusic() {
        backgroundSound.pause();
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
     * Maakt de spawner voor de eieren aan
     */
    public void createEggSpawner() {
        eggSpawner = new EggSpawner(this, eggFallSound, eggCatchSound, 1);
    }

    /**
     * Maakt het dashboard aan
     *
     * @param dashboardWidth  Gewenste breedte van dashboard
     * @param dashboardHeight Gewenste hoogte van dashboard
     */
    private void menuDashboard(int dashboardWidth, int dashboardHeight) {
        dashboardMenu = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        menu = new Menu(this);
        dashboardMenu.addGameObject(menu);
        addDashboard(dashboardMenu);
    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        dashboardGame = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("Aantal gevangen eieren: " + eggsCaught);
        dashboardGame.addGameObject(dashboardText);
        addDashboard(dashboardGame);
    }

    /**
     * Initialiseert de tilemap
     */
    private void initializeTileMap() {
        Sprite boardsSprite = new Sprite("src/main/java/nl/han/ica/oopd/eggcatcher/media/boards-tile.jpg");
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardsSprite);

        TileType[] tileTypes = {boardTileType};
        int tileSize = 50;
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
     * Verhoogt de teller voor het aantal eieren
     */
    public void increaseEggsCaught() {
        eggsCaught++;

        Statistics.setHighscore(Math.max(Statistics.getHighscore(), eggsCaught));
        Statistics.setLastScore(eggsCaught);

        refreshDasboardText();
    }

    public void resetEggsCounter() {
        eggsCaught = 0;
    }

    public float getThirdOfWorldSize() {
        return (float) worldWidth / 3;
    }

    @Override
    public void keyPressed() {
        System.out.println(key);
        if (key == ' ') {
            if (!GameState.isPlaying()) {
                GameState.plays();
                deleteGameObject(menu);
                deleteDashboard(dashboardMenu);
                startGame();
            }
        } else {
            super.keyPressed();
        }
    }

    public void reset() {
        deleteDashboard(dashboardGame);
        pauseMusic();
        dashboardGame = null;
        eggSpawner.stopAlarm();
        resetEggsCounter();
        menuDashboard(worldWidth, worldHeight);
    }
}