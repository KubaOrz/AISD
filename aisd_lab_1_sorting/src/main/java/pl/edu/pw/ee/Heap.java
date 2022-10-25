package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

import java.util.List;

import static pl.edu.pw.ee.services.SortUtils.swap;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private final List<T> data;
    private int currentSize;

    public Heap(List<T> data) {
        validateData(data);
        this.data = data;
        this.currentSize = data.size();
        this.build();
    }

    private void validateData(List<T> data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    public int getCurrentSize() {
        return currentSize;
    }

    @Override
    public void put(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
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
        if (startId < 0 || endId < 0 || startId > data.size() || endId > data.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
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
