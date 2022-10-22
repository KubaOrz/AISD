package pl.edu.pw.ee.performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.SelectionSort;
import pl.edu.pw.ee.services.DataGenerator;
import pl.edu.pw.ee.services.Sorting;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SelectionSortPerformanceTest {

    private Sorting selectionSort;
    private PrintWriter printWriter;
    private DataGenerator dataGenerator;
    private final int STEP = 1000;
    private final int MAX = 100000;


    @Before
    public void setUp() throws FileNotFoundException {
        selectionSort = new SelectionSort();
        printWriter = new PrintWriter("selection_sort_performance.txt");
        dataGenerator = new DataGenerator();
    }

    @Test
    public void writeSortingTimes() {
        double[] nums;
        long start, finish;
        double elapsedTime;
        for (int j = 0; j < 10; j++) {
            for (int i = STEP; i <= MAX; i += STEP) {
                nums = dataGenerator.generateRandom(i);
                start = System.currentTimeMillis();
                selectionSort.sort(nums);
                finish = System.currentTimeMillis();
                elapsedTime = (double) (finish - start) / 1000;
                printWriter.print(elapsedTime + "; ");
            }
            printWriter.println();
        }
        printWriter.close();
    }
}
