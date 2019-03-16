package eu.paulo.kart.test;

import eu.paulo.kart.FileController;
import eu.paulo.kart.entities.Pilot;
import eu.paulo.kart.wrappers.LapWrapper;
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

    private static List<LapWrapper> laps = new ArrayList<>();

    @BeforeAll
    public static void setUp() throws IOException {
        laps = fileController.getLapsFrom("file.txt");
    }

    @Test
    public void shouldGetPilotClassificationOrderedToFindTheFirst() {
        Race race = kartService.compileRaceInfo(laps);
        for (Pilot p : race.getPilots()) {
            System.out.println(p);
        }

        LocalTime fasterTime = LocalTime.MAX;

//        for (Kart k : karts) {
//            if (fasterTime.compareTo(k.getRaceTime()) > 0 && k.getLapsCompleted() == race.getTotalLaps()) {
//                fasterKart = k;
//            }
//        }
//        List<Kart> kartsOrdered = kartService.getPilotClassificationOrdered();
//
//        assertEquals(fasterKart, kartsOrdered.get(0));
    }

}
