package Ispirer.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class CreateInputFile {

    public static void main(String[] args) {
        String inputFile = "src/main/resources/input.txt";
        int fileSize = 1000000;

        try (FileOutputStream fos = new FileOutputStream(inputFile)) {
            Random random = new Random();
            byte[] buffer = new byte[1024];
            for (int i = 0; i < fileSize / 1024; i++) {
                random.nextBytes(buffer);
                for (int j = 0; j < buffer.length; j++) {
                    buffer[j] = (byte) ((Math.abs(buffer[j]) % 26) + 'a');
                }
                fos.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Input file created successfully: " + inputFile);
    }
}
