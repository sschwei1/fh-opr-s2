package task04;

import utils.Node;

public class Queue {
    private Node head;
    private Node tail;

    private int elements;
    private final int size;

    /** Constructor initializes queue with a standard size.*/
    public Queue() {
        this(10);
    }

    /** Constructor initializes queue with the given size.*/
    public Queue(int size) {
        this.head = null;
        this.tail = null;
        this.elements = 0;
        this.size = size;
    }

    /**
     * Copy constructor initializes queue with another queue.
     * This constructor must COPY all elements of the other queue.
     * The elements of the other queue must NOT be changed!
     */
    public Queue(Queue other) {
        this(other.size);
        this.enqueue(other);
    }

    /*
      Deinitializes the object; think about it and comment what to do here.
      In our case it is not needed

      Theoretically it can be used to close open file streams
      or database connections, but in our case we only have a
      Node element which will be cleaned up by the
      garbage collector anyway
     */
    protected void finalize() {

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
        this.tail.setNext(node);

        this.tail = node;
    }

    /**
     * Enqueues all elements from another queue onto this one. If another queue
     * [4,5] is enqueued into this queue [1,2,3], the result is [1,2,3,4,5] and
     * not [1,2,3,5,4]. The elements of the other queue must NOT be changed!
     */
    public void enqueue(Queue other) {
        if(other == null) {
            return;
        }

        Node current = other.head;
        while(current != null) {
            this.enqueue(current.getData());
            current = current.getNext();
        }
    }

    /** Dequeues the element at the front of the queue */
    public int dequeue() {
        if(this.head == null) {
            return Integer.MIN_VALUE;
        }

        this.elements--;

        int value = this.head.getData();
        this.head = this.head.getNext();

        // If the queue is empty, set the tail to null
        if(this.head == null) {
            this.tail = null;
        }

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

    /**
     * Clones this Queue instance and returns an exact COPY.
     */
    public Queue clone() {
        return new Queue(this);
    }

    /**
     * Returns true if the other queue is equal to this one, false otherwise.
     * The contents of the two queues must not be changed!
     */
    public boolean equals(Queue other) {
        if(this == other) {
            return true;
        }

        Node current1 = this.head;
        Node current2 = other.head;

        while(current1 != null && current2 != null) {
            if(current1.getData() != current2.getData()) {
                return false;
            }

            current1 = current1.getNext();
            current2 = current2.getNext();
        }

        return current1 == null && current2 == null;
    }

    /**
     * Returns a string representation of the queue.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node current = this.head;
        while(current != null) {
            sb.append(current.getData());
            if(current.getNext() != null) {
                sb.append(",");
            }
            current = current.getNext();
        }

        return "queue: [" + sb + "]";
    }

    /**
     * Returns true if the element val exists in the stack, false otherwise.
     */
    public boolean search(int val) {
        Node current = this.head;
        while(current != null) {
            if(current.getData() == val) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }
}
