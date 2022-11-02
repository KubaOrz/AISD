package pl.edu.pw.ee.performance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HashLinearProbing;
import pl.edu.pw.ee.services.HashTable;

public class HashLinearProbingPerformanceTest {
    
    private BufferedReader reader;
    private final String sourcePath = "src/test/java/pl/edu/pw/ee/performance/test_words.txt";
    private final String destPath = "src/test/java/pl/edu/pw/ee/performance/test_results.txt";
    private String[] words;
    private final int[] sizes = {4096, 8192, 16384, 32768, 65536, 131072, 262144};
    private PrintWriter writer;
    
    @Before
    public void setUp() throws IOException {
        reader = new BufferedReader(new FileReader(sourcePath));
        words = new String[100000];
        for (int i = 0; i < 100000; i++) {
            words[i] = reader.readLine();
        }
        writer = new PrintWriter(destPath);
    }
    
    @Test
    public void hashLinearProbingPerformanceTest() {
        for (int size: sizes) {
            HashTable<String> hash = new HashLinearProbing(size);
            long start = System.currentTimeMillis();
            for (String word: words) {
                hash.put(word);
            }
            long finish = System.currentTimeMillis();
            long elapsedTime = finish - start;
            writer.println(size + ": " + elapsedTime);
        }
    }
}
