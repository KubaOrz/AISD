package pl.edu.pw.ee;

public class Node implements Comparable<Node>{
    private Character letter;
    private int count;
    private Node leftChild;
    private Node rightChild;

    public Node() {

    }

    public Node(Character letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    public Node(int count) {
        this.letter = null;
        this.count = count;
    }

    public Character getLetter() {
        return letter;
    }

    public int getCount() {
        return count;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(Node o) {
        return this.count - o.count;
    }
}
