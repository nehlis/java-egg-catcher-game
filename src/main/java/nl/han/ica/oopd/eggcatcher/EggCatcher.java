package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopd.eggcatcher.controllers.SoundController;
import nl.han.ica.oopd.eggcatcher.dashboards.GameDashboard;
import nl.han.ica.oopd.eggcatcher.dashboards.MenuDashboard;
import nl.han.ica.oopd.eggcatcher.tiles.BoardsTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

import javax.swing.*;

public class EggCatcher extends GameEngine {
    private      int                 eggsCaught  = 0;
    public final int                 worldWidth  = 800;
    public final int                 worldHeight = 600;
    private      EggSpawner          eggSpawner;
    public       MenuDashboard       menuD;
    public       GameDashboard       gameD;

    public static void main(String[] args) {
        PApplet.runSketch(new String[]{"EggCatcher The Game"}, new EggCatcher());
    }

    /**
     * In deze methode worden de voor het spel
     * noodzakelijke zaken geïnitialiseerd
     */
    @Override
    public void setupGame() {
        menuD = new MenuDashboard(this);
        gameD = new GameDashboard(this);

        setIcon();
        menuD.create();
        createViewWithoutViewport();
    }

    public void setIcon() {
        ImageIcon gameIcon = new ImageIcon(loadBytes("src/main/java/nl/han/ica/oopd/eggcatcher/media/egg.png"));
        frame.setIconImage(gameIcon.getImage());
    }

    /**
     * Maakt de spawner voor de eieren aan
     */
    public void createEggSpawner() {
        eggSpawner = new EggSpawner(this, 1);
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
     *
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
        gameD.refresh();
    }

    /**
     * Initialiseert de tilemap
     */
    private void initializeTileMap() {
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, null);

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
     * Reset de game nadat de speler af is.
     */
    public void reset() {
        gameD.destroy();
        SoundController.pause(SoundController.getBackgroundSound());
        eggSpawner.stopAlarm();
        resetEggsCounter();
        menuD.create();
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
        menuD.destroy();
        gameD.create();
        initializeTileMap();
        SoundController.init(this);
        createObjects();
        createEggSpawner();
    }

    /**
     * Retourneert het aantal gevangen eieren.
     *
     * @return eggsCaught
     */
    public int getEggsCaught() {
        return eggsCaught;
    }

    @Override
    public void update() {
    }
}