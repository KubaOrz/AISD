package pl.edu.pw.ee.performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.RefAlgorithm;
import pl.edu.pw.ee.services.DataGenerator;
import pl.edu.pw.ee.services.Sorting;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RefAlgorithmPerformanceTest {

    private final int STEP = 1000;
    private final int MAX = 1000000;
    private Sorting refAlgorithm;
    private DataGenerator dataGenerator;

    @Before
    public void setUp() {
        refAlgorithm = new RefAlgorithm();
        dataGenerator = new DataGenerator();
    }

    @Test
    public void measureTimeForAscendingData() throws FileNotFoundException {
        double[] nums;
        long start, finish;
        double elapsedTime;
        PrintWriter printWriter = new PrintWriter("ref_algorithm_performance_ascending.txt");
        for (int j = 0; j < 10; j++) {
            for (int i = STEP; i <= MAX; i += STEP) {
                nums = dataGenerator.generateAscending(i);
                start = System.currentTimeMillis();
                refAlgorithm.sort(nums);
                finish = System.currentTimeMillis();
                elapsedTime = (double) (finish - start) / 1000;
                printWriter.print(elapsedTime + "; ");
            }
            printWriter.println();
        }
        printWriter.close();
    }

    @Test
    public void measureTimeForDescendingData() throws FileNotFoundException {
        double[] nums;
        long start, finish;
        double elapsedTime;
        PrintWriter printWriter = new PrintWriter("ref_algorithm_performance_descending.txt");
        for (int j = 0; j < 10; j++) {
            for (int i = STEP; i <= MAX; i += STEP) {
                nums = dataGenerator.generateDescending(i);
                start = System.currentTimeMillis();
                refAlgorithm.sort(nums);
                finish = System.currentTimeMillis();
                elapsedTime = (double) (finish - start) / 1000;
                printWriter.print(elapsedTime + "; ");
            }
            printWriter.println();
        }
        printWriter.close();
    }

    @Test
    public void measureTimeForAvgData() throws FileNotFoundException {
        double[] nums;
        long start, finish;
        double elapsedTime;
        PrintWriter printWriter = new PrintWriter("ref_algorithm_performance_avg.txt");
        for (int j = 0; j < 10; j++) {
            for (int i = STEP; i <= MAX; i += STEP) {
                nums = dataGenerator.generateRandom(i);
                start = System.currentTimeMillis();
                refAlgorithm.sort(nums);
                finish = System.currentTimeMillis();
                elapsedTime = (double) (finish - start) / 1000;
                printWriter.print(elapsedTime + "; ");
            }
            printWriter.println();
        }
        printWriter.close();
    }
}

