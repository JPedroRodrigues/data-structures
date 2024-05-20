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

public class HashTableOA extends HashTableData implements HashTable {

    // Fields
    private int size;

    // Constructors
    public HashTableOA(int size) {
        table = new HashTableData[size];
        this.size = size;
    }

    // Methods
    public int hashFunction(int key) { return key % size; }

    @Override
    public String search(int key) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while (table[hashKey] != null) {
            // If we found the key we are looking for, return its value
            if (table[hashKey].key == key) {
                return "Key " + key + " found. Value = " + table[hashKey].value + ".";
            }

            // Otherwise, keep searching for the key using linear probing
            hashKey = (hashKey + 1) % size;

            // If we have already traversed the entire array of the hash table and did not find the key, break
            if (hashKey == originalHashKey) break;
        }

        return "Key " + key + " not found.";
    }

    @Override
    public String insert(int key, String value) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while (table[hashKey] != null) {
            // If we found the key we are trying to insert, update its value and return
            if (table[hashKey].key == key) {
                String oldValue = table[hashKey].value;
                table[hashKey].value = value;
                return "Key " + key + " found and its value updated from " + oldValue + " to " + value + ".";
            }

            // Otherwise, keep searching for a free slot using linear probing
            hashKey = (hashKey + 1) % size;

            // If we have already traversed the entire array of the hash table and did not find a free slot, return
            if (hashKey == originalHashKey) {
                return "Element {" + key + ": " + value + "} could not be inserted.";
            }
        }

        // If we fond a free slot in the hash table, insert the element
        table[hashKey] = new HashTableData(key, value);
        return "Element {" + key + ": " + value + "} successfully inserted at index " + hashKey + ".";
    }

    @Override
    public Boolean remove(int key) {
        int hashKey = hashFunction(key);
        int originalHashKey = hashKey;

        while (table[hashKey] != null) {
            // If we found the key we are trying to remove, remove it and return
            if (table[hashKey].key == key) {
                System.out.println("Element {" + key + ": " + table[hashKey].value + "} successfully removed.");
                table[hashKey] = null;
                return true;
            }

            // Otherwise, keep searching for the key using linear probing
            hashKey = (hashKey + 1) % size;

            // If we have already traversed the entire array of the hash table and did not find the key to be removed, return
            if (hashKey == originalHashKey) break; 
        }

        System.out.println("Key " + key + " could not be removed.");
        return false;
    }

}
