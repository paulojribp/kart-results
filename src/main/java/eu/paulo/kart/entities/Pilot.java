package eu.paulo.kart.entities;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Pilot implements Comparable {

    private int number;

    private String name;
    private int position;
    private Duration totalRaceTime;
    private List<Lap> laps;
    private Lap bestLap;
    private double avgSpeed;

    public Pilot(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Lap> getLaps() {
        return laps;
    }

    public void setTotalRaceTime(Duration totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    public Duration getTotalRaceTime() {
        return this.totalRaceTime;
    }

    @Override
    public String toString() {
        return "| #" + position + " | " + number + " - " + name + " | " + laps.size() + " | " + (totalRaceTime.getSeconds() / 60) + ":"
                + (totalRaceTime.getSeconds() % 60) + "." + totalRaceTime.getNano() + " |";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilot pilot = (Pilot) o;
        return number == pilot.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public void setLaps(List<Lap> laps) {
        this.laps = laps;
    }

    @Override
    public int compareTo(Object o) {
        if (o != null && o instanceof Pilot) {
            Pilot pilot = (Pilot) o;

            if (this.totalRaceTime != null && pilot.getTotalRaceTime() != null)
                return this.totalRaceTime.compareTo(pilot.getTotalRaceTime());
        }

        return 0;
    }

    public void setBestLap(Lap bestLap) {
        this.bestLap = bestLap;
    }

    public Lap getBestLap() {
        return bestLap;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public int getAmountOfLaps() {
        if (this.laps != null)
            return this.laps.size();

        return 0;
    }
}
