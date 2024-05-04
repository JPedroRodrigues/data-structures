/* Atividade - Árvore AVL
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Tree;

public class Node {
    private int data;
    private int balanceFactor;
    private Node left;
    private Node right;
    private Node parent;

    public Node(int data, Node left, Node right, Node parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.balanceFactor = getBalanceFactor();
    }

    public Node(int data) { 
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balanceFactor = getBalanceFactor();
     }
    public Node() { 
        this.data = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balanceFactor = getBalanceFactor(); 
    }

    public int getData() { return data; }
    public int getBalanceFactor() { updateBalanceFactor(); return balanceFactor; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    public Node getParent() { return parent; }

    public Node setData(int data) {
        this.data = data;
        return this;
    }

    private void updateBalanceFactor() {
        int hR, hL;
        if (getRight() == null) hR = -1;
        else hR = getRight().getHeight();

        if (getLeft() == null) hL = -1;
        else hL = getLeft().getHeight();

        this.balanceFactor = hR - hL;
    }

    public Node setLeft(Node left) {
        this.left = left;
        return this;
    }

    public Node setRight(Node right) {
        this.right = right;
        return this;
    }

    public Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public boolean isRoot() { return parent == null; }
    public boolean isLeaf() { return left == null && right == null; }

    public int getDegree() {
        int degree = 0;
        if (left != null) ++degree;
        if (right != null) ++degree;
        return degree;
    }

    public int getLevel() {
        return getLevel(this);
    }

    private int getLevel(Node node) {
        if (node == null) return -1;
        return 1 + getLevel(node.getParent());
    }

    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data: ")
          .append(getData())
          .append("\n")
          .append("Parent: ");

        if (getParent() != null) sb.append(getParent().getData()).append("\n");
        else sb.append("null\n");

        sb.append("Left: ");

        if (getLeft() != null) sb.append(getLeft().getData()).append("\n");
        else sb.append("null\n");

        sb.append("Right: ");

        if (getRight() != null) sb.append(getRight().getData()).append("\n");
        else sb.append("null\n");

        sb.append("Balance Factor: ")
          .append(getBalanceFactor())
          .append("\n");

        return sb.toString();
    }

}
