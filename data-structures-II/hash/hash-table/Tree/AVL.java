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

public class AVL extends BST {

    public AVL() { super(null); }
    public AVL(Node root) { super(root); }

    public Node rotateLeft(Node node1) {
        Node node2 = node1.getRight();
        Node node2Left = node2.getLeft();

        if (node1.isRoot()) {
            setRoot(node2);
            node2.setParent(null);
        } else {
            node1.getParent().setRight(node2);
            node2.setParent(node1.getParent());
        }

        node1.setParent(node2);
        node2.setLeft(node1);

        node1.setRight(node2Left);
        if (node2Left != null) node2Left.setParent(node1);

        return node2;
    }

    public Node rotateRight(Node node3) {
        Node node2 = node3.getLeft();
        Node node2Right = node2.getRight();

        if (node3.isRoot()) {
            setRoot(node2);
            node2.setParent(null);
        } else {
            node3.getParent().setLeft(node2);
            node2.setParent(node3.getParent());
        }

        node3.setParent(node2);
        node2.setRight(node3);

        node3.setLeft(node2Right);
        if (node2Right != null) node2Right.setParent(node3);

        return node2;
    }

    public Node rotateLeftRight(Node node3) {
        Node node1 = node3.getLeft();
        Node node2 = node1.getRight();

        node3.setLeft(node2);
        node1.setParent(node2);
        node1.setRight(node2.getLeft());
        if (node2.getLeft() != null) node2.getLeft().setParent(node1);
        node2.setLeft(node1);

        rotateRight(node3);
        return node2;
    }

    public Node rotateRightLeft(Node node1) {
        Node node3 = node1.getRight();
        Node node2 = node3.getLeft();

        node1.setRight(node2);
        node3.setParent(node2);

        node3.setLeft(node2.getRight());
        if (node2.getRight() != null) node2.getRight().setParent(node3);
        node2.setRight(node3);

        rotateLeft(node1);
        return node2;
    }

    public void insert(Node node) {
        if (isEmpty()) {
            Node root = node;
            setRoot(root);
            return;
        }
        insert(root, node.getKey(), node.getValue());
    }

    private void insert(Node aux, int key, String value) {
        if (isEmpty()) {
            setRoot(aux);
            return;
        }

        if (search(key) != null) throw new RuntimeException();
        if (aux == null) return;

        Node node = new Node(key, value);

        if (aux.getKey() < key) {
            if (aux.getRight() == null) {
                aux.setRight(node);
                node.setParent(aux);
            } else insert(aux.getRight(), key, value);
        }
        else if (aux.getKey() > key) {
            if (aux.getLeft() == null) {
                aux.setLeft(node);
                node.setParent(aux);
            } else insert(aux.getLeft(), key, value);
        }
        balanceTree(node);
    }

    public void remove(int key) {
        if (isEmpty()) {
            System.out.println("Tree is empty.");
            return;
        }
        Node node = search(key);
        Node parent = node.getParent();

        if (node.isLeaf()) {
            if (node.isRoot()) setRoot(null);
            else {
                if (node.getParent().getKey() > node.getKey()) node.getParent().setLeft(null);
                else node.getParent().setRight(null);
                node.setParent(null);
            }
        }
        else {
            if (node.getLeft() != null) {
                Node predecessor = findPredecessor(key);
                node.setKey(predecessor.getKey());
                node.setValue(predecessor.getValue());

                if (predecessor.isLeaf()) {
                    if (predecessor.getParent().getKey() >= predecessor.getKey()) predecessor.getParent().setLeft(null);
                    else predecessor.getParent().setRight(null);
                } else {
                    node.setLeft(predecessor.getLeft());
                    predecessor.getLeft().setParent(node);
                    predecessor.setLeft(null);
                }
                predecessor.setParent(null);
            } else {
                Node successor = findSuccessor(key);
                node.setKey(successor.getKey());
                node.setValue(successor.getValue());

                if (successor.isLeaf()) {
                    if (successor.getParent().getKey() <= successor.getKey()) successor.getParent().setRight(null);
                    else successor.getParent().setLeft(null);
                } else {
                    node.setRight(successor.getRight());
                    successor.getRight().setParent(node);
                    successor.setRight(null);
                }
                successor.setParent(null);
            }
        }
        balanceTree(parent);
    }

    public void balanceTree(Node node) {
        while (node != null) {
            int bf = node.getBalanceFactor();

            if (bf > 1) {
                if (node.getRight().getBalanceFactor() < 0) {
                    // System.out.println("Node " + node.getKey() + " (BF = " + node.getBalanceFactor() + "): Rotation RL");
                    rotateRightLeft(node);
                } else {
                    // System.out.println("Node " + node.getKey() + " (BF = " + node.getBalanceFactor() + "): Rotation LL");
                    rotateLeft(node);
                }
            } else if (bf < -1) {
                if (node.getLeft().getBalanceFactor() > 0) {
                    // System.out.println("Node " + node.getKey() + " (BF = " + node.getBalanceFactor() + "): Rotation LR");
                    rotateLeftRight(node);
                } else {
                    // System.out.println("Node " + node.getKey() + " (BF = " + node.getBalanceFactor() + "): Rotation RR");
                    rotateRight(node);
                }
            }
            node = node.getParent();
        }
    }
}
