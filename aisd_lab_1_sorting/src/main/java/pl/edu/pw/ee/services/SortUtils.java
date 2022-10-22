package pl.edu.pw.ee.services;

import java.util.List;

public class SortUtils {

    public static void swap(double[] nums, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstVal = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstVal;
        }
    }

    public static <T> void swap(List<T> data, int firstId, int secondId) {
        T firstVal = data.get(firstId);
        data.set(firstId, data.get(secondId));
        data.set(secondId, firstVal);
    }
}
