package eu.paulo.kart;

import eu.paulo.kart.repository.KartRepository;
import eu.paulo.kart.entities.Pilot;
import test.java.test.generators.LapParserResource;
import eu.paulo.kart.parsers.LapParser;
import eu.paulo.kart.entities.Race;
import eu.paulo.kart.services.KartService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKartService {

    KartService kartService = new KartService();
    static KartRepository kartRepository = new KartRepository();

    private static List<LapParser> laps = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        laps = kartRepository.getLapsFrom("kart-results.txt");
    }

    @Test
    public void shouldGetPilotClassificationOrderedToFindTheFirstAndLast() {
        Race race = kartService.calculateRaceInfo(laps);

        Pilot first = race.getPilots().get(0);
        Pilot last = race.getPilots().get(race.getPilots().size() - 1);

        assertEquals(38, first.getNumber());
        assertEquals(1, first.getPosition());
        assertEquals(11, last.getNumber());
        assertEquals(Race.LAST_POSITION, last.getPosition());
    }

    @Test
    public void shouldSortPilotsByPositionGetOrderedPilotsWithLessLaps() {
        List<LapParser> laps = LapParserResource.getRaceWhenMoreThanOnePilotHasMadeLessLaps();
        List<Pilot> pilots = kartService.findPilotsAndLaps(laps);
        List<Pilot> sortedPilots = kartService.sortPilotsByPosition(pilots);

        Pilot expectedFirstPosition = laps.get(0).getPilot(); // O primeiro é Rubinho
        Pilot expectedLastPosition = laps.get(7).getPilot(); // O último é o Alonso, dos que não completaram, ele fez o pior tempo
        Pilot pilotInFirstPosition = sortedPilots.get(0);
        Pilot pilotInLastPosition = sortedPilots.get(sortedPilots.size() - 1);

        assertEquals(expectedFirstPosition, pilotInFirstPosition);
        assertEquals(1, pilotInFirstPosition.getPosition());
        assertEquals(expectedLastPosition, pilotInLastPosition);
        assertEquals(Race.LAST_POSITION, pilotInLastPosition.getPosition());
    }

    @Test
    public void shouldSortPilotsByPositionAndHave2PilotsInLastPosition() {
        List<LapParser> laps = LapParserResource.getRaceWhenMoreThanOnePilotHasMadeLessLaps();
        List<Pilot> pilots = kartService.findPilotsAndLaps(laps);
        List<Pilot> sortedPilots = kartService.sortPilotsByPosition(pilots);

        int size = sortedPilots.size();

        assertEquals(Race.LAST_POSITION, sortedPilots.get(size - 1).getPosition());
        assertEquals(Race.LAST_POSITION, sortedPilots.get(size - 2).getPosition());
    }

    @Test
    public void shouldCalculateAdditionalRaceInfoSuccessful() {
        Race race = kartService.calculateRaceInfo(laps);

        Race raceDetailed = kartService.calculateAdditionalRaceInfo(race);

        Duration bestRaceLap = LapParser.parseTime("1:02.769");
        Duration bestRaikkonenLap = LapParser.parseTime("1:03.076");
        Pilot raikkonen = raceDetailed.getPilots().get(1);

        assertEquals(bestRaceLap, raceDetailed.getBestLap().getTime());
        assertEquals(bestRaikkonenLap, raikkonen.getBestLap().getTime());
    }


}
