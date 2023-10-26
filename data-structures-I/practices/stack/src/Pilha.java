public class Pilha {
	private static final int TAM_MAX = 100;
	private char[] p;
	private int topoPilha;
	
	public Pilha(int tamPilha) {
		topoPilha = -1;
		p = new char[tamPilha];
	}
	
	public Pilha() {
		this(TAM_MAX);
	}
	
	public boolean isEmpty() {
		return topoPilha == -1;
	}
	
	public boolean isFull() {
		return topoPilha == p.length - 1;
	}
	
	public void push(char dado) {
		if (!isFull()) {
			p[++topoPilha] = dado;
			return;
		}
		System.out.println("Stack Overflow");
	}
	
	public char pop() {
		if(!isEmpty()) {
            return p[topoPilha--];
		}
		System.out.println("Stack Underflow");
		return ' ';
	}
	
	public char topo() {
		return p[topoPilha];
	}
	
	public int sizeElement() {
		return topoPilha;
	}
}
