/* APL2 - Lexer & Parser
 * João Pedro Rodrigues Vieira         10403595
 * Sabrina Midori F. T. de Carvalho    10410220
 * Pedro Pessuto Rodrigues Ferreira    10409729
 * Estrutura de Dados II - Turma 04G11
 * Prof. André Kishimoto
 */

package Tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String path;
    private String name;
    private String value;
    private String type;
    private List<Node> duplicated;

    private int balanceFactor;
    private Node left;
    private Node right;
    private Node parent;

    public Node(String path) { 
        this.path = path;
        String[] name = path.split("/");
        this.name = name[name.length - 1];
        this.value = null;
        this.type = null;
        this.duplicated = new ArrayList<>();
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balanceFactor = getBalanceFactor();
    }

    public Node(String path, String type) { 
        this.path = path;
        String[] name = path.split("/");
        this.name = name[name.length - 1];
        this.type = type;
        this.value = null;
        this.duplicated = new ArrayList<>();
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balanceFactor = getBalanceFactor();
    }

    public Node(String path, String type, String value) { 
        this.path = path;
        String[] name = path.split("/");
        this.name = name[name.length - 1];
        this.type = type;
        this.value =value;
        this.duplicated = new ArrayList<>();
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balanceFactor = getBalanceFactor();
    }

    public Node() { 
        this.path = null;
        this.name = null;
        this.type = null;
        this.value = null;
        this.duplicated = new ArrayList<>();
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balanceFactor = getBalanceFactor(); 
    }

    public String getPath() { return path; }
    public String getName() { return name; }
    public String getValue() { return value; }
    public String getType() { return type; }
    public int getBalanceFactor() { updateBalanceFactor(); return balanceFactor; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    public Node getParent() { return parent; }

    public Node getDuplicated() {
        if (notDuplicated()) throw new RuntimeException("There's no data with the same name.");
        else return duplicated.getFirst();
    }

    public boolean notDuplicated() {
        return duplicated.isEmpty();
    }

    private void updateBalanceFactor() {
        int hR, hL;
        if (getRight() == null) hR = -1;
        else hR = getRight().getHeight();

        if (getLeft() == null) hL = -1;
        else hL = getLeft().getHeight();

        this.balanceFactor = hR - hL;
    }

    public Node setPath(String path) {
        this.path = path;
        return this;
    }

    public Node setName(String name) {
        this.name = name;
        return this;
    }

    public Node setValue(String value) {
        this.value = value;
        return this;
    }

    public Node setType(String type) {
        this.type = type;
        return this;
    }

    public Node setDuplicated(Node node) {
        this.duplicated.add(node);
        return this;
    }

    public Node removeDuplicated() {
        return this.duplicated.removeFirst();
    }

    public Node setLeft(Node left) {
        this.left = left;
        return this;
    }

    public Node setRight(Node right) {
        this.right = right;
        return this;
    }

    public Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public boolean isRoot() { return parent == null; }
    public boolean isLeaf() { return left == null && right == null; }

    public int getDegree() {
        int degree = 0;
        if (left != null) ++degree;
        if (right != null) ++degree;
        return degree;
    }

    public int getLevel() {
        return getLevel(this);
    }

    private int getLevel(Node node) {
        if (node == null) return -1;
        return 1 + getLevel(node.getParent());
    }

    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data: ")
          .append(getPath())
          .append("\n")
          .append("Name: ")
          .append(getName())
          .append("\n")
          .append("Value: ")
          .append(getValue())
          .append("\n")
          .append("Type: ")
          .append(getType())
          .append("\n")
          .append("Level: ")
          .append(getLevel())
          .append("\n")
          .append("Parent: ");

        if (getParent() != null) sb.append(getParent().getPath()).append("\n");
        else sb.append("null\n");

        sb.append("Left: ");

        if (getLeft() != null) sb.append(getLeft().getPath()).append("\n");
        else sb.append("null\n");

        sb.append("Right: ");

        if (getRight() != null) sb.append(getRight().getPath()).append("\n");
        else sb.append("null\n");

        sb.append("Balance Factor: ")
          .append(getBalanceFactor())
          .append("\n");

        return sb.toString();
    }
}
