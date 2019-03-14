package eu.paulo.kart.test;

import eu.paulo.kart.FileController;
import eu.paulo.kart.entities.Kart;
import eu.paulo.kart.entities.Race;
import eu.paulo.kart.services.KartService;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KartServiceTest {

    KartService kartService = new KartService();
    FileController fileController = new FileController();

    @Test
    public void shouldGetPilotClassificationOrderedToFindTheFirst() {
        Race race = fileController.readRace();
        List<Kart> karts = race.getKarts();
        Kart fasterKart = null;
        LocalTime fasterTime = LocalTime.MAX;

        for (Kart k : karts) {
            if (fasterTime.compareTo(k.getRaceTime()) > 0 && k.getLapsCompleted() == race.getTotalLaps()) {
                fasterKart = k;
            }
        }
        List<Kart> kartsOrdered = kartService.getPilotClassificationOrdered();

        assertEquals(fasterKart, kartsOrdered.get(0));
    }

}
