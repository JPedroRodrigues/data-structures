import java.util.Objects;
import java.util.Scanner;

public class TesteTrabalhador {
    private static final int MAX_TRABALHADOR = 50;

    public static float mediaSalarial(Trabalhador[] v, int n) {
        float soma = 0;

        for (int i = 0; i < n; i++) {
            soma += v[i].getSalario();
        }
        return soma / n;
    }


    public static int maiorSalario(Trabalhador[] v, int n) {
        int index = 0;
        float maiorSal = v[0].getSalario();

        for (int i = 1; i < n; i++) {
            if(v[i].getSalario() > maiorSal) {
                maiorSal = v[i].getSalario();
                index = i;
            }
        }
        return index;
    }


    public static int menorSalarioMulher(Trabalhador[] v, int n) {
        float menorSalario = v[0].getSalario();
        int index = 0;
        for(int i = 0; i < n; i++) {
            if(Objects.equals(v[i].getSexo(), "feminino") && v[i].getSalario() < menorSalario) {
                menorSalario = v[i].getSalario();
                index = i;
            }
        }
        return index;
    }


    public static void main(String[] args) {
        Trabalhador[] workers = new Trabalhador[MAX_TRABALHADOR];

        workers[0] = new Trabalhador(
                "Marlon Miguel", 1300.f,
                "10987654321", 40,
                "masculino", "Rua do Mingau"
        );
        workers[1] = new Trabalhador(
                "Nicolas Cassiano", 1500.f,
                "12345678910", 39,
                "masculino", "Rua do Marechal"
        );
        workers[2] = new Trabalhador(
                "Paula Taís", 1000.f,
                "12983476510", 35,
                "feminino", "Rua do Miau"
        );
        workers[3] = new Trabalhador(
                "Jonathan Santos", 500.f,
                "12345678910", 35,
                "masculino", "Rua do Marsupial"
        );
        workers[4] = new Trabalhador(
                "Luana Mirian", 1800.f,
                "10987654321", 25,
                "feminino", "Rua do Mortal"
        );

        Scanner s = new Scanner(System.in);

        int i;

        for (i = 5; i < MAX_TRABALHADOR; i++) {
            workers[i] = new Trabalhador();

            System.out.print("Nome: ");
            workers[i].setNome(s.nextLine());

            System.out.print("Salário: ");
            workers[i].setSalario(Float.parseFloat(s.nextLine()));

            System.out.print("CPF: ");
            workers[i].setCpf(s.nextLine());

            System.out.print("Idade: ");
            workers[i].setIdade(Integer.parseInt(s.nextLine()));

            System.out.print("Sexo: ");
            workers[i].setSexo(s.nextLine());

            System.out.print("Endereço: ");
            workers[i].setEndereco(s.nextLine());
        }
        
        s.close();

        System.out.print("\n");
        for (i = 0; i < MAX_TRABALHADOR; i++) {
            workers[i].mostra();
        }

        float media = mediaSalarial(workers, MAX_TRABALHADOR);
        System.out.printf("\nMédia salarial dos trabalhadores: %.2f\n", media);

        System.out.print("\nHomens com salário acima da média geral:\n");
        for (i = 0; i < MAX_TRABALHADOR; i++) {
            if (workers[i].getSalario() > media && Objects.equals(workers[i].getSexo(), "masculino")) {
                System.out.print("\t- ");
                workers[i].mostra();
            }
        }

        float soma30 = 0;
        int qtd30 = 0;
        for (i = 0; i < MAX_TRABALHADOR; i++) {
            if (Objects.equals(workers[i].getSexo(), "masculino") && workers[i].getIdade() > 30) {
                soma30 += workers[i].getSalario();
                qtd30++;
            }
        }

        float media30 = soma30 / qtd30;
        int qtdAbaixo30 = 0;

        for (i = 0; i < MAX_TRABALHADOR; i++) {
            if (Objects.equals(workers[i].getSexo(), "masculino") && workers[i].getIdade() > 30 && workers[i].getSalario() < media30) {
                qtdAbaixo30++;
            }
        }

        System.out.printf("\nMédia salarial dos homens acima de 30 anos: %.2f\n", media30);
        System.out.printf("Quantidade de homens acima de 30 anos abaixo desta média: %d\n", qtdAbaixo30);

        System.out.print("\nTrabalhador(a) com o maior salário:\n\t- ");
        workers[maiorSalario(workers, MAX_TRABALHADOR)].mostra();
        
        System.out.print("\nTrabalhadora com o menor salário:\n\t- ");
        workers[menorSalarioMulher(workers, MAX_TRABALHADOR)].mostra();
    }
}
