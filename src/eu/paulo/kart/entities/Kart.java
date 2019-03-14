package eu.paulo.kart.entities;

import java.time.LocalTime;

public class Kart {

    private LocalTime raceTime;
    private int lapsCompleted;

    public LocalTime getRaceTime() {
        return this.raceTime;
    }

    public int getLapsCompleted() {
        return this.lapsCompleted;
    }
}
