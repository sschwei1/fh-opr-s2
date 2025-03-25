package at.fhhgb.mc;

public class BinarySearchTree {
    /**
     * Inner class for the binary tree node.
     */
    protected static class BinaryTreeNode {
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public int data;
        public BinaryTreeNode (int elem) {
            data = elem;
            left = null;
            right = null;
        }
    }

    /** Root node of the tree. **/
    protected BinaryTreeNode root;

    /** Number of elements stored in the tree. */
    protected int size;

    /**
     * Inserts the given element. Duplicate elements are not allowed.
     * Returns true if insertion was successful, false otherwise.
     *
     * @param elem - element to insert
     * @return - true if insertion was successful, false otherwise
     */
    public boolean insert(int elem) {
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
    protected boolean insert(int elem, BinaryTreeNode curr) {
        if(curr.data == elem) {
            return false;
        }

        boolean insertRight = elem > curr.data;
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
     */
    public boolean find(int key) {
        return this.find(key, this.root);
    }

    /**
     * Helper method to search for the (first) element with the given key.
     *
     * @param key - key to search for
     * @param curr - current node
     * @return - true if the key could be found, false otherwise
     */
    protected boolean find(int key, BinaryTreeNode curr) {
        if(curr == null) {
            return false;
        }

        if(curr.data == key) {
            return true;
        }

        if(key > curr.data) {
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
     */
    public boolean remove(int key) {
        this.root = this.remove(key, this.root);
        return false;
    }

    /**
     * Helper method to remove the element with the given key.
     *
     * @param key - key to remove
     * @param curr - current node
     * @return - new node after removal
     */
    protected BinaryTreeNode remove(int key, BinaryTreeNode curr) {
        if(curr == null) {
            return null;
        }

        if(key > curr.data) {
            curr.right = this.remove(key, curr.right);
            return curr;
        }

        if(key < curr.data) {
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

        // Case 2: Only right child exists
        if(curr.right == null) {
            return curr.left;
        }

        // Case 3: Two children
        curr.data = this.min(curr.right);
        curr.right = this.remove(curr.data, curr.right);

        return curr;
    }

    /**
     * Returns the replacement value for the given node.
     * This method needs to be called with "curr.right" as parameter and
     * "curr.right != null" must be true.
     *
     * @param curr - right child of which we need to find the replacement
     * @return - the replacement value
     */
    protected int findAndRemoveFollowupNode(BinaryTreeNode curr) {
        int replacement = curr.data;

        while(curr.left != null) {
            replacement = curr.left.data;
            curr = curr.left;
        }

        return replacement;
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
    public int getParent(int key) {
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
    protected int getParent(int key, BinaryTreeNode curr, BinaryTreeNode parent) {
        if(curr == null) {
            return Integer.MIN_VALUE;
        }

        if(curr.data == key) {
            return parent == null ?
                    Integer.MIN_VALUE :
                    parent.data;
        }

        if(key > curr.data) {
            return this.getParent(key, curr.right, curr);
        }

        return this.getParent(key, curr.left, curr);
    }

    /**
     * Returns the elements of the tree in ascending (inorder traversal)
     * or descending (reverse inorder traversal) order.
     *
     * @param ascending - true for ascending order, false for descending order
     * @return - array of elements in the specified order
     */
    public int[] toArray (boolean ascending) {
        int[] array = new int[this.size];
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
    protected int inOrderTraversal(BinaryTreeNode curr, int[] array, int index, boolean ascending) {
        if(curr == null) {
            return index;
        }

        var goFirst = ascending ? curr.left : curr.right;
        var goSecond = ascending ? curr.right : curr.left;

        index = this.inOrderTraversal(goFirst, array, index, ascending);
        array[index++] = curr.data;
        return this.inOrderTraversal(goSecond, array, index, ascending);
    }

    /**
     * Returns the elements of the tree (postorder traversal).
     * @return - array of elements in postorder traversal
     */
    public int[] toArrayPostOrder() {
        var array = new int[this.size];
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
    protected int postOrderTraversal(BinaryTreeNode curr, int[] array, int index) {
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
    public int[] toArrayPreOrder() {
        var array = new int[this.size];
        this.preOrderTraversal(this.root, array, 0);
        return new int[0];
    }

    /**
     * Helper method to traverse the tree in preorder traversal.
     *
     * @param curr - current node
     * @param array - array to store the elements
     * @param index - current index in the array
     * @return - new index in the array
     */
    protected int preOrderTraversal(BinaryTreeNode curr, int[] array, int index) {
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
    public int max() {
        return this.max(this.root);
    }

    /**
     * Returns largest number stored in the given node.
     * Integer.MIN_VALUE if no largest element can be found
     *
     * @param curr - node of which the max value should be found
     * @return - largest number stored in the tree
     */
    protected int max(BinaryTreeNode curr) {
        if(curr == null) {
            return Integer.MIN_VALUE;
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
    public int min() {
        return this.min(this.root);
    }

    /**
     * Returns smallest number stored in the given node.
     * Integer.MIN_VALUE if no smallest element can be found
     *
     * @param curr - node of which the min value should be found
     * @return - smallest number stored in the tree
     */
    protected int min(BinaryTreeNode curr) {
        if(curr == null) {
            return Integer.MIN_VALUE;
        }

        while(curr.left != null) {
            curr = curr.left;
        }

        return curr.data;
    }

    /**
     * Represents the tree in a human readable form.
     * @return - string representation of the tree
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toString(sb, "", this.root, false);
        return sb.toString();
    }

    /**
     * Helper method to represent the tree in a human readable form.
     * @param sb - string builder to store the tree representation
     * @param prefix - prefix for the current node
     * @param curr - current node
     * @param isLeft - true if the current node is the left child
     */
    private void toString(StringBuilder sb, String prefix, BinaryTreeNode curr, boolean isLeft) {
        String nodePath = isLeft ? "├──" : "└──";

        sb.append(prefix)
            .append(nodePath)
            .append(isLeft ? "L: " : "R: ")
            .append(curr == null ? "NULL" : curr.data)
            .append(System.lineSeparator());

        if(curr == null || curr.left == null && curr.right == null) {
            return;
        }

        var newPrefix = prefix + (isLeft ? "│  " : "   ");

        this.toString(sb, newPrefix, curr.left, true);
        this.toString(sb, newPrefix, curr.right, false);
    }
}