package pl.edu.pw.ee.performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HashDoubleHashing;
import pl.edu.pw.ee.services.HashTable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class HashDoubleHashingPerformanceTest {

    private final int[] sizes = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};
    private String[] words;
    private LinkedHashMap<Integer, ArrayList<Long>> results;

    @Before
    public void setUp() throws IOException {
        final String sourcePath = "src/test/java/pl/edu/pw/ee/performance/test_words.txt";
        words = FileHandler.readWordsFromFile(sourcePath);
        results = new LinkedHashMap<>();
    }

    @Test
    public void hashDoubleHashingPerformanceTest() throws FileNotFoundException {
        final String destPath = "src/test/java/pl/edu/pw/ee/performance/double_hashing_results.txt";
        for (int size : sizes) {
            results.put(size, new ArrayList<>());
        }
        for (int i = 0; i < 30; i++) {
            for (int size : sizes) {
                long elapsedTime = measureElapsedTime(new HashDoubleHashing<>(size));
                results.get(size).add(elapsedTime);
            }
        }
        LinkedHashMap<Integer, Double> processedResults = ResultsProcessor.processResults(results);
        FileHandler.writeToFile(destPath, processedResults);
    }

    private long measureElapsedTime(HashTable<String> hash) {
        long start = System.currentTimeMillis();
        for (String word : words) {
            hash.put(word);
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
