

public class HashData {
    private int key;
    private String value;

    public HashData(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public HashData() {
        this(0, null);
    }

    public int getKey() { return key; }
    public HashData setKey(int key) {
        this.key = key;
        return this;
    }

    public String getValue() { return value; }
    public HashData setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Key: ")
          .append(this.key)
          .append(" | Value: ")
          .append(value)
          .append("\n");

        return sb.toString();
    }
}