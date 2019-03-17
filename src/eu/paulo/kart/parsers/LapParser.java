package eu.paulo.kart.parsers;

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
        this.hour = parseLineToHour(line.substring(0, 17).trim());
        this.pilot = parsePilot(line.substring(18,21), line.substring(24,58).trim());
        this.number = parseLapNumber(line.substring(58,60).trim());
        this.time = parseTime(line.substring(64, 72));
        this.avgSpeed = parseAvgSpeed(line.substring(84));
    }
    public LapParser(String hour, String pilotNumber, String pilotName, String lapNumber, String time, String avgSpeed) {
        this.hour = parseLineToHour(hour);
        this.pilot = parsePilot(pilotNumber, pilotName);
        this.number = parseLapNumber(lapNumber);
        this.time = parseTime(time);
        this.avgSpeed = parseAvgSpeed(avgSpeed);
    }

    public static LocalTime parseLineToHour(String hourString) {
        LocalTime kartHour = LocalTime.parse(hourString);
        return kartHour;
    }

    public static Pilot parsePilot(String numberString, String nameString) {
        return new Pilot(
                numberString != null ? Integer.parseInt(numberString.trim()) : 0,
                nameString
        );
    }

    public static int parseLapNumber(String lapNumberString) {
        return Integer.parseInt(lapNumberString);
    }

    public static Duration parseTime(String timeString) {
        String minutes = timeString.substring(0, 1);
        String seconds = timeString.substring(2, 8);
        return Duration.parse("PT"+minutes+"M"+seconds+"S");
    }

    public static double parseAvgSpeed(String avgSpeedString) {
        try {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator(',');
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat df = new DecimalFormat("#,###,###,##0.00#", otherSymbols);
            return Double.parseDouble(String.valueOf(df.parse(avgSpeedString)));
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
