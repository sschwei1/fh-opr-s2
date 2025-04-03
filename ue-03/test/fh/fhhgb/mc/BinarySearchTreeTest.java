package fh.fhhgb.mc;

import at.fhhgb.mc.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {
    private BinarySearchTree bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree();

        // Example taken from lecture documents + added 97 duplicate
        var testData = new int[]{88, 65, 97, 54, 82, 97, 99, 76, 80};
        for (var i : testData) {
            bst.insert(i);
        }

        /*
         * Tree now looks like this:
         * ROOT: 88
         *   L: 65
         *     L: 54
         *     R: 82
         *       L: 76
         *         R: 80
         *   R: 97
         *     R: 99
         */
    }

    @Test
    public void testInsert() {
        // Test initial size (note: duplicate 97 was not inserted)
        assertEquals(8, bst.size());

        // Test inserting new element
        assertTrue(bst.insert(50));
        assertEquals(9, bst.size());
        assertTrue(bst.find(50));

        // Test inserting duplicate element
        assertFalse(bst.insert(65));
        assertEquals(9, bst.size());

        // Test inserting into new tree
        BinarySearchTree newTree = new BinarySearchTree();
        assertEquals(0, newTree.size());
        assertTrue(newTree.insert(100));
        assertEquals(1, newTree.size());
        assertTrue(newTree.find(100));
    }

    @Test
    public void testFind() {
        // Test finding existing elements
        assertTrue(bst.find(88));
        assertTrue(bst.find(65));
        assertTrue(bst.find(54));
        assertTrue(bst.find(80));
        assertTrue(bst.find(99));

        // Test finding non-existing elements
        assertFalse(bst.find(100));
        assertFalse(bst.find(0));
        assertFalse(bst.find(81));

        // Test finding in empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertFalse(emptyTree.find(88));
    }

    @Test
    public void testRemove() {
        // Test removing leaf node
        assertTrue(bst.remove(99));
        assertEquals(7, bst.size());
        assertFalse(bst.find(99));

        // Test removing node with one child
        assertTrue(bst.remove(82));
        assertEquals(6, bst.size());
        assertFalse(bst.find(82));
        assertTrue(bst.find(76)); // child should still exist

        // Test removing node with two children
        assertTrue(bst.remove(65));
        assertEquals(5, bst.size());
        assertFalse(bst.find(65));
        assertTrue(bst.find(54)); // left child should still exist
        assertTrue(bst.find(76)); // right child's subtree should still exist

        // Test removing root
        assertTrue(bst.remove(88));
        assertEquals(4, bst.size());
        assertFalse(bst.find(88));

        // Test removing non-existent element
        assertFalse(bst.remove(999));
        assertEquals(4, bst.size());

        // Test removing from empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertFalse(emptyTree.remove(88));
    }

    @Test
    public void testSize() {
        assertEquals(8, bst.size());

        bst.insert(50);
        assertEquals(9, bst.size());

        bst.remove(65);
        assertEquals(8, bst.size());

        BinarySearchTree newTree = new BinarySearchTree();
        assertEquals(0, newTree.size());

        newTree.insert(1);
        assertEquals(1, newTree.size());

        newTree.remove(1);
        assertEquals(0, newTree.size());
    }

    @Test
    public void testGetParent() {
        // Test parents of various nodes
        assertEquals(88, bst.getParent(65));
        assertEquals(88, bst.getParent(97));
        assertEquals(65, bst.getParent(54));
        assertEquals(65, bst.getParent(82));
        assertEquals(76, bst.getParent(80));
        assertEquals(97, bst.getParent(99));

        // Test root parent (should return MIN_VALUE)
        assertEquals(Integer.MIN_VALUE, bst.getParent(88));

        // Test non-existent node parent
        assertEquals(Integer.MIN_VALUE, bst.getParent(100));

        // Test empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertEquals(Integer.MIN_VALUE, emptyTree.getParent(88));
    }

    @Test
    public void testToArray() {
        // Test ascending order
        int[] ascendingExpected = {54, 65, 76, 80, 82, 88, 97, 99};
        assertArrayEquals(ascendingExpected, bst.toArray(true));

        // Test descending order
        int[] descendingExpected = {99, 97, 88, 82, 80, 76, 65, 54};
        assertArrayEquals(descendingExpected, bst.toArray(false));

        // Test empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertArrayEquals(new int[0], emptyTree.toArray(true));
        assertArrayEquals(new int[0], emptyTree.toArray(false));
    }

    @Test
    public void testToArrayPostOrder() {
        int[] expected = {54, 80, 76, 82, 65, 99, 97, 88};
        assertArrayEquals(expected, bst.toArrayPostOrder());

        // Test empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertArrayEquals(new int[0], emptyTree.toArrayPostOrder());
    }

    @Test
    public void testToArrayPreOrder() {
        int[] expected = {88, 65, 54, 82, 76, 80, 97, 99};
        assertArrayEquals(expected, bst.toArrayPreOrder());

        // Test empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertArrayEquals(new int[0], emptyTree.toArrayPreOrder());
    }

    @Test
    public void testMax() {
        assertEquals(99, bst.max());

        bst.remove(99);
        assertEquals(97, bst.max());

        bst.insert(100);
        assertEquals(100, bst.max());

        // Test empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertEquals(Integer.MIN_VALUE, emptyTree.max());
    }

    @Test
    public void testMin() {
        assertEquals(54, bst.min());

        bst.remove(54);
        assertEquals(65, bst.min());

        bst.insert(50);
        assertEquals(50, bst.min());

        // Test empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertEquals(Integer.MIN_VALUE, emptyTree.min());
    }

    @Test
    public void testToString() {
        String expected1 = """
                ROOT: 88
                 ├──L: 65
                 │  ├──L: 54
                 │  └──R: 82
                 │     └──L: 76
                 │        └──R: 80
                 └──R: 97
                    └──R: 99
                """.trim();

        assertEquals(expected1, bst.toString().trim());

        bst.remove(65);
        String expected2 = """
                ROOT: 88
                 ├──L: 76
                 │  ├──L: 54
                 │  └──R: 82
                 │     └──L: 80
                 └──R: 97
                    └──R: 99
                """.trim();
        assertEquals(expected2, bst.toString().trim());

        bst.remove(80);
        bst.remove(97);
        bst.insert(100);
        String expected3 = """
                ROOT: 88
                 ├──L: 76
                 │  ├──L: 54
                 │  └──R: 82
                 └──R: 99
                    └──R: 100
                """.trim();
        assertEquals(expected3, bst.toString().trim());

        // Test empty tree
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertEquals("ROOT: NULL\n", emptyTree.toString());
    }
}
