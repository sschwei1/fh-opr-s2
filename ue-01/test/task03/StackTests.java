package task03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTests {
    private Stack s1;

    @BeforeEach
    public void setUp() {
        s1 = new Stack();
    }

    @Test
    public void testInitialization() {
        /*
         * Default initialization => size = 10
         */
        Stack s1 = new Stack();
        assertEquals(10, s1.size());
        assertEquals(0, s1.elements());

        /*
         * Initialization with size parameter
         */
        Stack s2 = new Stack(20);
        assertEquals(20, s2.size());
        assertEquals(0, s2.elements());

        s2.push(11);
        s2.push(22);
        s2.push(33);

        /*
         * Copy constructor
         */
        Stack s3 = new Stack(s2);
        assertEquals(20, s3.size());
        assertEquals(3, s3.elements());

        /*
         * Check for equality of elements but not the same reference
         */
        assertTrue(s3.equals(s2));
        assertNotSame(s2, s3);
    }

    @Test
    public void testClear() {
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
    public void testPush() {
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

        /*
         * Test pushing another stack with same size
         */
        Stack s2 = new Stack();
        s2.push(s1);

        assertEquals(10, s2.size());
        assertEquals(10, s2.elements());

        for(int i = 0; i < s2.size(); i++) {
            int expected = s2.size() - 1 - i;
            assertEquals(expected, s2.pop());
        }

        assertEquals(0, s2.elements());
        assertEquals(Integer.MIN_VALUE, s2.peek());

        /*
         * Test pushing another stack with different size
         */
        Stack s3 = new Stack(5);
        s3.push(s1);

        assertEquals(5, s3.size());
        assertEquals(5, s3.elements());

        for(int i = 0; i < s3.size(); i++) {
            int expected = s3.size() - 1 - i;
            assertEquals(expected, s3.pop());
        }

        assertEquals(Integer.MIN_VALUE, s3.peek());

        /*
         * Test pushing null stack
         */
        int peekBefore = s1.peek();
        int elementsBefore = s1.elements();
        s1.push(null);

        assertEquals(peekBefore, s1.peek());
        assertEquals(elementsBefore, s1.elements());
    }

    @Test
    public void testPop() {
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
    public void testPeek() {
        s1.push(1);
        assertEquals(1, s1.peek());

        s1.push(2);
        assertEquals(2, s1.peek());

        s1.push(3);

        int currentElements = s1.elements();
        assertEquals(3, s1.peek());

        // Check if peek does not remove the element
        assertEquals(3, s1.peek());
        assertEquals(currentElements, s1.elements());

        // Peek empty stack
        s1.clear();
        assertEquals(Integer.MIN_VALUE, s1.peek());
    }

    @Test
    public void testElements() {
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
    public void testSize() {
        assertEquals(10, s1.size());

        Stack s2 = new Stack(22);
        assertEquals(22, s2.size());

        Stack s3 = new Stack(s2);
        assertEquals(22, s3.size());

        Stack s4 = new Stack(33);
        assertEquals(33, s4.size());
    }

    @Test
    public void testClone() {
        s1.push(1);
        s1.push(2);
        s1.push(3);

        Stack s2 = s1.clone();
        assertEquals(3, s2.elements());
        assertTrue(s2.equals(s1));
        assertNotSame(s1, s2);

        assertEquals(3, s2.pop());
        assertEquals(2, s2.pop());

        assertEquals(1, s2.elements());
        assertEquals(3, s1.elements());
    }

    @Test
    public void testEquals() {
        s1.push(1);
        s1.push(2);
        s1.push(3);

        Stack s2 = new Stack();
        s2.push(1);
        s2.push(2);
        s2.push(3);

        assertTrue(s1.equals(s2));
        assertNotSame(s1, s2);

        /*
         * Test equality with different elements in both sides
         */
        s2.push(4);
        assertFalse(s1.equals(s2));
        assertFalse(s2.equals(s1));

        Stack s3 = new Stack(s2);
        assertTrue(s2.equals(s3));

        s2.pop();
        assertFalse(s2.equals(s3));

        s3.pop();
        assertTrue(s2.equals(s3));

        s3.pop();
        s3.push(5);
        assertFalse(s2.equals(s3));

        /*
         * Test equality with same reference
         */
        Stack s4 = s3;
        assertTrue(s3.equals(s4));
    }

    @Test
    public void testToString() {
        assertEquals("stack: []", s1.toString());

        s1.push(1);
        s1.push(22);
        s1.push(33);
        assertEquals("stack: [1,22,33]", s1.toString());

        Stack s2 = new Stack(5);
        s2.push(55);
        s2.push(66);

        s1.push(s2);
        assertEquals("stack: [1,22,33,55,66]", s1.toString());

        s1.clear();
        assertEquals("stack: []", s1.toString());
    }

    @Test
    public void testSearch() {
        s1.push(1);
        s1.push(2);
        s1.push(3);
        s1.push(4);
        s1.push(5);

        assertTrue(s1.search(5));
        assertTrue(s1.search(4));
        assertTrue(s1.search(3));
        assertTrue(s1.search(2));
        assertTrue(s1.search(1));

        assertFalse(s1.search(6));
        assertFalse(s1.search(0));
        assertFalse(s1.search(-1));
        assertFalse(s1.search(100));

        /*
         * Check if list is unchanged
         */
        assertEquals(5, s1.elements());
        assertEquals(10, s1.size());
        assertEquals(5, s1.peek());
    }
}
