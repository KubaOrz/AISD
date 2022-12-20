package pl.edu.pw.ee;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman {

    private HashMap<Character, String> charCodeDict;
    private HashMap<String, Character> codeCharDict;
    private String dictionary;

    public Huffman() {

    }

    public int huffman(String pathToRootDir, boolean compress) {
    	FileHandler fileHandler = new FileHandler();
        charCodeDict = new HashMap<>();
        codeCharDict = new HashMap<>();

        if (compress) {
            FileHandler.FileContent fileContent = fileHandler.readFileToCompress(pathToRootDir);
            PriorityQueue<Node> subTreesQueue = new PriorityQueue<>();

            fileContent.getNodes().keySet().forEach(key -> {
                int count = fileContent.getNodes().get(key);
                subTreesQueue.add(new Node(key, count));
            });

            Node huffmanTree = createHuffmanTree(subTreesQueue);
            createDict(huffmanTree);

            fileHandler.writeDictToFile(encodeDict(huffmanTree), pathToRootDir);
            String encodedText = encodeText(fileContent.getText());
            return fileHandler.writeCodedTextToFile(encodedText, pathToRootDir);
        } else {
            dictionary = DataConverter.deleteMissingBits(fileHandler.readDictFromFile(pathToRootDir));
            Node huffmanTree = decodeHuffmanTree(null);
            createDict(huffmanTree);

            String encodedText = fileHandler.readEncodedTextFromFile(pathToRootDir);
            String decodedText = decodeText(encodedText);

            fileHandler.writeDecodedTextToFile(pathToRootDir, decodedText);
            return decodedText.length();
        }
    }

    private Node createHuffmanTree(PriorityQueue<Node> subTrees) {
        while(subTrees.size() > 1) {
            Node firstNode = subTrees.poll();
            Node secondNode = subTrees.poll();
            if (firstNode == null || secondNode == null) {
                throw new IllegalStateException("One of the subtrees is null");
            }
            Node newRoot = new Node(firstNode.getCount() + secondNode.getCount());
            newRoot.setLeftChild(firstNode);
            newRoot.setRightChild(secondNode);
            subTrees.add(newRoot);
        }
        return subTrees.poll();
    }

    private void createDict(Node huffmanTree) {
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

    private String encodeText(String text) {
        char[] chars = text.toCharArray();
        StringBuilder encodedText = new StringBuilder();
        for (char character: chars) {
            encodedText.append(charCodeDict.get(character));
        }
        return encodedText.toString();
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
            }
        }
        return currentNode;
    }

    private String decodeText(String encodedText) {
        StringBuilder currentCode = new StringBuilder();
        StringBuilder decodedText = new StringBuilder();
        char[] encodedTextChars = DataConverter.deleteMissingBits(encodedText).toCharArray();
        for (Character bit: encodedTextChars) {
            currentCode.append(bit);
            if (codeCharDict.containsKey(currentCode.toString())) {
                decodedText.append(codeCharDict.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }
        return decodedText.toString();
    }
}
