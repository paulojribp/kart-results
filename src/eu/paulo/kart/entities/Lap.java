package eu.paulo.kart.entities;

import eu.paulo.kart.wrappers.LapWrapper;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Lap {

    private int number;

    private LocalTime hour;

    private Duration time;

    private double avgSpeed;

    public Lap() { }

    public Lap(LapWrapper lapWrapper) {
        this.number = lapWrapper.getNumber();
        this.hour = lapWrapper.getHour();
        this.time = lapWrapper.getTime();
        this.avgSpeed = lapWrapper.getAverageSpeed();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lap lap = (Lap) o;
        return number == lap.number;
    }

    @Override
    public int hashCode() {

        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Lap{" +
                "number=" + number +
                ", hour=" + hour +
                ", time=" + time +
                ", avgSpeed=" + avgSpeed +
                '}';
    }
}
