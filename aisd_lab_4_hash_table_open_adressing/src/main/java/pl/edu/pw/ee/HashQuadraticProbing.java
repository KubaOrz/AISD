package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    private final double a;
    private final double b;

    public HashQuadraticProbing() {
        super();
        this.a = 0.5;
        this.b = 0.5;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        validateConstants(b);
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

    private void validateConstants(double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Constant b cannot be equal to 0!");
        }
    }
}
