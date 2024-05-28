/* APL2 - Lexer & Parser
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Pedro Pessuto Rodrigues Ferreira    10409729
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Tree;

public class BinaryTree {
    private Node root;

    public BinaryTree(Node root) { this.root = root; }
    public BinaryTree() { this(null); }

    public Node getRoot() { return root; }

    public BinaryTree setRoot(Node root) {
        this.root = root;
        return this;
    }

    public boolean isEmpty() { return root == null; }

    public int getDegree() {
        if (isEmpty()) return 0;
        return getDegree(root);
    }

    private int getDegree(Node root) {
        if (root == null) return 0;

        Node[] children = new Node[2];

        children[0] = root.getLeft();
        children[1] = root.getRight();

        int degree = 0;

        for (Node child : children) if (child != null) degree++;

        for (Node child: children) degree = Math.max(degree, getDegree(child));

        return degree;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node root) {
        if (root == null) return -1;

        return 1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));
    }

    public void preOrderTraversal() {
        if (isEmpty()) System.out.println("Tree is empty.");
        preOrderTraversal(root);
        System.out.println();
    }

    private void preOrderTraversal(Node root) {
        if (root == null) return;
        System.out.print(root.getPath() + " ");
        if (!root.notDuplicated()) System.out.print(root.getDuplicated().getPath() + " ");
        preOrderTraversal(root.getLeft());
        preOrderTraversal(root.getRight());
    }

    public void preOrderNode() {
        if (isEmpty()) System.out.println("Tree is empty.");
        else preOrderNode(root);
    }

    private void preOrderNode(Node root) {
        if (root == null) return;
        System.out.println(root);
        if (!root.notDuplicated()) System.out.println(root.getDuplicated());
        preOrderNode(root.getLeft());
        preOrderNode(root.getRight());
    }

    public void inOrderTraversal() {
        if (isEmpty()) System.out.println("Tree is empty.");
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node root) {
        if (root == null) return;
        inOrderTraversal(root.getLeft());
        System.out.print(root.getPath() + " ");
        if (!root.notDuplicated()) System.out.print(root.getDuplicated().getPath() + " ");
        inOrderTraversal(root.getRight());
    }

    public void inOrderNode() {
        if (isEmpty()) System.out.println("Tree is empty.");
        else inOrderNode(root);
    }

    private void inOrderNode(Node root) {
        if (root == null) return;
        inOrderNode(root.getLeft());
        System.out.println(root);
        if (!root.notDuplicated()) System.out.println(root.getDuplicated());
        inOrderNode(root.getRight());
    }

    public void postOrderTraversal() {
        if (isEmpty()) System.out.println("Tree is empty.");
        postOrderTraversal(root);
        System.out.println();
    }

    private void postOrderTraversal(Node root) {
        if (root == null) return;
        postOrderTraversal(root.getLeft());
        postOrderTraversal(root.getRight());
        System.out.print(root.getPath() + " ");
        if (!root.notDuplicated()) System.out.print(root.getDuplicated().getPath() + " ");
    }

    public void postOrderNode() {
        if (isEmpty()) System.out.println("Tree is empty.");
        else postOrderNode(root);
    }

    private void postOrderNode(Node root) {
        if (root == null) return;
        postOrderNode(root.getLeft());
        postOrderNode(root.getRight());
        System.out.println(root);
        if (!root.notDuplicated()) System.out.println(root.getDuplicated());
    }
}
