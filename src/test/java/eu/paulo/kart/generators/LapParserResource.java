package test.java.test.generators;

import eu.paulo.kart.parsers.LapParser;

import java.util.ArrayList;
import java.util.List;

public class LapParserResource {

    public static List<LapParser> getRaceWhenMoreThanOnePilotHasMadeLessLaps() {
        List<LapParser> lapsParser = new ArrayList<>();
        lapsParser.add(new LapParser("23:49:08.277", "038", "R.BARRICHELLO", "1", "1:00.452","44,371"));
        lapsParser.add(new LapParser("23:49:08.277", "038", "R.BARRICHELLO", "2", "1:12.658","48,655"));
        lapsParser.add(new LapParser("23:49:08.277", "038", "R.BARRICHELLO", "3", "1:04.782","43,875"));
        lapsParser.add(new LapParser("23:49:08.277", "025", "F.MASSA", "1", "0:59.951","49,275"));
        lapsParser.add(new LapParser("23:49:08.277", "025", "F.MASSA", "2", "1:22.452","40,032"));
        lapsParser.add(new LapParser("23:49:08.277", "025", "F.MASSA", "3", "1:03.852","44,651"));
        lapsParser.add(new LapParser("23:49:08.277", "030", "M.WEBBER", "1", "1:02.130","41,706"));
        lapsParser.add(new LapParser("23:49:08.277", "005", "F.ALONSO", "1", "1:07.980","45,315"));
        lapsParser.add(new LapParser("23:49:08.277", "005", "F.ALONSO", "2", "1:07.980","45,315"));

        return lapsParser;
    }
}
