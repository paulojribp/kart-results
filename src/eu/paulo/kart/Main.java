package eu.paulo.kart;

import eu.paulo.kart.wrappers.LapParser;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FileController fileController = new FileController();
        try {
            List<LapParser> laps = fileController.getLapsFrom("file.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
