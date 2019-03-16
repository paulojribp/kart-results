package eu.paulo.kart.wrappers;

import eu.paulo.kart.entities.Lap;
import eu.paulo.kart.entities.Pilot;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;

public class LapParser {

    private LocalTime hour;

    private Pilot pilot;

    private int number;

    private Duration time;

    private double avgSpeed;

    public LapParser(String line) {
        parseLineToHour(line.substring(0, 17).trim());
        parsePilot(line.substring(18,21), line.substring(24,58).trim());
        parseLapNumber(line.substring(58,60).trim());
        parseTime(line.substring(64, 72));
        parseAvgSpeed(line.substring(84));
    }
    public LapParser(String hour, String pilotNumber, String pilotName, String lapNumber, String time, String avgSpeed) {
        parseLineToHour(hour);
        parsePilot(pilotNumber, pilotName);
        parseLapNumber(lapNumber);
        parseTime(time);
        parseAvgSpeed(avgSpeed);
    }

    public void parseLineToHour(String hourString) {
        LocalTime kartHour = LocalTime.parse(hourString);
        this.hour = kartHour;
    }

    public void parsePilot(String numberString, String nameString) {
        this.pilot = new Pilot(
                numberString != null ? Integer.parseInt(numberString.trim()) : 0,
                nameString
        );
    }

    public void parseLapNumber(String lapNumberString) {
        this.number = Integer.parseInt(lapNumberString);
    }

    public void parseTime(String timeString) {
        String minutes = timeString.substring(0, 1);
        String seconds = timeString.substring(2, 8);
        this.time = Duration.parse("PT"+minutes+"M"+seconds+"S");
    }

    public void parseAvgSpeed(String avgSpeedString) {
        try {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator(',');
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat df = new DecimalFormat("#,###,###,##0.00#", otherSymbols);
            this.avgSpeed = Double.parseDouble(String.valueOf(df.parse(avgSpeedString)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Lap parseToLap() {
        return new Lap(this.number, this.hour, this.time, this.avgSpeed);
    }

    public LocalTime getHour() {
        return hour;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public int getNumber() {
        return number;
    }

    public Duration getTime() {
        return time;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }
}
