package pl.edu.pw.ee;

import java.io.*;
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
                if (character >= 128) {
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

            reader.close();
            return new FileContent(nodes, text.toString());
        } catch (IOException e) {
            throw new WrongFileNameException();
        }
    }

    public int writeCodedTextToFile(String encodedText, String pathname) {
        try {
            PrintWriter printWriter = new PrintWriter(pathname + resultFile);
            String fullBytesText = DataConverter.convertToFullBytes(new StringBuilder(encodedText));
            String textToWrite = DataConverter.compressBinaryString(fullBytesText);
            printWriter.print(textToWrite);
            printWriter.close();
            return fullBytesText.length();
        } catch (FileNotFoundException e) {
            throw new WrongFileNameException();
        }
    }

    public void writeDictToFile(String codedDict, String pathname) {
        try {
            PrintWriter printWriter = new PrintWriter(pathname + dictFile);
            printWriter.print(DataConverter.compressBinaryString(
                    DataConverter.convertToFullBytes(new StringBuilder(codedDict)))
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
                String binCharacter = Integer.toBinaryString(character);
                EncodedText.append(String.format("%8s", binCharacter).replaceAll(" ", "0"));
            }
            reader.close();
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
