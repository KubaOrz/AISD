package pl.edu.pw.ee;

import pl.edu.pw.ee.exceptions.ElementNotFoundException;
import pl.edu.pw.ee.exceptions.NotImplementedException;
import pl.edu.pw.ee.services.HashTable;

import java.util.Arrays;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private final T del = (T) new Comparable<T>() {
        @Override
        public int compareTo(T o) {
            return 0;
        }
    };
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId] != del) {
            if (newElem.equals(hashElems[hashId])) {
                hashElems[hashId] = newElem;
                return;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId] == elem) {
                return elem;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
        throw new ElementNotFoundException("Given element not found in the array!");
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId].equals(elem)) {
                hashElems[hashId] = del;
                nElems--;
                return;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
        throw new ElementNotFoundException("Given element not found in the array!");

    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        T[] prevHashElems = Arrays.copyOf(this.hashElems, this.size);
        this.size *= 2;
        nElems = 0;
        this.hashElems = (T[]) new Comparable[this.size];
        for (T elem: prevHashElems) {
            if (elem != nil) {
                put(elem);
            }
        }
    }

}