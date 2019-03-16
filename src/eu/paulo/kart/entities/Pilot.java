package eu.paulo.kart.entities;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Pilot implements Comparable {

    int number;

    String name;
    private int position;
    private Duration totalRaceTime;
    private List<Lap> laps;

    public Pilot(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private Duration getTotalRaceTime() {
        return this.totalRaceTime;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", laps=" + laps +
                '}';
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

}
