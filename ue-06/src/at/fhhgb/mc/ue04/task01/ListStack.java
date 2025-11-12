package at.fhhgb.mc.ue04.task01;

import at.fhhgb.mc.shared.InvalidAccessException;
import mc.opr.dll.MyDoubleLinkedList;

public class ListStack extends MyDoubleLinkedList {
    /**
     * Initializes an empty stack.
     */
    public ListStack() {
        super();
    }

    /**
     * Copy constructor which initializes the stack with another stack.
     * This constructor must COPY all elements of the other stack.
     *
     * @param other - the stack to copy
     */
    public ListStack(ListStack other) {
        this();

        if(other == null) {
            return;
        }

        for(int i = 0; i < other.elements(); i++) {
            this.push(other.peekElementAt(i));
        }
    }

    /**
     * Pushes an element onto the stack.
     * @param val - the value to push
     */
    public void push(int val) {
        super.pushBack(val);
    }

    /**
     * Returns the top element of the stack and removes it.
     * @return - the popped value
     * @throws InvalidAccessException - if the stack is empty
     */
    public int pop() throws InvalidAccessException {
        if(isEmpty()) {
            throw new InvalidAccessException("Queue is empty");
        }

        return super.popBack();
    }

    /**
     * Returns the top element of the stack without removing it.
     * @return - the top element
     * @throws InvalidAccessException - if the stack is empty
     */
    public int peek() throws InvalidAccessException{
        if(isEmpty()) {
            throw new InvalidAccessException("Queue is empty");
        }

        return super.peekBack();
    }

    /**
     * Checks if the stack is empty.
     * @return - true if the stack is empty, false otherwise
     */
    private boolean isEmpty() {
        return super.elements() == 0;
    }
}
