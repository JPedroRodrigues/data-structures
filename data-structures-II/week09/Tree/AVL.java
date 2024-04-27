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

        root.setRight(newRoot.getLeft());
        if (newRoot.getLeft() != null) newRoot.getLeft().setParent(root);

        newRoot.setLeft(root);
        newRoot.setParent(root.getParent());

        root.setParent(newRoot);
        if (super.getRoot().equals(root)) setRoot(newRoot);
        return newRoot;
    }

    public Node rotateRight(Node root) {
        Node newRoot = root.getLeft();

        root.setLeft(newRoot.getRight());
        if (newRoot.getRight() != null) newRoot.getRight().setParent(root);

        newRoot.setRight(root);
        newRoot.setParent(root.getParent());

        root.setParent(newRoot);

        if (super.getRoot().getData() == root.getData()) setRoot(newRoot);

        return newRoot;
    }

    public Node rotateLeftRight(Node root) {
        Node newRoot = root.getLeft().getRight();

        root.getLeft().setRight(newRoot.getLeft());
        if (newRoot.getLeft() != null) newRoot.getLeft().setParent(root.getLeft());

        newRoot.setLeft(root.getLeft());
        root.getLeft().setParent(newRoot);

        root.setLeft(newRoot);
        newRoot.setParent(root);

        return rotateRight(root);
    }

    public Node rotateRightLeft(Node root) {
        Node newRoot = root.getRight().getLeft();

        root.getRight().setLeft(newRoot.getRight());
        if (newRoot.getRight() != null) newRoot.getRight().setParent(root.getRight());

        newRoot.setRight(root.getRight());
        root.getRight().setParent(newRoot);

        root.setRight(newRoot);
        newRoot.setParent(root);

        return rotateLeft(root);
    }
}
