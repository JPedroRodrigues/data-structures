public class Main {
    public static void main(String[] args) {
        // Open adressing hash map
        HashTable hashOA = new HashMapOA(13);

        System.out.println(hashOA.insert(69, "Puppy"));

        System.out.println(hashOA.insert(4, "Kitten"));
        System.out.println(hashOA.search(4));

        System.out.println(hashOA.insert(15, "Kittea"));
        if (hashOA.remove(15)) {
            System.out.println("Key 15 removed with success");  // hard code eeeww
        } else System.out.println("Could not remove data with key 15");
        
        System.out.println(hashOA.insert(15, "Puppycorn"));
        System.out.println(hashOA.search(15));

        // Chaining hash map
        HashTable hashChained = HashMapChaining(13);

        System.out.println(hashChained.insert(69, "Puppy"));

        System.out.println(hashChained.insert(4, "Kitten"));
        System.out.println(hashChained.search(4));

        System.out.println(hashChained.insert(15, "Kittea"));
        if (hashChained.remove(15)) {
            System.out.println("Key 15 removed with success");  // hard code eeeww
        } else System.out.println("Could not remove data with key 15");
        
        System.out.println(hashChained.insert(15, "Puppycorn"));
        System.out.println(hashChained.search(15));
    }
}