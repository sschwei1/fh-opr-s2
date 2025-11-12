package at.fhhgb.mc.task02;

import at.fhhgb.mc.task01.RandomAccessDoubleLinkedList;

import java.util.Comparator;

public class SortableList<T extends Comparable<T>>
    extends RandomAccessDoubleLinkedList<T>
    implements Sortable<T>
{
    public SortableList() {
        super();
    }

    @Override
    public void insertSorted(T value, boolean ascending) {
        var comparator = this.getComparator(ascending);

        int insertionIndex = 0;
        while(insertionIndex < this.size()) {
            T current = this.get(insertionIndex);
            if(comparator.compare(current, value) > 0) {
                break;
            }
            insertionIndex++;
        }

        super.insertAt(insertionIndex, value);
    }

    private Comparator<T> getComparator(boolean ascending) {
        return ascending ?
            Comparator.naturalOrder() :
            Comparator.reverseOrder();
    }

    private SortableList<T> copy() {
        var newList = new SortableList<T>();
        newList.addAll(this);
        return newList;
    }

    @Override
    public Sortable<T> sortAscending() {
        var newList = this.copy();
        newList.sort(newList.getComparator(true));
        return newList;
    }

    @Override
    public Sortable<T> sortDescending() {
        var newList = this.copy();
        newList.sort(newList.getComparator(false));
        return newList;
    }
}
