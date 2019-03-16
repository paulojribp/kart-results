package eu.paulo.kart;

import eu.paulo.kart.wrappers.LapParser;
import eu.paulo.kart.entities.Race;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileController {

    private BufferedReader readFile(String fileName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(inputStream);
        return new BufferedReader(reader);
    }

    public List<LapParser> getLapsFrom(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.skip(1).map(linha -> new LapParser(linha)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        BufferedReader buffered = readFile(fileName);
//        buffered.readLine();
//        String line = buffered.readLine();
//        List<LapParser> laps = new ArrayList<>();
//
//        while (line != null) {
//            laps.add(new LapParser(line));
//            line = buffered.readLine();
//        }
//
//        return laps;
    }

}
