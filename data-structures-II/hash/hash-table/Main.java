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

public class Main {

    public static void main(String[] args) {

        int size = 3;
        String ans;

        // Hash Table with Open Adressing
        System.out.println("""
            ================================================
                     HASH TABLE WITH OPEN ADRESSING
            ================================================
            """);

        HashTable table1 = new HashTableOA(size);
        
        System.out.println("* Inserting keys and values...");
        
        ans = table1.insert(0, "A");
        System.out.println(ans);

        ans = table1.insert(1, "B");
        System.out.println(ans);

        ans = table1.insert(2, "C");
        System.out.println(ans);

        System.out.println("\n* Trying to insert into a full hash table...");

        ans = table1.insert(3, "D");
        System.out.println(ans);

        ans = table1.insert(4, "E");
        System.out.println(ans);

        ans = table1.insert(5, "F");
        System.out.println(ans);

        System.out.println("\n* Inserting keys that already exist...");

        ans = table1.insert(0, "X");
        System.out.println(ans);

        ans = table1.insert(1, "Y");
        System.out.println(ans);

        ans = table1.insert(2, "Z");
        System.out.println(ans);

        System.out.println("\n* Searching for keys...");

        ans = table1.search(0);
        System.out.println(ans);    // A

        ans = table1.search(1);
        System.out.println(ans);    // B

        ans = table1.search(2);
        System.out.println(ans);    // C

        ans = table1.search(3);
        System.out.println(ans);    // Key not found.

        System.out.println("\n* Removing elements...");

        // Removing an element that exists
        table1.remove(0);
        table1.remove(1);

        // Removing an element that doesn't exist
        table1.remove(5);

        System.out.println("\n* Inserting new elements after the removal...");

        ans = table1.insert(10, "Cat");
        System.out.println(ans);

        ans = table1.insert(20, "Dog");
        System.out.println(ans);

        ans = table1.insert(30, "Horse");
        System.out.println(ans);

        System.out.println();

        // Hash Table with Chaining
        System.out.println("""
            ================================================
                        HASH TABLE WITH CHAINING
            ================================================
            """);

        HashTable table2 = new HashTableChaining(3);

        System.out.println("* Inserting keys and values...");

        ans = table2.insert(10, "A");
        System.out.println(ans);

        ans = table2.insert(20, "B");
        System.out.println(ans);

        ans = table2.insert(30, "C");
        System.out.println(ans);

        ans = table2.insert(40, "D");
        System.out.println(ans);
        
        ans = table2.insert(50, "E");
        System.out.println(ans);

        ans = table2.insert(60, "F");
        System.out.println(ans);

        ans = table2.insert(70, "G");
        System.out.println(ans);

        ans = table2.insert(80, "H");
        System.out.println(ans);

        System.out.println("\n* Inserting keys that already exist...");
        ans = table2.insert(10, "I");
        System.out.println(ans);

        ans = table2.insert(50, "J");
        System.out.println(ans);

        System.out.println("\n* Searching for keys...");

        ans = table2.search(10);
        System.out.println(ans);

        ans = table2.search(30);
        System.out.println(ans);

        ans = table2.search(50);
        System.out.println(ans);

        ans = table2.search(81);
        System.out.println(ans);

        System.out.println("\n* Removing elements...");

        // Removing an element that exists
        table2.remove(20);
        table2.remove(40);
        table2.remove(60);

        // Removing an element that doesn't exist
        table2.remove(20);
        table2.remove(40);
        table2.remove(60);
        table2.remove(100);

        System.out.println("\n* Inserting new elements after the removal...");

        ans = table2.insert(20, "Bird");
        System.out.println(ans);

        ans = table2.insert(60, "Zebra");
        System.out.println(ans);

    }
}
