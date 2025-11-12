package at.fhhgb.mc.ue05.task01;

import at.fhhgb.mc.shared.ValueException;

import java.lang.reflect.Array;

public class BinarySearchTree<T extends Comparable<T>> {
    /**
     * Inner class for the binary tree node.
     */
    protected class BinaryTreeNode {
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public T data;

        public BinaryTreeNode (T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /** Root node of the tree. **/
    protected BinaryTreeNode root;

    /** Number of elements stored in the tree. */
    protected int size;

    private final Class<T> clazz;

    public BinarySearchTree(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Inserts the given element. Duplicate elements are not allowed.
     * Returns true if insertion was successful, false otherwise.
     *
     * @param elem - element to insert
     * @return - true if insertion was successful, false otherwise
     * @throws ValueException - if the element is null
     */
    public boolean insert(T elem) throws ValueException {
        if(elem == null) {
            throw new ValueException(null);
        }

        if(this.root != null) {
            return this.insert(elem, this.root);
        }

        this.root = new BinaryTreeNode(elem);
        this.size++;
        return true;
    }

    /**
     * Helper method to insert the given element.
     *
     * @param elem - element to insert
     * @param curr - current node
     * @return - true if insertion was successful, false otherwise
     */
    private boolean insert(T elem, BinaryTreeNode curr) {
        if(curr.data.compareTo(elem) == 0) {
            return false;
        }

        boolean insertRight = curr.data.compareTo(elem) < 0;
        BinaryTreeNode targetChild = insertRight ? curr.right : curr.left;

        if(targetChild != null) {
            return this.insert(elem, targetChild);
        }

        if(insertRight) {
            curr.right = new BinaryTreeNode(elem);
        } else {
            curr.left = new BinaryTreeNode(elem);
        }

        this.size++;
        return true;
    }

    /**
     * Searches for the (first) element with the given key.
     * Returns true if it could be found, false otherwise.
     *
     * @param key - key to search for
     * @return - true if the key could be found, false otherwise
     * @throws ValueException - if the key is null
     */
    public boolean find(T key) throws ValueException {
        if(key == null) {
            throw new ValueException(null);
        }

        return this.find(key, this.root);
    }

    /**
     * Helper method to search for the (first) element with the given key.
     *
     * @param key - key to search for
     * @param curr - current node
     * @return - true if the key could be found, false otherwise
     */
    private boolean find(T key, BinaryTreeNode curr) {
        if(curr == null) {
            return false;
        }

        if(curr.data.compareTo(key) == 0) {
            return true;
        }

        if(curr.data.compareTo(key) < 0) {
            return this.find(key, curr.right);
        }

        return this.find(key, curr.left);
    }

    /**
     * Removes the element with the given key.
     * Returns true if the key could be found (and removed), false otherwise.
     *
     * @param key - key to remove
     * @return - true if the key could be found (and removed), false otherwise
     * @throws ValueException - if the key is null
     */
    public boolean remove(T key) throws ValueException {
        if(key == null) {
            throw new ValueException(null);
        }

        int originalSize = this.size;
        this.root = this.remove(key, this.root);
        return originalSize != this.size;
    }

    /**
     * Helper method to remove the element with the given key.
     *
     * @param key - key to remove
     * @param curr - current node
     * @return - new node after removal
     */
    private BinaryTreeNode remove(T key, BinaryTreeNode curr) {
        if(curr == null) {
            return null;
        }

        if(curr.data.compareTo(key) < 0) {
            curr.right = this.remove(key, curr.right);
            return curr;
        }

        if(curr.data.compareTo(key) > 0) {
            curr.left = this.remove(key, curr.left);
            return curr;
        }

        /*
         * Current node value matches the key
         * If either child is null, we decrease the size
         *
         * We only want to decrease size a single time, in case 2 children are set
         * another remove will be called for a leaf node, which would decrease the size again,
         * this check makes sure the size is only reduced a single time.
         */
        if(curr.left == null || curr.right == null) {
            this.size--;
        }

        // Case 1: No children (or only right child)
        if(curr.left == null) {
            return curr.right;
        }

        // Case 2: Only left child exists
        if(curr.right == null) {
            return curr.left;
        }

        // Case 3: Two children
        curr.data = this.min(curr.right);
        curr.right = this.remove(curr.data, curr.right);

        return curr;
    }

    /**
     * Returns the number of elements stored in the tree.
     * @return - number of elements stored in the tree
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the parent element of the given key.
     * Integer.MIN_VALUE if no parent can be found.
     *
     * @param key - key of which we want to find the parent
     * @return - parent value of the given key
     */
    public T getParent(T key) {
        return this.getParent(key, this.root, null);
    }

    /**
     * Helper method to find the parent of the given key.
     *
     * @param key - key of which we want to find the parent
     * @param curr - current node
     * @param parent - parent of the current node
     * @return - parent value of the given key
     */
    private T getParent(T key, BinaryTreeNode curr, BinaryTreeNode parent) {
        if(curr == null) {
            return null;
        }

        if(curr.data.compareTo(key) == 0) {
            return parent == null ?
                    null :
                    parent.data;
        }

        if(curr.data.compareTo(key) < 0) {
            return this.getParent(key, curr.right, curr);
        }

        return this.getParent(key, curr.left, curr);
    }

    /**
     * Generates an empty array of type T, the array is prefilled
     * with null values.
     *
     * @return - empty array of the same type as the root node
     */
    @SuppressWarnings("unchecked")
    public T[] generateEmptyArray() {
        return (T[]) Array.newInstance(this.clazz, this.size);
    }

    /**
     * Returns the elements of the tree in ascending (inorder traversal)
     * or descending (reverse inorder traversal) order.
     *
     * @param ascending - true for ascending order, false for descending order
     * @return - array of elements in the specified order
     */
    public T[] toArray (boolean ascending) {
        T[] array = this.generateEmptyArray();
        this.inOrderTraversal(this.root, array, 0, ascending);
        return array;
    }

    /**
     * Helper method to traverse the tree in inorder traversal.
     *
     * @param curr - current node
     * @param array - array to store the elements
     * @param index - current index in the array
     * @param ascending - true for ascending order, false for descending order
     * @return - new index in the array
     */
    private int inOrderTraversal(BinaryTreeNode curr, T[] array, int index, boolean ascending) {
        if(curr == null) {
            return index;
        }

        BinaryTreeNode goFirst = ascending ? curr.left : curr.right;
        BinaryTreeNode goSecond = ascending ? curr.right : curr.left;

        index = this.inOrderTraversal(goFirst, array, index, ascending);
        array[index++] = curr.data;
        return this.inOrderTraversal(goSecond, array, index, ascending);
    }

    /**
     * Returns the elements of the tree (postorder traversal).
     * @return - array of elements in postorder traversal
     */
    public T[] toArrayPostOrder() {
        T[] array = this.generateEmptyArray();
        this.postOrderTraversal(this.root, array, 0);
        return array;
    }

    /**
     * Helper method to traverse the tree in postorder traversal.
     * @param curr - current node
     * @param array - array to store the elements
     * @param index - current index in the array
     * @return - new index in the array
     */
    private int postOrderTraversal(BinaryTreeNode curr, T[] array, int index) {
        if(curr == null) {
            return index;
        }

        index = this.postOrderTraversal(curr.left, array, index);
        index = this.postOrderTraversal(curr.right, array, index);
        array[index++] = curr.data;

        return index;
    }

    /**
     * Returns the elements of the tree (preorder traversal).
     * @return - array of elements in preorder traversal
     */
    public T[] toArrayPreOrder() {
        T[] array = this.generateEmptyArray();
        this.preOrderTraversal(this.root, array, 0);
        return array;
    }

    /**
     * Helper method to traverse the tree in preorder traversal.
     *
     * @param curr - current node
     * @param array - array to store the elements
     * @param index - current index in the array
     * @return - new index in the array
     */
    private int preOrderTraversal(BinaryTreeNode curr, T[] array, int index) {
        if(curr == null) {
            return index;
        }

        array[index++] = curr.data;
        index = this.preOrderTraversal(curr.left, array, index);
        return this.preOrderTraversal(curr.right, array, index);
    }

    /**
     * Returns largest number stored in the tree.
     * Integer.MIN_VALUE if no largest element can be found
     *
     * @return - largest number stored in the tree
     */
    public T max() {
        return this.max(this.root);
    }

    /**
     * Returns largest number stored in the given node.
     * Integer.MIN_VALUE if no largest element can be found
     *
     * @param curr - node of which the max value should be found
     * @return - largest number stored in the tree
     */
    private T max(BinaryTreeNode curr) {
        if(curr == null) {
            return null;
        }

        while(curr.right != null) {
            curr = curr.right;
        }

        return curr.data;
    }

    /**
     * Returns smallest number stored in the tree.
     * Integer.MIN_VALUE if no smallest element can be found
     *
     * @return - smallest number stored in the tree
     */
    public T min() {
        return this.min(this.root);
    }

    /**
     * Returns smallest number stored in the given node.
     * Integer.MIN_VALUE if no smallest element can be found
     *
     * @param curr - node of which the min value should be found
     * @return - smallest number stored in the tree
     */
    private T min(BinaryTreeNode curr) {
        if(curr == null) {
            return null;
        }

        while(curr.left != null) {
            curr = curr.left;
        }

        return curr.data;
    }

    private enum NodeState {
        LEFT, RIGHT, ONLY_LEFT
    }

    /**
     * Represents the tree in a human readable form.
     * @return - string representation of the tree
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String nodeValue = this.root == null ? "NULL" : this.root.data.toString();
        sb.append("ROOT: ")
                .append(nodeValue)
                .append(System.lineSeparator());

        if(this.root == null) {
            return sb.toString();
        }

        NodeState leftNodeState = this.root.right == null ? NodeState.ONLY_LEFT : NodeState.LEFT;
        this.toString(sb, " ", this.root.left, leftNodeState);
        this.toString(sb, " ", this.root.right, NodeState.RIGHT);

        return sb.toString();
    }

    /**
     * Helper method to represent the tree in a human readable form.
     * @param sb - string builder to store the tree representation
     * @param prefix - prefix for the current node
     * @param curr - current node
     * @param state - state of the current node (left, left_only, right)
     */
    private void toString(StringBuilder sb, String prefix, BinaryTreeNode curr, NodeState state) {
        if(curr == null) {
            return;
        }

        /*
         * true if the current node is the left child and
         * the right child is not null
         */
        boolean printLeftPath = state == NodeState.LEFT;
        String nodePath = printLeftPath ? "├──" : "└──";

        String nodeName = switch(state) {
            case LEFT, ONLY_LEFT -> "L: ";
            case RIGHT -> "R: ";
        };

        sb.append(prefix)
                .append(nodePath)
                .append(nodeName)
                .append(curr.data.toString())
                .append(System.lineSeparator());

        String newPrefix = prefix + (printLeftPath ? "│  " : "   ");

        NodeState leftNodeState = curr.right == null ? NodeState.ONLY_LEFT : NodeState.LEFT;
        this.toString(sb, newPrefix, curr.left, leftNodeState);
        this.toString(sb, newPrefix, curr.right, NodeState.RIGHT);
    }
}