package at.fhhgb.mc.task01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>(Integer.class);

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
        var newTree = new BinarySearchTree<>(Integer.class);
        assertEquals(0, newTree.size());
        assertTrue(newTree.insert(100));
        assertEquals(1, newTree.size());
        assertTrue(newTree.find(100));

        // Test inserting null
        assertFalse(newTree.insert(null));
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
        var emptyTree = new BinarySearchTree<>(Integer.class);
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
        var emptyTree = new BinarySearchTree<>(Integer.class);
        assertFalse(emptyTree.remove(88));
    }

    @Test
    public void testSize() {
        assertEquals(8, bst.size());

        bst.insert(50);
        assertEquals(9, bst.size());

        bst.remove(65);
        assertEquals(8, bst.size());

        var newTree = new BinarySearchTree<>(Integer.class);
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
        assertNull(bst.getParent(88));

        // Test non-existent node parent
        assertNull(bst.getParent(100));

        // Test empty tree
        var emptyTree = new BinarySearchTree<>(Integer.class);
        assertNull(emptyTree.getParent(88));
    }

    @Test
    public void testToArray() {
        // Test ascending order
        Integer[] ascendingExpected = {54, 65, 76, 80, 82, 88, 97, 99};
        assertArrayEquals(ascendingExpected, bst.toArray(true));

        // Test descending order
        Integer[] descendingExpected = {99, 97, 88, 82, 80, 76, 65, 54};
        assertArrayEquals(descendingExpected, bst.toArray(false));

        // Test empty tree
        var emptyTree = new BinarySearchTree<>(Integer.class);
        assertArrayEquals(new Integer[0], emptyTree.toArray(true));
        assertArrayEquals(new Integer[0], emptyTree.toArray(false));
    }

    @Test
    public void testToArrayPostOrder() {
        Integer[] expected = {54, 80, 76, 82, 65, 99, 97, 88};
        assertArrayEquals(expected, bst.toArrayPostOrder());

        // Test empty tree
        var emptyTree = new BinarySearchTree<>(Integer.class);
        assertArrayEquals(new Integer[0], emptyTree.toArrayPostOrder());
    }

    @Test
    public void testToArrayPreOrder() {
        Integer[] expected = {88, 65, 54, 82, 76, 80, 97, 99};
        assertArrayEquals(expected, bst.toArrayPreOrder());

        // Test empty tree
        var emptyTree = new BinarySearchTree<>(Integer.class);
        assertArrayEquals(new Integer[0], emptyTree.toArrayPreOrder());
    }

    @Test
    public void testMax() {
        assertEquals(99, bst.max());

        bst.remove(99);
        assertEquals(97, bst.max());

        bst.insert(100);
        assertEquals(100, bst.max());

        // Test empty tree
        var emptyTree = new BinarySearchTree<>(Integer.class);
        assertNull(emptyTree.max());
    }

    @Test
    public void testMin() {
        assertEquals(54, bst.min());

        bst.remove(54);
        assertEquals(65, bst.min());

        bst.insert(50);
        assertEquals(50, bst.min());

        // Test empty tree
        var emptyTree = new BinarySearchTree<>(Integer.class);
        assertNull(emptyTree.min());
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
        var emptyTree = new BinarySearchTree<>(Integer.class);
        assertEquals("ROOT: NULL\n", emptyTree.toString());


        // Test root only has left child
        emptyTree.insert(50);
        emptyTree.insert(40);
        String expected4 = """
                ROOT: 50
                 └──L: 40
                """.trim();
        assertEquals(expected4, emptyTree.toString().trim());
    }

    @Test
    public void testStringType() {
        var stringTree = new BinarySearchTree<>(String.class);

        // Insert some strings
        assertTrue(stringTree.insert("cherry"));
        assertTrue(stringTree.insert("banana"));
        assertTrue(stringTree.insert("apple"));
        assertTrue(stringTree.insert("grape"));
        assertTrue(stringTree.insert("kiwi"));
        assertFalse(stringTree.insert("cherry")); // duplicate

        // Test size
        assertEquals(5, stringTree.size());

        // Test find
        assertTrue(stringTree.find("banana"));
        assertFalse(stringTree.find("mango"));

        // Test traversal orders
        assertArrayEquals(new String[]{"apple", "banana", "cherry", "grape", "kiwi"}, stringTree.toArray(true));
        assertArrayEquals(new String[]{"kiwi", "grape", "cherry", "banana", "apple"}, stringTree.toArray(false));
        assertArrayEquals(new String[]{"apple", "banana", "kiwi", "grape", "cherry"}, stringTree.toArrayPostOrder());
        assertArrayEquals(new String[]{"cherry", "banana", "apple", "grape", "kiwi"}, stringTree.toArrayPreOrder());

        // Test remove
        assertTrue(stringTree.remove("cherry"));
        assertEquals(4, stringTree.size());
        assertFalse(stringTree.find("cherry"));

        // Test toString
        String expected = """
                ROOT: grape
                 ├──L: banana
                 │  └──L: apple
                 └──R: kiwi
                """.trim();
        assertEquals(expected, stringTree.toString().trim());
    }

    @Test
    public void testDoubleType() {
        var doubleTree = new BinarySearchTree<>(Double.class);

        // Insert some doubles
        assertTrue(doubleTree.insert(3.14));
        assertTrue(doubleTree.insert(2.71));
        assertTrue(doubleTree.insert(1.618));
        assertFalse(doubleTree.insert(3.14)); // duplicate

        // Test size
        assertEquals(3, doubleTree.size());

        // Test find
        assertTrue(doubleTree.find(2.71));
        assertFalse(doubleTree.find(0.577));

        // Test min/max
        assertEquals(1.618, doubleTree.min());
        assertEquals(3.14, doubleTree.max());

        // Test parent
        assertEquals(2.71, doubleTree.getParent(1.618));
        assertEquals(3.14, doubleTree.getParent(2.71));
        assertNull(doubleTree.getParent(3.14)); // root

        // Test remove
        assertTrue(doubleTree.remove(2.71));
        assertEquals(2, doubleTree.size());
        assertArrayEquals(new Double[]{1.618, 3.14}, doubleTree.toArray(true));
    }

    private static class Person implements Comparable<Person> {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return this.name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    @Test
    public void testCustomType() {
        var personTree = new BinarySearchTree<>(Person.class);

        Person dave = new Person("Dave", 40);
        Person eve = new Person("Eve", 45);
        Person alice = new Person("Alice", 25);
        Person charlie = new Person("Charlie", 35);
        Person bob = new Person("Bob", 30);

        // Insert people
        assertTrue(personTree.insert(dave));
        assertTrue(personTree.insert(eve));
        assertTrue(personTree.insert(alice));
        assertTrue(personTree.insert(charlie));
        assertTrue(personTree.insert(bob));
        assertFalse(personTree.insert(new Person("Alice", 40))); // duplicate name

        // Test size
        assertEquals(5, personTree.size());

        // Test find
        assertTrue(personTree.find(new Person("Bob", 0))); // only name matters for comparison
        assertFalse(personTree.find(new Person("David", 0)));

        // Test traversal
        Person[] expectedAscending = {alice, bob, charlie, dave, eve};
        assertArrayEquals(expectedAscending, personTree.toArray(true));

        // Test parent
        assertEquals(alice, personTree.getParent(charlie));
        assertEquals(charlie, personTree.getParent(bob));
        assertNull(personTree.getParent(dave));

        // Test remove
        assertTrue(personTree.remove(bob));
        assertEquals(4, personTree.size());
        assertFalse(personTree.find(new Person("Bob", 0)));

        // Test toString
        String expected = """
                ROOT: Dave(40)
                 ├──L: Alice(25)
                 │  └──R: Charlie(35)
                 └──R: Eve(45)
                """.trim();
        assertEquals(expected, personTree.toString().trim());
    }
}

