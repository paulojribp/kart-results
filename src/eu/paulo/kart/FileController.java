package eu.paulo.kart;

import eu.paulo.kart.entities.LapWrapper;
import eu.paulo.kart.entities.Race;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileController {

    private BufferedReader readFile(String fileName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(inputStream);
        return new BufferedReader(reader);
    }

    public List<LapWrapper> getKartsFrom(String fileName) throws IOException {
        BufferedReader buffered = readFile(fileName);
        buffered.readLine();
        String line = buffered.readLine();
        List<LapWrapper> karts = new ArrayList<>();

        while (line != null) {
            LapWrapper kart = new LapWrapper();
            kart.parseHora(line);
            kart.parsePilot(line);
            kart.setNumber(Integer.parseInt(line.substring(58,60).trim()));
            String tempo = line.substring(64, 83);
            System.out.println("["+tempo+"]");
            kart.setTime(LocalTime.parse("00:0"+tempo.trim(), DateTimeFormatter.ofPattern("HH:mm:ss[.SSS]")));

            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator(',');
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat df = new DecimalFormat("#,###,###,##0.00#", otherSymbols);
            try {
                kart.setAverageSpeed( Double.parseDouble(String.valueOf(df.parse(line.substring(84)))) );
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(kart);
            karts.add(kart);

            line = buffered.readLine();
        }

        return karts;
    }

    public Race readRace() {
        Race race = new Race();

        return null;
    }
}
