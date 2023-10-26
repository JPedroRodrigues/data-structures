public class Aluno {
    private String rgm;
    private String nome;
    private char sexo;
    private float notaA;
    private float notaB;
    private float notaC;
    private float notaD;

    public Aluno() {
        this("00000000", "sem-nome", 0.f, 0.f, 0.f, 0.f);
    }

    public Aluno(String rgm, String nome) {
        this.nome = nome;
        this.rgm = rgm;
    }

    public Aluno(String rgm, String nome, float notaA, float notaB, float notaC, float notaD) {
        this.rgm = rgm;
        this.nome = nome;
        this.notaA = notaA;
        this.notaB = notaB;
        this.notaC = notaC;
        this.notaD = notaD;
    }

    public String getRgm() {
        return rgm;
    }

    public void setRgm(String rgm) {
        this.rgm = rgm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public float getNotaA() {
        return notaA;
    }

    public void setNotaA(float notaA) {
        this.notaA = notaA;
    }

    public float getNotaB() {
        return notaB;
    }

    public void setNotaB(float notaB) {
        this.notaB = notaB;
    }

    public float getNotaC() {
        return notaC;
    }

    public void setNotaC(float notaC) {
        this.notaC = notaC;
    }

    public float getNotaD() {
        return notaD;
    }

    public void setNotaD(float notaD) {
        this.notaD = notaD;
    }

    public float calculaMedia() {
        return (notaA + notaB + notaC + notaD) / 4;
    }

    public void mostraDados() {
        System.out.printf(
                "RGM: %s,\tNome: %s," +
                        "\tSexo: %c,\tNota A: %.2f," +
                        "\tNota B: %.2f,\tNota C: %.2f," +
                        "\tNota D: %.2f,\tMédia: %.2f,",
                rgm, nome, sexo,notaA, notaB, notaC, notaD, calculaMedia()
        );

        if (avaliaSituacao()) System.out.println("\tSituação: Aprovado.");
        else System.out.println("\tSituação: Reprovado.");
    }

    public boolean avaliaSituacao() {
        if(calculaMedia() >= 6) return true;
        else return false;
    }
}
