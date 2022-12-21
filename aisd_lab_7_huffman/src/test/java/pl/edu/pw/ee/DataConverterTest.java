package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

public class DataConverterTest {

    @Test
    public void should_ConvertToFullBytes_WhenGivenSixMissingBits() {
        // given
        StringBuilder encodedText = new StringBuilder("111000011010111000");
        String expectedResult = "011111000011010111000000";

        // when
        String convertedText = DataConverter.convertToFullBytes(encodedText);

        // then
        Assert.assertEquals(expectedResult, convertedText);
    }

    @Test
    public void should_ConvertToFullBytes_WhenGivenThreeMissingBits() {
        // given
        StringBuilder encodedText = new StringBuilder("01110");
        String expectedResult = "00001110";

        // when
        String convertedText = DataConverter.convertToFullBytes(encodedText);

        // then
        Assert.assertEquals(expectedResult, convertedText);
    }

    @Test
    public void should_GenerateMissingBitsProperly() {
        // given
        String expectedBits = "000000";

        // when
        String bits = DataConverter.generateMissingBits(6);

        // then
        Assert.assertEquals(expectedBits, bits);
    }

    @Test
    public void should_CompressProperly_WhenGivenProperBinaryString() {
        // given
        String toCompress = "0011110000110011";
        String expected = "<3";

        // when
        String copmressed = DataConverter.compressBinaryString(toCompress);

        // then
        Assert.assertEquals(expected, copmressed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenTryToCompressWrongLengthBinaryString() {
        // given
        String toCompress = "0011110000001";

        // when
        DataConverter.compressBinaryString(toCompress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenTryToCompressWrongBinaryString() {
        // given
        String toCompress = "ab0101hu";

        // when
        DataConverter.compressBinaryString(toCompress);

        // then
        assert false;
    }

    @Test
    public void should_ReturnTrimmedString_WhenGivenProperBinaryString() {
        // given
        String toTrim = "011111000011010111000000";
        String expected = "111000011010111000";

        // when
        String trimmed = DataConverter.deleteMissingBits(toTrim);

        // then
        Assert.assertEquals(expected, trimmed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_whenGivenTooShortBinaryString() {
        // given
        String toTrim = "0111";

        // when
        DataConverter.deleteMissingBits(toTrim);

        // then
        assert false;
    }

    @Test
    public void should_ReturnEmptyString_WhenGivenEmptyString() {
        // given
        String toTrim = "";
        String expected = "";

        // when
        String trimmed = DataConverter.deleteMissingBits(toTrim);

        // then
        Assert.assertEquals(expected, trimmed);
    }
}
