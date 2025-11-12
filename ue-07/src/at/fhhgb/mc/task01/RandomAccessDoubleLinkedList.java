package at.fhhgb.mc.task01;

import java.util.ArrayList;

public class RandomAccessDoubleLinkedList<T extends Comparable<T>> extends ArrayList<T> {
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
    public RandomAccessDoubleLinkedList(RandomAccessDoubleLinkedList<T> other) {
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
     * @throws IndexOutOfBoundsException - if the index is negative
     */
    public void insertAt (int index, T val) throws IndexOutOfBoundsException {
        if(index < 0) {
            throw new IndexOutOfBoundsException();
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
    public boolean contains (T val) {
        return super.contains(val);
    }

    /**
     * Removes the element at the given index. False if
     * index > list’s size.
     *
     * @param index - the index of the element to be removed
     * @return - true if the element was removed, false otherwise
     * @throws IndexOutOfBoundsException - if the index is out of bounds
     */
    public boolean removeAt (int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
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
    public boolean removeAll (T val) {
        return super.removeIf(e -> e.compareTo(val) == 0);
    }

    /**
     * Returns the integer value at the given index. If index > list’s
     * size, Integer.MIN_VALUE is returned.
     *
     * @param index - the index of the element to be returned
     * @return - the value at the given index
     * @throws IndexOutOfBoundsException - if the index is out of bounds
     */
    public T elementAt(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }

        return super.get(index);
    }
}
