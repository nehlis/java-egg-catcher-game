package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.sound.Sound;

import java.util.Random;

public class EggSpawner implements IAlarmListener {

    private final float      eggsPerSecond;
    private final Random     random;
    private final EggCatcher world;
    private final Sound      fallSound;
    private final Sound      catchSound;
    private       Chicken[]  chickens;
    private       Alarm      alarm;

    /**
     * Constructor
     *
     * @param world         Referentie naar de wereld
     * @param fallSound     Geluid dat moet klinken als een ei valt.
     * @param eggsPerSecond Aantal eieren dat per seconden gemaakt moet worden.
     */
    public EggSpawner(EggCatcher world, Sound fallSound, Sound catchSound, float eggsPerSecond) {
        this.eggsPerSecond = eggsPerSecond;
        this.world = world;
        this.fallSound = fallSound;
        this.catchSound = catchSound;
        random = new Random();

        generateChickens();
        startAlarm();
    }

    private void generateChickens() {
        chickens = new Chicken[]{new Chicken(), new Chicken(), new Chicken()};

        for (int i = 0; i < chickens.length; i++) {
            world.addGameObject(chickens[i], (world.getThirdOfWorldSize() * (i + 1)) - (world.getThirdOfWorldSize() / 2), 75);
        }
    }

    private void startAlarm() {
        alarm = new Alarm("New egg", 1 / eggsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    public void stopAlarm() {
        alarm.stop();

    }


    @Override
    public void triggerAlarm(String alarmName) {
        Egg     b             = new Egg(world, fallSound, catchSound);
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
