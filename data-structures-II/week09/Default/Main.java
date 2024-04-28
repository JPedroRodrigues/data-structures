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
        
        // 5, 4, 3, 1, 2, 6, 7, 9, 8
        avl.insert(5);
        avl.preOrderTraversal();
        
        avl.insert(4);
        avl.preOrderTraversal();
        
        avl.insert(3);
        avl.preOrderTraversal();
        
        avl.insert(1);
        avl.preOrderTraversal();
        
        avl.insert(2);
        avl.preOrderTraversal();
        
        avl.insert(6);
        avl.preOrderTraversal();
        
        avl.insert(7);
        avl.preOrderTraversal();
        
        avl.insert(9);
        avl.preOrderTraversal();
        
        avl.insert(8);
        avl.preOrderTraversal();
        
        avl.preOrderNode();
        avl.preOrderTraversal();
        avl.inOrderTraversal();
    }
}