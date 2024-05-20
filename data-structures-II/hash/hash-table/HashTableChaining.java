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

import Tree.*;

public class HashTableChaining implements HashTable {

    // Fields
    private int size;
    private AVL table[];

    // Constructors
    public HashTableChaining(int size) {
        table = new AVL[size];
        this.size = size;
    }

    // Methods
    public int hashFunction(int key) {
        final double a = 0.61803;
        double hash = size * ((key * a) % 1);
        return (int) Math.floor(hash);
    }

    @Override
    public String search(int key) {
        int hashKey = hashFunction(key);
        AVL avl = table[hashKey];
        String str;

        // Check if the AVL at hashKey index is null
        if (avl == null) str = "Key " + key + " not found.";

        // AVL at hashKey is not null
        else {
            Node aux = avl.search(key);

            // If the key already exists, return its value
            if (aux != null) {
                str = "Key " + key + " found. Value = " + aux.getValue() + ".";

            // If the key doesn't exist
            } else str = "Key " + key + " not found.";
        }

        return str;
    }

    @Override
    public String insert(int key, String value) {
        int hashKey = hashFunction(key);
        AVL avl = table[hashKey];
        String str;

        // Check if the AVL at hashKey index is null
        if (avl == null) {
            // Initialize a new AVL tree and insert the node
            avl = new AVL();
            table[hashKey] = avl;
            Node node = new Node(key, value);
            avl.insert(node);
            str = "Element {" + key + ": " + value + "} successfully inserted at index " + hashKey + ".";

        // AVL at hashKey is not null, proceed with insertion or update
        } else {
            Node aux = avl.search(key);

            // If the key already exists, update its value
            if (aux != null) {
                str = "Key " + key + " found and its value updated from " + aux.getValue() + " to " + value + ".";
                aux.setValue(value);

            // If the key doesn't exist, insert a new node
            } else {
                Node node = new Node(key, value);
                avl.insert(node);
                str = "Element {" + key + ": " + value + "} successfully inserted at index " + hashKey + ".";
            }
        }

        return str;           
    }

    @Override
    public Boolean remove(int key) {
        int hashKey = hashFunction(key);
        AVL avl = table[hashKey];

        // Check if the AVL at hashKey index is null
        if (avl == null) {
            System.out.println("Key " + key + " not found.");
            return false;

        // AVL at hashKey is not null
        } else {
            Node aux = avl.search(key);

            // If the key already exists, remove the node from the AVL
            if (aux != null) {
                System.out.println("Element {" + key + ": " + aux.getValue() + "} found and removed.");
                avl.remove(key);
                return true;

            // If the key doesn't exist, return false
            } else {
                System.out.println("Key " + key + " not found.");
                return false;
            }
        }
    }

}
