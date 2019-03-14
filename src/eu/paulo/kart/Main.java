package eu.paulo.kart;

import eu.paulo.kart.entities.LapWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FileController fileController = new FileController();
        try (BufferedReader buffered = fileController.readFile("file.txt")){
            List<LapWrapper> kartMapper = fileController.getKarts(buffered);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
