package at.fhhgb.mc.task01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest {
    DoubleLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new DoubleLinkedList<>();
    }

    @Test
    public void testInitialize() {
        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();

        assertTrue(list.equals(list2));

        list.append(1);
        list.append(2);
        list.append(3);
        list.prepend(4);
        list.prepend(5);
        list.prepend(6);

        list2 = new DoubleLinkedList<>(list);
        assertTrue(list.equals(list2));
    }

    @Test
    public void testClear() {
        list.clear();
        assertEquals(0, list.size());

        list.append(1);
        list.append(2);
        list.append(3);
        list.clear();
        assertEquals(0, list.size());

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testPrepend() {
        list.prepend(1);
        assertEquals(1, list.size());
        assertEquals(1, list.peekFront());
        assertEquals(1, list.peekBack());

        list.prepend(2);
        assertEquals(2, list.size());
        assertEquals(2, list.peekFront());
        assertEquals(1, list.peekBack());

        list.prepend(3);
        assertEquals(3, list.size());
        assertEquals(3, list.peekFront());
        assertEquals(1, list.peekBack());

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.prepend(4);
        list2.prepend(5);
        list2.prepend(6);

        list.prepend(list2);
        assertEquals(6, list.size());
        assertEquals(6, list.peekFront());
        assertEquals(1, list.peekBack());

        assertEquals(6, list.popFront());
        assertEquals(5, list.popFront());
        assertEquals(4, list.popFront());
        assertEquals(3, list.popFront());
        assertEquals(2, list.popFront());
        assertEquals(1, list.popFront());
    }

    @Test
    public void testAppend() {
        list.append(1);
        assertEquals(1, list.size());
        assertEquals(1, list.peekFront());
        assertEquals(1, list.peekBack());

        list.append(2);
        assertEquals(2, list.size());
        assertEquals(1, list.peekFront());
        assertEquals(2, list.peekBack());

        list.append(3);
        assertEquals(3, list.size());
        assertEquals(1, list.peekFront());
        assertEquals(3, list.peekBack());

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.append(4);
        list2.append(5);
        list2.append(6);

        list.append(list2);
        assertEquals(6, list.size());
        assertEquals(1, list.peekFront());
        assertEquals(6, list.peekBack());

        assertEquals(1, list.popFront());
        assertEquals(2, list.popFront());
        assertEquals(3, list.popFront());
        assertEquals(4, list.popFront());
        assertEquals(5, list.popFront());
        assertEquals(6, list.popFront());
    }

    @Test
    public void testGet() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });

        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(3);
        });
    }

    @Test
    public void testPopFront() {
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(1, list.popFront());
        assertEquals(2, list.popFront());
        assertEquals(3, list.popFront());
        assertThrows(NoSuchElementException.class, () -> {
            list.popFront();
        });
        list.prepend(1);
        list.prepend(2);
        list.prepend(3);

        assertEquals(3, list.popFront());
        assertEquals(2, list.popFront());
        assertEquals(1, list.popFront());
        assertThrows(NoSuchElementException.class, () -> {
            list.popFront();
        });
    }

    @Test
    public void testPeekFront() {
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(1, list.peekFront());
        list.popFront();
        assertEquals(2, list.peekFront());
        list.popFront();
        assertEquals(3, list.peekFront());
        list.popFront();
        assertThrows(NoSuchElementException.class, () -> {
            list.peekFront();
        });

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);

        assertEquals(3, list.peekFront());
        list.popFront();
        assertEquals(2, list.peekFront());
        list.popFront();
        assertEquals(1, list.peekFront());
        list.popFront();
        assertThrows(NoSuchElementException.class, () -> {
            list.peekFront();
        });    }

    @Test
    public void testPopBack() {
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(3, list.popBack());
        assertEquals(2, list.popBack());
        assertEquals(1, list.popBack());

        assertThrows(NoSuchElementException.class, () -> {
            list.popBack();
        });

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);

        assertEquals(1, list.popBack());
        assertEquals(2, list.popBack());
        assertEquals(3, list.popBack());
        assertThrows(NoSuchElementException.class, () -> {
            list.popBack();
        });
    }

    @Test
    public void testPeekBack() {
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(3, list.peekBack());
        list.popBack();
        assertEquals(2, list.peekBack());
        list.popBack();
        assertEquals(1, list.peekBack());
        list.popBack();
        assertThrows(NoSuchElementException.class, () -> {
            list.peekBack();
        });
        list.prepend(1);
        list.prepend(2);
        list.prepend(3);

        assertEquals(1, list.peekBack());
        list.popBack();
        assertEquals(2, list.peekBack());
        list.popBack();
        assertEquals(3, list.peekBack());
        list.popBack();
        assertThrows(NoSuchElementException.class, () -> {
            list.peekBack();
        });
    }

    @Test
    public void testSize() {

        assertEquals(0, list.size());

        list.append(1);
        assertEquals(1, list.size());

        list.prepend(2);
        assertEquals(2, list.size());

        list.popFront();
        assertEquals(1, list.size());

        list.popBack();
        assertEquals(0, list.size());

        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();

        list2.append(1);
        list2.append(2);
        list2.append(3);

        list.append(list2);
        assertEquals(3, list.size());
        list.append(list2);
        assertEquals(6, list.size());
        list.append(list2);
        assertEquals(9, list.size());
    }

    @Test
    public void testReverse() {
        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();

        /*
         * Create 2 identical lists and reverse one of them.
         */
        int testSize = 10;
        for(int i = 0; i < testSize; i++) {
            list.append(i);
            list2.append(i);
        }

        list.reverse();

        for(int i = 0; i < testSize; i++) {
            assertEquals(list.get(i), list2.get(testSize - i - 1));
        }

        list2.reverse();
        assertTrue(list.equals(list2));
    }

    @Test
    public void testClone() {
        list.append(1);
        list.append(2);
        list.append(3);
        list.prepend(4);
        list.prepend(5);
        list.prepend(6);

        DoubleLinkedList<Integer> list2 = list.clone();

        assertTrue(list.equals(list2));
        list2.popFront();
        assertFalse(list.equals(list2));
        list.popFront();
        assertTrue(list.equals(list2));

        list = new DoubleLinkedList<>();
        list2 = list.clone();

        assertTrue(list.equals(list2));
    }

    @Test
    public void testEquals() {
        list.append(1);
        list.append(2);
        list.append(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.append(1);
        list2.append(2);
        list2.append(3);

        assertTrue(list.equals(list2));

        list2.popFront();
        assertFalse(list.equals(list2));

        DoubleLinkedList<Integer> list3 = list.clone();
        assertTrue(list.equals(list3));

        DoubleLinkedList<Integer> list4 = new DoubleLinkedList<>(list);
        assertTrue(list.equals(list4));

        DoubleLinkedList<Integer> list5 = list.clone();
        list5.popFront();
        list5.append(7);
        assertFalse(list.equals(list5));

        assertTrue(list.equals(list));
        assertFalse(list.equals(null));
    }

    @Test
    public void testToString() {
        assertEquals("List of size 0: []", list.toString());

        list.append(1);
        list.append(2);
        list.append(3);
        assertEquals("List of size 3: [1 > 2 > 3]", list.toString());

        list.prepend(4);
        list.prepend(5);
        list.prepend(6);
        assertEquals("List of size 6: [6 > 5 > 4 > 1 > 2 > 3]", list.toString());

        list.reverse();
        assertEquals("List of size 6: [3 > 2 > 1 > 4 > 5 > 6]", list.toString());

        list.clear();
        assertEquals("List of size 0: []", list.toString());

        list = new DoubleLinkedList<>();
        DoubleLinkedList<Integer> list2 = list.clone();

        list.reverse();
        assertTrue(list.equals(list2));
    }

    @Test
    public void testSearch() {
        list.append(1);
        list.append(2);
        list.append(3);

        assertTrue(list.search(1));
        assertTrue(list.search(2));
        assertTrue(list.search(3));
        assertFalse(list.search(4));

        list.prepend(4);
        list.prepend(5);
        list.prepend(6);

        assertTrue(list.search(1));
        assertTrue(list.search(2));
        assertTrue(list.search(3));
        assertTrue(list.search(4));
        assertTrue(list.search(5));
        assertTrue(list.search(6));
        assertFalse(list.search(7));
    }
}
