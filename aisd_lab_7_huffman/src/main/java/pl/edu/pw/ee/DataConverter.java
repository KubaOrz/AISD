package pl.edu.pw.ee;

public class DataConverter {

    public static String convertToFullBytes(StringBuilder encodedText) {
        if (encodedText.length() == 0) {
            return "";
        }
        int bitCount = encodedText.length();
        int lastByteBitCount = (bitCount + 3) % 8;
        int emptyBitCount = 8 - lastByteBitCount;
        if (emptyBitCount == 8) {
            emptyBitCount = 0;
        }
        String emptyBitsBin = Integer.toBinaryString(emptyBitCount);
        emptyBitsBin = String.format("%3s", emptyBitsBin).replaceAll(" ", "0");
        encodedText.append(generateMissingBits(emptyBitCount));
        encodedText.insert(0, emptyBitsBin);
        return encodedText.toString();
    }

    public static String generateMissingBits(int length) {
        return "0".repeat(length);
    }

    public static String compressBinaryString(String binary) {
        if (binary.length() % 8 != 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringToWrite = new StringBuilder();
        while (!binary.isEmpty()) {
            String character = binary.substring(0, 8);
            binary = binary.substring(8);
            try {
                char charToWrite = (char) Integer.parseInt(character, 2);
                stringToWrite.append(charToWrite);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
        return stringToWrite.toString();
    }

    public static String deleteMissingBits(String encodedText) {
        if (encodedText.isEmpty()) {
            return encodedText;
        }
        try {
            int missingBitsCount = Integer.parseInt(encodedText.substring(0, 3), 2);
            return encodedText.substring(3, encodedText.length() - missingBitsCount);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
