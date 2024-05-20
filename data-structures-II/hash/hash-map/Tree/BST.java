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

public class BST extends BinaryTree {

    public BST() { super(null); }
    public BST(Node root) { super(root); }

    public Node search(int key) {
        if (isEmpty()) return null;
        return search(root, key);
    }

    private Node search(Node node, int key) {
        if (node == null || node.getKey() == key) return node;
        else if (node.getKey() < key) return search(node.getRight(), key);
        else return search(node.getLeft(), key);
    }

    public Node findMin() {
        if (isEmpty()) return null;
        return findMin(root, root);
    }

    private Node findMin(Node node, Node min) {
        if (node == null) return min;
        if (node.getKey() < min.getKey()) min = node;
        return findMin(node.getLeft(), min);
    }

    public Node findMax() {
        if (isEmpty()) return null;
        return findMax(root, root);
    }

    private Node findMax(Node node, Node max) {
        if (node == null) return max;
        if (node.getKey() > max.getKey()) max = node;
        return findMax(node.getRight(), max);
    }

    public Node findPredecessor(int key) {
        Node node = search(key);
        if (node == null) return null;

        // If node is the min value of the tree there is no predecessor
        if (key == findMin().getKey()) return null;

        // If node does not have left subtree
        if (node.getLeft() == null) {
            // If parent's key is smaller than key, predecessor is node's parent
            if (node.getParent().getKey() < key) return node.getParent();
            // If parent's key is bigger than key, go up in the tree
            else {
                while (node.getParent() != null) {
                    if (node.getParent().getKey() < key) return node.getParent();
                    else node = node.getParent();
                }
                // If node doesn't have predecessor
                return null;
            }

        // If node has left subtree
        } else return findPredecessor(node.getLeft());
    }

    private Node findPredecessor(Node node) {
        if (node.getRight() == null) return node;
        return findPredecessor(node.getRight());
    }

    public Node findSuccessor(int key) {
        Node node = search(key);
        if (node == null) return null;

        // If node is the max value of the tree there is no successor
        if (key == findMax().getKey()) return null;

        // If node does not have right subtree
        if (node.getRight() == null) {
            // If parent's key is bigger than key, successor is node's parent
            if (node.getParent().getKey() > key) return node.getParent();
            // If parent's key is smaller than key, go up in the tree
            else {
                while (node.getParent() != null) {
                    if (node.getParent().getKey() > key) return node.getParent();
                    else node = node.getParent();
                }
                // If node doesn't have successor
                return null;
            }

        // If node has right subtree
        } else return findSuccessor(node.getRight());
    }

    private Node findSuccessor(Node node) {
        if (node.getLeft() == null) return node;
        return findSuccessor(node.getLeft());
    }

    public void clear() {
        if (isEmpty()) {
            System.out.println("Tree is empty.");
            return;
        }
        clear(root);
        setRoot(null);
    }

    private void clear(Node node) {
        if (node == null) return;
        clear(node.getLeft());
        clear(node.getRight());
        node.setLeft(null);
        node.setRight(null);
        // preOrderTraversal();
    }
}
