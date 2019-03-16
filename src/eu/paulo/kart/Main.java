package eu.paulo.kart;

import eu.paulo.kart.wrappers.LapWrapper;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FileController fileController = new FileController();
        try {
            List<LapWrapper> laps = fileController.getLapsFrom("file.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
