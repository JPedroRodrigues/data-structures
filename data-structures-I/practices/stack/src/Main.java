public class Main {
    public static void main(String[] args) {
            PilhaInteiros stack = new PilhaInteiros(5);
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            //stack.push(5);
            //stack.clear();
            stack.print();

            System.out.printf("Top value: %d\n", stack.top());
            System.out.printf("Stack size: %d\n", stack.size());

            if (stack.isEmpty()) System.out.println("Stack is empty.");
            else if (stack.isFull()) System.out.println("Stack is full.");
            else System.out.println("Stack has values.");
    }
}