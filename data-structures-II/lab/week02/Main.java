/* Estrutura de Dados II - Atividade: POO - Herança e Polimorfismo com Java
 * João Pedro Rodrigues Vieira                      10403595
 * Sabrina Midori Futami Teixeira de Carvalho       10410220
 * Turma: 04G11
 * Referência: materiais de aula do Professor André Kishimoto
 *      - https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-2/EDII-POO_Heranca_Java.pdf
 *      - https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-2/ExemploEmAula-04G11-Pessoa.zip
 */

public class Main {
    public static void main(String[] args) {

        LandVehicle [] lv = new LandVehicle[8];

        lv[0] = new PassengerCar(1990,"Blue",5);
        lv[1] = new PassengerCar(2004, "Grey", 4);
        lv[2] = new Truck(1999,"Green",4);
        lv[3] = new PassengerCar(2010,"Black",5);
        lv[4] = new PassengerCar(2009,"Black",7);
        lv[5] = new Truck(2011,"Green",4);
        lv[6] = new Truck(2000,"Blue",4);
        lv[7] = new Truck(2005,"Black",3);

        System.out.println("\n=========================================================================\n");
        System.out.println("A) List of Vehicles\n");

        for (var v : lv) {
            if (v instanceof PassengerCar) System.out.println("* " + v);
            else if (v instanceof Truck) System.out.println("* " + v);
        }

        System.out.println("\n=========================================================================\n");
        System.out.println("B) Passenger cars with 5 or more passenger seats and year less than 2010\n");

        for (var v: lv) {
            if ((v instanceof PassengerCar) && (((PassengerCar)v).getPassengers() >= 5) && (v.getYear() < 2010))
                System.out.println("* " + v);
        }

        System.out.println("\n=========================================================================\n");
        System.out.println("C) Total of green trucks with 4 axles\n");

        int truckSum = 0;

        for (var v : lv) {
            if ((v instanceof Truck) && (v.getColour().equals("Green")) && (((Truck)v).getAxles() == 4)) {
                System.out.println("* " + v);
                ++truckSum;
            }
        }
        System.out.println("* Total of green trucks with 4 axles = " + truckSum);
    }
}