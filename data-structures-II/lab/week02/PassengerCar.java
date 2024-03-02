/* Estrutura de Dados II - Atividade: POO - Herança e Polimorfismo com Java
 * João Pedro Rodrigues Vieira                      10403595
 * Sabrina Midori Futami Teixeira de Carvalho       10410220
 * Turma: 04G11
 * Referência: materiais de aula do Professor André Kishimoto
 */

public class PassengerCar extends LandVehicle {

    private int passengers;

    public PassengerCar(int year, String colour, int passengers) {
        super(year, colour);
        this.passengers = passengers;
    }

    public int getPassengers() { return passengers; }
    public void setPassengers(int passengers) { this.passengers = passengers; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Passenger car: ")
            .append(super.toString())
            .append(", Passengers = ")
            .append(passengers);

        return sb.toString();
    }

}
