package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

import java.util.Arrays;
import java.util.Random;

public class RefAlgorithmTest {

    private static final double delta = 0;
    private Sorting refAlgorithm;

    @Before
    public void setUp() {
        refAlgorithm = new RefAlgorithm();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfNull() {

        // given
        double[] nums = null;

        // when
        refAlgorithm.sort(nums);

        // then
        assert false;
    }

    @Test
    public void shouldReturnEmptyArrayWhenInpArrIsEmpty() {

        // given
        double[] nums = {};

        // when
        refAlgorithm.sort(nums);

        //then
        Assert.assertEquals(0, nums.length);
    }

    @Test
    public void shouldReturnArrayWhenInpArrHasOneElem() {

        // given
        double[] nums = {3};

        // when
        refAlgorithm.sort(nums);

        //then
        double[] expected = {3};
        Assert.assertArrayEquals(expected, nums, delta);
    }

    @Test
    public void shouldReturnSortedArrayWhenInpArrHasTwoUnsortedElem() {

        // given
        double[] nums = {3, 1};

        // when
        refAlgorithm.sort(nums);

        //then
        double[] expected = {1, 3};
        Assert.assertArrayEquals(expected, nums, delta);
    }

    @Test
    public void shouldReturnSortedArrayWhenInpArrHasSortedElem() {

        // given
        double[] nums = {3, 4, 5, 6};

        // when
        refAlgorithm.sort(nums);

        //then
        double[] expected = {3, 4, 5, 6};
        Assert.assertArrayEquals(expected, nums, delta);
    }

    @Test
    public void shouldReturnSortedArrayWhenInpArrHasElemInDescendingOrder() {

        // given
        double[] nums = {6, 5, 4, 3, 2};

        // when
        refAlgorithm.sort(nums);

        //then
        double[] expected = {2, 3, 4, 5, 6};
        Assert.assertArrayEquals(expected, nums, delta);
    }

    @Test
    public void shouldReturnSortedArrayWhenInpArrHasRandomElem() {

        //given
        double[] nums = {4, 1, 2, 6, 9, 0, 5};

        //when
        refAlgorithm.sort(nums);

        //then
        double[] expected = {0, 1, 2, 4, 5, 6, 9};
        Assert.assertArrayEquals(expected, nums, delta);
    }

    @Test
    public void shouldReturnSortedArrayWhenInpArrHasNegativeElem() {

        //given
        double[] nums = {-4, -1, -2, -6, -9, 0, -5};

        //when
        refAlgorithm.sort(nums);

        //then
        double[] expected = {-9, -6, -5, -4, -2, -1, 0};
        Assert.assertArrayEquals(expected, nums, delta);
    }

    @Test
    public void shouldReturnSortedArrayWhenInpArrHasEqualElem() {

        //given
        double[] nums = {1, 1, 1, 1, 1, 1, 1};

        //when
        refAlgorithm.sort(nums);

        //then
        double[] expected = {1, 1, 1, 1, 1, 1, 1};
        Assert.assertArrayEquals(expected, nums, delta);
    }

    @Test
    public void shouldReturnSortedArrayWhenInpArrHasManyRandomElem() {

        final int numberOfElements = 100000;

        //given
        double[] nums = new double[numberOfElements];
        double[] expected = new double[numberOfElements];
        Random random = new Random();

        for (int i = 0; i < numberOfElements; i++) {
            double generatedNumber = random.nextDouble();
            nums[i] = generatedNumber;
            expected[i] = generatedNumber;
        }

        //when
        refAlgorithm.sort(nums);

        //then
        Arrays.sort(expected);
        Assert.assertArrayEquals(expected, nums, delta);
    }

}
