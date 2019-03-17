package eu.paulo.kart;

import eu.paulo.kart.entities.Pilot;
import eu.paulo.kart.entities.Race;
import eu.paulo.kart.repository.KartRepository;
import eu.paulo.kart.parsers.LapParser;
import eu.paulo.kart.services.KartService;

import java.io.PrintStream;
import java.time.Duration;
import java.util.List;

public class Main {

    private static final PrintStream out = System.out;

    public static void main(String[] args) {
        KartRepository kartRepository = new KartRepository();
        List<LapParser> laps = kartRepository.getLapsFrom("kart-results.txt");

        KartService kartService = new KartService();
        Race race = kartService.calculateRaceInfo(laps);
        kartService.calculateAdditionalRaceInfo(race);

        out.println("-------------------------------------------------- RACE STATS -------------------------------------------------");
        out.println("===============================================================================================================");
        out.println("| POS  |          PILOT          | COMPLETED LAPS |   TOTAL TIME   |   AFTER #1 |    BEST LAP   |  AVG SPEED  |");
        printRaceStats(race.getPilots());
        out.println("===============================================================================================================");

    }

    private static void printRaceStats(List<Pilot> pilots) {
        Duration firstPosRaceTime = Duration.ZERO;
        for (Pilot p : pilots) {
            if (p.getPosition() == 1)
                firstPosRaceTime = p.getTotalRaceTime();

            Duration afterFirst = p.getTotalRaceTime().minus(firstPosRaceTime);

            out.printf("| #%-3s | %-2s - %-18s |        %-7s |     %s:%02d.%3.3s   |  +%04d.%3.3s |    %s:%02d.%3.3s   |     %3.3f  |%n",
                    p.getPosition(), p.getNumber(), p.getName(), p.getAmountOfLaps(),
                    (p.getTotalRaceTime().getSeconds()/60), (p.getTotalRaceTime().getSeconds() % 60), p.getTotalRaceTime().getNano(),
                    afterFirst.getSeconds(), afterFirst.getNano(),
                    (p.getBestLap().getTime().getSeconds()/60), (p.getBestLap().getTime().getSeconds() % 60), p.getBestLap().getTime().getNano(),
                    p.getAvgSpeed()
            );
        }
    }
}
