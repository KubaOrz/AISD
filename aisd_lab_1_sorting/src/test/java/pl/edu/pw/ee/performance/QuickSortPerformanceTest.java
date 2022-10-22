package pl.edu.pw.ee.performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.QuickSort;
import pl.edu.pw.ee.services.DataGenerator;
import pl.edu.pw.ee.services.Sorting;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class QuickSortPerformanceTest {

    private Sorting quickSort;
    private DataGenerator dataGenerator;
    private final int STEP = 1000;
    private final int MAX = 100000;


    @Before
    public void setUp() {
        quickSort = new QuickSort();
        dataGenerator = new DataGenerator();
    }

    @Test
    public void measureTimeForPessimisticData() throws FileNotFoundException {
        double[] nums;
        long start, finish;
        double elapsedTime;
        PrintWriter printWriter = new PrintWriter("quick_sort_performance_pessimistic.txt");
        for (int j = 0; j < 10; j++) {
            for (int i = STEP; i <= MAX; i += STEP) {
                nums = dataGenerator.generateAscending(i);
                start = System.currentTimeMillis();
                quickSort.sort(nums);
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
        PrintWriter printWriter = new PrintWriter("quick_sort_performance_avg.txt");
        for (int j = 0; j < 10; j++) {
            for (int i = STEP; i <= MAX; i += STEP) {
                nums = dataGenerator.generateRandom(i);
                start = System.currentTimeMillis();
                quickSort.sort(nums);
                finish = System.currentTimeMillis();
                elapsedTime = (double) (finish - start) / 1000;
                printWriter.print(elapsedTime + "; ");
            }
            printWriter.println();
        }
        printWriter.close();
    }
}
