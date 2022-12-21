package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class FileHandlerTest {

    private static final String TEST_FILE1 = "src/main/resources/FileHandler_test_1/";
    private static final String TEST_FILE2 = "src/main/resources/FileHandler_test_2/";
    private FileHandler fileHandler;

    @Before
    public void setUp() {
        fileHandler = new FileHandler();
    }

    @Test
    public void should_ReturnProperFileContent() {
        // given
        String expectedText = "niemanie";
        HashMap<Character, Integer> expectedNodes = new HashMap<>();
        expectedNodes.put('n', 2);
        expectedNodes.put('i', 2);
        expectedNodes.put('e', 2);
        expectedNodes.put('m', 1);
        expectedNodes.put('a', 1);

        // when
        FileHandler.FileContent fileContent = fileHandler.readFileToCompress(TEST_FILE1);

        // then
        Assert.assertEquals(expectedText, fileContent.getText());
        Assert.assertEquals(expectedNodes, fileContent.getNodes());
    }

    @Test(expected = WrongFileNameException.class)
    public void should_throwException_WhenTryToWriteDictToReadonlyFile() {
        // when
        fileHandler.writeDictToFile("0110100101", TEST_FILE1);
    }

    @Test(expected = WrongFileNameException.class)
    public void should_throwException_WhenTryToWriteEncodedTextToReadonlyFile() {
        // when
        fileHandler.writeCodedTextToFile("0110100101", TEST_FILE1);
    }

    @Test(expected = WrongFileNameException.class)
    public void should_throwException_WhenTryToWriteDecodedTextToReadonlyFile() {
        // when
        fileHandler.writeDecodedTextToFile(TEST_FILE2, "11010101010");
    }
}
