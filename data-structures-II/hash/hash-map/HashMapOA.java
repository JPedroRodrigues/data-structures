

public class HashMapOA implements HashTable {
    private int m;
    private HashData[] map;

    public HashMapOA(int m) {
        this.m = m;
        this.map = new HashData[m];
    }

    public HashMapOA() {
        this.m = 97;
        this.map = new HashData[m];
    }

    private int hashFunction(int key) { return key % m; }

    @Override
    public String search(int key) {
        int hashKey = hashFunction(key);
        int traverseKey = hashKey;

        while (map[traverseKey] != null) {
            if (map[traverseKey].getKey() == key && !map[traverseKey].getValue().equals("DELETED")) return "Key " + key + " found with value " + map[traverseKey].getValue();

            traverseKey = (traverseKey + 1) % m;

            if (traverseKey == hashKey) break;
        }

        return "Key " + key + " not found";
    }

    @Override
    public String insert(int key, String value) {
        HashData data = new HashData(key, value);

        int hashKey = hashFunction(key);
        int traverseKey = hashKey;

        while (map[traverseKey] != null) {
            if (map[traverseKey].getValue().equals("DELETED")) {
                map[traverseKey] = data;
                return "Data with key " + key + " and value " + value + " inserted with success in the position " + traverseKey;
            } else if (map[traverseKey].getKey() == key) { 
                map[traverseKey] = data;
                return "Data with key " + key + " updated with success (new value: " + value + ")"; 
            } else if (map[traverseKey].getKey() != key) {
                traverseKey = (traverseKey + 1) % m;
            } else if (traverseKey == hashKey) {
                return "No space available in the hash table";
            }
        }

        map[traverseKey] = data;
        return "Data with key " + key + " and value " + value + " inserted with success in the position " + traverseKey;
    }

    @Override
    public boolean remove(int key) {
        int hashKey = hashFunction(key);
        int traverseKey = hashKey;

        while (map[traverseKey] != null) {
            if (map[traverseKey].getKey() == key && !map[traverseKey].getValue().equals("DELETED")) {
                map[traverseKey].setValue("DELETED");
                return true;
            } else if (map[traverseKey].getKey() == key) break;

            traverseKey = (traverseKey + 1) % m;

            if (traverseKey == hashKey) break;
        }
        return false;
    }
}