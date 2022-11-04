package pl.edu.pw.ee.performance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HashLinearProbing;
import pl.edu.pw.ee.services.HashTable;

public class HashLinearProbingPerformanceTest {
    
    private String[] words;
    private FileHandler fileHandler;
    private final int[] sizes = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};
    private LinkedHashMap<Integer, ArrayList<Long>> results;

    @Before
    public void setUp() throws IOException {
        fileHandler = new FileHandler();
        final String sourcePath = "src/test/java/pl/edu/pw/ee/performance/test_words.txt";
        words = fileHandler.readWordsFromFile(sourcePath);
        results = new LinkedHashMap<>();
    }
    
    @Test
    public void hashLinearProbingPerformanceTest() throws FileNotFoundException {
        final String destPath = "src/test/java/pl/edu/pw/ee/performance/linear_probing_results.txt";
        for (int size : sizes) {
            results.put(size, new ArrayList<>());
        }
        for (int i = 0; i < 30; i++) {
            for (int size : sizes) {
                long elapsedTime = measureElapsedTime(new HashLinearProbing<>(size));
                results.get(size).add(elapsedTime);
            }
        }
        fileHandler.writeToFile(destPath, processResults());
    }

    private long measureElapsedTime(HashTable<String> hash) {
        long start = System.currentTimeMillis();
        for (String word : words) {
            hash.put(word);
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    private LinkedHashMap<Integer, Double> processResults() {
        LinkedHashMap<Integer, Double> avgResults = new LinkedHashMap<>();
        for (Integer size: results.keySet()) {
            results.get(size).sort(new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return o1.compareTo(o2);
                }
            });
            ArrayList<Long> trimmedResults = new ArrayList<>(results.get(size).subList(10,19));
            double avgTime = 0;
            for (Long time: trimmedResults) {
                avgTime += time;
            }
            avgTime /= trimmedResults.size();
            avgResults.put(size, avgTime);
        }
        return avgResults;
    }
}
