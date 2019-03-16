package eu.paulo.kart;

import eu.paulo.kart.wrappers.LapWrapper;
import eu.paulo.kart.entities.Race;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController {

    private BufferedReader readFile(String fileName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(inputStream);
        return new BufferedReader(reader);
    }

    public List<LapWrapper> getLapsFrom(String fileName) throws IOException {
        BufferedReader buffered = readFile(fileName);
        buffered.readLine();
        String line = buffered.readLine();
        List<LapWrapper> laps = new ArrayList<>();

        while (line != null) {
            //TODO Mudar isso para único método que faz parse de tudo.
            LapWrapper lap = new LapWrapper();
            lap.parseLineToHour(line);
            lap.parsePilot(line);
            lap.parseLapNumber(line);
            lap.parseTime(line);
            lap.parseAverageSpeed(line);

            laps.add(lap);

            line = buffered.readLine();
        }

        return laps;
    }

    public Race readRace() {
        Race race = new Race();


        return null;
    }
}
