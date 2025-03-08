package utils;

public class Node {
    private final int data;
    private Node next;
    private Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public int getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
