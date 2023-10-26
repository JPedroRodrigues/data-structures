public class TesteAluno {
    public static void main(String[] args) {
        Aluno alunoA = new Aluno("32281730", "João Pedro", 10.f, 10.f, 10.f, 10.f);
        Aluno alunoB = new Aluno("03718223", "ordeP oãoJ", 2.f, 5.f, 6.f, 3.5f);

        alunoA.setSexo('M');
        alunoB.setSexo('M');

        alunoA.mostraDados();
        alunoB.mostraDados();
    }
}
