/* Nome: João Pedro Rodrigues Vieira - TIA: 32281730
 * Turma: 03G
 * */

public class PilhaGenerica<T> {
    private static final int TAM_MAX = 100;
    private T[] p;
    private int topoPilha;

    public PilhaGenerica(int tamPilha) {
        topoPilha = -1;
        p = (T[]) new Object[tamPilha];
    }

    public PilhaGenerica() {
        this(TAM_MAX);
    }

    public boolean isEmpty() {
        return topoPilha == -1;
    }

    public boolean isFull() {
        return topoPilha == p.length - 1;
    }

    public void push(T dado) {
        if (!isFull()) {
            p[++topoPilha] = dado;
        } else System.out.println("Stack Overflow");
    }

    public T pop() {
        if(!isEmpty()) {
            return p[topoPilha--];
        }
        System.out.println("Stack Underflow");
        return null;
    }

    public T topo() {
        return p[topoPilha];
    }

    public int sizeElement() {
        return topoPilha;
    }

    public void clear() {
        topoPilha = -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.setLength(0);

        for (int i = 0; i <= topoPilha; i++) {
            sb.append(p[i]);
        }
        return sb.toString();
    }
}
