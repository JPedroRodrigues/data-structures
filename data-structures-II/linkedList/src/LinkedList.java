/* Estrutura de Dados II - Atividade: Revisão POO com Java
 * João Pedro Rodrigues Vieira                      10403595
 * Sabrina Midori Futami Teixeira de Carvalho       10410220
 * Turma: 04G11
 * Referência: https://github.com/JPedroRodrigues/vi-editor.git
 * Referência: https://github.com/JPedroRodrigues/learning-java/tree/main/data-structures-I/lab/josephus
 */

public class LinkedList {

    private Movie head, tail;
    private int count;

    public LinkedList() {
        head = tail = null;
        count = 0;
    }

    public Movie getHead() { return head; }
    public Movie getTail() { return tail; }
    public int getCount() { return count; }

    public void append(Movie movie) {
        if (head == null) head = movie;
        else tail.setNext(movie);

        tail = movie;
        ++count;
    }
}