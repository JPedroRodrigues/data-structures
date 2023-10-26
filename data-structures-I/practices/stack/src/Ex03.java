import java.util.Scanner;

public class Ex03 {
	public static boolean match(String exp) {
		exp = exp.trim();

		int len = exp.length();
		Pilha stack = new Pilha(len);

		for (int i = 0; i < len; i++){
			char c = exp.charAt(i);
			if (c == '(' || c == '[' || c == '{' || c == '<') {
				stack.push(c);
			}
			if (!stack.isEmpty()) {
				if (stack.topo() == '(' && c == ')') stack.pop();
				else if (stack.topo() == '[' && c == ']') stack.pop();
				else if (stack.topo() == '{' && c == '{') stack.pop();
				else if (stack.topo() == '<' && c == '>') stack.pop();
			}
			else if (c == ')' || c == ']' || c == '}' || c == '>') return false;
		}

		return stack.isEmpty();
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		System.out.print("Expression: ");
		String expression = s.nextLine();

		s.close();

		if (match(expression)) System.out.printf("\"%s\" is matched.\n", expression);
		else System.out.printf("\"%s\" is matched incorrectly.\n", expression);
	}
}
