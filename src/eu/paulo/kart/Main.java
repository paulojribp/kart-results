package eu.paulo.kart;

import eu.paulo.kart.repository.KartRepository;
import eu.paulo.kart.parsers.LapParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        KartRepository kartRepository = new KartRepository();
        List<LapParser> laps = kartRepository.getLapsFrom("kart-results.txt");
    }
}
