import java.util.Scanner;

public class Ex01 {
	public static boolean palindromo(String palavra) {
		String word = palavra.trim().replaceAll("\\s+", "").toLowerCase();

		int len = word.length();
		Pilha p = new Pilha(1);
		
		int j = len - 1;
		
		for (int i = 0; i < len; i++) {
			p.push(word.charAt(i));
			if (p.topo() != word.charAt(j - i)) return false;
			p.pop();
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Palavra: ");
		String palavra = s.nextLine();
		
		s.close();
		
		if (palindromo(palavra)) System.out.printf("\"%s\" é um palíndromo.\n", palavra);
		else System.out.printf("\"%s\" não é um palíndromo.\n", palavra);
		
	}
}
