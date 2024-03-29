/* Atividade Árvore Binária
 * João Pedro Rodrigues Vieira         10403595
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Tree;

public class Node<T extends Comparable <T>> {

    private T data;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    public Node(T data, Node<T> parent, Node<T> left, Node<T> right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public Node(T data) {
        this(data, null, null, null);
    }

    public Node() {
        this(null, null, null, null);
    }

    public T getData() { return data; }

    public Node<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Node<T> getParent() { return parent; }

    public Node<T> setParent(Node<T>parent) {
        this.parent = parent;
        return this;
    }

    public Node<T> getLeft() { return left; }

    public Node<T> setLeft(Node<T> left) {
        this.left = left;
        return this;
    }

    public Node<T> getRight() { return right; }

    public Node<T> setRight(Node<T> right) {
        this.right = right;
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
        int level = 0;
        Node<T> aux = getParent();

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

    private int getHeight(Node<T> node) {
        if (node == null) return -1;

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Data: " + getData() + "\n");

        if (getParent() != null) sb.append("Parent's data: " + getParent().getData() + "\n");
        if (getLeft() != null) sb.append("Left child's data: " + getLeft().getData() + "\n");
        if (getRight() != null) sb.append("Right child's data: " + getRight().getData() + "\n");

        sb.append("Is root? " + isRoot() + "\n")
          .append("Is leaf? " +isLeaf() + "\n")
          .append("Degree: " + getDegree() + "\n")
          .append("Level: " + getLevel() + "\n")
          .append("Height: " + getHeight() + "\n");

        return sb.toString();
    }
}