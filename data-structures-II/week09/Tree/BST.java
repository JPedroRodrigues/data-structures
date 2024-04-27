/* Atividade - Árvore AVL
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Tree;

public class BST extends BinaryTree {
    public BST(Node root) { super(root); }
    public BST() { super(null); }

    public Node search(int data) {
        Node node = new Node(data);
        return search(super.getRoot(), node);
    }

    private Node search(Node root, Node node) {
        if (node == null) return null;

        if (node.getData() > root.getData()) return search(root.getRight(), node);
        else if (node.getData() < root.getData()) return search(root.getLeft(), node);
        else return root;
    }

    public void insert(int data) {
        Node node = new Node(data);

        if (isEmpty()) setRoot(node);
        else insert(super.getRoot(), node);
    }

    private void insert(Node root, Node node) throws RuntimeException {
        if (root.getLeft() == null && node.getData() < root.getData()) {
            root.setLeft(node);
            node.setParent(root);
            return;
        } else if (root.getRight() == null && node.getData() > root.getData()) {
            root.setRight(node);
            node.setParent(root);
            return;
        }

        if (node.getData() > root.getData()) insert(root.getRight(), node);
        else if (node.getData() < root.getData()) insert(root.getLeft(), node);
        else throw new RuntimeException("This value already exists in tree.");
    }


    public Node remove(int data) throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Tree is empty.");

        Node node = search(data);

        if (node == null) throw new RuntimeException("Data not found.");

        if (node.isLeaf()) return removeLeaf(node);
        else if (node.getDegree() == 1) return removeOneDegreeNode(node);
        else return removeTwoDegreeNode(node);
    }

    private Node removeLeaf(Node node) {
        if (node.isRoot()) setRoot(null);
        else if (node.getData() > node.getParent().getData()) node.getParent().setRight(null);
        else node.getParent().setLeft(null);

        // Terminating all associations
        node.setParent(null);
        node.setLeft(null);
        node.setRight(null);

        return node;
    }

    private Node removeOneDegreeNode(Node node) {
        if (node.isRoot()) {
            if (node.getRight() != null) setRoot(node.getRight());
            else setRoot(node.getLeft());
        } else if (node.getData() > node.getParent().getData()) {
            if (node.getRight() != null) node.getParent().setRight(node.getRight());
            else node.getParent().setRight(node.getLeft());
        } else {
            if (node.getRight() != null) node.getParent().setLeft(node.getRight());
            else node.getParent().setLeft(node.getLeft());
        }

        // Terminating all associations
        node.setParent(null);
        node.setLeft(null);
        node.setRight(null);

        return node;
    }

    private Node removeTwoDegreeNode(Node node) {
        Node predecessor = findPredecessor(node.getData());
        Node aux = predecessor;

        predecessor.setParent(node.getParent());
        predecessor.setLeft(node.getLeft());
        predecessor.setRight(node.getRight());

        node.setParent(aux.getParent());
        node.setLeft(aux.getLeft());
        node.setRight(aux.getRight());

        if (node.isLeaf()) return removeLeaf(node);
        else return removeOneDegreeNode(node);
    }

    public Node findPredecessor(int data) {
        Node node = search(data);
        if (node == null) return null;

        // If node is the min value of the tree there is no predecessor
        if (data == findMin().getData()) return null;

        // If node does not have left subtree
        if (node.getLeft() == null) {
            // If parent's data is smaller than data, predecessor is node's parent
            if (node.getParent().getData() < data) return node.getParent();
            // If parent's data is bigger than data, go up in the tree
            else {
                while (node.getParent() != null) {
                    if (node.getParent().getData() < data) return node.getParent();
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

    public Node findSuccessor(int data) {
        Node node = search(data);
        if (node == null) return null;

        // If node is the max value of the tree there is no successor
        if (data == findMax().getData()) return null;

        // If node does not have right subtree
        if (node.getRight() == null) {
            // If parent's data is bigger than data, successor is node's parent
            if (node.getParent().getData() > data) return node.getParent();
            // If parent's data is smaller than data, go up in the tree
            else {
                while (node.getParent() != null) {
                    if (node.getParent().getData() > data) return node.getParent();
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

    public Node findMin() {
        if (isEmpty()) return null;
        else return findMin(super.getRoot());
    }

    private Node findMin(Node root) {
        if (root.getLeft() == null) return root;
        return findMin(root.getLeft());
    }

    public Node findMax() {
        if (isEmpty()) return null;
        else return findMax(super.getRoot());
    }

    private Node findMax(Node root) {
        if (root.getRight() == null) return root;
        return findMax(root.getRight());
    }

    public void clear() {
        clear(super.getRoot());
        super.setRoot(null);
    }

    private void clear(Node root) {
        if (root == null) return;

        clear(root.getLeft());
        clear(root.getRight());

        root.setParent(null);
        root.setLeft(null);
        root.setRight(null);
    }
}
