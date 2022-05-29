package cupheadProject.View.Components;

import javafx.animation.AnimationTimer;

public abstract class PeriodicTask extends AnimationTimer {

    long nanosBetweenPulses;
    long lastPulseTimeStamp;


    public PeriodicTask(double secondsBetweenPulses){

        if(secondsBetweenPulses < 0) secondsBetweenPulses = 500000L;


        nanosBetweenPulses = (long) (secondsBetweenPulses * 1000000000L);
    }

    public void handle(long now) {

        long nanosSinceLastPulse = now - lastPulseTimeStamp;


        if(nanosSinceLastPulse > nanosBetweenPulses){

            lastPulseTimeStamp = now;

            run();
        }
    }
    public abstract void run();
}
