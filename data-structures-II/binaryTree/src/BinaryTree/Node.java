/* Atividade Árvore Binária
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package BinaryTree;

import Queue.QNode;

public class Node extends QNode {

    protected String data;
    private Node parent;
    private Node left;
    private Node right;

    public Node(String data, Node parent, Node left, Node right, QNode next) {
        super(next);
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public Node(String data) { this(data, null, null, null, null); }
    public Node() { this(null, null, null, null, null); }

    public String getData() { return data; }
    public Node getParent() { return parent; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }

    public void setData(String data) { this.data = data; }
    public void setParent(Node parent) { this.parent = parent; }
    public void setLeft(Node left) { this.left = left; }
    public void setRight(Node right) { this.right = right; }

    public boolean isRoot() { return parent == null; }
    public boolean isLeaf() { return left == null && right == null; }

    public int getDegree() {
        int degree = 0;

        if (left != null) ++degree;
        if (right != null) ++degree;
        return degree;
    }

    public int getLevel() {
        int level = 0;
        Node aux = getParent();

        while (aux != null) {
            ++level;
            aux = aux.getParent();
        }
        return level;
    }

    public int getHeight() {
        int leftHeight = getHeight(left);
        int rightHeight = getHeight(right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private int getHeight(Node node) {
        if (node == null) return -1;

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Data: " + data + "\n")
                .append("Is root? " + isRoot() + "\n")
                .append("Is leaf? " + isLeaf() + "\n")
                .append("Node's degree: " + getDegree() + "\n")
                .append("Node's level: " + getLevel() + "\n")
                .append("Node's height: " + getHeight() + "\n");

        return sb.toString();
    }
}

