package cupheadProject.View.Components;

import javafx.animation.AnimationTimer;

public abstract class PeriodicTask extends AnimationTimer {

    long nanosBetweenPulses;
    long lastPulseTimeStamp;


    public PeriodicTask(double secondsBetweenPulses){
        //if negative time, default to 0.5 seconds.
        if(secondsBetweenPulses < 0) secondsBetweenPulses = 500000L;

        //convert seconds to nanos;
        nanosBetweenPulses = (long) (secondsBetweenPulses * 1000000000L);
    }

    public void handle(long now) {
        //calculate time since last pulse in nanoseconds
        long nanosSinceLastPulse = now - lastPulseTimeStamp;

        //work out whether to fire another pulse
        if(nanosSinceLastPulse > nanosBetweenPulses){
            //reset timestamp
            lastPulseTimeStamp = now;

            //execute user's code
            run();
        }
    }
    public abstract void run();
}
