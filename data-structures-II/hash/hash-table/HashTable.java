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

public interface HashTable {
    // Methods signatures

    // The search must be done by the key and should return the associated value of the key, if it exists.
    String search(int key);

    // The insertion should return one of three results, which indicate "key-value inserted", "value of the key updated", or "error inserting key-value".
    String insert(int key, String value);

    // The removal must be done by the key and should return true if it was possible to remove the key-value pair or false otherwise.
    Boolean remove(int key);

}
