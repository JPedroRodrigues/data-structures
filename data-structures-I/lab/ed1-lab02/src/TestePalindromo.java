import java.util.Scanner;
import java.util.Objects;

public class TestePalindromo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while(true) {
            System.out.print("Digite uma palavra: ");
            Palindromo palavra = new Palindromo(s.nextLine());

            if (Objects.equals(palavra.getTexto(), "\\sair")) break;

            if (palavra.verificar()) System.out.printf("\"%s\" é palíndromo!\n", palavra.getTexto());
            else System.out.printf("\"%s\" não é um palíndromo.\n", palavra.getTexto());
        }
        s.close();
    }
}
