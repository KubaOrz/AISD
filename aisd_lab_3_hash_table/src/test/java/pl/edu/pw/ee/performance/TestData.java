package pl.edu.pw.ee.performance;

public enum TestData {

    PRIMES(new int[]{4091, 8369, 16529, 32869, 65371, 131297, 262147},
            "src/test/java/pl/edu/pw/ee/performance/primes_performance_test.txt"),
    POWERS(new int[]{4096, 8192, 16384, 32768, 65536, 131072, 262144},
            "src/test/java/pl/edu/pw/ee/performance/powers_performance_test.txt"),
    OTHER_PRIMES(new int[]{4091, 8969, 17231, 33377, 63467, 136519, 262147},
            "src/test/java/pl/edu/pw/ee/performance/other_primes_performance_test.txt");

    private final int[] sizes;
    private final String pathName;

    TestData(int[] sizes, String pathName) {
        this.sizes = sizes;
        this.pathName = pathName;
    }

    public int[] getSizes() {
        return sizes;
    }

    public String getPathName() {
        return pathName;
    }
}
