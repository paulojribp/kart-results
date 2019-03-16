package eu.paulo.kart.test;

import eu.paulo.kart.FileController;
import eu.paulo.kart.entities.Pilot;
import eu.paulo.kart.test.generators.LapParserGenerator;
import eu.paulo.kart.wrappers.LapParser;
import eu.paulo.kart.entities.Race;
import eu.paulo.kart.services.KartService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KartServiceTest {

    KartService kartService = new KartService();
    static FileController fileController = new FileController();

    private static List<LapParser> laps = new ArrayList<>();

    @BeforeAll
    public static void setUp() throws IOException {
        laps = fileController.getLapsFrom("kart-results.txt");
    }

    @Test
    public void shouldGetPilotClassificationOrderedToFindTheFirstAndLast() {
        Race race = kartService.compileRaceInfo(laps);

        Pilot first = race.getPilots().get(0);
        Pilot last = race.getPilots().get(race.getPilots().size() - 1);

        assertEquals(38, first.getNumber());
        assertEquals(1, first.getPosition());
        assertEquals(11, last.getNumber());
        assertEquals(Race.LAST_POSITION, last.getPosition());
    }

    @Test
    public void shouldGetOrderedPilotsWithLessLaps() throws IOException {
        List<LapParser> laps = LapParserGenerator.getRaceWithLessLaps();
        List<Pilot> pilots = kartService.getPilotsLaps(laps);
        pilots = kartService.sortPilotsByPosition(pilots);

        Pilot firstPosition = laps.get(0).getPilot(); // O primeiro é Rubinho
        Pilot lastPosition = laps.get(7).getPilot(); // O último é o Alonso, dos que não completaram, ele fez o pior tempo

        assertEquals(firstPosition, pilots.get(0));
        assertEquals(1, pilots.get(0).getPosition());
        assertEquals(lastPosition, pilots.get(pilots.size()-1));
        assertEquals(Race.LAST_POSITION, pilots.get(pilots.size()-1).getPosition());
    }

}
