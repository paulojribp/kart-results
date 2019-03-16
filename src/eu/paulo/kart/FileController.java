package eu.paulo.kart;

import eu.paulo.kart.wrappers.LapParser;
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

    public List<LapParser> getLapsFrom(String fileName) throws IOException {
        BufferedReader buffered = readFile(fileName);
        buffered.readLine();
        String line = buffered.readLine();
        List<LapParser> laps = new ArrayList<>();

        while (line != null) {
            laps.add(new LapParser(line));
            line = buffered.readLine();
        }

        return laps;
    }

}
