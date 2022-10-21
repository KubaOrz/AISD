package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {

    private Heap<Double> heap;
    private Heap<Double> heapFromArray;
    private static final int delta = 0;

    @Before
    public void setUp() {
        heap = new Heap<>();
        double[] nums = {1, 6, 4, 7, 9, 10, 21, -3, -8};
        List<Double> numsToBuild = new ArrayList<>();
        for (Double num: nums) {
            numsToBuild.add(num);
        }
        heapFromArray = new Heap<>(numsToBuild);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenEmpty() {

        //when
        heap.pop();

        //then
        assert false;
    }

    @Test
    public void shouldReturnProperSizeWhenInit() {

        Assert.assertEquals(0, heap.getCurrentSize());
    }

    @Test
    public void shouldReturnProperSizeWHenPutSomeElem() {

        //when
        heap.put(1.1);
        heap.put(1.2);
        heap.put(1.3);
        int expected = 3;

        //then
        Assert.assertEquals(expected, heap.getCurrentSize());
    }

    @Test
    public void shouldReturnProperSizeWHenPutAndPopSomeElem() {

        //when
        heap.put(1.1);
        heap.put(1.2);
        heap.put(1.3);
        heap.pop();
        heap.pop();
        int expected = 1;

        //then
        Assert.assertEquals(expected, heap.getCurrentSize());
    }

    @Test
    public void shouldReturnElemWhenPutOneElem() {

        //given
        heap.put(2.137);

        //when
        double result = heap.pop();
        double expected = 2.137;

        //then
        Assert.assertEquals(expected, result, delta);
    }

    @Test
    public void shouldReturnMaxElemWhenPutTwoElem() {
        //given
        heap.put(2.137);
        heap.put(2.115);

        //when
        double result = heap.pop();
        double expected = 2.137;

        //then
        Assert.assertEquals(expected, result, delta);
    }

    @Test
    public void shouldReturnMaxElemWhenPutSomeElem() {
        //given
        heap.put(2.137);
        heap.put(3.52);
        heap.put(21.0);
        heap.put(3246.1);
        heap.put(1.1);
        heap.put(2.2);
        heap.put(3.3);
        heap.put(4.4);

        //when
        double result = heap.pop();
        double expected = 3246.1;

        //then
        Assert.assertEquals(expected, result, delta);
    }

    @Test
    public void shouldReturnCorrectOrderWhenPutAndPopSomeElem() {

        //given
        heap.put(1.1);
        heap.put(2.1);
        heap.put(5.1);
        heap.put(3.1);
        heap.put(-1.1);
        heap.put(21.1);

        //when
        double[] results = new double[6];
        double[] expected = {21.1, 5.1, 3.1, 2.1, 1.1, -1.1};
        for (int i = 0; i < 6; i++) {
            results[i] = heap.pop();
        }

        //then
        Assert.assertArrayEquals(expected, results, delta);
    }

    @Test
    public void shouldReturnProperSizeWhenBuilt() {

        //when
        int expected = 9;

        //then
        Assert.assertEquals(expected, heapFromArray.getCurrentSize());
    }

    @Test
    public void shouldReturnMaxElemWhenBuilt() {

        //when
        double expected = 21;
        double result = heapFromArray.pop();

        //then
        Assert.assertEquals(expected, result, delta);
    }

    @Test
    public void shouldReturnProperOrderWhenBuilt() {

        //when
        double[] results = new double[9];
        double[] expected = {21, 10, 9, 7, 6, 4, 1, -3, -8};
        for (int i = 0; i < 9; i++) {
            results[i] = heapFromArray.pop();
        }

        //then
        Assert.assertArrayEquals(expected, results, delta);
    }

}
