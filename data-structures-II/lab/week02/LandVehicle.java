/* Estrutura de Dados II - Atividade: POO - Herança e Polimorfismo com Java
 * João Pedro Rodrigues Vieira                      10403595
 * Sabrina Midori Futami Teixeira de Carvalho       10410220
 * Turma: 04G11
 * Referência: materiais de aula do Professor André Kishimoto
 *      - https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-2/EDII-POO_Heranca_Java.pdf
 *      - https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-2/ExemploEmAula-04G11-Pessoa.zip
 */

public class LandVehicle {

    private int year;
    private String colour;

    public LandVehicle(int year, String colour){
        this.year = year;
        this.colour = colour;
    }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("year = ")
                .append(year)
                .append(", colour = ")
                .append(colour);

        return sb.toString();
    }
}
