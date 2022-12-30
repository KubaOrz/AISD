package pl.edu.pw.ee;

import org.junit.Assert;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LongestCommonSubsequenceTest {

    private static final String MODELS_PATH = "src/test/resources/models/";
    private static final String RESULTS_PATH = "src/test/resources/results/";
    private LongestCommonSubsequence lcs;

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenBothStringsAreNull() {
        // when
        lcs = new LongestCommonSubsequence(null, null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenFirstStringIsNull() {
        // when
        lcs = new LongestCommonSubsequence(null, "non null string");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenSecondStringIsNull() {
        // when
        lcs = new LongestCommonSubsequence("non null string", null);

        // then
        assert false;
    }

    @Test
    public void should_ReturnEmptyString_WhenGivenTwoEmptyStrings() {
        // given
        String string1 = "";
        String string2 = "";
        String expectedLCS = "";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnEmptyString_WhenFirstStringIsEmpty() {
        // given
        String string1 = "";
        String string2 = "not empty string";
        String expectedLCS = "";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnEmptyString_WhenSecondStringIsEmpty() {
        // given
        String string1 = "not empty string";
        String string2 = "";
        String expectedLCS = "";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenTwoShortStrings() {
        // given
        String string1 = "mikołaj";
        String string2 = "ikona";
        String expectedLCS = "ikoa";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenMultipleLcsExist() {
        // given
        String string1 = "abc";
        String string2 = "cba";
        String expectedLCS = "c";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();
        lcs.display();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenNewLineSigns() {
        // given
        String string1 = "miko\nł\naj";
        String string2 = "iko\nna\n";
        String expectedLCS = "iko\na";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenCarriageReturnSigns() {
        // given
        String string1 = "mikoł\raj";
        String string2 = "iko\rna";
        String expectedLCS = "iko\ra";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenTabulationSigns() {
        // given
        String string1 = "miko\tł\taj";
        String string2 = "iko\tna";
        String expectedLCS = "iko\ta";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenBackspaceSigns() {
        // given
        String string1 = "mik\bołaj";
        String string2 = "ik\bona";
        String expectedLCS = "ik\boa";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenDifferentSpecialSigns() {
        // given
        String string1 = "Kocham algory\btmy i\n struk\ttury dany\rch!";
        String string2 = "rytmi\nk\ra";
        String expectedLCS = "rytmi\nk\r";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenSemicolon() {
        // given
        String string1 = "aaabb\"semicolon\"a";
        String string2 = "acdfb\"semicolon\"f";
        String expectedLCS = "ab\"semicolon\"";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenEqualStrings() {
        // given
        String string1 = "Kocham algorytmy i struktury danych!";
        String string2 = "Kocham algorytmy i struktury danych!";
        String expectedLCS = "Kocham algorytmy i struktury danych!";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenContainingStrings() {
        // given
        String string1 = "Konstantynopolitańczykowianeczka";
        String string2 = "Konstantynopolitańczyk";
        String expectedLCS = "Konstantynopolitańczyk";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenStringsWithDifferentCharacters() {
        // given
        String string1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String string2 = "bbbbbbbbbbbbbbb";
        String expectedLCS = "";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenEqualStringsWithDiffCapitalization() {
        // given
        String string1 = "Let it snow";
        String string2 = "lEt It SNow";
        String expectedLCS = "t t ow";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnCorrectLcs_WhenGivenLongStrings() {
        // given
        String string1 = "często_z_odkrywaniem";
        String string2 = "rzeczy_nie_trzeba\n_się_spieszyć";
        String expectedLCS = "cz__raie";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        String actualLCS = lcs.findLCS();

        // then
        Assert.assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_ReturnDisplayTableCorrectly_WhenGivenShortStrings() {
        // given
        String string1 = "mikołaj";
        String string2 = "ikona";
        String modelFilePath = MODELS_PATH + "short_strings_display_model.txt";
        String resultFilePath = RESULTS_PATH + "short_strings_display_results.txt";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        lcs.findLCS();
        lcs.display(resultFilePath);

        // then
        assert checkFileEquality(modelFilePath, resultFilePath);
    }

    @Test
    public void should_ReturnDisplayTableCorrectly_WhenGivenLongStrings() {
        // given
        String string1 = "często_z_odkrywaniem";
        String string2 = "rzeczy_nie_trzeba\n_się_spieszyć";
        String modelFilePath = MODELS_PATH + "long_strings_display_model.txt";
        String resultFilePath = RESULTS_PATH + "long_strings_display_results.txt";

        // when
        lcs = new LongestCommonSubsequence(string1, string2);
        lcs.findLCS();
        lcs.display(resultFilePath);

        // then
        assert checkFileEquality(modelFilePath, resultFilePath);
    }

    private boolean checkFileEquality(String modelFilePath, String resultFilePath) {
        try {
            BufferedReader modelReader = new BufferedReader(new FileReader(modelFilePath));
            BufferedReader resultReader = new BufferedReader(new FileReader(resultFilePath));
            String modelLine;
            String resultLine;

            while ((modelLine = modelReader.readLine()) != null) {
                resultLine = resultReader.readLine();

                if (!Objects.equals(modelLine, resultLine)) {
                    return false;
                }
            }
            modelReader.close();
            resultReader.close();
            return true;
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read the file!");
        }
    }
}
