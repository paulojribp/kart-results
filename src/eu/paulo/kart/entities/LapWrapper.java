package eu.paulo.kart.entities;

import java.time.LocalTime;

public class LapWrapper {

    private LocalTime hour;

    private Pilot pilot;

    private int number;

    private LocalTime time;

    private double averageSpeed;

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    @Override
    public String toString() {
        return "{" +
                "hour=" + hour +
                ", pilot=" + pilot +
                ", number=" + number +
                ", time=" + time +
                ", averageSpeed=" + averageSpeed +
                '}';
    }

    public void parseLineToHour(String line) {
        LocalTime kartHour = LocalTime.parse(line.substring(0, 17).trim());
        this.hour = kartHour;
    }

    public void parsePilot(String line) {
        String numberString = line.substring(18,21);
        numeroString != null ? Integer.parseInt(numberString) : 0;
        this.pilot = new Pilot(
                ,
                line.substring(24,58).trim()
        );
    }
}
