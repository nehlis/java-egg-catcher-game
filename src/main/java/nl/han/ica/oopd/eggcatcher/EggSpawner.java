package nl.han.ica.oopd.eggcatcher;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.sound.Sound;

import java.util.Random;

/**
 * @author Ralph Niels
 * Klasse die Bubbles aanmaakt met configureerbare
 * snelheid.
 */
public class EggSpawner implements IAlarmListener {

    private float      eggsPerSecond;
    private Random     random;
    private EggCatcher world;
    private Sound      fallSound;

    /**
     * Constructor
     *
     * @param world         Referentie naar de wereld
     * @param fallSound     Geluid dat moet klinken als een ei valt.
     * @param eggsPerSecond Aantal eieren dat per seconden gemaakt moet worden.
     */
    public EggSpawner(EggCatcher world, Sound fallSound, float eggsPerSecond) {
        this.eggsPerSecond = eggsPerSecond;
        this.world = world;
        this.fallSound = fallSound;
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
        int bubbleSize = random.nextInt(10) + 10;
        Egg b          = new Egg(bubbleSize, world, fallSound);
        world.addGameObject(b, random.nextInt(world.width), world.height);
        startAlarm();
    }
}
