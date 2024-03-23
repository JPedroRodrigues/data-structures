/* Atividade Árvore Binária
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Tree;

public class BST<T extends Comparable <T>> extends BinaryTree<T> {

    public BST (Node<T> root) {
        super(root);
    }

    public BST () { super(null); }

    public Node<T> search(T data) {

        if (isEmpty()) return null;
        return search(super.getRoot(), data);

    }

    private Node<T> search(Node<T> node, T data) {
        if (node == null) return null;

        if (data.equals(node.getData())) return node;
        else if (data.compareTo(node.getData()) > 0) { return search(node.getRight(), data); }
        else { return search(node.getLeft(), data); }
    }

    public void insert(T data) {

        if (isEmpty()) { super.setRoot(new Node<>(data)); }

        insert(super.getRoot(), data);
    }

    private void insert(Node<T> node, T data) throws RuntimeException {
        Node<T> child = new Node<>(data);

        if (data.equals(node.getData())) throw new RuntimeException("This value already exists.");
        else if(data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(child);
                child.setParent(node);
            } else insert(node.getRight(), data);
        }
        else {
            if (node.getLeft() == null) {
                child.setParent(node);
                node.setLeft(child);
            } else insert(node.getLeft(), data);
        }
    }

    public void remove(T data) throws RuntimeException {

        Node<T> node = search(data);
        if (node == null) throw new RuntimeException("Data not found.");

        if (node.isRoot()) {
            if (node.getDegree() == 0) {
                setRoot(null);
            } else if (node.getDegree() == 1) {
                if (node.getLeft() != null) setRoot(node.getLeft());
                else setRoot(node.getRight());
            } else {
                Node<T> predecessor = findPredecessor(data);

                predecessor.getParent().setRight(null);
                predecessor.setParent(node.getParent());

                predecessor.setLeft(node.getLeft());
                predecessor.setRight(node.getRight());

                node.setLeft(null);
                node.setRight(null);
            }
            return;
        }

        int compareParent = node.getData().compareTo(node.getParent().getData());

        if (node.isLeaf()) {
            if (compareParent > 0) node.getParent().setRight(null);
            else node.getParent().setLeft(null);
        } else if (node.getDegree() == 1) {
            int compareChild;

            if (node.getLeft() != null) compareChild = node.getData().compareTo(node.getLeft().getData());
            else compareChild = node.getData().compareTo(node.getRight().getData());

            if (compareChild > 0) {
                node.getLeft().setParent(node.getParent());
                if (compareParent < 0) node.getParent().setLeft(node.getLeft());
                else node.getParent().setRight(node.getLeft());
            } else {
                node.getRight().setParent(node.getParent());
                if (compareParent < 0) node.getParent().setLeft(node.getRight());
                else node.getParent().setRight(node.getRight());
            }
        } else {
            Node<T> predecessor = findPredecessor(data);

            predecessor.getParent().setRight(null);
            predecessor.setParent(node.getParent());

            predecessor.setLeft(node.getLeft());
            predecessor.setRight(node.getRight());

            node.setLeft(null);
            node.setRight(null);
        }
    }

    public Node<T> findMin() {

        if (isEmpty()) return null;
        return findMin(super.getRoot());
    }

    private Node<T> findMin(Node<T> root) {

        if (root.getLeft() == null) return root;
        return findMin(root.getLeft());
    }

    public Node<T> findMax() {

        if (isEmpty()) return null;
        return findMax(super.getRoot());
    }

    private Node<T> findMax(Node<T> root) {

        if (root.getRight() == null) return root;
        return findMax(root.getRight());
    }

    public Node<T> findPredecessor(T data) {
        Node<T> node = search(data);
        if (node == null) return null;

        Node<T> predecessor = node.getLeft();
        if (predecessor == null) return node;

        while (predecessor.getRight() != null) predecessor = predecessor.getRight();
        return predecessor;
    }

    public Node<T> findSuccessor(T data) {
        Node<T> node = search(data);
        if (node == null) return null;

        Node <T> successor = node.getRight();
        if (successor == null) return node;

        while (successor.getLeft() != null) successor = successor.getLeft();
        return successor;
    }

    public void clear() {
        if (isEmpty()) return;

        clear(super.getRoot());

        setRoot(null);
    }

    private void clear(Node<T> root) {

        if (root == null) return;

        clear(root.getLeft());
        clear(root.getRight());


        root.setLeft(null);
        root.setRight(null);

        // showing how the tree is cleaned
        preOrder();
    }
}
