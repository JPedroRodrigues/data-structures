/* Atividade - Árvore AVL
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Tree;

public class AVL extends BST {
    public AVL() { super(null); }
    public AVL(Node root) { super(root); }

    public Node rotateLeft(Node root) {
        Node newRoot = root.getRight();
        
        // ▪ Nó 2 se torna raiz.
        if (root.isRoot()) { setRoot(newRoot);}

        // ▪ Nó 2 se torna pai do nó 1.
        newRoot.setParent(root.getParent());
        root.setParent(newRoot);

        // ▪ Filho direito do nó 1 recebe filho esquerdo do nó 2.
        Node leftSon = newRoot.getLeft();
        root.setRight(leftSon);

        // ▪ Nó 1 se torna pai do filho esquerdo do nó 2.
        if (leftSon != null) leftSon.setParent(root);

        // ▪ Nó 1 se torna filho esquerdo do nó 2.
        newRoot.setLeft(root);

        return newRoot;
    }

    public Node rotateRight(Node root) {
        Node newRoot = root.getLeft();

        // ▪ Nó 2 se torna raiz.
        if (root.isRoot()) setRoot(newRoot);

        // ▪ Nó 2 se torna pai do nó 3.
        newRoot.setParent(root.getParent());
        root.setParent(newRoot);

        // ▪ Filho esquerdo do nó 3 recebe filho direito do nó 2.
        Node rightSon = newRoot.getRight();
        root.setLeft(rightSon);

        // ▪ Nó 3 se torna pai do filho direito do nó 2.
        if (rightSon != null) rightSon.setParent(root);

        // ▪ Nó 3 se torna filho direito do nó 2.
        newRoot.setRight(root);

        return newRoot;
    }

    public Node rotateLeftRight(Node root) {
        // Rotação à esquerda do nó 1
        Node middleRoot = root.getLeft().getRight();
        Node lastNode = root.getLeft();
        
        // ▪ Nó 2 se torna filho esquerdo do nó 3.
        middleRoot.setParent(root);
        root.setLeft(middleRoot);

        // ▪ Nó 2 se torna pai do nó 1.
        lastNode.setParent(middleRoot);

        // ▪ Filho direito do nó 1 recebe filho esquerdo do nó 2.
        lastNode.setRight(middleRoot.getLeft());

        // ▪ Nó 1 se torna pai do filho esquerdo do nó 2.
        if (middleRoot.getLeft() != null) middleRoot.getLeft().setParent(lastNode);

        // ▪ Nó 1 se torna filho esquerdo do nó 2.
        middleRoot.setLeft(lastNode);

        return rotateRight(root);
    }

    public Node rotateRightLeft(Node root) {
        // Rotação à direita do nó 3:
        Node middlNode = root.getRight().getLeft();
        Node lastNode = root.getRight();

        // ▪ Nó 2 se torna filho direito do nó 1.
        middlNode.setParent(root);
        root.setRight(middlNode);

        // ▪ Nó 2 se torna pai do nó 3.
        lastNode.setParent(middlNode);

        // ▪ Filho esquerdo do nó 3 recebe filho direito do nó 2.
        lastNode.setLeft(middlNode.getRight());

        // ▪ Nó 3 se torna pai do filho direito do nó 2.
        if (middlNode.getRight() != null) middlNode.getRight().setParent(lastNode);

        // ▪ Nó 3 se torna filho direito do nó 2.
        middlNode.setRight(lastNode);

        return rotateLeft(root);
    }

    private Node checkBalance(Node root) {
        if (root.getBalanceFactor() > 1) {
            if (root.getRight() != null && root.getRight().getBalanceFactor() < 0)
                return rotateRightLeft(root);
            else 
                return rotateLeft(root);
        } else if (root.getBalanceFactor() < -1) {
            if (root.getLeft() != null && root.getLeft().getBalanceFactor() > 0)
                return rotateLeftRight(root);
            else 
                return rotateRight(root);
        } else return root;
    }


    @Override
    public void insert(int data) {
        Node node = new Node(data);

        if (isEmpty()) setRoot(node);
        else insert(getRoot(), node);
    }

    @Override
    protected void insert(Node root, Node node) throws RuntimeException {
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

        Node newRoot = checkBalance(root);
        Node newParent = newRoot.getParent();

        if (newParent != null) {
            if (newParent.getData() < newRoot.getData()) newParent.setRight(newRoot);
            else newParent.setLeft(newRoot);
        }
    }
}
