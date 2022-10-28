package pl.edu.pw.ee.performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HashListChaining;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HashListChainingPerformanceTest {

    private BufferedReader reader;
    private HashListChaining<String> hashList;
    private String[] words;
    private PrintWriter powerOfTwoPrintWriter;
    private PrintWriter primePrintWriter;
    private HashMap<Integer, ArrayList<Double>> powerOfTwoResults;
    private HashMap<Integer, ArrayList<Double>> primeResults;

    @Before
    public void setUp() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("words.txt"));
        words = new String[100000];
    }

    @Before
    public void powerOfTwoSetUp() throws FileNotFoundException {
        powerOfTwoPrintWriter = new PrintWriter("power_of_two_results.txt");
        powerOfTwoResults = new HashMap<>();
        int firstHashSize = 4096;
        int multiplier = 2;
        for (int i = 0; i < 7; i++) {

        }
    }

    @Test
    public void hashListPerformanceTest() throws IOException {
        for (int i = 0; i < 100000; i++) {
            words[i] = reader.readLine();
        }

        for (int i = 0; i < 30; i++) {

        }

    }
}
