package at.fhhgb.mc.task01;

import mc.opr.dll.MyDoubleLinkedList;

public class ListQueue extends MyDoubleLinkedList {
    /**
     * Initializes an empty queue.
     */
    public ListQueue() {
        super();
    }

    /**
     * Copy constructor which initializes the queue with another queue.
     * This constructor must COPY all elements of the other queue.
     *
     * @param other - the queue to copy
     */
    public ListQueue(ListQueue other) {
        this();

        if(other == null) {
            return;
        }

        for(int i = 0; i < other.elements(); i++) {
            this.enqueue(other.peekElementAt(i));
        }
    }

    /**
     * Enqueues an element at the back of the queue.
     * @param val - the value to enqueue
     */
    public void enqueue(int val) {
        super.pushBack(val);
    }

    /**
     * Dequeues the element at the front of the queue.
     * @return - the dequeued value
     */
    public int dequeue() {
        return super.popFront();
    }

    /**
     * Returns the front element of the queue without removing it.
     * @return - the front element
     */
    public int peek() {
        return super.peekFront();
    }
}
