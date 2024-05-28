/* APL2 - Lexer & Parser
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Pedro Pessuto Rodrigues Ferreira    10409729
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
        if (isEmpty()) throw new RuntimeException("BST.search(): Tree is Empty.");
        return search(super.getRoot(), path);
    }

    private Node search(Node root, String path) {
        if (root == null) throw new RuntimeException("BST.search(): Node not found");

        if (path.compareToIgnoreCase(root.getPath()) > 0) return search(root.getRight(), path);
        else if (path.compareToIgnoreCase(root.getPath()) < 0) return search(root.getLeft(), path);
        else return root;
    }

    public Node searchScope(String path) {
        if (isEmpty()) throw new RuntimeException("BST.searchScope(): Tree is Empty.");
        return searchScope(super.getRoot(), path);
    }

    private Node searchScope(Node root, String path) {
        if (root == null) throw new RuntimeException("BST.searchScope(): Scope not found");

        if (path.compareToIgnoreCase(root.getPath()) > 0) return searchScope(root.getRight(), path);
        else if (path.compareToIgnoreCase(root.getPath()) < 0) return searchScope(root.getLeft(), path);
        else {
            if (root.getType().equals("SCOPE")) return root;
            else if (root.notDuplicated()) throw new RuntimeException("BST.searchScope(): The data must be a scope");
            else return root.getDuplicated();
        }
    }

    public Node searchKey(String path) {
        if (isEmpty()) throw new RuntimeException("BST.searchScope(): Tree is Empty.");
        return searchKey(super.getRoot(), path);
    }

    private Node searchKey(Node root, String path) {
        if (root == null) throw new RuntimeException("BST.searchKey(): Key not found");

        if (path.compareToIgnoreCase(root.getPath()) > 0) return searchKey(root.getRight(), path);
        else if (path.compareToIgnoreCase(root.getPath()) < 0) return searchKey(root.getLeft(), path);
        else {
            if (root.getType().equals("KEY")) return root;
            else if (root.notDuplicated()) throw new RuntimeException("BST.searchKey(): The data must be a key");
            else return root.getDuplicated();
        }
    }

    public void searchNodes(String path) {
        if (isEmpty()) throw new RuntimeException("> BST.search(): Tree is Empty.");
        int findCount = 0;
        searchNodes(super.getRoot(), path, findCount);
    }

    private void searchNodes(Node root, String path, int findCount) {
        if (root == null) return;

        String[] rootPath = root.getPath().split("/");
        if (path.compareToIgnoreCase(rootPath[rootPath.length - 1]) == 0) {
            System.out.println("Number of comparisons needed: " + findCount);
            System.out.println(root);
            if (!root.notDuplicated()) System.out.println(root.getDuplicated());
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
            if (node.getType().equals("SCOPE") && root.getType().equals("SCOPE")) throw new RuntimeException(">>> BST.insert(): Invalid insertion: scope already exists");
            else if (node.getType().equals("KEY") && root.getType().equals("KEY")) root.setValue(node.getValue());
            else {
                // If both nodes have the same path but different types, then we add it in a list as a duplicated node
                // unless the root already has a duplicated node
                if (root.notDuplicated()) root.setDuplicated(node);
                else throw new RuntimeException("There's already a data with the same name and type.");
            }
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

    public Node removeKey(String path) throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Tree is empty.");

        Node keyNode = searchKey(path);
        Node node = search(path);

        // If the key node found is a duplicate of other node,
        // then we just need to remove it from the tree
        if (keyNode.isLeaf() && keyNode.isRoot()) {
            return node.removeDuplicated();
        } else if (!keyNode.notDuplicated()) {
            // If the key node has a duplicate, then we just
            // need to update its attributes using its duplicate
            Node removedNode = keyNode;

            keyNode.setPath(keyNode.getDuplicated().getPath());
            keyNode.setName(keyNode.getDuplicated().getName());
            keyNode.setType(keyNode.getDuplicated().getType());
            keyNode.setValue(keyNode.getDuplicated().getValue());
            
            // Since the duplicate took the position of the key node
            // we just need to delete de duplicate from the list
            keyNode.removeDuplicated();
            return removedNode;
        }

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
