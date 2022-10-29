package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.lang.reflect.Array;

public class HashListChaining<T> implements HashTable<T> {

    private final Elem<T> nil = null;
    private final Elem<T>[] hashElems;
    private int nElem;

    @SuppressWarnings("unchecked")
    public HashListChaining(int size) {
        validate(size);
        hashElems = (Elem<T>[]) Array.newInstance(Elem.class, size);
        initializeHash();
    }

    private void validate(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem<>(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];

        if (elem == null) {
            return;
        }
        Elem<T> prevElem = null;

        while (!elem.value.equals(value)) {
            prevElem = elem;
            if (elem.next == null) {
                return;
            }
            elem = elem.next;
        }

        if (prevElem == null) {
            hashElems[hashId] = elem.next;
        } else {
            prevElem.next = elem.next;
        }
        nElem--;
    }

    // Metoda potrzebna wyłącznie do testowania
    public int countElements() {
        return nElem;
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode) % n;
    }

    private static class Elem<V> {
        private V value;
        private Elem<V> next;

        Elem(V value, Elem<V> nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

}