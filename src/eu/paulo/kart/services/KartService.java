package eu.paulo.kart.services;

import eu.paulo.kart.entities.Lap;
import eu.paulo.kart.wrappers.LapWrapper;
import eu.paulo.kart.entities.Pilot;
import eu.paulo.kart.entities.Race;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.Collectors;

public class KartService {

    public Race compileRaceInfo(List<LapWrapper> laps) {
        Race race = new Race();
        Set<Pilot> pilots = getPilotsLaps(laps);
        race.setPilots(sortPilotsByPosition(pilots));




        return race;
    }

    public Set<Pilot> sortPilotsByPosition(Set<Pilot> pilots) {
        TreeSet<Pilot> sortedPilots = new TreeSet<>();
        pilots.forEach(p -> sortedPilots.add(p));

        return sortedPilots;
    }

    public Set<Pilot> getPilotsLaps(List<LapWrapper> laps) {
        Set<Pilot> pilots = getPilotsFrom(laps);

        for (Pilot p : pilots) {
            Duration totalRaceTime = Duration.ZERO;
            List<Lap> raceLaps = new ArrayList<>();
            List<LapWrapper> pilotLaps = laps.stream().filter(l -> p.equals(l.getPilot())).collect(Collectors.toList());
            for (LapWrapper pl : pilotLaps) {
                totalRaceTime = totalRaceTime.plus(pl.getTime());

                raceLaps.add(new Lap(pl));
            }
            System.out.println("Pilot: " + p.getNumber() + " - " + p.getName() + " || Total Lap Time: " + totalRaceTime.toString());
            p.setTotalRaceTime(totalRaceTime);
            p.setLaps(raceLaps);
        }

        return pilots;
    }

    public Set<Pilot> getPilotsFrom(List<LapWrapper> laps) {
        return laps.stream().map(p -> p.getPilot()).distinct().collect(Collectors.toCollection(HashSet::new));
    }
}
