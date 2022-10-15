package pl.edu.pw.ee;

import pl.edu.pw.ee.services.SortUtils;
import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {  
        
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null!");
        }
        
        int minId;
        int n = nums.length;
        
        for (int i = 0; i < n - 1; i++) {
            minId = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minId]) {
                    minId = j;
                }
            }
            SortUtils.swap(nums, i, minId);
        }
    }

}
