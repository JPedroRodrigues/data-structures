import Tree.*;

public class HashMapChaining implements HashTable {
    private int m;
    private AVL[] map;

    public HashMapChaining(int m) {
        this.m = m;
        this.map = new AVL[m];
    }

    public HashMapChaining() {
        this.m = 97;
        this.map = new AVL[m];
    }

    private int hashFunction(int key) {
        final double ct = 0.58579;
        return (int) Math.floor((double) (m * ((key * ct) % 1)));
    }

    @Override
    public String insert(int key, String value) {
        int hashKey = hashFunction(key);

        if (map[hashKey] == null) {
            map[hashKey] = new AVL(new Node(key, value));
            return "Data with key " + key + " and value " + value + " inserted with success in the position " + hashKey;
        } else {
            Node node = map[hashKey].search(key);
            if (node != null) {
                node.setValue(value);
                return "Data with key " + key + " updated with success (new value: " + value + ")"; 
            } else {
                map[hashKey].insert(new Node(key, value));
                return "Data with key " + key + " and value " + value + " inserted with success in the position " + hashKey;
            }
        }
    }

    @Override
    public String search(int key) {
        int hashKey = hashFunction(key);

        if (map[hashKey] == null) return "Key " + key + " not found";

        Node node = map[hashKey].search(key);
        if (node == null) return "Key " + key + " not found";
        
        return "Key " + key + " found with value " + node.getValue();
    }

    @Override
    public boolean remove(int key) {
        int hashKey = hashFunction(key);

        if (map[hashKey] == null) {
            System.out.println("Key " + key + " not found");
            return false;
        }

        Node node = map[hashKey].search(key);
        if (node == null) {
            System.out.println("Key " + key + " not found");
            return false;
        }

        System.out.println("Key " + key + " with value " + node.getValue() + " removed with success");
        map[hashKey].remove(key);
        return true;
    }
}