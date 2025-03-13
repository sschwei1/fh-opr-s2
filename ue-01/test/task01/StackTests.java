package task01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTests {
    /*
     * Used to redirect output stream so the print method of the stack can be tested
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    private Stack s1;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @BeforeEach
    void setUpQueue() {
        this.s1 = new Stack();
        s1.initStack();
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void initStack() {
        /*
         * Default initialization - size = 10
         */
        Stack test1 = new Stack();
        test1.initStack();
        assertEquals(0, test1.elements());
        assertEquals(10, test1.size());

        /*
         * Custom initialization - size = 22
         */
        Stack test2 = new Stack();
        test2.initStack(22);
        assertEquals(0, test2.elements());
        assertEquals(22, test2.size());
    }

    @Test
    void testClear() {
        s1.push(22);
        s1.push(33);
        s1.push(44);
        s1.pop();

        s1.clear();
        assertEquals(0, s1.elements());
        assertEquals(Integer.MIN_VALUE, s1.peek());

        s1.pop();
        s1.clear();
        assertEquals(0, s1.elements());
        assertEquals(Integer.MIN_VALUE, s1.peek());

        s1.push(22);
        s1.clear();
        assertEquals(0, s1.elements());
        assertEquals(Integer.MIN_VALUE, s1.peek());
    }

    @Test
    void testPush() {
        s1.push(1);
        assertEquals(1, s1.elements());
        assertEquals(1, s1.peek());

        s1.push(25);
        assertEquals(2, s1.elements());
        assertEquals(25, s1.peek());

        s1.push(33);
        assertEquals(3, s1.elements());
        assertEquals(33, s1.peek());

        /*
         * Test pushing more elements than the stack size
         */
        s1.clear();
        for(int i = 0; i < 100; i++) {
            s1.push(i);
        }
        assertEquals(10, s1.elements());
    }

    @Test
    void testPop() {
        s1.push(1);
        s1.push(2);
        s1.push(3);

        /*
         * Values are expected to be returned in reverse order
         */
        assertEquals(3, s1.pop());
        assertEquals(2, s1.pop());
        assertEquals(1, s1.pop());

        /*
         * Pop from empty stack
         */
        assertEquals(Integer.MIN_VALUE, s1.pop());
    }

    @Test
    void testPeek() {
        s1.push(1);
        assertEquals(1, s1.peek());

        s1.push(2);
        assertEquals(2, s1.peek());

        s1.push(3);
        assertEquals(3, s1.peek());

        // Check if peek does not remove the element
        assertEquals(3, s1.peek());

        // Peek empty stack
        s1.clear();
        assertEquals(Integer.MIN_VALUE, s1.peek());
    }

    @Test
    void testElements() {
        s1.push(1);
        assertEquals(1, s1.elements());

        s1.push(2);
        assertEquals(2, s1.elements());

        s1.push(3);
        assertEquals(3, s1.elements());

        s1.peek();
        assertEquals(3, s1.elements());

        s1.pop();
        assertEquals(2, s1.elements());

        s1.pop();
        assertEquals(1, s1.elements());

        s1.pop();
        assertEquals(0, s1.elements());

        // Elements should not change when popping from empty stack
        s1.pop();
        assertEquals(0, s1.elements());
    }

    @Test
    void testSize() {
        assertEquals(10, s1.size());

        Stack s2 = new Stack();
        s2.initStack(22);
        assertEquals(22, s2.size());

        Stack s3 = new Stack();
        s3.initStack(33);
        assertEquals(33, s3.size());
    }

    @Test
    void testPrint() {
        s1.print();
        assertEquals("stack: []", outContent.toString().trim());
        outContent.reset();

        s1.push(1);
        s1.push(22);
        s1.push(33);
        s1.print();
        assertEquals("stack: [1,22,33]", outContent.toString().trim());
        outContent.reset();

        s1.clear();
        s1.print();
        assertEquals("stack: []", outContent.toString().trim());
    }
}
