package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HuffmanTest {

    private Huffman huffman;

    @Before
    public void setUp() {
        this.huffman = new Huffman();
    }

    @Test
    public void test() throws IOException {
        huffman.huffman("src/test/java/pl/edu/pw/ee/", false);
    }
}
