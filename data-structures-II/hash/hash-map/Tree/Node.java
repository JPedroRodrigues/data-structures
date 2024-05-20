/*
 * Joao Pedro Rodrigues Vieira          RA 10403595
 * Sabrina Midori F. T. de Carvalho     RA 10403595
 * Pedro Pessuto Rodrigues Ferreira     RA 10409729
 * Course: Data Structures II           Class 04G11
 * Professor Andre Kishimoto            Hash Table Project
 * References:
    * https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-12/Tabela%20Hash.pdf
    * https://www.freecodecamp.org/portuguese/news/interfaces-em-java-explicadas-com-exemplos/
 */

package Tree;

public class Node {

    private Node left;
    private Node right;
    private Node parent;
    private int key;
    private String value;
    private int balanceFactor;

    public Node(int key, String value, Node left, Node right, Node parent) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.balanceFactor = getBalanceFactor();
    }

    public Node(int key, String value) {
        this(key, value, null, null, null);
        this.balanceFactor = getBalanceFactor();
    }

    public Node() { this(0, null, null, null, null); this.balanceFactor = getBalanceFactor(); }

    public Node getLeft() { return left; }

    public Node getRight() { return right; }

    public Node getParent() { return parent; }

    public int getKey() { return key; }

    public String getValue() { return value; }

    public Node getNode(int key) { return this; }

    public int getBalanceFactor() {
        updateBalanceFactor();
        return balanceFactor;
    }

    public void setLeft(Node left) { this.left = left; }

    public void setRight(Node right) { this.right = right; }

    public void setParent(Node parent) { this.parent = parent; }

    public void setKey(int key) { this.key = key; }

    public void setValue(String value) { this.value = value; }

    private void updateBalanceFactor() {
        int rightHeight = -1;
        int leftHeight = -1;

        if (getRight() != null) rightHeight = getRight().getHeight();
        if (getLeft() != null) leftHeight = getLeft().getHeight();

        balanceFactor = rightHeight - leftHeight;
    }

    public boolean isRoot() { return parent == null; }

    public boolean isLeaf() { return (left == null && right == null); }

    public int getDegree() {
        if (left == null && right == null) return 0;
        else if (left != null && right != null) return 2;
        else return 1;
    }

    public int getLevel() {
        if (isRoot()) return 0;
        return getLevel(this);
    }

    private int getLevel(Node node) {
        if (node == null) return -1;
        return getLevel(node.getParent()) + 1;
    }

    public int getHeight() {
        if (isLeaf()) return 0;
        return getHeight(this);
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

        sb.append("Key: " + key + "; Value: " + value + "\n");
        if (getParent() != null) sb.append("Parent: " + getParent().getKey() + "\n");
        if (getLeft() != null) sb.append("Left Child: " + getLeft().getKey() + "\n");
        if (getRight() != null) sb.append("Right Child: " + getRight().getKey() + "\n");

        sb.append("Is root? " + isRoot() + "\n")
          .append("Is leaf? " + isLeaf() + "\n")
          .append("Degree: " + getDegree() + "\n")
          .append("Level: " + getLevel() + "\n")
          .append("Height: " + getHeight() + "\n")
          .append("Balance Factor: " + getBalanceFactor());

        return sb.toString();
    }
}
