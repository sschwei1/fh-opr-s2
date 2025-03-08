package task02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueTests {
    /*
     * Used to redirect output stream so the print method of the queue can be tested
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private Queue q1;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @BeforeEach
    void setUpQueue() {
        this.q1 = new Queue();
        q1.initQueue();
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testInitQueue() {
        /*
         * Default initialization - size = 10
         */
        Queue test1 = new Queue();
        test1.initQueue();
        assertEquals(0, test1.elements());
        assertEquals(10, test1.size());

        /*
         * Custom initialization - size = 22
         */
        Queue test2 = new Queue();
        test2.initQueue(22);
        assertEquals(0, test2.elements());
        assertEquals(22, test2.size());
    }

    @Test
    public void testClear() {
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.dequeue();

        q1.clear();
        assertEquals(0, q1.elements());

        q1.dequeue();
        q1.clear();
        assertEquals(0, q1.elements());

        q1.enqueue(1);
        q1.clear();
        assertEquals(0, q1.elements());
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

        q1.clear();
        for(int i = 0; i < 100; i++) {
            q1.enqueue(i);
        }

        assertEquals(10, q1.elements());
        assertEquals(0, q1.peek());
    }

    @Test
    public void testDequeue() {
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);

        /*
         * Dequeue returns elements in same order as they were queued
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
        assertEquals(1, q1.peek());

        // Check if peek does not remove the element
        assertEquals(1, q1.peek());

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

        // Elements should not change when de-queuing from empty queue
        q1.dequeue();
        assertEquals(0, q1.elements());
    }

    @Test
    public void testSize() {
        // Default size is 10
        assertEquals(10, q1.size());

        Queue q2 = new Queue();
        q2.initQueue(22);
        assertEquals(22, q2.size());

        Queue q3 = new Queue();
        q3.initQueue(33);
        assertEquals(33, q3.size());
    }

    @Test
    public void testPrint() {
        q1.print();
        assertEquals("queue: [] (front -> back)", outContent.toString().trim());
        outContent.reset();

        q1.enqueue(1);
        q1.enqueue(22);
        q1.enqueue(33);
        q1.print();
        assertEquals("queue: [1,22,33] (front -> back)", outContent.toString().trim());
        outContent.reset();

        q1.clear();
        q1.print();
        assertEquals("queue: [] (front -> back)", outContent.toString().trim());
    }
}
