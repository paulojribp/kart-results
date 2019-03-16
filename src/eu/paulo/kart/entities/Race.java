package eu.paulo.kart.entities;

import java.util.Set;

public class Race {

    private int totalLaps;
    private Set<Pilot> pilots;

    public int getTotalLaps() {
        return this.totalLaps;
    }

    public Set<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(Set<Pilot> pilots) {
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
