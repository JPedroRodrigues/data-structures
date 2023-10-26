/*
* Nome: João Pedro Rodrigues Vieira - TIA: 32281730
* Nome: Otávio Augusto Freire de Andrade Bruzadin - TIA: 42208165
* */

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        FluxDataQueue flux1 = new FluxDataQueue();
        FluxDataQueue flux2 = new FluxDataQueue();
        FluxDataQueue flux3 = new FluxDataQueue();

        SharedQueue Multiplex = new SharedQueue();

        while (true) {
            System.out.println("""
                    |========== Menu de Opções ==========|
                    1. Criar fluxo 1.
                    2. Criar fluxo 2.
                    3. Criar fluxo 3.
                    4. Imprimir na tela os 3 fluxos.
                    5. Criar canal de multiplexação.
                    6. Imprimir canal multiplexado.
                    7. Finalizar o programa.
                    |====================================|
                    """);

            Scanner s = new Scanner(System.in);
            System.out.print("Opção: ");
            int opt = Integer.parseInt(s.next());

            if (opt == 7) {
                s.close();
                return;
            }
            else if (opt == 1) {
                System.out.println("Iniciando criação do fluxo 1");

                int value;
                int i = 0;

                while (true) {
                    System.out.printf("Digite o %dº valor: ", i + 1);
                    value = Integer.parseInt(s.next());
                    ++i;
                    if (value != -1) flux1.enqueue(value);
                    else break;
                }
            } else if (opt == 2) {
                System.out.println("Iniciando criação do fluxo 2");

                int value;
                int i = 0;

                while (true) {
                    System.out.printf("Digite o %dº valor: ", i + 1);
                    value = Integer.parseInt(s.next());
                    ++i;
                    if (value != -1) flux2.enqueue(value);
                    else break;
                }
            } else  if (opt == 3) {
                System.out.println("Iniciando criação do fluxo 3");

                int value;
                int i = 0;

                while (true) {
                    System.out.printf("Digite o %dº valor: ", i + 1);
                    value = Integer.parseInt(s.next());
                    ++i;
                    if (value != -1) flux3.enqueue(value);
                    else break;
                }
            } else if (opt == 4) {
                if (flux1.isEmpty() || flux2.isEmpty() || flux3.isEmpty()) {
                    System.out.println("Preencha todos os fluxos para poder mostrar o conteúdo de cada um deles.");
                    continue;
                }

                System.out.println("Conteúdo de cada fluxo: ");

                System.out.println("Fluxo 1:");
                System.out.println(flux1);

                System.out.println("Fluxo 2:");
                System.out.println(flux2);

                System.out.println("Fluxo 3:");
                System.out.println(flux3);
            } else if (opt == 5) {
                if (flux1.isEmpty() || flux2.isEmpty() || flux3.isEmpty()) {
                    System.out.println("Preencha os fluxos para adicionar ao canal multiplexado.");
                    continue;
                }

                while (!flux1.isEmpty() || !flux2.isEmpty() || !flux3.isEmpty()) {
                    if (!flux1.isEmpty()) Multiplex.enqueue(new FluxData(1, flux1.dequeue()));
                    if (!flux2.isEmpty()) Multiplex.enqueue(new FluxData(2, flux2.dequeue()));
                    if (!flux3.isEmpty()) Multiplex.enqueue(new FluxData(3, flux3.dequeue()));
                }

                System.out.println("Canal multiplexado criado com sucesso");
            } else if (opt == 6) {
                if (Multiplex.isEmpty()) {
                    System.out.println("Crie uma canal multiplexado para poder imprimí-lo.");
                    continue;
                }
                System.out.println("Fila multiplexada:\n" + Multiplex);

                while(!Multiplex.isEmpty()) Multiplex.dequeue();
            } else {
                System.out.println("Opção Inválida. Digite corretamente uma das opções do menu.");
            }
        }
    }
}