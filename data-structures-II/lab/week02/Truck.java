/* Estrutura de Dados II - Atividade: POO - Herança e Polimorfismo com Java
 * João Pedro Rodrigues Vieira                      10403595
 * Sabrina Midori Futami Teixeira de Carvalho       10410220
 * Turma: 04G11
 * Referência: materiais de aula do Professor André Kishimoto
 *      - https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-2/EDII-POO_Heranca_Java.pdf
 *      - https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-2/ExemploEmAula-04G11-Pessoa.zip
 */

public class Truck extends LandVehicle {

    private int axles;

    public Truck(int year, String colour, int axles) {
        super(year, colour);
        this.axles = axles;
    }

    public int getAxles() { return axles; }
    public void setAxles(int axles) { this.axles = axles; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Truck: ")
                .append(super.toString())
                .append(", Axles = ")
                .append(axles);
        return sb.toString();
    }
}
