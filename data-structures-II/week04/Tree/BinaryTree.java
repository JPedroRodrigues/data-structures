/* Atividade Árvore Binária
 * João Pedro Rodrigues Vieira         10403595
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */
package Tree;

//import Queue.*;

public class BinaryTree<T extends Comparable <T>> {

    protected Node<T> root;

    public BinaryTree(Node<T> root) { this.root = root; }
    public BinaryTree() { this(null); }

    public Node<T> getRoot() { return root; }
    public void setRoot(Node<T> root) { this.root = root; }

    public boolean isEmpty() { return root == null; }

    public int getDegree() {
        if (isEmpty() || root.getLeft() == null && root.getRight() == null) return 0;

        Node<T> aux = root;

        while (aux != null) {
            Node<T> left = aux.getLeft();
            Node<T> right = aux.getRight();

            if (left != null && right != null) return 2;
            else if (left == null) aux = aux.getRight();
            else aux = aux.getLeft();
        }
        return 1;
    }

    public int getHeight() { return getHeight(root); }

    private int getHeight(Node<T> node) {
        if (isEmpty()) return -1;
        if (node == null) return 0;

        if (node.isLeaf()) return node.getLevel();

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());

        return Math.max(leftHeight, rightHeight);
    }

    public void inOrderTraversal() {
        if (isEmpty()) return;
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node<T> node) {
        if (node == null) return;

        inOrderTraversal(node.getLeft());
        System.out.print(node.getData() + " ");
        inOrderTraversal(node.getRight());
    }

    public void preOrder() {
        if (isEmpty()) return;
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node<T> node) {
        if (node == null) return;

        System.out.print(node.getData() + " ");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void postOrder() {
        if (isEmpty()) return;
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node<T> node) {
        if (node == null) return;

        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getData() + " ");
    }

//    public void levelOrderTraversal() {
//        if (isEmpty()) return;
//
//        Queue q = new Queue(root);
//
//        while (!q.isEmpty()) {
//            Node node = q.dequeue();
//            System.out.print(node.getData() + " ");
//
//            if (node.getLeft() != null) q.enqueue(node.getLeft());
//            if (node.getRight() != null) q.enqueue(node.getRight());
//        }
//    }
}