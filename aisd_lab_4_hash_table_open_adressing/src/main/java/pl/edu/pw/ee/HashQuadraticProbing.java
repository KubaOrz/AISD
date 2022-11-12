package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    private double a;
    private double b;

    public HashQuadraticProbing() {
        super();
        this.a = 0.5;
        this.b = 0.5;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        validateConstants(a, b);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) (key % m + a * i + b * i * i) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private void validateConstants(double a, double b) {
        if (a == 0 && b == 0) {
            this.a = 0.5;
            this.b = 0.5;
            throw new IllegalArgumentException("Constants a and b cannot be both equal to 0!");
        }
    }
}
