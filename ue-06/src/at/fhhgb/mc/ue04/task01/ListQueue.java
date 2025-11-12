package at.fhhgb.mc.ue04.task01;

import at.fhhgb.mc.shared.InvalidAccessException;
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
     * @throws InvalidAccessException - if the queue is empty
     */
    public int dequeue() throws InvalidAccessException {
        if(isEmpty()) {
            throw new InvalidAccessException("Queue is empty");
        }

        return super.popFront();
    }

    /**
     * Returns the front element of the queue without removing it.
     * @return - the front element
     * @throws InvalidAccessException - if the queue is empty
     */
    public int peek() throws InvalidAccessException {
        if(isEmpty()) {
            throw new InvalidAccessException("Queue is empty");
        }

        return super.peekFront();
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     * @return - true if the queue is empty, false otherwise
     */
    private boolean isEmpty() {
        return super.elements() == 0;
    }
}
