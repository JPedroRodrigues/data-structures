/* Atividade Árvore Binária
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 *
 * Referências:
 * GOODRICH, Michael T.; TAMASSIA, Roberto. Estruturas de dados e algoritmos em java.
 * https://www.geeksforgeeks.org/level-order-tree-traversal/
 */

package Default;

import BinaryTree.*;

public class Program {

    public static void main(String[] args) {

        Tree t = new Tree();

        Node a = new Node("A");
        t.setRoot(a);

        Node b = new Node("B");
        a.setLeft(b);
        b.setParent(a);

        Node d = new Node("D");
        b.setLeft(d);
        d.setParent(b);

        Node c = new Node("C");
        a.setRight(c);
        c.setParent(a);

        Node e = new Node("E");
        c.setLeft(e);
        e.setParent(c);

        Node f = new Node("F");
        c.setRight(f);
        f.setParent(c);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);

        System.out.println("Is the tree empty? " + t.isEmpty());
        System.out.println("Tree's degree: " + t.getDegree());
        System.out.println("Tree's height: " + t.getHeight());
        System.out.print("In Order: ");
        t.inOrderTraversal();
        System.out.print("Pre Order: ");
        t.preOrder();
        System.out.print("Post Order: ");
        t.postOrder();
        System.out.print("Level Order: ");
        t.levelOrderTraversal();
    }
}
