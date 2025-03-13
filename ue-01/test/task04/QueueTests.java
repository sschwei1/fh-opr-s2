package task04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTests {
    private Queue q1;

    @BeforeEach
    public void setUp() {
        q1 = new Queue();
    }

    @Test
    public void testInitialization() {
        /*
         * Default initialization => size = 10
         */
        Queue q1 = new Queue();
        assertEquals(10, q1.size());
        assertEquals(0, q1.elements());

        /*
         * Initialization with size parameter
         */
        Queue q2 = new Queue(20);
        assertEquals(20, q2.size());
        assertEquals(0, q2.elements());

        q2.enqueue(11);
        q2.enqueue(22);
        q2.enqueue(33);

        /*
         * Copy constructor
         */
        Queue q3 = new Queue(q2);
        assertEquals(20, q3.size());
        assertEquals(3, q3.elements());

        /*
         * Check for equality of elements but not the same reference
         */
        assertTrue(q2.equals(q3));
        assertNotSame(q2, q3);
    }

    @Test
    public void testClear() {
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.dequeue();

        q1.clear();
        assertEquals(0, q1.elements());
        assertEquals(Integer.MIN_VALUE, q1.peek());

        q1.dequeue();
        q1.clear();
        assertEquals(0, q1.elements());
        assertEquals(Integer.MIN_VALUE, q1.peek());

        q1.enqueue(1);
        q1.clear();
        assertEquals(0, q1.elements());
        assertEquals(Integer.MIN_VALUE, q1.peek());
    }

    @Test
    public void testEnqueue() {
        q1.enqueue(1);
        assertEquals(1, q1.elements());
        assertEquals(1, q1.peek());

        q1.enqueue(2);
        assertEquals(2, q1.elements());
        assertEquals(1, q1.peek());

        q1.enqueue(3);
        assertEquals(3, q1.elements());
        assertEquals(1, q1.peek());

        /*
         * Test enqueueing more elements than the queue size
         */
        q1.clear();
        for(int i = 0; i < 15; i++) {
            q1.enqueue(i);
        }
        assertEquals(10, q1.elements());

        /*
         * Test enqueueing another queue
         */
        Queue q2 = new Queue();
        q2.enqueue(q1);

        assertEquals(10, q2.elements());
        assertEquals(0, q2.peek());

        for(int i = 0; i < q2.size(); i++) {
            assertEquals(i, q2.dequeue());
        }

        assertEquals(0, q2.elements());
        assertEquals(Integer.MIN_VALUE, q2.peek());

        /*
         * Test enqueueing another queue with different size
         */
        Queue q3 = new Queue(5);
        q3.enqueue(q1);

        assertEquals(5, q3.size());
        assertEquals(5, q3.elements());

        for(int i = 0; i < q3.size(); i++) {
            assertEquals(i, q3.dequeue());
        }

        assertEquals(Integer.MIN_VALUE, q3.peek());

        /*
         * Test enqueueing null
         */
        int peekBefore = q1.peek();
        int elementsBefore = q1.elements();
        q1.enqueue(null);

        assertEquals(peekBefore, q1.peek());
        assertEquals(elementsBefore, q1.elements());
    }

    @Test
    public void testDequeue() {
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);

        /*
         * Dequeue returns elements in same order as they were enqueued
         */
        assertEquals(1, q1.dequeue());
        assertEquals(2, q1.dequeue());
        assertEquals(3, q1.dequeue());

        // Dequeue from empty queue
        assertEquals(Integer.MIN_VALUE, q1.dequeue());
    }

    @Test
    public void testPeek() {
        q1.enqueue(1);
        assertEquals(1, q1.peek());

        q1.enqueue(2);
        assertEquals(1, q1.peek());

        q1.enqueue(3);

        int currentElements = q1.elements();
        assertEquals(1, q1.peek());

        // Check if peek does not remove any elements
        assertEquals(1, q1.peek());
        assertEquals(currentElements, q1.elements());

        // Peek empty queue
        q1.clear();
        assertEquals(Integer.MIN_VALUE, q1.peek());
    }

    @Test
    public void testElements() {
        q1.enqueue(1);
        assertEquals(1, q1.elements());

        q1.enqueue(2);
        assertEquals(2, q1.elements());

        q1.enqueue(3);
        assertEquals(3, q1.elements());

        q1.peek();
        assertEquals(3, q1.elements());

        q1.dequeue();
        assertEquals(2, q1.elements());

        q1.dequeue();
        assertEquals(1, q1.elements());

        q1.dequeue();
        assertEquals(0, q1.elements());

        // Elements should not change when dequeuing from empty queue
        q1.dequeue();
        assertEquals(0, q1.elements());
    }

    @Test
    public void testSize() {
        assertEquals(10, q1.size());

        Queue q2 = new Queue(22);
        assertEquals(22, q2.size());

        Queue q3 = new Queue(q2);
        assertEquals(22, q3.size());

        Queue q4 = new Queue(33);
        assertEquals(33, q4.size());
    }

    @Test
    public void testClone() {
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);

        Queue q2 = q1.clone();
        assertEquals(3, q2.elements());
        assertTrue(q2.equals(q1));
        assertNotSame(q1, q2);

        assertEquals(1, q2.dequeue());
        assertEquals(2, q2.dequeue());

        assertEquals(1, q2.elements());
        assertEquals(3, q1.elements());
    }

    @Test
    public void testEquals() {
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);

        Queue q2 = new Queue();
        q2.enqueue(1);
        q2.enqueue(2);
        q2.enqueue(3);

        assertTrue(q1.equals(q2));
        assertNotSame(q1, q2);

        /*
         * Test equality with different elements in both sides
         */
        q2.enqueue(4);
        assertFalse(q1.equals(q2));
        assertFalse(q2.equals(q1));

        Queue q3 = new Queue(q2);
        assertTrue(q2.equals(q3));

        q2.dequeue();
        assertFalse(q2.equals(q3));

        q3.dequeue();
        assertTrue(q2.equals(q3));

        q3.dequeue();
        q3.enqueue(5);
        assertFalse(q2.equals(q3));

        /*
         * Test equality with same reference
         */
        Queue q4 = q3;
        assertTrue(q3.equals(q4));
    }

    @Test
    public void testToString() {
        assertEquals("queue: []", q1.toString());

        q1.enqueue(1);
        q1.enqueue(22);
        q1.enqueue(33);
        assertEquals("queue: [1,22,33]", q1.toString());

        Queue q2 = new Queue(5);
        q2.enqueue(55);
        q2.enqueue(66);

        q1.enqueue(q2);
        assertEquals("queue: [1,22,33,55,66]", q1.toString());

        q1.clear();
        assertEquals("queue: []", q1.toString());
    }

    @Test
    public void testSearch() {
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.enqueue(4);
        q1.enqueue(5);

        assertTrue(q1.search(1));
        assertTrue(q1.search(2));
        assertTrue(q1.search(3));
        assertTrue(q1.search(4));
        assertTrue(q1.search(5));

        assertFalse(q1.search(6));
        assertFalse(q1.search(0));
        assertFalse(q1.search(-1));
        assertFalse(q1.search(100));

        /*
         * Check if list is unchanged
         */
        assertEquals(5, q1.elements());
        assertEquals(10, q1.size());
        assertEquals(1, q1.peek());
    }
}
