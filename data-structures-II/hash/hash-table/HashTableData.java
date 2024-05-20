/*
 * Joao Pedro Rodrigues Vieira          RA 10403595
 * Sabrina Midori F. T. de Carvalho     RA 10403595
 * Pedro Pessuto Rodrigues Ferreira     RA 10409729
 * Course: Data Structures II           Class 04G11
 * Professor Andre Kishimoto            Hash Table Project
 * References:
    * https://profkishimoto.github.io/edii04g11-2024-1/conteudo/semana-12/Tabela%20Hash.pdf
    * https://www.freecodecamp.org/portuguese/news/interfaces-em-java-explicadas-com-exemplos/
 */

public class HashTableData {

    // Fields
    protected int key;
    protected String value;
    protected HashTableData[] table;

    // Constructors
    public HashTableData() {};

    public HashTableData(int key, String value) {
        this.key = key;
        this.value = value;
    }

}
