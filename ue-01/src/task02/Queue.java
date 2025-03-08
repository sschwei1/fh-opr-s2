package task02;

import utils.Node;

public class Queue {
    private Node head;
    private Node tail;

    private int elements;
    private int size;

    /** Initializes the queue instance */
    public void initQueue() {
        this.initQueue(10);
    }

    /** Initializes the queue instance */
    public void initQueue(int size) {
        this.head = null;
        this.tail = null;
        this.elements = 0;
        this.size = size;
    }

    /** Clears all elements from the queue */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.elements = 0;
    }

    /** Enqueues an element at the back of the queue */
    public void enqueue(int val) {
        if(this.elements >= this.size) {
            return;
        }

        this.elements++;

        if(this.head == null) {
            this.head = new Node(val);
            this.tail = this.head;
            return;
        }

        Node node = new Node(val);
        node.setPrev(this.tail);
        this.tail.setNext(node);

        this.tail = node;
    }

    /** Dequeues the element at the front of the queue */
    public int dequeue() {
        if(this.head == null) {
            return Integer.MIN_VALUE;
        }

        this.elements--;

        int value = this.head.getData();
        this.head = this.head.getNext();

        return value;
    }

    /** Returns the front element of the queue without removing it */
    public int peek() {
        if(this.head == null) {
            return Integer.MIN_VALUE;
        }

        return this.head.getData();
    }

    /** Returns the number of elements in the queue */
    public int elements() {
        return this.elements;
    }

    /** Returns the maximum size of the queue */
    public int size() {
        return size;
    }

    /** Prints all elements in the queue */
    public void print() {
        StringBuilder sb = new StringBuilder();

        sb.append("queue: [");
        Node current = this.head;
        while(current != null) {
            sb.append(current.getData());
            if(current.getNext() != null) {
                sb.append(",");
            }
            current = current.getNext();
        }

        sb.append("] (front -> back)");

        // .toString is called implicitly
        System.out.println(sb);
    }
}
