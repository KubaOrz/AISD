package pl.edu.pw.ee.performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.HeapSort;
import pl.edu.pw.ee.services.DataGenerator;
import pl.edu.pw.ee.services.Sorting;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HeapSortPerformanceTest {

    private final int STEP = 1000;
    private final int MAX = 1000000;
    private Sorting heapSort;
    private DataGenerator dataGenerator;

    @Before
    public void setUp() {
        heapSort = new HeapSort();
        dataGenerator = new DataGenerator();
    }

    @Test
    public void measureTimeForAvgData() throws FileNotFoundException {
        double[] nums;
        long start, finish;
        double elapsedTime;
        PrintWriter printWriter = new PrintWriter("heap_sort_performance_avg.txt");
        for (int j = 0; j < 10; j++) {
            for (int i = STEP; i <= MAX; i += STEP) {
                nums = dataGenerator.generateRandom(i);
                start = System.currentTimeMillis();
                heapSort.sort(nums);
                finish = System.currentTimeMillis();
                elapsedTime = (double) (finish - start) / 1000;
                printWriter.print(elapsedTime + "; ");
            }
            printWriter.println();
        }
        printWriter.close();
    }
}
