package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;
import pl.edu.pw.ee.services.SortUtils;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {

        if (nums == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    SortUtils.swap(nums, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

}
