package at.fhhgb.mc.task02;

public interface Sortable<T extends Comparable<T>> {
    // Insert one element into the Sortable and do so either in ascending
    // or descending fashion
    void insertSorted(T value, boolean ascending);

    // Create a new Sortable which is guaranteed to be sorted ascending
    Sortable<T> sortAscending();

    // Create a new Sortable which is guaranteed to be sorted descending
    Sortable<T> sortDescending();
}
