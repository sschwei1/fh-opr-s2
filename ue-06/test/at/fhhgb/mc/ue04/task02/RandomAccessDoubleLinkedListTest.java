package at.fhhgb.mc.ue04.task02;

import at.fhhgb.mc.shared.InvalidAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomAccessDoubleLinkedListTest {
    private RandomAccessDoubleLinkedList list;

    @BeforeEach
    public void setUp() {
        list = new RandomAccessDoubleLinkedList();
    }

    @Test
    public void testCopyConstructor() throws InvalidAccessException {
        list.add(10);
        list.add(20);
        list.add(30);

        RandomAccessDoubleLinkedList copy = new RandomAccessDoubleLinkedList(list);

        // Verify elements are the same
        assertEquals(3, copy.size());
        assertEquals(10, copy.elementAt(0));
        assertEquals(20, copy.elementAt(1));
        assertEquals(30, copy.elementAt(2));

        // Verify it's a deep copy
        copy.removeAt(0);
        assertEquals(3, list.size()); // original shouldn't be affected

        // Test with an empty list
        RandomAccessDoubleLinkedList emptyCopy = new RandomAccessDoubleLinkedList(null);
    }

    @Test
    public void testInsertAt() throws InvalidAccessException {
        // Test normal insertion
        list.insertAt(0, 10);
        assertEquals(1, list.size());
        assertEquals(10, list.elementAt(0));

        // Test insertion at end when index > size
        list.insertAt(5, 20);
        assertEquals(2, list.size());
        assertEquals(20, list.elementAt(1));

        // Test insertion in middle
        list.insertAt(1, 15);
        assertEquals(3, list.size());
        assertEquals(15, list.elementAt(1));

        // Test negative index
        assertThrows(
            InvalidAccessException.class,
            () -> list.insertAt(-1, 5)
        );
        assertEquals(3, list.size()); // should not insert
    }

    @Test
    public void testContains() {
        assertFalse(list.contains(10));

        list.add(10);
        list.add(20);

        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertFalse(list.contains(30));
    }

    @Test
    public void testRemoveAt() throws InvalidAccessException {
        list.add(10);
        list.add(20);
        list.add(30);

        // Test valid removal
        assertTrue(list.removeAt(1));
        assertEquals(2, list.size());
        assertEquals(10, list.elementAt(0));
        assertEquals(30, list.elementAt(1));

        // Test invalid indices
        assertThrows(
            InvalidAccessException.class,
            () -> list.removeAt(-1)
        );
        assertThrows(
            InvalidAccessException.class,
            () -> list.removeAt(2)
        );
    }

    @Test
    public void testRemoveAll() throws InvalidAccessException {
        list.add(10);
        list.add(20);
        list.add(10);
        list.add(30);
        list.add(10);

        // Remove existing value
        assertTrue(list.removeAll(10));
        assertEquals(2, list.size());
        assertEquals(20, list.elementAt(0));
        assertEquals(30, list.elementAt(1));

        // Remove non-existing value
        assertFalse(list.removeAll(40));
    }

    @Test
    public void testElementAt() throws InvalidAccessException {
        list.add(10);
        list.add(20);
        list.add(30);

        // Test valid indices
        assertEquals(10, list.elementAt(0));
        assertEquals(20, list.elementAt(1));
        assertEquals(30, list.elementAt(2));

        // Test invalid indices
        assertThrows(
            InvalidAccessException.class,
            () -> list.elementAt(-1)
        );
        assertThrows(
            InvalidAccessException.class,
            () -> list.elementAt(3)
        );
    }
}
