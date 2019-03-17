package eu.paulo.kart.entities;

import java.util.List;

public class Race {

    public static final int LAST_POSITION = 40;
    private Lap bestLap;
    private List<Pilot> pilots;

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }

    public Lap getBestLap() {
        return this.bestLap;
    }

    public void setBestLap(Lap bestLap) {
        this.bestLap = bestLap;
    }

    @Override
    public String toString() {
        return "Race{" +
                ", pilots=" + pilots +
                '}';
    }

}
