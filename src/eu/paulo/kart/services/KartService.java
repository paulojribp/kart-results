package eu.paulo.kart.services;

import eu.paulo.kart.entities.Lap;
import eu.paulo.kart.parsers.LapParser;
import eu.paulo.kart.entities.Pilot;
import eu.paulo.kart.entities.Race;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KartService {

    public Race calculateRaceInfo(List<LapParser> laps) {
        Race race = new Race();
        List<Pilot> pilots = findPilotsAndLaps(laps);
        race.setPilots(sortPilotsByPosition(pilots));

        return race;
    }

    public List<Pilot> sortPilotsByPosition(final List<Pilot> pilots) {
        SortedSet<Pilot> pilotsSet = new TreeSet<>();
        int maxLaps = 0;
        for (Pilot p : pilots) {
            pilotsSet.add(p);
            maxLaps = p.getAmountOfLaps() > maxLaps ? p.getAmountOfLaps() : maxLaps;
        }
        final int finalTotalLaps = maxLaps;

        TreeSet<Pilot> firsts = pilotsSet.stream().filter(p -> (finalTotalLaps == p.getAmountOfLaps())).collect(Collectors.toCollection(TreeSet::new));
        TreeSet<Pilot> lasts = pilotsSet.stream().filter(p -> (p.getAmountOfLaps() < finalTotalLaps)).collect(Collectors.toCollection(TreeSet::new));

        List<Pilot> sortedPilots = Stream.concat(firsts.stream(), lasts.stream()).collect(Collectors.toList());
        int position = 1;
        for (Pilot p : sortedPilots) {
            if (p.getAmountOfLaps() == finalTotalLaps) {
                p.setPosition(position);
                position++;
            } else {
                p.setPosition(Race.LAST_POSITION);
            }
        }

        return sortedPilots;
    }

    public List<Pilot> findPilotsAndLaps(final List<LapParser> laps) {
        List<Pilot> pilots = laps.stream().map(p -> p.getPilot()).distinct().collect(Collectors.toList());

        for (Pilot p : pilots) {
            Duration totalRaceTime = Duration.ZERO;
            List<Lap> raceLaps = new ArrayList<>();
            List<LapParser> pilotLaps = laps.stream().filter(l -> p.equals(l.getPilot())).collect(Collectors.toList());
            for (LapParser pl : pilotLaps) {
                totalRaceTime = totalRaceTime.plus(pl.getTime());

                raceLaps.add(pl.parseToLap());
            }

            p.setTotalRaceTime(totalRaceTime);
            p.setLaps(raceLaps);
        }

        return pilots;
    }

    public Race calculateAdditionalRaceInfo(final Race race) {
        Duration bestRaceLap = Duration.parse("PT59M");

        for (Pilot pilot : race.getPilots()) {
            Duration bestPilotLap = Duration.parse("PT59M");
            double avgRaceSpeed = 0;

            for (Lap lap : pilot.getLaps()) {
                avgRaceSpeed += lap.getAvgSpeed();

                if (lap.getTime().compareTo(bestPilotLap) < 0) {
                    pilot.setBestLap(lap);
                    bestPilotLap = lap.getTime();

                    if (lap.getTime().compareTo(bestRaceLap) < 0) {
                        race.setBestLap(lap);
                        bestRaceLap = lap.getTime();
                    }
                }
            }
            avgRaceSpeed = avgRaceSpeed / pilot.getAmountOfLaps();
            pilot.setAvgSpeed(avgRaceSpeed);
        }

        return race;
    }
}
