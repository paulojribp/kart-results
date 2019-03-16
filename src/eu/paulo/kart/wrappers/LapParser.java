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
        parseLineToHour(line);
        parsePilot(line);
        parseLapNumber(line);
        parseTime(line);
        parseAvgSpeed(line);
    }

    public void parseLineToHour(String line) {
        LocalTime kartHour = LocalTime.parse(line.substring(0, 17).trim());
        this.hour = kartHour;
    }

    public void parsePilot(String line) {
        String numberString = line.substring(18,21);
        this.pilot = new Pilot(
                numberString != null ? Integer.parseInt(numberString.trim()) : 0,
                line.substring(24,58).trim()
        );
    }

    public void parseLapNumber(String line) {
        this.number = Integer.parseInt(line.substring(58,60).trim());
    }

    public void parseTime(String line) {
        String minutes = line.substring(64, 65);
        String seconds = line.substring(66, 72);
        this.time = Duration.parse("PT"+minutes+"M"+seconds+"S");
    }

    public void parseAvgSpeed(String line) {
        try {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator(',');
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat df = new DecimalFormat("#,###,###,##0.00#", otherSymbols);
            this.avgSpeed = Double.parseDouble(String.valueOf(df.parse(line.substring(84))));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Lap parseToLap() {
        Lap lap = new Lap();
        lap.setNumber( this.number );
        lap.setHour( this.hour );
        lap.setTime( this.time );
        lap.setAvgSpeed( this.avgSpeed );

        return lap;
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
