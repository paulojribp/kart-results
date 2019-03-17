package eu.paulo.kart.repository;

import eu.paulo.kart.parsers.LapParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KartRepository {

    public List<LapParser> getLapsFrom(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.skip(1).map(LapParser::new).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
