package task01;

import utils.Node;

public class Stack {
    private Node top;
    private int elements;
    private int size;

    /** Initializes the stack instance */
    public void initStack() {
        this.initStack(10);
    }

    /** Initializes the stack instance */
    public void initStack(int size) {
        this.top = null;
        this.elements = 0;
        this.size = size;
    }

    /** Clears all elements from the stack */
    public void clear() {
        this.top = null;
        this.elements = 0;
    }

    /** Pushes an element onto the stack */
    public void push(int val) {
        if(this.elements >= this.size) {
            return;
        }

        this.elements++;
        Node node = new Node(val);
        node.setNext(this.top);
        this.top = node;
    }

    /** Returns the top element of the stack and removes it */
    public int pop() {
        if(this.top == null) {
            return Integer.MIN_VALUE;
        }

        this.elements--;
        int value = this.top.getData();
        this.top = this.top.getNext();
        return value;
    }

    /** Returns the top element of the stack without removing it */
    public int peek() {
        if(this.top == null) {
            return Integer.MIN_VALUE;
        }

        return this.top.getData();
    }

    /** Returns the number of elements in the stack */
    public int elements() {
        return this.elements;
    }

    /** Returns the maximum size of the stack */
    public int size() {
        return this.size;
    }

    /** Prints all elements in the stack */
    public void print() {
        StringBuilder sb = new StringBuilder();

        Node current = this.top;
        while(current != null) {
            sb.append(current.getData());
            if(current.getNext() != null) {
                sb.append(",");
            }
            current = current.getNext();
        }

        /*
         * Since top contains last element of our stack,
         * we need to reverse order of the stack here
         * so values are sorted [bottom -> top]
         */
        sb.reverse();
        System.out.println("stack: [" + sb + "]");
    }
}
