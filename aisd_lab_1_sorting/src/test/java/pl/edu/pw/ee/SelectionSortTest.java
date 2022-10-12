package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

public class SelectionSortTest {
    
    private Sorting selectionSort;
    private static final double EPS = 0;
    
    @Before
    public void setUp() {
        selectionSort = new SelectionSort();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void checkIfNull() {
        
        // given
        double[] nums = null;
        
        // when
        selectionSort.sort(nums);
        
        // then
        assert false;
    }
    
    @Test
    public void checkArrayWithNoElements() {
        
        // given
        double[] nums = {};
        
        // when
        selectionSort.sort(nums);
        
        //then
        Assert.assertEquals(0, nums.length);
    }

    @Test
    public void checkArrayWithOneElement() {
        
        // given
        double[] nums = {3};
        
        // when
        selectionSort.sort(nums);
        
        //then
        double[] expected = {3};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldSortArrayWithSortedElement() {
        
        // given
        double[] nums = {3, 4, 5, 6};
        
        // when
        selectionSort.sort(nums);
        
        //then
        double[] expected = {3, 4, 5, 6};
        Assert.assertArrayEquals(expected, nums, EPS);
    }
    
    @Test
    public void shouldSortArrayWithDescendingOrderElement() {
        
        // given
        double[] nums = {6, 5, 4, 3, 2};       
        
        // when
        selectionSort.sort(nums);
        
        //then
        double[] expected = {2, 3, 4, 5, 6};
        Assert.assertArrayEquals(expected, nums, EPS);
    }
}
