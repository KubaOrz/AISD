package pl.edu.pw.ee;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman {

    private HashMap<Character, String> charCodeDict;
    private HashMap<String, Character> codeCharDict;
    private String dictionary;

    public Huffman() {
        charCodeDict = new HashMap<>();
        codeCharDict = new HashMap<>();
    }

    public int huffman(String pathToRootDir, boolean compress) throws IOException {
    	FileHandler fileHandler = new FileHandler();

        if (compress) {
            FileHandler.FileContent fileContent = fileHandler.readFileToCompress(pathToRootDir);
            PriorityQueue<Node> subTreesQueue = new PriorityQueue<>();

            fileContent.getNodes().keySet().forEach(key -> {
                int count = fileContent.getNodes().get(key);
                subTreesQueue.add(new Node(key, count));
                System.out.println(key + ": " + count);
            });

            Node huffmanTree = createHuffmanTree(subTreesQueue);

            createCharCodeDict(huffmanTree);

            for (Character letter : charCodeDict.keySet()) {
                System.out.println(letter + ": " + charCodeDict.get(letter));
            }
            fileHandler.writeDictToFile(encodeDict(huffmanTree), pathToRootDir);
            return fileHandler.writeCodedTextToFile(charCodeDict, fileContent.getText(), pathToRootDir);
        } else {
            dictionary = deleteMissingBits(fileHandler.readDictFromFile(pathToRootDir));
            Node huffmanTree = decodeHuffmanTree(null);
            createCharCodeDict(huffmanTree);

            for (Character letter : charCodeDict.keySet()) {
                System.out.println(letter + ": " + charCodeDict.get(letter));
            }
            String encodedText = fileHandler.readEncodedTextFromFile(pathToRootDir);
            String decodedText = decodeText(encodedText);
            fileHandler.writeDecodedTextToFile(pathToRootDir, decodedText);
            System.out.println(decodedText);
        }
        return -1;
    }

    private Node createHuffmanTree(PriorityQueue<Node> subTrees) {
        while(subTrees.size() > 1) {
            Node firstNode = subTrees.poll();
            Node secondNode = subTrees.poll();
            Node newRoot = new Node(firstNode.getCount() + secondNode.getCount()); // Chyba nie powinno się wywalić
            newRoot.setLeftChild(firstNode);
            newRoot.setRightChild(secondNode);
            subTrees.add(newRoot);
        }
        return subTrees.poll();
    }

    private void createCharCodeDict(Node huffmanTree) {
        traversePostOrder(huffmanTree, "");
    }

    private void traversePostOrder(Node node, String code) {
        if (node != null) {
            traversePostOrder(node.getLeftChild(), code + '0');
            traversePostOrder(node.getRightChild(), code + '1');
            if (node.getLetter() != null) {
                if (code.isEmpty()) {
                    code = "0";
                }
                charCodeDict.put(node.getLetter(), code);
                codeCharDict.put(code, node.getLetter());
            }
        }
    }

    private String encodeDict(Node huffmanTree) {
        return traversePreOrder(huffmanTree, "");
    }

    private String traversePreOrder(Node node, String result) {
        if (node != null) {

            if (node.getLetter() != null) {
                String charToBinStr = Integer.toBinaryString(node.getLetter());
                result += "1" + String.format("%8s", charToBinStr).replaceAll(" ", "0");
            } else {
                result += '0';
            }
            result = traversePreOrder(node.getLeftChild(), result);
            result = traversePreOrder(node.getRightChild(), result);
        }
        return result;
    }

    private Node decodeHuffmanTree(Node currentNode) {
        if (!dictionary.isEmpty()) {
            char currentChar = dictionary.charAt(0);
            dictionary = dictionary.substring(1);
            if (currentChar == '0') {
                Node node = new Node();
                node.setLeftChild(decodeHuffmanTree(node));
                node.setRightChild(decodeHuffmanTree(node));
                return node;
            } else if (currentChar == '1') {
                String character = dictionary.substring(0, 8);
                dictionary = dictionary.substring(8);
                return new Node((char) Integer.parseInt(character, 2), 0);
            } else {
                throw new IllegalArgumentException("Non permitted signs in the file!");
            }
        }
        return currentNode;
    }

    private String decodeText(String encodedText) {
        StringBuilder currentCode = new StringBuilder();
        StringBuilder decodedText = new StringBuilder();
        char[] encodedTextChars = deleteMissingBits(encodedText).toCharArray();
        for (Character bit: encodedTextChars) {
            currentCode.append(bit);
            if (codeCharDict.containsKey(currentCode.toString())) {
                decodedText.append(codeCharDict.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }
        return decodedText.toString();
    }

    private String deleteMissingBits(String encodedText) {
        int missingBitsCount = Integer.parseInt(encodedText.substring(0, 3), 2);
        return encodedText.substring(3, encodedText.length() - missingBitsCount);
    }
}
