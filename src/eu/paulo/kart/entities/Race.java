package eu.paulo.kart.entities;

import java.util.List;

public class Race {

    public static final int LAST_POSITION = 40;
    private int totalLaps;
    private List<Pilot> pilots;

    public int getTotalLaps() {
        return this.totalLaps;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }

    @Override
    public String toString() {
        return "Race{" +
                "totalLaps=" + totalLaps +
                ", pilots=" + pilots +
                '}';
    }

}
