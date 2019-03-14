package eu.paulo.kart.entities;

import java.util.List;

public class Race {

    private List<Kart> karts;
    private int totalLaps;

    public List<Kart> getKarts() {
        return this.karts;
    }

    public int getTotalLaps() {
        return this.totalLaps;
    }
}
