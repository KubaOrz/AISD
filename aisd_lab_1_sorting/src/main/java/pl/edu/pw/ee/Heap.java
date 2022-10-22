package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

import static pl.edu.pw.ee.services.SortUtils.swap;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;
    private int currentSize;

    public Heap(List<T> data) {

        this.data = data;
        currentSize = data.size();
        this.build();
    }

    @Override
    public void put(T item) {

        data.add(item);
        heapUp(item);
        currentSize++;
    }

    public void heapUp(T item) {

        int index = data.size() - 1;
        int parent = (index - 1) / 2;
        while (index > 0 && (item.compareTo(data.get(parent)) > 0)) {
            swap(data, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    @Override
    public T pop() {

        if (currentSize == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T maxValue = data.get(0);
        swap(data, 0, --currentSize);
        heapify(0, currentSize);
        return maxValue;
    }
    
    @Override
    public void build() {

        int n = data.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n);
        }
    }

    @Override
    public void heapify(int startId, int endId) {

        int left = 2 * startId + 1;
        int right = 2 * startId + 2;
        int largest = startId;
        int tmp = startId;
        while (true) {
            if (left < endId && data.get(left).compareTo(data.get(largest)) > 0) {
                largest = left;
            }
            if (right < endId && data.get(right).compareTo(data.get(largest)) > 0) {
                largest = right;
            }
            if (largest == tmp) {
                return;
            }
            swap(data, largest, tmp);
            tmp = largest;
            left = 2 * tmp + 1;
            right = 2 * tmp + 2;
        }
    }

}
