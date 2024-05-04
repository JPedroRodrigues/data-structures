/* Atividade Árvore Binária
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Queue;

import BinaryTree.Node;

public class Queue {
    private Node head;
    private Node tail;

    public Queue() { head = tail = null; }

    public Queue(Node head) { this.head = head; }

    public Node getHead() { return head; }

    public boolean isEmpty() { return head == null; }

    public void enqueue(Node node) {
        if (isEmpty()) {
            head = tail = node;
            head.setNext(null);
            tail.setNext(null);
        } else {
            tail.setNext(node);
            node.setNext(null);
            tail = node;
        }
    }

    public Node dequeue() {
        if (isEmpty()) return null;

        Node aux = head;
        head = (Node) head.getNext();
        aux.setNext(null);

        return aux;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        QNode aux = head;
//
//        while (aux != null) {
//            sb.append(aux).append(" ");
//            aux = aux.getNext();
//        }
//        return sb.toString();
//    }

}
