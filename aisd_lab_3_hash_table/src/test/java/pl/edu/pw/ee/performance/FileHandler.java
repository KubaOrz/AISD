package pl.edu.pw.ee.performance;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class FileHandler {

    private final BufferedReader reader;
    private final String wordsPath = "src/test/java/pl/edu/pw/ee/performance/words.txt";
    private final int wordCount = 100000;

    public FileHandler() throws IOException {
        reader = new BufferedReader(new FileReader(wordsPath));
    }

    public String[] readWords() throws IOException {
        String[] words = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            words[i] = reader.readLine();
        }
        return words;
    }

    public void processResults(LinkedHashMap<Integer, ArrayList<Long>> results, String pathName, ArrayList<Double> loadFactors) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(pathName);
        for (int key : results.keySet()) {
            double loadFactor = loadFactors.get(0);
            loadFactors.remove(0);
            ArrayList<Long> list = results.get(key);
            list.sort(new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return o1.compareTo(o2);
                }
            });
            ArrayList<Long> processedResults = new ArrayList<>(list.subList(10, 19));
            double averageTime = 0;
            for (Long time : processedResults) {
                averageTime += time;
            }
            averageTime /= processedResults.size();
            printWriter.println(key + "; " + averageTime + "; " + loadFactor);
        }
        printWriter.close();
    }

}
