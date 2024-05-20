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

public class BinaryTree {

    protected Node root;

    public BinaryTree() { this.root = null; }
    public BinaryTree(Node root) { this.root = root; }

    public Node getRoot() { return root; }

    public void setRoot(Node root) { this.root = root; }

    public boolean isEmpty() { return root == null; }

    public int getDegree() {
        if (isEmpty() || root.getLeft() == null && root.getRight() == null) return 0;

        Node node = root;

        while (node != null) {
            if (node.getLeft() != null && node.getRight() != null) return 2;
            else if (node.getLeft() == null) node = node.getRight();
            else node = node.getLeft();
        }
        return 1;
    }

    public int getHeight() {
        if (isEmpty()) return -1;
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) return -1;
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void inOrderTraversal() {
        if (isEmpty()) return;
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node node) {
        if (node == null) return;
        inOrderTraversal(node.getLeft());
        System.out.print(node.getKey() + " ");
        inOrderTraversal(node.getRight());
    }

    public void preOrderTraversal() {
        if (isEmpty()) return;
        preOrderTraversal(root);
        System.out.println();
    }

    private void preOrderTraversal(Node node) {
        if (node == null) return;
        System.out.print(node.getKey() + " ");
        preOrderTraversal(node.getLeft());
        preOrderTraversal(node.getRight());
    }

    public void postOrderTraversal() {
        if (isEmpty()) return;
        postOrderTraversal(root);
        System.out.println();
    }

    private void postOrderTraversal(Node node) {
        if (node == null) return;
        postOrderTraversal(node.getLeft());
        postOrderTraversal(node.getRight());
        System.out.print(node.getKey() + " ");
    }
}
