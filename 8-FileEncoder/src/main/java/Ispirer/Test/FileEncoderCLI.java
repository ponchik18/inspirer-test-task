package Ispirer.Test;

import java.io.*;

public class FileEncoderCLI {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: java FileEncoderCLI <inputFile> <outputFile> <inputEncoding> <outputEncoding>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        String inputEncoding = args[2];
        String outputEncoding = args[3];

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), inputEncoding));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), outputEncoding));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            System.out.println("File encoded successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
