package task03;

import utils.Node;

public class Stack {
    private Node top;
    private int elements;
    private final int size;

    /** Constructor initializes stack with a standard size. */
    public Stack() {
        this(10);
    }

    /** Constructor initializes stack with the given size. */
    public Stack(int size) {
        this.top = null;
        this.elements = 0;
        this.size = size;
    }

    /*
      Deinitializes the object; think about it and comment what to do here.
      In our case it is not needed

      Theoretically it can be used to close open file accesses
      or database connections, but in our case we only have a
      Node element which will be cleaned up by the
      garbage collector anyways
     */
    // protected void finalize() {
    //    ...
    // }

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

    /**
     * Pushes all elements from another stack onto this one. If another stack
     * [4,5] is pushed onto this stack [1,2,3], the result is [1,2,3,4,5] and
     * not [1,2,3,5,4]. The elements of the other stack must NOT be changed!
     */
    public void push(Stack other) {
        if(other == null) {
            return;
        }

        insertElements(other.top, this);
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

    /**
     * Clones this Stack instance and returns an exact COPY.
     */
    public Stack clone() {
        Stack clone = new Stack(this.size);
        insertElements(this.top, clone);

        return clone;
    }

    /*
     * Inserts all elements of given node into given stack
     *
     * Since we have single linked list, where top element
     * is first, we use a recursive method to handle insertion
     * in the correct order
     */
    private static void insertElements(Node node, Stack stack) {
        if(node == null || stack == null) {
            return;
        }

        insertElements(node.getNext(), stack);
        stack.push(node.getData());
    }

    /**
     * Returns true if the other stack is equal to this one, false otherwise.
     * The contents of the two stacks must not be changed!
     */
    public boolean equals(Stack other) {
        if(this == other) {
            return true;
        }

        Node current = this.top;
        Node otherCurrent = other.top;

        while(current != null && otherCurrent != null) {
            if(current.getData() != otherCurrent.getData()) {
                return false;
            }

            current = current.getNext();
            otherCurrent = otherCurrent.getNext();
        }

        /*
         * If both stacks are null after loop, they are equal
         */
        return current == null && otherCurrent == null;
    }

    /**
     * Returns a string representation of the stack.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node current = this.top;
        while(current != null) {
            sb.append(current.getData());
            if(current.getNext() != null) {
                sb.append(",");
            }
            current = current.getNext();
        }

        sb.reverse();
        return "stack: [" + sb + "]";
    }

    /**
     * Returns true if the element val exists in the stack, false otherwise.
     */
    public boolean search(int val) {
        Node current = this.top;
        while(current != null) {
            if(current.getData() == val) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }
}
