package at.fhhgb.mc.task01;

import at.fhhgb.mc.Node;

public class DoubleLinkedList {
    /** Pointer to the first and last element of the list */
    private Node head;
    private Node tail;

    private int size;

    /** Constructor initializes an empty list.*/
    public DoubleLinkedList() {
        this.clear();
    }

    /** Clears all elements from the linked list */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /** Adds an element at the front of the linked list.*/
    public void prepend(int val) {
        Node newNode = new Node();
        newNode.val = val;

        this.size++;

        if(this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }

        this.head.prev = newNode;
        newNode.next = this.head;
        this.head = newNode;
    }

    /** Adds an element at the back of the linked list. */
    public void append(int val) {
        Node newNode = new Node();
        newNode.val = val;

        this.size++;

        if(this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }

        this.tail.next = newNode;
        newNode.prev = this.tail;
        this.tail = newNode;
    }

    /**
     * Returns the element at position ‘index’.
     * Returns Integer.MIN_VALUE if ‘index’ is invalid.
     */
    public int get(int index) {
        if(index < 0 || index >= this.size) {
            return Integer.MIN_VALUE;
        }

        Node current = this.head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.val;
    }

    /**
     * Removes and returns the front element of the linked list.
     * Returns Integer.MIN_VALUE if empty
     */
    public int popFront() {
        if(this.head == null) {
            return Integer.MIN_VALUE;
        }

        this.size--;

        int val = this.head.val;
        this.head = this.head.next;

        if(this.head == null) {
            this.tail = null;
        } else {
            this.head.prev = null;
        }

        return val;
    }

    /**
     * Returns the front element of the list without removing it.
     * Returns Integer.MIN_VALUE if empty
     */
    public int peekFront() {
        if(this.head == null) {
            return Integer.MIN_VALUE;
        }

        return this.head.val;
    }

    /**
     * Removes and returns the element from the back of the linked list.
     * Returns Integer.MIN_VALUE if empty
     */
    public int popBack() {
        if(this.tail == null) {
            return Integer.MIN_VALUE;
        }

        this.size--;

        int val = this.tail.val;
        this.tail = this.tail.prev;

        if(this.tail == null) {
            this.head = null;
        } else {
            this.tail.next = null;
        }

        return val;
    }

    /**
     * Returns the element at the back of the list without removing it.
     * Returns Integer.MIN_VALUE if empty
     */
    public int peekBack() {
        if(this.tail == null) {
            return Integer.MIN_VALUE;
        }

        return this.tail.val;
    }

    /** Returns the number of elements in the double linked list */
    public int size() {
        return this.size;
    }

    /**
     * Reverses the order of all elements in the list.
     * “He who is first, shall be last!”
     */
    public void reverse() {
        Node current = this.head;
        Node temp = null;

        this.tail = this.head;

        while(current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;

            // Technically is .next, next and prev were just swapped
            current = current.prev;
        }

        /*
         * If temp is not null, then the list had at least one element.
         * Temp will contain the last element before the tail.
         * Head needs to be set to old tail, which is prev element of temp.
         */
        if(temp != null) {
            this.head = temp.prev;
        }
    }
}
