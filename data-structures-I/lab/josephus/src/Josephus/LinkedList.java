package Josephus;

import java.util.Objects;

public class LinkedList {
    private Node head;
    private int count;

    public LinkedList() {
        head = null;
        count = 0;
    }

    public int getCount() { return count; }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFull() {
        Node temp = new Node();
        return temp == null;
    }

    public boolean insertHead(Soldier soldier) {
        if (isFull()) return false;

        Node temp = new Node(soldier, null);

        if (isEmpty()) {
            head = temp;
            temp.setNext(head);
        }
        // If list is not empty
        else {
            Node currPointer = head;

            // Search for last element of the list
            while (currPointer.getNext() != head) {
                currPointer = currPointer.getNext();
            }
            // Last element points to new head
            temp.setNext(head);
            head = temp;
            currPointer.setNext(head);
        }
        count++;
        return true;
    }

    public boolean remove(Soldier soldier){
        if (isEmpty()) return false;
        Node prevPointer = null, currPointer = head;

        int index = 0;

        // searching the last list's node
        while ((index != count) && (currPointer.getSoldier() != soldier)){
            prevPointer = currPointer;
            currPointer = currPointer.getNext();
            index++;
        }

        if ((index == count) && (currPointer.getSoldier() != soldier)) { return false; }
        else if ((head == currPointer && count == 1)) { head = null; }

        // Node is the head and there are others
        else if (head == currPointer) {
            Node tempPointer = head;

            // searching for last list's node
            while (tempPointer.getNext() != head) {
                tempPointer = tempPointer.getNext();
            }

            Node temp = head;
            head = head.getNext();

            tempPointer.setNext(head);
            temp.setNext(null);

        // If node is not the head
        } else {
            prevPointer.setNext(currPointer.getNext());
            currPointer.setNext(null);
        }
        count--;
        return true;
    }

    public Node search(String name) {
        // If the list is empty, there's no soldier to be found
        if (isEmpty()) return null;

        // Creating a temporary node to store and go through every element of the list
        Node temp = head;

        boolean found = false;
        int index = 0;

        while (index != count) {
            if (Objects.equals(
                    temp
                            .getSoldier()
                            .getName()
                            .toLowerCase()
                            .replace(" ", ""),
                    name
                            .toLowerCase()
                            .replace(" ", "")
            )) {
                found = true;
                break;
            }
            temp = temp.getNext();
            index++;
        }

        if (!found) return null;
        return temp;
    }

    public void print() {
        Node currPointer;

        if (!isEmpty()) {
            currPointer = head;
            int i = 1;

            // While list is not over
            while (currPointer.getNext() != head) {
                System.out.println("\nSoldier n. " + i);
                System.out.print("Name: " + currPointer.getSoldier().getName() + "\n");
                System.out.print("ID: " + currPointer.getSoldier().getId() + "\n");
                currPointer = currPointer.getNext();
                i++;
            }
            System.out.println("\nSoldier n. " + i);
            System.out.print("Name: " + currPointer.getSoldier().getName() + "\n");
            System.out.print("ID: " + currPointer.getSoldier().getId() + "\n");
        }
    }


    public Node getHead () {
        if (isEmpty()) return null;
        return head;
    }
}
