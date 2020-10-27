package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

import java.util.Random;

public class EggSpawner implements IAlarmListener {
    private final float      eggsPerSecond;
    private final Random     random;
    private final EggCatcher world;
    private       Chicken[]  chickens;
    private       Alarm      alarm;

    /**
     * Constructor
     *
     * @param world         Referentie naar de wereld
     * @param eggsPerSecond Aantal eieren dat per seconden gemaakt moet worden.
     */
    public EggSpawner(EggCatcher world, float eggsPerSecond) {
        this.eggsPerSecond = eggsPerSecond;
        this.world = world;
        random = new Random();

        generateChickens();
        startAlarm();
    }

    /**
     * Genereerd de kippen die de eieren laten vallen.
     */
    private void generateChickens() {
        chickens = new Chicken[]{new Chicken(world), new Chicken(world), new Chicken(world)};

        for (int i = 0; i < chickens.length; i++) {
            world.addGameObject(chickens[i], (world.getThirdOfWorldSize() * (i + 1)) - (world.getThirdOfWorldSize() / 2) - (117/2), 10);
        }
    }

    /**
     * Begint het alarm.
     */
    private void startAlarm() {
        alarm = new Alarm("New egg", 1 / eggsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    /**
     * Stopt het alarm.
     */
    public void stopAlarm() {
        alarm.stop();
    }

    /**
     * Triggert het alarm.
     *
     * @param alarmName Naam van het alarm
     */
    @Override
    public void triggerAlarm(String alarmName) {
        Egg     b             = new Egg(world);
        Chicken spawnLocation = getRandomChicken();

        world.addGameObject(b, spawnLocation.getX(), spawnLocation.getY());
        startAlarm();
    }

    /**
     * @return Returns a random chicken.
     */
    public Chicken getRandomChicken() {
        return chickens[random.nextInt(chickens.length)];
    }
}
