package fh.fhhgb.mc;

import at.fhhgb.mc.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BinarySearchTreeTest {
    private BinarySearchTree bst;

    @BeforeEach
    public void setUp() {
        this.bst = new BinarySearchTree();
    }

    @Test
    public void testInsert() {
        var data = new int[]{28, 16, 34, 12, 19, 31, 49, 8, 15, 29};
        for (int elem : data) {
            this.bst.insert(elem);
        }

//        System.out.println("Inorder (ascending): " + Arrays.toString(bst.toArray(true)));
//        System.out.println("Inorder (descending): " + Arrays.toString(bst.toArray(false)));
        System.out.println("Postorder: " + Arrays.toString(bst.toArrayPostOrder()));
    }
}
