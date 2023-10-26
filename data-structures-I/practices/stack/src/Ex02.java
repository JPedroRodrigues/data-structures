import java.util.Scanner;

public class Ex02 {
	public static boolean palindromo(String data) {
		String dt = data.trim().replace("/", "");
		
		int len = dt.length();
		int j = len - 1;
		
		Pilha stack = new Pilha(1);
		for (int i = 0; i < len; i++) {
			stack.push(dt.charAt(i));
			if (stack.topo() != dt.charAt(j - i)) return false;
			stack.pop();
		}
		
		return true;
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Data: ");
		String data = s.nextLine();
		
		s.close();		
		
		if (palindromo(data)) System.out.printf("\"%s\" é uma data palíndromo.\n", data);
		else System.out.printf("\"%s\" não é palindromo.\n", data);
	}
}
