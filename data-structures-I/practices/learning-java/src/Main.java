public class Main {
    public static void main(String[] args) {
        Estudante estudante = new Estudante("87654321", "João Pedro");
        estudante.atualizarN1(10.0f);
        estudante.atualizarN2(10.0f);
        estudante.atualizarSub(0.0f);
        estudante.atualizarPf(0.0f);

        System.out.println(estudante);
    }
}
