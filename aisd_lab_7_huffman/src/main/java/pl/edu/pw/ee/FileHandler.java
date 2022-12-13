package pl.edu.pw.ee;

import java.io.*;
import java.util.HashMap;

public class FileHandler {

    private final String fileToCompress = "to_compress.txt";
    private final String resultFile = "compressed_text.txt";
    private final String dictFile = "dictionary.txt";

    public class FileContent {
        private final HashMap<Character, Integer> nodes;
        private final String text;

        public FileContent(HashMap<Character, Integer> nodes, String text) {
            this.nodes = nodes;
            this.text = text;
        }

        public HashMap<Character, Integer> getNodes() {
            return nodes;
        }

        public String getText() {
            return text;
        }
    }

    public FileContent readFileToCompress(String pathname) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathname + fileToCompress));
        StringBuilder text = new StringBuilder();
        HashMap<Character, Integer> nodes = new HashMap<>();

        int character;

        while ((character = reader.read()) != -1) {
            Character parsedChar = (char) character;
            text.append(parsedChar);
            if (nodes.containsKey(parsedChar)) {
                nodes.put(parsedChar, nodes.get(parsedChar) + 1);
            } else {
                nodes.put(parsedChar, 1);
            }
        }

        return new FileContent(nodes, text.toString());
    }

    public int writeCodedTextToFile(HashMap<Character, String> codesDict, String text, String pathname) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(pathname + resultFile);
        char[] chars = text.toCharArray();
        int bitCount = 0;
        StringBuilder encodedText = new StringBuilder();
        for (char character: chars) {
            encodedText.append(codesDict.get(character));
            bitCount += codesDict.get(character).length();
        }
        printWriter.print(compressBinaryString(convertToFullBytes(encodedText, bitCount)));
        printWriter.close();
        return bitCount;
    }

    private String convertToFullBytes(StringBuilder encodedText, int bitCount) {
        int bytes = bitCount / 8;
        int lastByteBitCount = (bitCount + 3) % 8;
        int emptyBitCount = 8 - lastByteBitCount;
        String emptyBitsBin = Integer.toBinaryString(emptyBitCount);
        emptyBitsBin = String.format("%3s", emptyBitsBin).replaceAll(" ", "0");
        encodedText.append(generateMissingBits(emptyBitCount));
        encodedText.insert(0, emptyBitsBin);
        return encodedText.toString();
    }

    private String generateMissingBits(int length) {
        StringBuilder missingBits = new StringBuilder();
        for (int i = 0; i < length; i++) {
            missingBits.append('0');
        }
        return missingBits.toString();
    }

    private String compressBinaryString(String binary) {
        StringBuilder stringToWrite = new StringBuilder();
        while (!binary.isEmpty()) {
            String character = binary.substring(0, 8);
            binary = binary.substring(8);
            char charToWrite = (char) Integer.parseInt(character, 2);
            stringToWrite.append(charToWrite);
        }
        return stringToWrite.toString();
    }

    public void writeDictToFile(String codedDict, String pathname) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(pathname + dictFile);
        printWriter.println(compressBinaryString(
                convertToFullBytes(new StringBuilder(codedDict), codedDict.length()))
        );
        printWriter.close();
    }

    public String readDictFromFile(String pathname) throws IOException {
        return readFromFile(pathname + dictFile);
    }

    public String readEncodedTextFromFile(String pathname) throws IOException {
        return readFromFile(pathname + resultFile);
    }

    private String readFromFile(String pathname) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathname));
        int character;
        StringBuilder EncodedText = new StringBuilder();

        while ((character = reader.read()) != -1) {
            String binCharacter = Integer.toBinaryString(character);
            EncodedText.append(String.format("%8s", binCharacter).replaceAll(" ", "0"));
        }
        System.out.println(EncodedText);
        return EncodedText.toString();
    }

    public void writeDecodedTextToFile(String pathname, String decodedText) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(pathname + fileToCompress);
        writer.print(decodedText);
        writer.close();
    }
}
