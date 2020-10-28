package nl.han.ica.oopd.eggcatcher.dashboards;

import nl.han.ica.oopd.eggcatcher.EggCatcher;
import nl.han.ica.oopd.eggcatcher.Menu;
import nl.han.ica.oopd.eggcatcher.interfaces.IDashboard;
import nl.han.ica.oopg.dashboard.Dashboard;

public class MenuDashboard implements IDashboard {
    private final EggCatcher game;
    private       Dashboard  dashboard;
    private       Menu       menu;

    /**
     * Constructor
     *
     * @param game De game
     */
    public MenuDashboard(EggCatcher game) {
        this.game = game;
    }

    /**
     * Maakt het menu dashboard aan.
     */
    @Override
    public void create() {
        dashboard = new Dashboard(0, 0, game.worldWidth, game.worldHeight);
        menu = new Menu(game);
        dashboard.addGameObject(menu);
        game.addDashboard(dashboard);
    }

    /**
     * Verwijdert het menu dashboard.
     */
    @Override
    public void destroy() {
        game.deleteDashboard(dashboard);
        game.deleteGameObject(menu);
    }

    @Override
    public void refresh() {}
}
