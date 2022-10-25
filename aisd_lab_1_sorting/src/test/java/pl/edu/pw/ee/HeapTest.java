package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {

    private static final double DELTA = 0;
    private Heap<Double> heap;
    private Heap<Double> heapFromArray;

    @Before
    public void setUp() {

        heap = new Heap<>(new ArrayList<Double>());
        double[] nums = {1, 6, 4, 7, 9, 10, 21, -3, -8};
        List<Double> numsToBuild = new ArrayList<>();
        for (Double num : nums) {
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
    public void shouldReturnProperSizeWhenEmpty() {

        //when
        int result = heap.getCurrentSize();
        int expected = 0;

        //then
        Assert.assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenNull() {

        //when
        Heap<Double> heapWithNull = new Heap<>(null);

        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenHeapifyWithFirstNegativeArg() {

        //when
        heap.heapify(-3, 8);

        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenHeapifyWithSecondNegativeArg() {

        //when
        heap.heapify(3, -8);

        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenHeapifyWithBothNegativeArg() {

        //when
        heap.heapify(-3, -8);

        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenHeapifyWithSecondArgTooBig() {

        //when
        heap.heapify(0, heap.getCurrentSize() + 6);

        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenHeapifyWithBothArgTooBig() {

        //when
        heap.heapify(heap.getCurrentSize() + 3, heap.getCurrentSize() + 6);

        //then
        assert false;
    }

    @Test
    public void shouldReturnElemWhenPutOneElem() {

        //given
        heap.put(2.137);

        //when
        double result = heap.pop();
        double expected = 2.137;

        //then
        Assert.assertEquals(expected, result, DELTA);
    }

    @Test
    public void shouldReturnProperSizeWhenPutOneElem() {

        //given
        heap.put(2.0);

        //when
        int expected = 1;
        int result = heap.getCurrentSize();

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnProperSizeWhenPutAndPopOneElem() {

        //given
        heap.put(2.0);
        heap.pop();

        //when
        int expected = 0;
        int result = heap.getCurrentSize();

        //then
        Assert.assertEquals(expected, result);
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
        Assert.assertEquals(expected, result, DELTA);
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
        Assert.assertEquals(expected, result, DELTA);
    }

    @Test
    public void shouldReturnProperSizeWhenPutSomeElem() {

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
        int result = heap.getCurrentSize();
        int expected = 8;

        //then
        Assert.assertEquals(expected, result);
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
        Assert.assertArrayEquals(expected, results, DELTA);
    }

    @Test
    public void shouldReturnMaxElemWhenBuilt() {

        //when
        double expected = 21;
        double result = heapFromArray.pop();

        //then
        Assert.assertEquals(expected, result, DELTA);
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
        Assert.assertArrayEquals(expected, results, DELTA);
    }

    @Test
    public void shouldReturnProperSizeWhenBuilt() {

        //when
        int result = heapFromArray.getCurrentSize();
        int expected = 9;

        //then
        Assert.assertEquals(expected, result);
    }

}
