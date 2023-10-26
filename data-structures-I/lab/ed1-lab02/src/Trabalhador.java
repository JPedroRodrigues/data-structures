public class Trabalhador {
    private String nome;
    private float salario;
    private String cpf;
    private int idade;
    private String sexo;
    private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Trabalhador() {
        this("sem-nome", 0.f, "00000000000", 0, "sem-sexo", "sem-endereco");
    }

    public Trabalhador(String nome, float salario, String cpf, int idade, String sexo, String endereco) {
        this.nome = nome;
        this.salario = salario;
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
        this.endereco = endereco;
    }

    public void mostra() {
        System.out.printf("Nome: %s", nome);
        System.out.printf(", \tSalario: R$%.2f", salario);
        System.out.printf(", \tCPF: %s", cpf);
        System.out.printf(", \tIdade: %d", idade);
        System.out.printf(", \tSexo: %s", sexo);
        System.out.printf(", \tEndereço: %s\n", endereco);
    }

    public float calculaSalarioAnual() {
        return salario * 13 + salario / 3;
    }
}
