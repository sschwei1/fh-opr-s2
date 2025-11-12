package at.fhhgb.mc.ue04.task01;

import at.fhhgb.mc.shared.InvalidAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListQueueTest {
    private ListQueue queue;

    @BeforeEach
    public void setUp() {
        queue = new ListQueue();
    }

    @Test
    public void testCopyConstructor() throws InvalidAccessException {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        ListQueue copy = new ListQueue(queue);

        // Verify elements are the same
        assertEquals(3, copy.elements());
        assertEquals(10, copy.dequeue());
        assertEquals(20, copy.dequeue());
        assertEquals(30, copy.dequeue());

        // Verify it's a deep copy
        assertEquals(3, queue.elements()); // original shouldn't be affected

        // Test with an empty queue
        ListQueue emptyCopy = new ListQueue(null);
        assertEquals(0, emptyCopy.elements());
    }

    @Test
    public void testEnqueue() throws InvalidAccessException {
        queue.enqueue(10);
        assertEquals(1, queue.elements());
        assertEquals(10, queue.peek());

        queue.enqueue(20);
        assertEquals(2, queue.elements());
        assertEquals(10, queue.peek());
    }

    @Test
    public void testDequeue() throws InvalidAccessException {
        queue.enqueue(10);
        queue.enqueue(20);

        assertEquals(10, queue.dequeue());
        assertEquals(1, queue.elements());

        assertEquals(20, queue.dequeue());
        assertEquals(0, queue.elements());

        // Test dequeue on empty queue
        queue.clear();
        assertThrows(
            InvalidAccessException.class,
            () -> queue.dequeue()
        );
    }

    @Test
    public void testPeek() throws InvalidAccessException {
        queue.enqueue(10);
        queue.enqueue(20);

        assertEquals(10, queue.peek());
        assertEquals(2, queue.elements());

        queue.dequeue();
        assertEquals(20, queue.peek());
        assertEquals(1, queue.elements());

        // Test peek on empty queue
        queue.clear();
        assertThrows(
            InvalidAccessException.class,
            () -> queue.peek()
        );
    }
}
