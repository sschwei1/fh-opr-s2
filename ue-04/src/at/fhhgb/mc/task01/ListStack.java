package at.fhhgb.mc.task01;

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
     */
    public int pop() {
        return super.popBack();
    }

    /**
     * Returns the top element of the stack without removing it.
     * @return - the top element
     */
    public int peek() {
        return super.peekBack();
    }
}
