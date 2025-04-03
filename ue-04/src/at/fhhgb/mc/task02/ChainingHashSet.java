package at.fhhgb.mc.task02;

public class ChainingHashSet {
    /**
     * Array which stores overflow lists that are indexed by the hash
     * code of their elements.
     */
    private final RandomAccessDoubleLinkedList[] array;

    /**
     * Initializes an empty hashtable with the given number of overflow ts.
     * @param indexSize - Number of overflow lists.
     */
    public ChainingHashSet(int indexSize) {
        if(indexSize == 0) {
            /*
             * Probably should throw IllegalArgumentException however
             * Professor said we can assume that indexSize is always > 0
             *
             * I still wanted to handle the error case in some way, so
             * I just set indexSize to 1
             */
            indexSize = 1;
        }

        indexSize = Math.abs(indexSize);
        this.array = new RandomAccessDoubleLinkedList[indexSize];
        for(int i = 0; i < indexSize; i++) {
            this.array[i] = new RandomAccessDoubleLinkedList();
        }
    }

    /**
     * Hash function which returns the hash code of the given value.
     *
     * @param val - Value to be hashed.
     * @return - Hash code of the given value.
     */
    private int hash(int val) {
        return val % this.array.length;
    }

    /**
     * Returns the overflow list that is indexed by the hash code of the
     *
     * @param val - Value to be hashed.
     * @return - Overflow list that is indexed by the hash code of the given
     */
    private RandomAccessDoubleLinkedList getList(int val) {
        return this.array[this.hash(val)];
    }

    /**
     * Inserts a new element val into the hashtable (hashcode = val %
     * array.length), if it did not exist in the table before. Returns true
     * if a new element was inserted, false if it already existed.
     *
     * @param val - Value to be inserted.
     * @return - True if a new element was inserted, false if it already existed.
     */
    public boolean insert (int val) {
        var list = this.getList(val);

        if(list.contains(val)) {
            return false;
        }

        list.add(val);
        return true;
    }

    /**
     * Returns true if the given value is already stored in the
     * hashtable, false otherwise.
     *
     * @param val - Value to be checked.
     * @return - True if the given value is already stored in the hashtable, false otherwise.
     */
    public boolean contains (int val) {
        var list = this.getList(val);
        return list.contains(val);
    }

    /**
     * Removes the given element from the hashtable if it exists.
     * Returns true if an element was removed, false if no such
     * element existed.
     *
     * @param val - Value to be removed.
     * @return - True if an element was removed, false if no such element existed.
     */
    public boolean remove (int val) {
        var list = this.getList(val);
        return list.removeAll(val);
    }

    /**
     * Counts the amount of values, which are stored in the same
     * overflow list
     *
     * @param hashVal - Hash value of the element.
     * @return - Amount of values in the same overflow list.
     */
    public int getOverflowCount(int hashVal) {
        if(hashVal < 0 || hashVal >= this.array.length) {
            return 0;
        }

        var list = this.array[hashVal];
        return list.size();
    }

    /**
     * Counts the amount of elements in the hashtable by adding the
     * amount of elements in each overflow list.
     *
     * @return - Amount of elements in the hashtable.
     */
    public int elements() {
        int sum = 0;

        for(var list : this.array) {
            sum += list.size();
        }

        return sum;
    }
}
