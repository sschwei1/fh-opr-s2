package at.fhhgb.mc.task01;

import java.util.NoSuchElementException;

public class DoubleLinkedList<T extends Comparable<T>> {
    public class Node {
        private Node next;
        private Node previous;
        private final T value;

        public Node() {
            this.value = null;
        }

        public Node(T value) {
            this.value = value;
        }
    }

    /** Pointer to the first and last element of the list */
    private Node head;
    private Node tail;

    private int size;

    /** Constructor initializes an empty list.*/
    public DoubleLinkedList() {
        this.size = 0;
    }

    /**
     * Copy constructor initializes list with another list.
     * This constructor must COPY all elements of the other list.
     * The elements of the other list must NOT be changed!
     */
    public DoubleLinkedList(DoubleLinkedList<T> other) {
        this();

        Node current = other.head;
        while(current != null) {
            this.append(current.value);
            current = current.next;
        }
    }

    /** Clears all elements from the linked list */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /** Adds an element at the front of the linked list.*/
    public void prepend(T val) {
        Node newNode = new Node(val);

        this.size++;

        if(this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }

        this.head.previous = newNode;
        newNode.next = this.head;
        this.head = newNode;
    }

    /**
     * Adds all elements from another list at the front of this linked list.
     */
    public void prepend(DoubleLinkedList<T> other) {
        Node current = other.tail;
        while(current != null) {
            this.prepend(current.value);
            current = current.previous;
        }
    }

    /** Adds an element at the back of the linked list. */
    public void append(T val) {
        Node newNode = new Node(val);

        this.size++;

        if(this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }

        this.tail.next = newNode;
        newNode.previous = this.tail;
        this.tail = newNode;
    }

    /**
     * Adds all elements from another list at the back of this linked list.
     */
    public void append(DoubleLinkedList<T> other) {
        Node current = other.head;
        while(current != null) {
            this.append(current.value);
            current = current.next;
        }
    }

    /**
     * Returns the element at position ‘index’.
     */
    public T get(int index) {
        if(index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = this.head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    /**
     * Removes and returns the front element of the linked list.
     */
    public T popFront() {
        if(this.head == null) {
            throw new NoSuchElementException();
        }

        this.size--;

        T val = this.head.value;
        this.head = this.head.next;

        if(this.head == null) {
            this.tail = null;
        } else {
            this.head.previous = null;
        }

        return val;
    }

    /**
     * Returns the front element of the list without removing it.
     * Returns Integer.MIN_VALUE if empty
     */
    public T peekFront() {
        if(this.head == null) {
            throw new NoSuchElementException();
        }

        return this.head.value;
    }

    /**
     * Removes and returns the element from the back of the linked list.
     * Returns Integer.MIN_VALUE if empty
     */
    public T popBack() {
        if(this.tail == null) {
            throw new NoSuchElementException();
        }

        this.size--;

        T val = this.tail.value;
        this.tail = this.tail.previous;

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
    public T peekBack() {
        if(this.tail == null) {
            throw new NoSuchElementException();
        }

        return this.tail.value;
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
            temp = current.previous;
            current.previous = current.next;
            current.next = temp;

            // Technically is .next, next and prev were just swapped
            current = current.previous;
        }

        /*
         * If temp is not null, then the list had at least one element.
         * Temp will contain the last element before the tail.
         * Head needs to be set to old tail, which is prev element of temp.
         */
        if(temp != null) {
            this.head = temp.previous;
        }
    }

    /** Clones this DoubleLinkedList instance and returns an exact COPY.*/
    public DoubleLinkedList<T> clone() {
        return new DoubleLinkedList<>(this);
    }

    /**
     * Returns true if the other list is equal to this one, false otherwise.
     * The contents of the two lists must not be changed!
     */
    public boolean equals(DoubleLinkedList<T> other) {
        // Check for same reference
        if(this == other) {
            return true;
        }

        // Check for null
        if(other == null) {
            return false;
        }

        // Check for list size
        if(this.size != other.size) {
            return false;
        }

        Node currentThis = this.head;
        Node currentOther = other.head;

        /*
         * We don't need to check for currentOther != null,
         * because we already know both lists are the same size
         */
        while(currentThis != null) {
            if(currentThis.value.compareTo(currentOther.value) != 0) {
                return false;
            }

            currentThis = currentThis.next;
            currentOther = currentOther.next;
        }

        return true;
    }

    /**
     * Returns a string representation of the list.
     * Example: List of size 5: 1 -> 2 -> 3 -> 4 -> 5
     * Implementation: List of size 5: 1 > 2 > 3 > 4 > 5
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of size ");
        sb.append(this.size);

        sb.append(": [");

        Node current = this.head;
        while(current != null) {
            sb.append(current.value);
            current = current.next;

            if(current != null) {
                sb.append(" > ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    /** Returns true if the element val exists in the list, false otherwise.*/
    public boolean search(T val) {
        Node current = this.head;
        while(current != null) {
            if(current.value.compareTo(val) == 0) {
                return true;
            }

            current = current.next;
        }

        return false;
    }
}
