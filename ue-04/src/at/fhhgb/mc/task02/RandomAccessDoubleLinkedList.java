package at.fhhgb.mc.task02;

import java.util.ArrayList;

public class RandomAccessDoubleLinkedList extends ArrayList<Integer> {
    /**
     * Initializes an empty list.
     */
    public RandomAccessDoubleLinkedList() {
        super();
    }

    /**
     * Copy constructor which initializes the list with another list.
     * This constructor must COPY all elements of the other list.
     *
     * @param other - the list to be copied
     */
    public RandomAccessDoubleLinkedList(RandomAccessDoubleLinkedList other) {
        this();

        if(other == null) {
            return;
        }

        super.addAll(other);
    }

    /**
     * Inserts a new element with value val at the given index. If the
     * index is larger than the current size, the element is added at the
     * last position in the list. Should index be < 0, then do nothing.
     *
     * @param index - the index at which the element should be inserted
     * @param val - the value to be inserted
     */
    public void insertAt (int index, int val) {
        if(index < 0) {
            return;
        }

        if(index >= size()) {
            super.add(val);
            return;
        }

        super.add(index, val);
    }

    /**
     * Returns true if an element with the given value exists, false
     * otherwise. However, true is returned upon the first occurrence of
     * val.
     *
     * @param val - the value to be searched for
     * @return - true if the value exists, false otherwise
     */
    public boolean contains (int val) {
        return super.contains(val);
    }

    /**
     * Removes the element at the given index. False if
     * index > list’s size.
     *
     * @param index - the index of the element to be removed
     * @return - true if the element was removed, false otherwise
     */
    public boolean removeAt (int index) {
        if(index < 0 || index >= this.size()) {
            return false;
        }

        super.remove(index);
        return true;
    }

    /**
     * Removes all elements with the given value. False if
     * val was not found.
     *
     * @param val - the value to be removed
     * @return - true if an element was removed, false otherwise
     */
    public boolean removeAll (int val) {
        return super.removeIf(e -> e == val);
    }

    /**
     * Returns the integer value at the given index. If index > list’s
     * size, Integer.MIN_VALUE is returned.
     *
     * @param index - the index of the element to be returned
     * @return - the value at the given index
     */
    public int elementAt(int index) {
        if(index < 0 || index >= this.size()) {
            return Integer.MIN_VALUE;
        }

        return super.get(index);
    }
}
