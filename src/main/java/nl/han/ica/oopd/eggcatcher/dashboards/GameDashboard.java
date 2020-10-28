package nl.han.ica.oopd.eggcatcher.dashboards;

import nl.han.ica.oopd.eggcatcher.EggCatcher;
import nl.han.ica.oopd.eggcatcher.TextObject;
import nl.han.ica.oopd.eggcatcher.interfaces.IDashboard;
import nl.han.ica.oopg.dashboard.Dashboard;

public class GameDashboard implements IDashboard {
    private final EggCatcher game;
    private       Dashboard  dashboard;
    private       TextObject text;

    /**
     * Constructor
     *
     * @param game De game
     */
    public GameDashboard(EggCatcher game) {
        this.game = game;
    }

    /**
     * Maakt het game dashboard aan.
     */
    @Override
    public void create() {
        dashboard = new Dashboard(0, 0, game.worldWidth, game.worldHeight);
        text = new TextObject("Aantal gevangen eieren: " + game.getEggsCaught());
        dashboard.addGameObject(text);
        game.addDashboard(dashboard);
    }

    /**
     * Verwijdert het game dashboard.
     */
    @Override
    public void destroy() {
        game.deleteDashboard(dashboard);
    }

    /**
     * Vernieuwt het game dashboard.
     */
    public void refresh() {
        text.setText("Aantal gevangen eieren: " + game.getEggsCaught());
    }
}
