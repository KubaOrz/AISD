package pl.edu.pw.ee.performance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.edu.pw.ee.HashListChaining;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

@RunWith(Parameterized.class)
public class HashListChainingPerformanceTest {

    private final int[] hashSizes;
    private final String resultPath;
    private LinkedHashMap<Integer, ArrayList<Long>> results;
    private ArrayList<Double> loadFactors;
    private FileHandler fileHandler;
    private String[] words;

    public HashListChainingPerformanceTest(int[] hashSizes, String resultPath) {
        this.hashSizes = hashSizes;
        this.resultPath = resultPath;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {TestData.PRIMES.getSizes(), TestData.PRIMES.getPathName()},
                {TestData.POWERS.getSizes(), TestData.POWERS.getPathName()},
                {TestData.OTHER_PRIMES.getSizes(), TestData.OTHER_PRIMES.getPathName()}
        });
    }

    @Before
    public void setUp() throws IOException {
        fileHandler = new FileHandler();
        results = new LinkedHashMap<>();
        words = fileHandler.readWords();
        loadFactors = new ArrayList<>();
    }

    @Test
    public void hashListChainingPrimesPerformanceTest() throws FileNotFoundException {
        for (int size : hashSizes) {
            results.put(size, new ArrayList<>());
        }

        boolean shouldCountLoadFactor = true;

        for (int i = 0; i < 30; i++) {
            for (int size : hashSizes) {
                HashListChaining<String> hashList = new HashListChaining<>(size);
                for (String word : words) {
                    hashList.add(word);
                }
                if (shouldCountLoadFactor) {
                    loadFactors.add(hashList.countLoadFactor());
                }

                long start = System.currentTimeMillis();

                for (String word : words) {
                    hashList.get(word);
                }

                long finish = System.currentTimeMillis();
                long elapsedTime = finish - start;
                results.get(size).add(elapsedTime);
            }
            shouldCountLoadFactor = false;
        }
        fileHandler.processResults(results, resultPath, loadFactors);
    }

}
