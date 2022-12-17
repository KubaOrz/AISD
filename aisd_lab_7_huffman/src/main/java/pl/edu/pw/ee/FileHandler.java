package pl.edu.pw.ee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.HashMap;

public class FileHandler {

    private final String fileToCompress = "to_compress.txt";
    private final String resultFile = "compressed_text.txt";
    private final String dictFile = "dictionary.txt";

    public static class FileContent {
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

    public FileContent readFileToCompress(String pathname) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathname + fileToCompress));
            StringBuilder text = new StringBuilder();
            HashMap<Character, Integer> nodes = new HashMap<>();

            int character;

            while ((character = reader.read()) != -1) {
                if (character > 128) {
                    throw new IllegalArgumentException();
                }
                Character parsedChar = (char) character;
                text.append(parsedChar);
                if (nodes.containsKey(parsedChar)) {
                    nodes.put(parsedChar, nodes.get(parsedChar) + 1);
                } else {
                    nodes.put(parsedChar, 1);
                }
            }

            return new FileContent(nodes, text.toString());
        } catch (IOException e) {
            throw new WrongFileNameException();
        }
    }

    public int writeCodedTextToFile(String encodedText, String pathname) {
        try {
            PrintWriter printWriter = new PrintWriter(pathname + resultFile);
            String fullBytesText = convertToFullBytes(new StringBuilder(encodedText), encodedText.length());
            String textToWrite = compressBinaryString(fullBytesText);
            printWriter.print(textToWrite);
            printWriter.close();
            return textToWrite.length();
        } catch (FileNotFoundException e) {
            throw new WrongFileNameException("Nie znaleziono pliku " + resultFile);
        }
    }

    private String convertToFullBytes(StringBuilder encodedText, int bitCount) {
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

    private String generateMissingBits(int length) {
        return "0".repeat(length);
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

    public void writeDictToFile(String codedDict, String pathname) {
        try {
            PrintWriter printWriter = new PrintWriter(pathname + dictFile);
            printWriter.println(compressBinaryString(
                    convertToFullBytes(new StringBuilder(codedDict), codedDict.length()))
            );
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new WrongFileNameException();
        }
    }

    public String readDictFromFile(String pathname) {
        return readFromFile(pathname + dictFile);
    }

    public String readEncodedTextFromFile(String pathname) {
        return readFromFile(pathname + resultFile);
    }

    private String readFromFile(String pathname) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathname));
            int character;
            StringBuilder EncodedText = new StringBuilder();

            while ((character = reader.read()) != -1) {
                if (character > 128) {
                    throw new IllegalArgumentException();
                }
                String binCharacter = Integer.toBinaryString(character);
                EncodedText.append(String.format("%8s", binCharacter).replaceAll(" ", "0"));
            }
            return EncodedText.toString();
        } catch (IOException e) {
            throw new WrongFileNameException();
        }
    }

    public void writeDecodedTextToFile(String pathname, String decodedText) {
        try {
            PrintWriter writer = new PrintWriter(pathname + fileToCompress);
            writer.print(decodedText);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new WrongFileNameException();
        }
    }
}
