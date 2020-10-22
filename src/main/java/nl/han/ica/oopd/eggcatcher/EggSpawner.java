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

        startAlarm();
    }

    private void startAlarm() {
        Alarm alarm = new Alarm("New egg", 1 / eggsPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        Egg b = new Egg(world, fallSound, catchSound);
        world.addGameObject(b, getEggLocation(), 0);
        startAlarm();
    }

    /**
     * @return The chickens random egg location
     */
    public int getEggLocation() {
        return ((this.world.getWorldWidth() / 3) * (random.nextInt(3) + 1)) - ((this.world.getWorldWidth() / 3) / 2);
    }
}
