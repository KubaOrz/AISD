package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTest {

    private Huffman huffman;
    private static final String REGULAR_TEXT_FILE = "src/main/resources/test_1/";
    private static final String NO_MISSING_BITS_FILE = "src/main/resources/test_2/";
    private static final String NO_SIGNS_FILE = "src/main/resources/test_3/";
    private static final String ONE_SIGN_FILE = "src/main/resources/test_4/";
    private static final String ILLEGAL_SIGNS_FILE = "src/main/resources/test_5/";
    private static final String LONG_TEXT_FILE = "src/main/resources/test_6/";
    private static final String ONLY_NEW_LINES_FILE = "src/main/resources/test_7/";
    private static final String NON_EXISTING_FILE = "src/main/resources/test_420";


    @Before
    public void setUp() {
        this.huffman = new Huffman();
    }

    @Test(expected = WrongFileNameException.class)
    public void should_ThrowException_WhenTryToCompressNonExistingFile() {
        // when
        huffman.huffman(NON_EXISTING_FILE, true);

        // then
        assert false;
    }

    @Test(expected = WrongFileNameException.class)
    public void should_ThrowException_WhenTryToDecompressNonExistingFile() {
        // when
        huffman.huffman(NON_EXISTING_FILE, false);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenFileHasIllegalSigns() {
        // when
        huffman.huffman(ILLEGAL_SIGNS_FILE, true);

        // then
        assert false;
    }

    @Test
    public void should_ReturnProperBitCount_WhenGivenBasicText() {
        // given
        int expectedBitCount = 24;

        // when
        int bitCount = huffman.huffman(REGULAR_TEXT_FILE, true);

        // then
        Assert.assertEquals(expectedBitCount, bitCount);
    }

    @Test
    public void should_ReturnProperBitCount_WhenGivenNoMissingBitsText() {
        // given
        int expectedBitCount = 64;

        // when
        int bitCount = huffman.huffman(NO_MISSING_BITS_FILE, true);

        // then
        Assert.assertEquals(expectedBitCount, bitCount);
    }

    @Test
    public void should_ReturnProperBitCount_WhenGivenNoSignsText() {
        // given
        int expectedBitCount = 0;

        // when
        int bitCount = huffman.huffman(NO_SIGNS_FILE, true);

        // then
        Assert.assertEquals(expectedBitCount, bitCount);
    }

    @Test
    public void should_ReturnProperBitCount_WhenGivenOneSignText() {
        // given
        int expectedBitCount = 8;

        // when
        int bitCount = huffman.huffman(ONE_SIGN_FILE, true);

        // then
        Assert.assertEquals(expectedBitCount, bitCount);
    }

    @Test
    public void should_ReturnProperBitCount_WhenGivenOnlyNewLinesText() {
        // given
        int expectedBitCount = 16;

        // when
        int bitCount = huffman.huffman(ONLY_NEW_LINES_FILE, true);

        // then
        Assert.assertEquals(expectedBitCount, bitCount);
    }

    @Test
    public void should_CompressCorrectly_WhenGivenRegularText() {
        // given
        String expectedCode = "011111000011010111000000";

        // when
        huffman.huffman(REGULAR_TEXT_FILE, true);
        String code = readFile(REGULAR_TEXT_FILE + "compressed_text.txt");

        // then
        Assert.assertEquals(expectedCode, code);
    }

    @Test
    public void should_CompressCorrectly_WhenGivenNoSignsText() {
        // given
        String expectedCode = "";

        // when
        huffman.huffman(NO_SIGNS_FILE, true);
        String code = readFile(NO_SIGNS_FILE + "compressed_text.txt");

        // then
        Assert.assertEquals(expectedCode, code);
    }

    @Test
    public void should_CompressCorrectly_WhenGivenOneSignText() {
        // given
        String expectedCode = "10000000";

        // when
        huffman.huffman(ONE_SIGN_FILE, true);
        String code = readFile(ONE_SIGN_FILE + "compressed_text.txt");

        // then
        Assert.assertEquals(expectedCode, code);
    }

    @Test
    public void should_CompressAndDecompressCorrectly_WhenGivenRegularText() {
        // given
        String expectedCode = readFile(REGULAR_TEXT_FILE + "to_compress.txt");

        // when
        huffman.huffman(REGULAR_TEXT_FILE, true);
        huffman.huffman(REGULAR_TEXT_FILE, false);
        String decompressedCode = readFile(REGULAR_TEXT_FILE + "to_compress.txt");

        // then
        Assert.assertEquals(expectedCode, decompressedCode);
    }

    @Test
    public void should_CompressAndDecompressCorrectly_WhenGivenNoMissingBitsText() {
        // given
        String expectedCode = readFile(NO_MISSING_BITS_FILE + "to_compress.txt");

        // when
        huffman.huffman(NO_MISSING_BITS_FILE, true);
        huffman.huffman(NO_MISSING_BITS_FILE, false);
        String decompressedCode = readFile(NO_MISSING_BITS_FILE + "to_compress.txt");

        // then
        Assert.assertEquals(expectedCode, decompressedCode);
    }

    @Test
    public void should_CompressAndDecompressCorrectly_WhenGivenNoSignsText() {
        // given
        String expectedCode = readFile(NO_SIGNS_FILE + "to_compress.txt");

        // when
        huffman.huffman(NO_SIGNS_FILE, true);
        huffman.huffman(NO_SIGNS_FILE, false);
        String decompressedCode = readFile(NO_SIGNS_FILE + "to_compress.txt");

        // then
        Assert.assertEquals(expectedCode, decompressedCode);
    }

    @Test
    public void should_CompressAndDecompressCorrectly_WhenGivenOneSignText() {
        // given
        String expectedCode = readFile(ONE_SIGN_FILE + "to_compress.txt");

        // when
        huffman.huffman(ONE_SIGN_FILE, true);
        huffman.huffman(ONE_SIGN_FILE, false);
        String decompressedCode = readFile(ONE_SIGN_FILE + "to_compress.txt");

        // then
        Assert.assertEquals(expectedCode, decompressedCode);
    }

    @Test
    public void should_CompressAndDecompressCorrectly_WhenGivenLongText() {
        // given
        String expectedCode = readFile(LONG_TEXT_FILE + "to_compress.txt");

        // when
        huffman.huffman(LONG_TEXT_FILE, true);
        huffman.huffman(LONG_TEXT_FILE, false);
        String decompressedCode = readFile(LONG_TEXT_FILE + "to_compress.txt");

        // then
        Assert.assertEquals(expectedCode, decompressedCode);
    }

    @Test
    public void should_CompressAndDecompressCorrectly_WhenGivenOnlyNewLinesText() {
        // given
        String expectedCode = readFile(ONLY_NEW_LINES_FILE + "to_compress.txt");

        // when
        huffman.huffman(ONLY_NEW_LINES_FILE, true);
        huffman.huffman(ONLY_NEW_LINES_FILE, false);
        String decompressedCode = readFile(ONLY_NEW_LINES_FILE + "to_compress.txt");

        // then
        Assert.assertEquals(expectedCode, decompressedCode);
    }

    private String readFile(String pathname) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathname));
            int character;
            StringBuilder fileContent = new StringBuilder();

            while ((character = reader.read()) != -1) {
                String binaryChar = Integer.toBinaryString(character);
                binaryChar = String.format("%8s", binaryChar).replaceAll(" ", "0");
                fileContent.append(binaryChar);
            }

            reader.close();
            return fileContent.toString();
        } catch (IOException e) {
            throw new WrongFileNameException();
        }
    }
}
