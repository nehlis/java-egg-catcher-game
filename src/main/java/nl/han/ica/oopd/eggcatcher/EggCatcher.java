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
    private       TextObject dashboardText;
    private       int        eggsCaught  = 0;
    private final int        worldWidth  = 800;
    private final int        worldHeight = 600;
    private       Menu       menu;
    private       Dashboard  menuDashboard;
    private       Dashboard  gameDashboard;
    private       EggSpawner eggSpawner;


    public static void main(String[] args) {
        String[]   processingArgs = {"EggCatcher The Game"};
        EggCatcher game           = new EggCatcher();

        PApplet.runSketch(processingArgs, game);
    }

    /**
     * In deze methode worden de voor het spel
     * noodzakelijke zaken geïnitialiseerd
     */
    @Override
    public void setupGame() {
        setIcon();
        createMenuDashboard();
        createViewWithoutViewport();
    }

    public void setIcon() {
        ImageIcon gameIcon = new ImageIcon(loadBytes("src/main/java/nl/han/ica/oopd/eggcatcher/media/egg.png"));
        frame.setIconImage(gameIcon.getImage());
    }

    /**
     * Tekent het game dashboard.
     */
    private void createGameDashboard() {
        gameDashboard = new Dashboard(0, 0, worldWidth, worldHeight);
        dashboardText = new TextObject("Aantal gevangen eieren: " + eggsCaught);
        gameDashboard.addGameObject(dashboardText);
        addDashboard(gameDashboard);
    }

    /**
     * Verwijderd het game dashboard.
     */
    private void removeGameDashboard() {
        deleteDashboard(gameDashboard);
    }

    /**
     * Tekent het menu dashboard.
     */
    private void createMenuDashboard() {
        menuDashboard = new Dashboard(0, 0, worldWidth, worldHeight);
        menu = new Menu(this);
        menuDashboard.addGameObject(menu);
        addDashboard(menuDashboard);
    }

    /**
     * Verwijderd het menu dashboard.
     */
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
     */
    private void createViewWithoutViewport() {
        View view = new View(worldWidth, worldHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/oopd/eggcatcher/media/background.jpg"));


        setView(view);
        size(worldWidth, worldHeight);
    }

    /**
     * Berekening van het plaatsen van de kippen.
     * @return Een derde van de breedte van de world.
     */
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
            startGame();
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

    /**
     * Reset de game nadat de speler af is.
     */
    public void reset() {
        removeGameDashboard();
        SoundController.pause(SoundController.getBackgroundSound());
        eggSpawner.stopAlarm();
        resetEggsCounter();
        createMenuDashboard();
    }

    /**
     * Reset de gevangen eieren counter.
     */
    public void resetEggsCounter() {
        eggsCaught = 0;
    }

    /**
     * Start de game op.
     */
    public void startGame() {
        removeMenuDashboard();
        createGameDashboard();
        initializeTileMap();
        SoundController.init(this);
        createObjects();
        createEggSpawner();
    }

    /**
     * Retourneerd het aantal gevangen eieren.
     * @return eggsCaught
     */
    public int getEggsCaught() {
        return eggsCaught;
    }

    @Override
    public void update() {}
}