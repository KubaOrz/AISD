package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining implements HashTable {

    private final Elem nil = null;
    private final Elem[] hashElems;
    private int nElem;

    private class Elem {
        private Object value;
        private Elem next;

        Elem(Object value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = new Elem[size];
        initializeHash();
    }

    @Override
    public void add(Object value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) { // value??
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public Object get(Object value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : nil;
    }
    
    @Override
    public void delete(Object value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);
        
        Elem elem = hashElems[hashId];
        
        if (elem == null) {
            return;
        }
        Elem prevElem = null;
        
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

}