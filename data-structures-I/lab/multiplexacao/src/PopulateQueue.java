import java.util.Scanner;

public class PopulateQueue {

    public static FluxDataQueue PopulateFluxDataQueue(int size){

        Scanner scanner = new Scanner(System.in);
        FluxDataQueue queue = new FluxDataQueue(size);

        for (int i = 0; i < size; i++) {
            System.out.println("Digite o valor da posicao"+i);
            int data = scanner.nextInt();
            queue.enqueue(data);
        }
        return queue;
    }

}
