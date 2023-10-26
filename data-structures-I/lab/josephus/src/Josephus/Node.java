package Josephus;

public class Node {

    private Soldier soldier;
    private Node next;

    public Node() { this(null, null); }

    public Node(Soldier soldier, Node next) {
        this.soldier = soldier;
        this.next = next;
    }

    public Node getNext() { return next;}

    public Soldier getSoldier() { return soldier; }

    public void setNext(Node next) { this.next = next; }

    public void setSoldier(Soldier soldier) { this.soldier = soldier; }

}
