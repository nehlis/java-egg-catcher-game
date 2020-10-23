package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopd.eggcatcher.tiles.BoardsTile;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

import javax.swing.*;

public class EggCatcher extends GameEngine {
    private TextObject dashboardText;
    private int        eggsCaught = 0;
    private int        worldWidth;
    private int        worldHeight;
    private Menu       menu;
    private Dashboard  menuDashboard;
    private Dashboard  gameDashboard;
    private EggSpawner eggSpawner;


    public static void main(String[] args) {
        String[]   processingArgs = { "EggCatcher The Game" };
        EggCatcher game           = new EggCatcher();

        PApplet.runSketch(processingArgs, game);
    }

    /**
     * In deze methode worden de voor het spel
     * noodzakelijke zaken geïnitialiseerd
     */
    @Override
    public void setupGame() {
        this.worldWidth = 800;
        this.worldHeight = 600;

        ImageIcon gameIcon = new ImageIcon(loadBytes("src/main/java/nl/han/ica/oopd/eggcatcher/media/egg.png"));
        frame.setIconImage(gameIcon.getImage());

        createMenuDashboard();
        createViewWithoutViewport(worldWidth, worldHeight);
    }

    private void createGameDashboard() {
        gameDashboard = new Dashboard(0, 0, worldWidth, worldHeight);
        dashboardText = new TextObject("Aantal gevangen eieren: " + eggsCaught);
        gameDashboard.addGameObject(dashboardText);
        addDashboard(gameDashboard);
    }

    private void removeGameDashboard() {
        deleteDashboard(gameDashboard);
    }

    private void createMenuDashboard() {
        menuDashboard = new Dashboard(0, 0, worldWidth, worldHeight);
        menu = new Menu(this);
        menuDashboard.addGameObject(menu);
        addDashboard(menuDashboard);
    }

    private void removeMenuDashboard() {
        deleteDashboard(menuDashboard);
        deleteGameObject(menu);
    }

    /**
     * Maakt de spawner voor de eieren aan
     */
    public void createEggSpawner() {
        eggSpawner = new EggSpawner(this, SoundController.getEggFallSound(), SoundController.getEggCatchSound(), 1);
    }

    /**
     * Maakt de spelobjecten aan
     */
    private void createObjects() {
        Player player = new Player(this);
        addGameObject(player, (float) this.worldWidth / 2, this.worldHeight - 75);
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

    public int getEggsCaught() {
        return eggsCaught;
    }

    public float getThirdOfWorldSize() {
        return (float) worldWidth / 3;
    }

    /**
     * Verhoogt de teller voor het aantal eieren
     */
    public void increaseEggsCaught() {
        eggsCaught++;

        refreshDasboardText();
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
    public void keyPressed() {
        if (!GameState.isPlaying() && key == ' ') {
            GameState.plays();
            start();
            return;
        }

        super.keyPressed();
    }

    /**
     * Vernieuwt het dashboard
     */
    public void refreshDasboardText() {
        dashboardText.setText("Aantal gevangen eieren: " + eggsCaught);
    }

    public void reset() {
        removeGameDashboard();
        SoundController.pause(SoundController.getBackgroundSound());
        eggSpawner.stopAlarm();
        resetEggsCounter();
        createMenuDashboard();
    }

    public void resetEggsCounter() {
        eggsCaught = 0;
    }



    public void start() {
        removeMenuDashboard();
        createGameDashboard();
        initializeTileMap();
        SoundController.init(this);
        createObjects();
        createEggSpawner();
    }

    @Override
    public void update() {}
}