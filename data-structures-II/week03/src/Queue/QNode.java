/* Atividade Árvore Binária
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Queue;

public class QNode {
    private QNode next;

    public QNode(QNode next) { this.next = next; }

    public QNode() { this(null); }

    public QNode getNext() { return next; }
    public void setNext(QNode next) { this.next = next; }
}

