/* Nome: João Pedro Rodrigues Vieira - TIA: 32281730
 * Turma: 03G
 * */

public class FilaGenerica<T> {
    private static final int TAM = 100;

    private int first;
    private int last;
    private int size;

    private T[] f;

    public FilaGenerica() {
        f = (T[]) new Object[TAM];
        first = last = size = 0;
    }

    public FilaGenerica(int tam) {
        f = (T[]) new Object[tam];
        first = 0;
        last = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == f.length;
    }

    public int queueSize() {
        return size;
    }

    public void enqueue(T value) {
        if (!isFull()) {
            f[last] = value;
            last = (last + 1) % f.length;
            ++size;
        } else {
            System.out.println("Queue Overflow.");
        }
    }

    public T dequeue() {
        if (!isEmpty()) {
            T data = f[first];
            first = ++first % f.length;
            size--;

            return data;
        } else {
            System.out.println("Queue Underflow.");
            return null;
        }
    }

    public T front() {
        if (!isEmpty()) return f[first];
        else {
            System.out.println("Queue Underflow.");
            return null;
        }
    }

    public T rear() {
        if (!isEmpty()) {
            int lastIndex;
            if (last == 0) lastIndex = f.length - 1;
            else lastIndex = last - 1;
            return f[lastIndex];
        } else {
            System.out.println("Queue Underflow");
            return null;
        }
    }

    public void clear() {
        first = last = size = 0;
    }

    @Override
    public String toString() {

        int newIndex = (first + last) % f.length;

        StringBuilder sb = new StringBuilder();
        if (size != 0) {
            if (newIndex <= first) {
                for (int i = first; i < f.length; ++i)
                    sb.append(f[i]);
                for (int i = 0; i < newIndex; ++i)
                    sb.append(f[i]);
            } else {
                for (int i = first; i < newIndex; ++i)
                    sb.append(f[i]);
            }
        }
        return sb.toString();
    }
}
