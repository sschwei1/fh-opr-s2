package at.fhhgb.mc.task01;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListStackTest {
    private ListStack stack;

    @BeforeEach
    public void setUp() {
        stack = new ListStack();
    }

    @Test
    public void testCopyConstructor() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        ListStack copy = new ListStack(stack);

        // Verify elements are the same
        assertEquals(3, copy.elements());
        assertEquals(30, copy.pop());
        assertEquals(20, copy.pop());
        assertEquals(10, copy.pop());

        // Verify it's a deep copy
        assertEquals(3, stack.elements()); // original shouldn't be affected

        // Test with an empty stack
        ListStack emptyCopy = new ListStack(null);
    }

    @Test
    public void testPush() {
        stack.push(10);
        assertEquals(1, stack.elements());
        assertEquals(10, stack.peek());

        stack.push(20);
        assertEquals(2, stack.elements());
        assertEquals(20, stack.peek());
    }

    @Test
    public void testPop() {
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.pop());
        assertEquals(1, stack.elements());

        assertEquals(10, stack.pop());
        assertEquals(0, stack.elements());
    }

    @Test
    public void testPeek() {
        stack.push(10);
        assertEquals(10, stack.peek());
        assertEquals(1, stack.elements()); // size shouldn't change

        stack.push(20);
        assertEquals(20, stack.peek());
    }
}
