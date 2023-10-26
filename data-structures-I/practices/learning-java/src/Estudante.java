public class Estudante {
    // Getters e Setters da matrícula
    private String matricula;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    private final String nome;
    private float n1;
    private float n2;
    private float sub;
    private float pf;

    // Construtores
    // Não passo void, pois não é uma função, que retornará ou não um valor específico.
    public Estudante() {
        /* Se nenhum parâmetro for passado,
         * o programa atribui a matrícula um '?' e ao nome, ''.
         */
        this("?", "");
    }

    public Estudante(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        n1 = 0.0f;
        n2 = 0.0f;
        sub = 0.0f;
        pf = 0.0f;
    }

    public void atualizarN1(float n1) {
        this.n1 = n1;
    }

    public void atualizarN2(float n2) {
        this.n2 = n2;
    }

    public void atualizarSub(float sub) {
        this.sub = sub;
    }

    public void atualizarPf(float pf) {
        this.pf = pf;
    }

    @Override
    public String toString() {
        return matricula + "; " + nome + "; " + n1 + "; " + n2 + "; " + sub + "; " + pf + ".";
    }
}