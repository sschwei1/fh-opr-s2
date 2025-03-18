package at.fhhgb.mc.task01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest {
    DoubleLinkedList list;

    @BeforeEach
    public void setUp() {
        list = new DoubleLinkedList();
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
    }

    @Test
    public void testGet() {
        assertEquals(Integer.MIN_VALUE, list.get(0));
        assertEquals(Integer.MIN_VALUE, list.get(-1));
        assertEquals(Integer.MIN_VALUE, list.get(1));

        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(Integer.MIN_VALUE, list.get(3));
    }

    @Test
    public void testPopFront() {
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(1, list.popFront());
        assertEquals(2, list.popFront());
        assertEquals(3, list.popFront());
        assertEquals(Integer.MIN_VALUE, list.popFront());

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);

        assertEquals(3, list.popFront());
        assertEquals(2, list.popFront());
        assertEquals(1, list.popFront());
        assertEquals(Integer.MIN_VALUE, list.popFront());
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
        assertEquals(Integer.MIN_VALUE, list.peekFront());

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);

        assertEquals(3, list.peekFront());
        list.popFront();
        assertEquals(2, list.peekFront());
        list.popFront();
        assertEquals(1, list.peekFront());
        list.popFront();
        assertEquals(Integer.MIN_VALUE, list.peekFront());
    }

    @Test
    public void testPopBack() {
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(3, list.popBack());
        assertEquals(2, list.popBack());
        assertEquals(1, list.popBack());
        assertEquals(Integer.MIN_VALUE, list.popBack());

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);

        assertEquals(1, list.popBack());
        assertEquals(2, list.popBack());
        assertEquals(3, list.popBack());
        assertEquals(Integer.MIN_VALUE, list.popBack());
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
        assertEquals(Integer.MIN_VALUE, list.peekBack());

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);

        assertEquals(1, list.peekBack());
        list.popBack();
        assertEquals(2, list.peekBack());
        list.popBack();
        assertEquals(3, list.peekBack());
        list.popBack();
        assertEquals(Integer.MIN_VALUE, list.peekBack());
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
    }

    @Test
    public void testReverse() {
        DoubleLinkedList list2 = new DoubleLinkedList();

        /*
         * Create 2 identical lists and reverse one of them.
         */
        for(int i = 0; i < 10; i++) {
            list.append(i);
            list2.append(i);
        }

        list.reverse();

        for(int i = 0; i < 10; i++) {
            assertEquals(list.popFront(), list2.popBack());
        }

        assertEquals(Integer.MIN_VALUE, list.popFront());
        assertEquals(Integer.MIN_VALUE, list2.popFront());

        list = new DoubleLinkedList();
        list.reverse();

        assertEquals(Integer.MIN_VALUE, list.popFront());
    }
}
