package pl.edu.pw.ee.services;

import java.util.Random;

public class DataGenerator {

    private static final long SEED = 263046;
    private Random random;

    public DataGenerator() {
        random = new Random(SEED);
    }

    public double[] generateRandom(int size) {
        double[] generated = new double[size];
        for (int i = 0; i < size; i++) {
            generated[i] = random.nextDouble();
        }
        return generated;
    }

    public double[] generateAscending(int size) {
        double[] generated = new double[size];
        for (int i = 0; i < size; i++) {
            generated[i] = i;
        }
        return generated;
    }

    public double[] generateDescending(int size) {
        double[] generated = new double[size];
        int index = 0;
        for (int i = size; i > 0; i--) {
            generated[index] = i;
            index++;
        }
        return generated;
    }
}
