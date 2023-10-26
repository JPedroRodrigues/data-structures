public class PilhaInteiros {
    final int STACK_CAPACITY = 10;

    private int count = 0;
    int[] stack;

    public PilhaInteiros() {
        stack = new int[STACK_CAPACITY];
    }

    public PilhaInteiros(int capacity) {
        stack = new int[capacity];
    }

    public void push(int value) {
        if (count == stack.length) {
            System.out.println("Stack is full.");
            return;
        } else if (value < 0) {
            System.out.println("Cannot input negative numbers.");
            return;
        }

        stack[count] = value;
        ++count;
    }

    public int pop() {
        if (count == 0) {
            System.out.println("Your stack is empty.");
            return -1;
        }
        --count;

        int top = stack[count];
        stack[count] = -1;

        return top;
    }

    public int top() {
        if (count == 0) {
            System.out.println("Your stack is empty.");
            return -1;
        }
        return stack[count - 1];
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == stack.length;
    }

    public void clear() {
        if (!isEmpty()) {
            for (int i = 0; i < count; i++) {
                stack[i] = -1;
            }
            count = 0;
        }
        else System.out.println("Your stack is empty. It's not possible to clear it all.");
    }

    public void print() {
        if (!isEmpty()) {
            System.out.printf("Stack = [%d", stack[0]);
            for (int i = 1; i < count; i++) System.out.printf(", %d", stack[i]);
            System.out.println("]");
        }
        else System.out.println("Your stack is empty. It's not possible to print its content");
    }
}
