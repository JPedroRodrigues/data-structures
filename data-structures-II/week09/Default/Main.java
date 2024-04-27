/* Atividade - Árvore AVL
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 *
 * Referências:
 * https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-8/%C3%81rvore%20AVL.pdf
 */

package Default;
import Tree.*;

public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();

        // 1st case
        avl.insert(1);
        avl.insert(2);
        avl.insert(3);

        System.out.println("|=================================================|");
        System.out.println("AVL before rebalancing (pre-order traversal):");
        avl.preOrderTraversal();

        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(3));

        System.out.println("After rebalancing with Left Rotation:");
        avl.rotateLeft(avl.getRoot());
        avl.preOrderTraversal();

        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(3));

        avl.clear();


        // 2nd case
        avl.insert(3);
        avl.insert(2);
        avl.insert(1);

        System.out.println("|=================================================|");
        System.out.println("AVL before rebalancing (pre-order traversal):");
        avl.preOrderTraversal();

        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(3));

        System.out.println("After rebalancing with Right Rotation:");
        avl.rotateRight(avl.getRoot());
        avl.preOrderTraversal();

        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(3));

        avl.clear();

        // 3rd case
        avl.insert(3);
        avl.insert(1);
        avl.insert(2);

        System.out.println("|=================================================|");
        System.out.println("AVL before rebalancing (pre-order traversal):");
        avl.preOrderTraversal();

        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(3));

        System.out.println("After rebalancing with Left-Right Rotation:");
        avl.rotateLeftRight(avl.getRoot());
        avl.preOrderTraversal();

        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(3));

        avl.clear();

        // 4th case
        avl.insert(1);
        avl.insert(3);
        avl.insert(2);

        System.out.println("|=================================================|");
        System.out.println("AVL before rebalancing (pre-order traversal):");
        avl.preOrderTraversal();

        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(3));

        System.out.println("After rebalancing with Right-Left Rotation: ");
        avl.rotateRightLeft(avl.getRoot());
        avl.preOrderTraversal();

        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(3));

        avl.clear();
    }
}