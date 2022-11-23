package pl.edu.pw.ee.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

public class DataIO {

    private static final String WORDS_FILEPATH = "src/main/resources/wordlist_100_000.txt";
    private static final Logger LOG = Logger.getLogger(DataIO.class.getName());

    public static String[] prepareListOfWords() {
        List<String> words = new ArrayList<>();
        String line;
        int nToRepeat = 10;

        try (FileReader fReader = new FileReader(WORDS_FILEPATH);
                BufferedReader buffReader = new BufferedReader(fReader)) {

            while ((line = buffReader.readLine()) != null) {

                for (int i = 0; i < nToRepeat; i++) {
                    words.add(line + i);
                }
            }

        } catch (IOException e) {
            LOG.log(SEVERE, "[ERROR] An error occurred while preparing the words.", e);
        }

        return words.toArray(new String[0]);
    }

    public static void saveMeasuredHeight(List<String> results, String filename) {
        validateSaveInput(results, filename);

        String pathToFile = "src/main/resources/" + filename;
        int n = results.size();

        try (FileWriter fWriter = new FileWriter(pathToFile);
                BufferedWriter buffWriter = new BufferedWriter(fWriter)) {

            for (int i = 0; i < n; i++) {
                buffWriter.write(results.get(i));

                if (i < n - 1) {
                    buffWriter.newLine();
                }
            }

        } catch (IOException e) {
            LOG.log(SEVERE, "[ERROR] An error occurred while saving file: " + pathToFile, e);
        }
    }

    private static void validateSaveInput(List<String> results, String filename) {
        if (results == null || filename == null) {
            throw new IllegalArgumentException(
                    "Input args in saveMeasured method cannot be null!");
        }
    }

}
