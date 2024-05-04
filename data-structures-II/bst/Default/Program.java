/* Atividade Árvore Binária
 * João Pedro Rodrigues Vieira         10403595
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 *
 * Referências:
 * Material de aula do professor André Kishimoto
 * (https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-3/%C3%81rvores%20-%20Fundamentos.pdf)
 */

package Default;

import Tree.*;

public class Program {
    public static void preOrder(Node<String> node) {
        if (node == null) return;

        System.out.println(node);
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public static void main(String[] args) {

        Node<String> root = new Node<>("30");
        BST<String> bst = new BST<>(root);

//      5. Para testar a sua implementação da BST, defina um conjunto de chaves (valores do tipo string) que serão
//      inseridos na árvore. Inclua uma inserção de uma chave que já esteja presente na BST (teste de não permitir
//      duplicatas na BST)

        System.out.println("Inserting 15");
        bst.insert("15");

        System.out.println("Inserting 38");
        bst.insert("38");

        System.out.println("Inserting 10");
        bst.insert("10");

        System.out.println("Inserting 20");
        bst.insert("20");

        System.out.println("Inserting 51");
        bst.insert("51");

        System.out.println("Inserting 8");
        bst.insert("8");

        System.out.println("Inserting 16");
        bst.insert("16");

        System.out.println("Inserting node 27");
        bst.insert("27");

        // Inserção de um nó que já existe
        System.out.println("Trying to insert node 20 again");
        try {
            bst.insert("20");
        } catch (RuntimeException exp) {
            System.out.println("Exception: " + exp);
        }

        bst.inOrderTraversal();

//      Realize buscas na árvore. Inclua buscas por chaves que não existem na BST. Para cada nó encontrado, exiba
//      todas as informações do nó. Para cada nó não encontrado, exiba uma mensagem pertinente.


        System.out.println("Searching node 38:\n" + bst.search("38") + "\n");
        System.out.println("Searching node 8:\n" + bst.search("8") + "\n");
        System.out.println("Searching 999:\n" + bst.search("999") + "\n");


//      Remova alguns nós da árvore (pelo menos uma remoção para cada cenário estudado em aula) e tente remover
//      nós que não existem na BST.

        // Removing a leaf
        System.out.println("Removing leaf 8.");
        bst.remove("8");
        bst.inOrderTraversal();

        // Removing a node with one child
        System.out.println("Removing node 10, with one child");
        bst.remove("10");
        bst.inOrderTraversal();

        // Removing a node with two children
        System.out.println("Removing node 15, with two children");
        bst.remove("15");
        bst.inOrderTraversal();

        // Removing root
        System.out.println("Removing node 30, with two children");
        bst.remove("30");
        bst.preOrder();
        bst.inOrderTraversal();

        // Removing a node that doesn't exist
        System.out.println("Removing node 50, which doesn't exist");

        try {
            bst.remove("50");
            bst.inOrderTraversal();
        } catch (RuntimeException exp) {
            System.out.println("Exeption: " + exp);
        }

//      6. Para demonstrar o funcionamento correto da BST, para cada alteração da BST (realizada no item anterior),
//      o programa deve exibir o conteúdo da árvore com o percurso em ordem.

//      "Exibir o conteúdo da árvore" significa exibir todas as informações de cada nó (chave do nó, chave do pai,
//      chave do filho esquerdo, chave do filho direito, se é raiz, se é folha, grau, nível e altura).

        preOrder(bst.getRoot());

//      Demonstre também o uso das operações findMin(), findMax(), findPredecessor(), findSuccessor() e
//      clear().

        System.out.println("Minimum value of the tree:\n" + bst.findMin());
        System.out.println("Maximum value of the tree:\n" + bst.findMax());

        System.out.println("Predecessor of tree's root:\n" + bst.findPredecessor(bst.getRoot().getData()));
        System.out.println("Predecessor of node 20:\n" + bst.findPredecessor("20"));

        System.out.println("Successor of tree's root:\n" + bst.findSuccessor(bst.getRoot().getData()));
        System.out.println("Successor of node 20:\n" + bst.findSuccessor("20"));

        System.out.println("Erasing all the tree's nodes");
        bst.clear();
        System.out.println("Is the tree empty? " + bst.isEmpty());
        bst.preOrder();
    }
}