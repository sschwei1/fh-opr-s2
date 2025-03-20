package at.fhhgb.mc;

public class BinarySearchTree {
    /** Inner class for the binary tree node. **/
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
     */
    public boolean insert(int elem) {
        if(this.root != null) {
            return this.insert(elem, this.root);
        }

        this.root = new BinaryTreeNode(elem);
        this.size++;
        return true;
    }

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
     */
    public boolean find(int key) {
        return this.find(key, this.root);
    }

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
     */
    public boolean remove(int key) {
        // TODO
        return false;
    }

    /** Returns the number of elements stored in the tree. */
    public int size() {
        return this.size;
    }

    /**
     * Returns the parent element of the given key.
     * Integer.MIN_VALUE if no parent can be found.
     */
    public int getParent(int key) {
        return this.getParent(key, this.root, null);
    }

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
     */
    public int[] toArray (boolean ascending) {
        int[] array = new int[this.size];
        this.inOrderTraversal(this.root, array, 0, ascending);
        return array;
    }

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

    /** Returns the elements of the tree (postorder traversal). */
    public int[] toArrayPostOrder() {
        var array = new int[this.size];
        this.postOrderTraversal(this.root, array, 0);
        return array;
    }

    protected int postOrderTraversal(BinaryTreeNode curr, int[] array, int index) {
        if(curr == null) {
            return index;
        }

        index = this.postOrderTraversal(curr.left, array, index);
        index = this.postOrderTraversal(curr.right, array, index);
        array[index++] = curr.data;

        return index;
    }

    /** Returns the elements of the tree (preorder traversal). */
    public int[] toArrayPreOrder() {
        var array = new int[this.size];
        this.preOrderTraversal(this.root, array, 0);
        return new int[0];
    }

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
     */
    public int max() {
        if(this.root == null) {
            return Integer.MIN_VALUE;
        }

        return this.max(this.root);
    }

    protected int max(BinaryTreeNode curr) {
        if(curr.right == null) {
            return curr.data;
        }

        return this.max(curr.right);
    }

    /**
     * Returns smallest number stored in the tree.
     * Integer.MIN_VALUE if no smallest element can be found
     */
    public int min() {
        if(this.root == null) {
            return Integer.MIN_VALUE;
        }

        return this.min(this.root);
    }

    protected int min(BinaryTreeNode curr) {
        if(curr.left == null) {
            return curr.data;
        }

        return this.min(curr.left);
    }

    /** Represents the tree in a human readable form. */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return this.toString(sb, "", this.root, true);
    }

    protected String toString(StringBuilder sb, String prefix, BinaryTreeNode curr, boolean isLeft) {
        // TODO
        return "";
    }
}