package nl.han.ica.oopd.eggcatcher.controllers;

import nl.han.ica.oopd.eggcatcher.EggCatcher;
import nl.han.ica.oopd.eggcatcher.Menu;
import nl.han.ica.oopd.eggcatcher.TextObject;
import nl.han.ica.oopg.dashboard.Dashboard;

public class DashboardController {
    private       Menu       menu;
    private       Dashboard  menuDashboard;
    private       Dashboard  gameDashboard;
    private       TextObject dashboardText;
    private final EggCatcher game;

    public DashboardController(EggCatcher game) {
        this.game = game;
    }

    /**
     * Tekent het game dashboard.
     */
    public void createGameDashboard() {
        gameDashboard = new Dashboard(0, 0, game.worldWidth, game.worldHeight);
        dashboardText = new TextObject("Aantal gevangen eieren: " + game.getEggsCaught());
        gameDashboard.addGameObject(dashboardText);
        game.addDashboard(gameDashboard);
    }

    /**
     * Verwijderd het game dashboard.
     */
    public void removeGameDashboard() {
        game.deleteDashboard(gameDashboard);
    }

    /**
     * Tekent het menu dashboard.
     */
    public void createMenuDashboard() {
        menuDashboard = new Dashboard(0, 0, game.worldWidth, game.worldHeight);
        menu = new Menu(game);
        menuDashboard.addGameObject(menu);
        game.addDashboard(menuDashboard);
    }

    /**
     * Verwijderd het menu dashboard.
     */
    public void removeMenuDashboard() {
        game.deleteDashboard(menuDashboard);
        game.deleteGameObject(menu);
    }

    /**
     * Vernieuwt het dashboard
     */
    public void refreshGameDashboardText() {
        dashboardText.setText("Aantal gevangen eieren: " + game.getEggsCaught());
    }
}
