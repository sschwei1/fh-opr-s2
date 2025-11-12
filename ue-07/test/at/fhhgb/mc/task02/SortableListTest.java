package at.fhhgb.mc.task02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortableListTest {
    private SortableList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new SortableList<>();
    }

    @Test
    public void testInsertSorted() {
        list.insertSorted(5, true);
        assertArrayEquals(new Integer[]{5}, list.toArray());

        list.insertSorted(3, true);
        assertArrayEquals(new Integer[]{3,5}, list.toArray());

        list.insertSorted(7, true);
        assertArrayEquals(new Integer[]{3,5,7}, list.toArray());

        list.insertSorted(4, true);
        assertArrayEquals(new Integer[]{3,4,5,7}, list.toArray());

        list.clear();
        list.insertSorted(6, false);
        assertArrayEquals(new Integer[]{6}, list.toArray());

        list.insertSorted(8, false);
        assertArrayEquals(new Integer[]{8,6}, list.toArray());

        list.insertSorted(2, false);
        assertArrayEquals(new Integer[]{8,6,2}, list.toArray());

        // special cases
        list.clear();
        list.add(42);
        list.add(17);
        list.insertSorted(5, true);
        assertArrayEquals(new Integer[]{5,42,17}, list.toArray());
    }

    @Test
    public void testSortAscending() {
        list.add(5);
        list.add(3);
        list.add(7);
        list.add(4);

        assertArrayEquals(new Integer[]{5,3,7,4}, list.toArray());

        var sorted = (SortableList<Integer>)list.sortAscending();
        assertArrayEquals(new Integer[]{3,4,5,7}, sorted.toArray());
    }

    @Test
    public void testSortDescending() {
        list.add(5);
        list.add(3);
        list.add(7);
        list.add(4);

        assertArrayEquals(new Integer[]{5,3,7,4}, list.toArray());

        var sorted = (SortableList<Integer>)list.sortDescending();
        assertArrayEquals(new Integer[]{7,5,4,3}, sorted.toArray());
    }
}
