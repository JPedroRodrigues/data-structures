/* APL2 - Lexer and Parser
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Tree;

public class BST extends BinaryTree {
    private int size;
    public BST(Node root) { super(root); this.size = 0; }
    public BST() { super(null); this.size = 0; }

    public int getSize() { return this.size; }

    public Node search(String path) {
        if (isEmpty()) throw new RuntimeException("> BST.search(): Tree is Empty.");
        return search(super.getRoot(), path);
    }

    private Node search(Node root, String path) {
        if (root == null) throw new RuntimeException("> BST.search(): Node not found");

        String[] rootPath = root.getPath().split("/");
        if (path.compareToIgnoreCase(rootPath[0]) > 0) return search(root.getRight(), path);
        else if (path.compareToIgnoreCase(rootPath[0]) < 0) return search(root.getLeft(), path);
        else return root;
    }

    public void searchNodes(String path) {
        if (isEmpty()) throw new RuntimeException("> BST.search(): Tree is Empty.");
        int findCount = 0;
        searchNodes(super.getRoot(), path, findCount);
    }

    private void searchNodes(Node root, String path, int findCount) {
        if (root == null) return;

        String[] rootPath = root.getPath().split("/");
        if (path.compareToIgnoreCase(rootPath[0]) == 0) {
            System.out.println("Number of comparisons needed: " + findCount);
            System.out.println(root);
        }

        searchNodes(root.getLeft(), path, findCount + 1);
        searchNodes(root.getRight(), path, findCount + 1);
    }

    public void insert(Node node) {
        if (isEmpty())setRoot(node);
        else insert(super.getRoot(), node);
        size++;
    }

    protected void insert(Node root, Node node) throws RuntimeException {
        if (root.getLeft() == null && node.getPath().compareToIgnoreCase(root.getPath()) < 0) {
            root.setLeft(node);
            node.setParent(root);
            return;
        } else if (root.getRight() == null && node.getPath().compareToIgnoreCase(root.getPath()) > 0) {
            root.setRight(node);
            node.setParent(root);
            return;
        }

        if (node.getPath().compareToIgnoreCase(root.getPath()) > 0) insert(root.getRight(), node);
        else if (node.getPath().compareToIgnoreCase(root.getPath()) < 0) insert(root.getLeft(), node);
        else {
            if (node.getType().equals("SCOPE")) throw new RuntimeException("Invalid insertion: scope already exists");
            root.setValue(node.getValue());
        }
    }


    public Node remove(String path) throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Tree is empty.");

        Node node = search(path);

        if (node == null) throw new RuntimeException("Path not found.");

        if (node.isLeaf()) return removeLeaf(node);
        else if (node.getDegree() == 1) return removeOneDegreeNode(node);
        else return removeTwoDegreeNode(node);
    }

    protected Node removeLeaf(Node node) {
        if (node.isRoot()) setRoot(null);
        else if (node.getPath().compareToIgnoreCase(node.getParent().getPath()) > 0) node.getParent().setRight(null);
        else node.getParent().setLeft(null);

        // Terminating all associations
        node.setParent(null);
        node.setLeft(null);
        node.setRight(null);

        --size;
        return node;
    }

    protected Node removeOneDegreeNode(Node node) {
        Node child;
        if (node.isRoot()) {
            if (node.getRight() != null) {
                child = node.getRight();
                setRoot(child);
            }
            else {
                child = node.getLeft();
                setRoot(child);
            } 
        } else if (node.getPath().compareToIgnoreCase(node.getParent().getPath()) > 0) {
            if (node.getRight() != null) {
                child = node.getRight();
                node.getParent().setRight(child);
            }
            else {
                child = node.getLeft();
                node.getParent().setRight(node.getLeft());
            }
        } else {
            if (node.getRight() != null) {
                child = node.getRight();
                node.getParent().setLeft(node.getRight());
            }
            else {
                child = node.getLeft();
                node.getParent().setLeft(node.getLeft());
            }
        }

        child.setParent(node.getParent());
        
        // Terminating all associations
        node.setParent(null);
        node.setLeft(null);
        node.setRight(null);

        --size;
        return node;
    }

    private Node removeTwoDegreeNode(Node node) {
        Node predecessor = findPredecessor(node.getPath());
        String aux = node.getPath();

        node.setPath(predecessor.getPath());
        predecessor.setPath(aux);

        if (predecessor.isLeaf()) return removeLeaf(predecessor);
        else return removeOneDegreeNode(predecessor);
    }

    public Node findPredecessor(String path) {
        Node node = search(path);
        if (node == null) return null;

        // If node is the min value of the tree there is no predecessor
        if (path.equals(findMin().getPath())) return null;

        // If node does not have left subtree
        if (node.getLeft() == null) {
            // If parent's Path is smaller than path, predecessor is node's parent
            if (node.getParent().getPath().compareToIgnoreCase(path) < 0) return node.getParent();
            // If parent's Path is bigger than path, go up in the tree
            else {
                while (node.getParent() != null) {
                    if (node.getParent().getPath().compareToIgnoreCase(path) < 0) return node.getParent();
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

    public Node findSuccessor(String path) {
        Node node = search(path);
        if (node == null) return null;

        // If node is the max value of the tree there is no successor
        if (path.equals(findMax().getPath())) return null;

        // If node does not have right subtree
        if (node.getRight() == null) {
            // If parent's Path is bigger than path, successor is node's parent
            if (node.getParent().getPath().compareToIgnoreCase(path) > 0) return node.getParent();
            // If parent's Path is smaller than Path, go up in the tree
            else {
                while (node.getParent() != null) {
                    if (node.getParent().getPath().compareToIgnoreCase(path) > 0) return node.getParent();
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
