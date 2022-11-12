package pl.edu.pw.ee.performance;

import java.io.*;
import java.util.LinkedHashMap;

public class FileHandler {

    public static String[] readWordsFromFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String[] words = new String[100000];
        for (int i = 0; i < 100000; i++) {
            words[i] = reader.readLine();
        }
        return words;
    }

    public static void writeToFile(String path, LinkedHashMap<Integer, Double> results) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(path);
        for (Integer size : results.keySet()) {
            printWriter.println(size + ": " + results.get(size));
        }
        printWriter.close();
    }
}
