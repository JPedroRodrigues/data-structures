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
        
        System.out.println("||=====================================================||");
        System.out.println("\n>> Inserting values \"1, 2, 3\":\n");
        for (int i = 1; i <= 3; i++) avl.insert(i);
        
        System.out.println(">> Node attributes: ");
        avl.preOrderNode();

        System.out.print(">> Pre-order traversal: ");
        avl.preOrderTraversal();
        System.out.println();
        avl.clear();

        System.out.println("||=====================================================||");
        System.out.println("\n>> Inserting values \"3, 2, 1\":\n");
        for (int i = 3; i >= 1; i--) avl.insert(i);
        
        System.out.println(">> Node attributes: ");
        avl.preOrderNode();

        System.out.print(">> Pre-order traversal: ");
        avl.preOrderTraversal();
        System.out.println();
        avl.clear();

        System.out.println("||=====================================================||");
        System.out.println("\n>> Inserting values \"3, 1, 2\":\n");
        avl.insert(3);
        avl.insert(1);
        avl.insert(2);

        System.out.println(">> Node attributes: ");
        avl.preOrderNode();

        System.out.print(">> Pre-order traversal: ");
        avl.preOrderTraversal();
        System.out.println();
        avl.clear();

        System.out.println("||=====================================================||");
        System.out.println("\n>> Inserting values \"1, 3, 2\":\n");
        avl.insert(1);
        avl.insert(3);
        avl.insert(2);

        System.out.println(">> Node attributes: ");
        avl.preOrderNode();

        System.out.print(">> Pre-order traversal: ");
        avl.preOrderTraversal();
        System.out.println();
        avl.clear();

        System.out.println("||=====================================================||");
        System.out.println("\n>> Inserting values \"5, 4, 3, 1, 2, 6, 7, 9, 8\":\n");
        avl.insert(5);
        avl.insert(4);
        avl.insert(3);
        avl.insert(1);
        avl.insert(2);
        avl.insert(6);
        avl.insert(7);
        avl.insert(9);
        avl.insert(8);
        
        System.out.println(">> Node attributes: ");
        avl.preOrderNode();

        System.out.print(">> Pre-order traversal: ");
        avl.preOrderTraversal();
        System.out.println();

        System.out.println("||=====================================================||");
        System.out.println("\n>> Removing node \"4\":\n");
        avl.remove(4);
        
        System.out.println(">> Node attributes: ");
        avl.preOrderNode();

        System.out.print(">> Pre-order traversal: ");
        avl.preOrderTraversal();
        System.out.println();

        System.out.println("||=====================================================||");
        System.out.println("\n>> Removing node \"5\":\n");
        avl.remove(5);
        
        System.out.println(">> Node attributes: ");
        avl.preOrderNode();

        System.out.print(">> Pre-order traversal: ");
        avl.preOrderTraversal();
        System.out.println();

        System.out.println("||=====================================================||");
        System.out.println("\n>> Removing node \"1\":\n");
        avl.remove(1);
        
        System.out.println(">> Node attributes: ");
        avl.preOrderNode();

        System.out.print(">> Pre-order traversal: ");
        avl.preOrderTraversal();
        System.out.println();
    }
}