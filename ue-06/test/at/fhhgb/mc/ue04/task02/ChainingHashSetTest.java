package at.fhhgb.mc.ue04.task02;

import at.fhhgb.mc.shared.ValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChainingHashSetTest {
    private ChainingHashSet set;

    @BeforeEach
    public void setUp() {
        set = new ChainingHashSet(10);
    }

    @Test
    public void testInitialization() {
        assertNotNull(set);
        assertEquals(0, set.elements());

        // Initialize with invalid size
        set = new ChainingHashSet(0);
        assertNotNull(set);
    }

    @Test
    public void testInsert() throws ValueException {
        set.insert(10);
        assertEquals(1, set.elements());

        // Test duplicate insertion
        assertThrows(
            ValueException.class,
            () -> set.insert(10)
        );
        assertEquals(1, set.elements());

        // Test multiple insertions
        set.insert(20);
        set.insert(30);
        assertEquals(3, set.elements());

        // Test insert with collision
        assertThrows(
            ValueException.class,
            () -> set.insert(10)
        );
        assertEquals(3, set.elements());
    }

    @Test
    public void testContains() throws ValueException {
        assertFalse(set.contains(10));

        set.insert(10);
        assertTrue(set.contains(10));
        assertFalse(set.contains(20));

        set.insert(20);
        assertTrue(set.contains(20));
    }

    @Test
    public void testRemove() throws ValueException {
        set.insert(10);
        set.insert(20);

        assertTrue(set.remove(10));
        assertEquals(1, set.elements());
        assertFalse(set.contains(10));

        // Test removing non-existent element
        assertFalse(set.remove(30));

        // Test removing remaining element
        assertTrue(set.remove(20));
        assertEquals(0, set.elements());
    }

    @Test
    public void testGetOverflowCount() throws ValueException {
        assertEquals(0, set.getOverflowCount(0));
        assertEquals(0, set.getOverflowCount(1));
        assertEquals(0, set.getOverflowCount(2));

        // Insert elements that would go to different buckets
        set.insert(1);
        set.insert(2);
        assertEquals(0, set.getOverflowCount(0));
        assertEquals(1, set.getOverflowCount(1));
        assertEquals(1, set.getOverflowCount(2));

        // Force collisions (assuming size 10 table)
        set.insert(11); //
        set.insert(21);

        set.insert(-105);

        assertEquals(0, set.getOverflowCount(0));
        assertEquals(3, set.getOverflowCount(1));
        assertEquals(1, set.getOverflowCount(2));

        // Test with invalid hash values
        assertThrows(
            ValueException.class,
            () -> set.getOverflowCount(-1)
        );
        assertThrows(
            ValueException.class,
            () -> set.getOverflowCount(10)
        );
    }

    @Test
    public void testElements() throws ValueException {
        assertEquals(0, set.elements());

        set.insert(10);
        assertEquals(1, set.elements());

        set.insert(20);
        set.insert(30);
        assertEquals(3, set.elements());

        set.remove(20);
        assertEquals(2, set.elements());
    }
}
